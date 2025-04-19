/*
 * @author Loh Thiam Wei 
 */


/*
Alignment : left , center , right
Position : left , center ,right
*/
package utility;

import java.io.PrintStream;

public class PrintUtil extends PrintStream {

    private final int maxOutputWidth;
    private final Formatter formatter = new Formatter();

    public PrintUtil() {
        super(System.out);
        this.maxOutputWidth = 100;
    }
    
    public PrintUtil(int maxOutputWidth) {
        super(System.out);
        this.maxOutputWidth = maxOutputWidth;
    }
    
    public void cls() {
        for (int i = 0; i < 100; i++) {
            println();
        }
    }
    
    public void printNewLine(int numberOfNewLine){
        for(int i = 0; i < numberOfNewLine; i ++){
            this.println();
        }
    }
    
    public String wordWrap(String str, int width) {
        String[] word = str.split(" ");
        String wrapString = "";
        for (int i = 0; i < word.length; i++) {
            int curWidth = word[i].length();
            wrapString += word[i];
            while (curWidth < width) {
                if (i+1 < word.length && curWidth + word[i + 1].length() + 1 < width) {
                    curWidth += word[++i].length() + 1;
                    wrapString += " " + word[i];
                }else{
                    break;
                }
            }
            wrapString += "\n";
        }
        return wrapString;
    }
    
    public String[] wordWrapArr(String str, int width) {
        String[] lineArr = new String[50];
        int entry = 0;
        String[] word = str.split(" ");
        for (int i = 0; i < word.length; i++) {
            int curWidth = word[i].length();
            if(lineArr[entry] == null){
                lineArr[entry] = "";
            }
            lineArr[entry] += word[i];
            while (curWidth < width) {
                if (i+1 < word.length && curWidth + word[i + 1].length() + 1 < width) {
                    curWidth += word[++i].length() + 1;
                    lineArr[entry] += " " + word[i];
                }else{
                    break;
                }
            }
            entry++;
        }
        
        String[] tempArr = lineArr;
        lineArr = new String[entry];
        for(int i = 0; i < entry;i++){
            lineArr[i] = tempArr[i];
        }
        return lineArr;
    }
    
    public String toBox(Object content, String textAlign, char horizontalFrame, char verticalFrame) {
        return formatter.addBox(content, textAlign, horizontalFrame, verticalFrame, maxOutputWidth);
    }

    public String toBox(Object content, String textAlign, char horizontalFrame, char verticalFrame, int outputWidth) {
        return formatter.addBox(content, textAlign, horizontalFrame, verticalFrame, outputWidth);
    }

    public String toBox(Object content, String position, String textAlign, char horizontalFrame, char verticalFrame, int boxWidth) {
        String tempStr = formatter.addBox(content, textAlign, horizontalFrame, verticalFrame, boxWidth);
        if (position.equals("center")) {
            tempStr = formatter.alignCenter(tempStr, maxOutputWidth) + "\n";
        }else if(position.equals("right")){
            tempStr = formatter.alignRight(tempStr, maxOutputWidth) + "\n";
        }
        return tempStr;
    }

    public String toBox(Object content, String position, String textAlign, char horizontalFrame, char verticalFrame, int boxWidth, int outputWidth) {
        String tempStr = formatter.addBox(content, textAlign, horizontalFrame, verticalFrame, boxWidth);
        if (position.equals("center")) {
            tempStr = formatter.alignCenter(tempStr, outputWidth) + "\n";
        }else if(position.equals("right")){
            tempStr = formatter.alignRight(tempStr, outputWidth) + "\n";
        }
        return tempStr;
    }

    public String getVerticalLine(char frame) {
        return formatter.generateVerticalFrame(frame, maxOutputWidth);
    }

    public String getVerticalLine(char frame, int width) {
        return formatter.generateVerticalFrame(frame, width);
    }

    public String toFrame(Object content, String position, char frame) {
        content = formatter.addFrame(content, position, frame, maxOutputWidth);
        return content + "\n";
    }

    public String toFrame(Object content, String position, char frame, int width) {
        content = formatter.addFrame(content, position, frame, width);
        return content + "\n";
    }

    public String toCenter(Object content) {
        content = formatter.alignCenter(content, maxOutputWidth);
        return content + "\n";
    }

    public String toCenter(Object content, boolean newLine) {
        content = formatter.alignCenter(content, maxOutputWidth);
        if (newLine) {
            content += "\n";
        }
        return content + "";
    }

    public String toCenter(Object content, int width) {
        content = formatter.alignCenter(content, width);
        return content + "\n";
    }

    public String toCenter(Object content, boolean newLine, int width) {
        content = formatter.alignCenter(content, width);
        if (newLine) {
            content += "\n";
        }
        return content + "";
    }

    public String toLeft(Object content) {
        return content + "";
    }

    public String toRight(Object content) {
        return formatter.alignRight(content,maxOutputWidth) + "\n";
    }

    public String toRight(Object content, int width) {
        return formatter.alignRight(content,width) + "\n";
    }

