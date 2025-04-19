/*
 * @author Loh Thiam Wei 
 */
package utility;

import java.util.Scanner;

public class InputUtil {

    private int maxOutputWidth;
    private final Scanner scan = new Scanner(System.in);
    private final PrintUtil pt = new PrintUtil(maxOutputWidth);

    public InputUtil(int maxOutputWidth) {
        this.maxOutputWidth = maxOutputWidth;
    }

    public void pressToContinue(String promptMessage) {
        pt.print("\n" + promptMessage);
        scan.nextLine();
    }

    public void enterToContinue(String promptMsg) {
        pt.print(promptMsg);
        scan.nextLine();
    }

    public char inputCharRange(String promptMessage, String invalidMessage, String promptInfo, char min, char max) {
        char input = '\0';
        String promptStr = promptInfo + "\n" + promptMessage;
        while (input == '\0') {
            pt.cls();
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            inputStr = inputStr.toUpperCase();
            if (inputStr.isEmpty() || inputStr.charAt(0) < min || inputStr.charAt(0) > max) {
                promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
            } else {
                input = inputStr.charAt(0);
            }
        }
        return input;
    }

    public double inputDoubleRange(String promptMessage, String invalidMessage, String promptInfo, double min, double max) {
        String input = "";
        String promptStr = promptInfo + "\n" + promptMessage;
        while (input.isEmpty()) {
            pt.cls();
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            try {
                Double doubleInput = Double.parseDouble(inputStr);
                if (doubleInput < min || doubleInput > max) {
                    promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
                } else {
                    input = inputStr;
                }
            } catch (NumberFormatException e) {
                promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
            }
        }
        return Double.parseDouble(input);
    }

    public int inputIntRange(String promptMessage, String invalidMessage, String promptInfo, int min, int max) {
        String input = "";
        String promptStr = promptInfo + "\n" + promptMessage;
        while (input.isEmpty()) {
            pt.cls();
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            try {
                int intInput = Integer.parseInt(inputStr);
                if (intInput < min || intInput > max) {
                    promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
                } else {
                    input = inputStr;
                }
            } catch (NumberFormatException e) {
                promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
            }
        }
        return Integer.parseInt(input);
    }

    public char inputChar(String promptMessage, String invalidMessage, String promptInfo) {
        char input = '\0';
        String promptStr = promptInfo + "\n" + promptMessage;
        while (input == '\0') {
            pt.cls();
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
            pt.cls();
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
            pt.cls();
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            try {
                input = Integer.parseInt(inputStr) + "";
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
            pt.cls();
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            try {
                input = Double.parseDouble(inputStr) + "";
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
            pt.cls();
            pt.print(promptStr);
            String inputStr = scan.nextLine().trim();
            try {
                input = Float.parseFloat(inputStr) + "";
            } catch (NumberFormatException e) {
                promptStr = promptInfo + "\n" + invalidMessage + "\n" + promptMessage;
            }
        }
        return Float.parseFloat(input);
    }
}
