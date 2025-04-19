package Boundary;

/*
 * @author Tan Wei Siang
 */
import control.AssignmentTeamController;
import utility.WKInputUtil;
import utility.PrintUtil;

public class AssignmentTeamUI {

    private static int maxOutputWidth = 80;
    private static PrintUtil pt = new PrintUtil(maxOutputWidth);
    private static WKInputUtil ipt = new WKInputUtil(maxOutputWidth);

    public static void main(String[] args) {
        AssignmentTeamUI ui = new AssignmentTeamUI();
        ui.startUI();
    }

    public void startUI() {
        AssignmentTeamController assmctrl = new AssignmentTeamController();

        int menuOption = -1;
        while (menuOption != 0) {
            assignmentTeamMenu();
            menuOption = ipt.inputInt("Enter a Number : ", "Integer Please", "0 - 9");
            if (menuOption == 1) {
                assmctrl.createAssignmentTeam();
            } else if (menuOption == 2) {
                assmctrl.removeAssignmentTeam();
            } else if (menuOption == 3) {
                assmctrl.amendAssignmentTeamDetails();
            } else if (menuOption == 4) {
                assmctrl.addStudent();
            } else if (menuOption == 5) {
                assmctrl.removeStudent();
            } else if (menuOption == 6) {
                assmctrl.filterAssignmentTeams();
            } else if (menuOption == 7) {
                System.out.println(assmctrl.toString());
            } else if (menuOption == 8) {
                assmctrl.listStudentUnderAssignmentTeam();
            } else if (menuOption == 9) {
                assmctrl.generateReport();
            } else if (menuOption != 0) {
                assignmentTeamMenu();
            }
        }

    }

    public static void assignmentTeamMenu() {
        pt.printBox("Assignment Team Management Sub System Menu", "center", "center", '|', '-',45);
        pt.printBox(
                "1. Add Asignment Team\n"
                + "2. Remove Asignment Team\n"
                + "3. Amend Assignment Team Details\n"
                + "4. Add student to assignment team\n"
                + "5. Remove student from assignment team\n"
                + "6. Filter assignment teams based on criteria\n"
                + "7. List assignment teams\n"
                + "8. List students under an assignment team\n"
                + "9. Generate relevant reports\n"
                + "0. Exit", "center", '|', '-');

    }

}