    public void printBox(Object content, String textAlign, char horizontalFrame, char verticalFrame) {
        print(formatter.addBox(content, textAlign, horizontalFrame, verticalFrame, maxOutputWidth));
    }

    public void printBox(Object content, String textAlign, char horizontalFrame, char verticalFrame, int outputWidth) {
        print(formatter.addBox(content, textAlign, horizontalFrame, verticalFrame, outputWidth));
    }

    public void printBox(Object content, String position, String textAlign, char horizontalFrame, char verticalFrame, int boxWidth) {
        String tempStr = formatter.addBox(content, textAlign, horizontalFrame, verticalFrame, boxWidth);
        if (position.equals("center")) {
            tempStr = formatter.alignCenter(tempStr, maxOutputWidth) + "\n";
        }else if(position.equals("right")){
            tempStr = formatter.alignRight(tempStr, maxOutputWidth) + "\n";
        }
        print(tempStr);
    }

    public void printBox(Object content, String position, String textAlign, char horizontalFrame, char verticalFrame, int boxWidth, int outputWidth) {
        String tempStr = formatter.addBox(content, textAlign, horizontalFrame, verticalFrame, boxWidth);
        if (position.equals("center")) {
            tempStr = formatter.alignCenter(tempStr, outputWidth) + "\n";
        }else if(position.equals("right")){
            tempStr = formatter.alignRight(tempStr, outputWidth) + "\n";
        }
        print(tempStr);
    }

    public void printVerticalLine(char frame) {
        print(formatter.generateVerticalFrame(frame, maxOutputWidth));
    }

    public void printVerticalLine(char frame, int width) {
        print(formatter.generateVerticalFrame(frame, width));
    }

    public void printFrame(Object content, String position, char frame) {
        content = formatter.addFrame(content, position, frame, maxOutputWidth);
        println(content);
    }

    public void printFrame(Object content, String position, char frame, int width) {
        content = formatter.addFrame(content, position, frame, width);
        println(content);
    }

    public void printCenter(Object content) {
        content = formatter.alignCenter(content, maxOutputWidth);
        println(content);
    }

    public void printCenter(Object content, boolean newLine) {
        content = formatter.alignCenter(content, maxOutputWidth);
        if (newLine) {
            content += "\n";
        }
        print(content);
    }

    public void printCenter(Object content, int width) {
        content = formatter.alignCenter(content, width);
        println(content);
    }

    public void printCenter(Object content, boolean newLine, int width) {
        content = formatter.alignCenter(content, width);
        if (newLine) {
            content += "\n";
        }
        print(content);
    }

    public void printLeft(Object content) {
        println(content);
    }

    public void printRight(Object content) {
        println(formatter.alignRight(content,maxOutputWidth));
    }

    public void printRight(Object content, int width) {
        println(formatter.alignRight(content,width));
    }

    private class Formatter {

        public String alignCenter(Object content, int width) {
            if (width > 0) {
                String[] arrline = content.toString().split("\n");
                String tempStr = "";
                for (int i = 0; i < arrline.length; i++) {
                    int contentLength = arrline[i].length();
                    int padding = (width - contentLength) / 2;
                    for (int j = 0; j < padding; j++) {
                        arrline[i] = " " + arrline[i];
                    }
                    tempStr += arrline[i];
                    if (i != arrline.length - 1) {
                        tempStr += "\n";
                    }
                }
                content = tempStr;
            }
            return content.toString();
        }
        
        public String alignRight(Object content, int width) {
            if (width > 0) {
                String[] arrline = content.toString().split("\n");
                String tempStr = "";
                for (int i = 0; i < arrline.length; i++) {
                    arrline[i] = String.format("%" + (width) + "s", arrline[i]);
                    tempStr += arrline[i];
                    if (i != arrline.length - 1) {
                        tempStr += "\n";
                    }
                }
                content = tempStr;
            }
            return content.toString();
        }

        public String addFrame(Object content, String position, char frame, int width) {
            String[] arrline = content.toString().split("\n");
            String tempStr = "";
            for (int i = 0; i < arrline.length; i++) {
                String line = "";
                line += frame + " ";
                switch (position) {
                    case "left":
                        line += arrline[i];
                        break;
                    case "center":
                        line += alignCenter(arrline[i], width - 4);
                        break;
                    case "right":
                        line += String.format("%" + (width - 4) + "s", arrline[i]);
                        break;
                    default:
                        break;
                }
                line += String.format("%" + (width - line.length()) + "c", frame);
                tempStr += line;
                if (i != arrline.length - 1) {
                    tempStr += "\n";
                }
            }
            return tempStr;
        }

        public String addBox(Object content, String textAlign, char horizontalFrame, char verticalFrame, int width) {
            String tempStr = generateVerticalFrame(verticalFrame, width);
            tempStr += addFrame(content, textAlign, horizontalFrame, width) + "\n";
            tempStr += generateVerticalFrame(verticalFrame, width);
            return tempStr;
        }

        private String generateVerticalFrame(char frame, int width) {
            String tempStr = "";
            for (int i = 0; i < width; i++) {
                tempStr += frame;
            }
            return tempStr + "\n";
        }

    }

}
