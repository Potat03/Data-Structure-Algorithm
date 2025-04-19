/*
 * @author Loh Thiam Wei 
 */
package utility;

import java.util.Scanner;

public class WKInputUtil {
    
    private int maxOutputWidth;
    private Scanner scan = new Scanner(System.in);
    private PrintUtil pt = new PrintUtil(maxOutputWidth);

    public WKInputUtil(int maxOutputWidth) {
        this.maxOutputWidth = maxOutputWidth;
    }

    public char inputChar(String promptMessage, String invalidMessage, String promptInfo) {
        char input = '\0';
        String promptStr = promptInfo + "\n" + promptMessage;
        while (input == '\0') {
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            if (inputStr.isEmpty()) {
                promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
            } else {
                input = inputStr.charAt(0);
            }
        }
        return input;
    }

    public String inputString(String promptMessage, String invalidMessage, String promptInfo) {
        String input = "";
        String promptStr = promptInfo + "\n" + promptMessage;
        while (input.isEmpty()) {
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            if (inputStr.isEmpty()) {
                promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
            } else {
                input = inputStr;
            }
        }
        return input;
    }

    public int inputInt(String promptMessage, String invalidMessage, String promptInfo) {
        String input = "";
        String promptStr = promptInfo + "\n" + promptMessage;
        while (input.isEmpty()) {
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            try {
                input = Integer.parseInt(inputStr) + "";
                input = inputStr;
            } catch (NumberFormatException e) {
                promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
            }
        }
        return Integer.parseInt(input);
    }
    
    public double inputDouble(String promptMessage, String invalidMessage, String promptInfo) {
        String input = "";
        String promptStr = promptInfo + "\n" + promptMessage;
        while (input.isEmpty()) {
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            try {
                input = Double.parseDouble(inputStr) + "";
                input = inputStr;
            } catch (NumberFormatException e) {
                promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
            }
        }
        return Double.parseDouble(input);
    }
    
    public float inputFloat(String promptMessage, String invalidMessage, String promptInfo) {
        String input = "";
        String promptStr = promptInfo + "\n" + promptMessage;
        while (input.isEmpty()) {
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            try {
                input = Float.parseFloat(inputStr) + "";
                input = inputStr;
            } catch (NumberFormatException e) {
                promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
            }
        }
        return Float.parseFloat(input);
    }
}
