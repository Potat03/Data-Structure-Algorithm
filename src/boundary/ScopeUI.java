/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.InputUtil;
import utility.PrintUtil;
import utility.Table;

/**
 *
 * @author USER
 */
public class ScopeUI {

    private static int maxOutputWidth = 120;
    private static PrintUtil pt = new PrintUtil(maxOutputWidth);
    private static InputUtil ipt = new InputUtil(maxOutputWidth);

    //
    public int getMainMenu() {
        String header = pt.toBox("Assignment Scope Main Menu", "center", "center", '|', '=', 61, 62);
        String menu = pt.toBox("Scope Selection \n 1. Team Scope       \n 2. Individual Scope \n 3. Dummy Data       \n 4. Exit            ", "center", "center", '|', '-', 61, 62);

        int input = ipt.inputIntRange(">> ", "**Enter an option ( 1 - 4 )**", header + menu, 1, 4);

        return input;
    }

    public int getModifyMethodMenu(String title) {
        String header = pt.toBox(title, "center", "center", '|', '=', 61, 62);
        String menu = pt.toBox("Method Selection \n 1. Add         \n 2. Retrieve     \n 3. Update       \n 4. Delete       \n 5. Report       \n 6. Return       ", "center", "center", '|', '-', 61, 62);

        int input = ipt.inputIntRange(">> ", "**Enter an option ( 1 - 6 )**", header + menu, 1, 6);

        return input;
    }

    public int getTeamScopeRetrieveMethodMenu() {
        String menu = pt.toBox("Method (Retrieve) Selection \n 1. All           \n 2. By Student    \n 3. By Scope Name  \n 4. By Team Number\n 5. Exit          ", "center", "center", '|', '-', 61, 62);

        int input = ipt.inputIntRange(">> ", "**Enter an option ( 1 - 5 )**", menu, 1, 5);

        return input;
    }

    public int getIndividualScopeRetrieveMethodMenu() {
        String menu = pt.toBox("Method (Retrieve) Selection \n 1. All           \n 2. By Student    \n 3. By Scope Name \n 4. Return        ", "center", "center", '|', '-', 61, 62);

        int input = ipt.inputIntRange(">> ", "**Enter an option ( 1 - 4 )**", menu, 1, 4);

        return input;
    }

    public int getSelectionMenu(String title, String options, int max) {
        String menu = pt.toBox(title + " Selection\n (type 0 to exit) \n" + options, "center", "center", '|', '-', 61, 62);
        int input = ipt.inputIntRange(">> ", "Please enter a number (0 - " + max + ")", menu, 0, max);
        return input;
    }

    public String getStringMenu(String text, String promptMessage, String invalidMessage) {
        String menu = pt.toBox(text, "center", "center", '|', '-', 61, 62);
        String input = ipt.inputString(promptMessage + " >> ", invalidMessage, menu);

        return input;
    }

    public void emptyList(String title) {
        pt.cls();
        pt.printBox("No " + title + " has been added or set yet! Returning...", "center", "center", '|', '-', 61, 62);
        ipt.enterToContinue("\n>> Press ENTER to continue <<");
    }

    public void errorMessage(String title) {
        pt.cls();
        pt.printBox(title + " Returning...", "center", "center", '|', '-', 61, 62);
        ipt.enterToContinue("\n>> Press ENTER to continue <<");
    }

    public void completionMessage(String title) {
        pt.cls();
        pt.printBox(title + " Returning...", "center", "center", '|', '-', 81, 82);
        ipt.enterToContinue("\n>> Press ENTER to continue <<");
    }

    public void returnMessage() {
        pt.cls();
        pt.printBox("Returning To Main Menu...", "center", "center", '|', '-', 61, 62);
        ipt.enterToContinue("\n>> Press ENTER to continue <<");
    }

    public void displayTable(String title, String[][] data, int row, int col, int[] colWidth, int titleWidth) {
        Table tb = new Table(title, row, col);
        tb.setColWidth(colWidth);
        tb.setData(data);
        pt.cls();
        int width = 0;
        for (int i = 0; i < colWidth.length; i++) {
            width += colWidth[i];
        }
        pt.printBox(title, "center", "center", '|', '-', width, width + titleWidth);
        ipt.enterToContinue(tb + "\n>> Press ENTER to continue <<");
    }

    public Table getTable(String title, String[][] data, int row, int col, int[] colWidth) {
        Table tb = new Table(title, row, col);
        tb.setColWidth(colWidth);
        tb.setData(data);
        pt.cls();
        return tb;
    }
    
    public void displayFrame(String title, String content, String footer, int width){
        pt.cls();
        pt.printBox(title,"center", '|', '=', width);
        pt.printFrame(content, "center", '|', width);
        pt.printBox(footer,"center", '|', '=', width);
        ipt.enterToContinue("\n>> Press ENTER to continue <<");
    }

    public void exitMessage() {
        pt.printBox("Thank You !! GoodBye~~ >.<", "center", "center", '|', '-', 61, 62);
    }
}
