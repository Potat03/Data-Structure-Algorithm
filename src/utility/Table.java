/*
 * @author Loh Thiam Wei 
 */
package utility;

public class Table {

    private static PrintUtil pt = new PrintUtil();
    private String tableName;
    private String[][] data;
    private int[] colWidth;
    private int row;
    private int col;

    public Table(int row, int col) {
        this.data = new String[row][col];
        this.row = row;
        this.col = col;
    }

    public Table(String tableName, int row, int col) {
        this.data = new String[row][col];
        this.row = row;
        this.col = col;
        this.tableName = tableName;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public void setColWidth(int[] colWidth) {
        this.colWidth = colWidth;
    }

    public void addRow(String[] row) {
        String[][] tempData = data;
        data = new String[this.row + 1][col];
        for (int i = 0; i < this.row; i++) {
            data[i] = tempData[i];
        }
        data[this.row++] = row;
    }

    private int getTableWidth() {
        int tableWidth = 0;
        for (int width : colWidth) {
            tableWidth += width + 3;
        }
        return tableWidth + 1;
    }

    public void showWidth() {
        for (int width : colWidth) {
            System.out.print(width + " ");
        }
    }

    @Override
    public String toString() {
        String tempStr = pt.getVerticalLine('-', getTableWidth());

        for (int k = 0; k < row; k++) {
            String[] row = data[k];
            String[][] wrapText = new String[col][40];
            int maxLength = 0;
            for (int i = 0; i < col; i++) {
                wrapText[i] = pt.wordWrapArr(row[i], colWidth[i]);
                if (wrapText[i].length > maxLength) {
                    maxLength = wrapText[i].length;
                }
            }
            String[][] tempArr = wrapText;
            wrapText = new String[col][maxLength];
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < tempArr[i].length; j++) {
                    wrapText[i][j] = tempArr[i][j];
                }
            }
            for (int i = 0; i < maxLength; i++) {
                tempStr += "| ";
                for (int j = 0; j < col; j++) {
                    String text = "";
                    if (wrapText[j][i] != null) {
                        text = wrapText[j][i];
                    }
                    tempStr += String.format("%-" + colWidth[j] + "s | ", text);
                }
                tempStr += "\n";
            }
            tempStr += pt.getVerticalLine('-', getTableWidth());
        }
        return tempStr;
    }
}
