/*
 * @author Loh Thiam Wei 
 */
package boundary;

import Boundary.AssignmentTeamUI;
import control.AssignmentAssesmentControl;
import control.ScopeController;
import utility.*;

public class MainUI {

    private final PrintUtil pt = new PrintUtil(100);
    private final InputUtil ipt = new InputUtil(100);

    private void mainUI() {

        String str
                = pt.toCenter(
                        pt.toBox("OkMeh College", "center", '|', '-', 60)
                        + pt.toFrame("1. Assignment Assessment Sub System", "left", '|', 60)
                        + pt.toFrame("2. Tutorial Assignment Sub System", "left", '|', 60)
                        + pt.toFrame("3. Assignment Scope Sub System", "left", '|', 60)
                        + pt.toFrame("4. Assignment Team Management Sub System", "left", '|', 60)
                        + pt.toFrame("5. Exit System", "left", '|', 60)
                        + pt.getVerticalLine('-', 60), 70
                );

        int choice = -1;
        while (choice != 5) {
            choice = ipt.inputIntRange("Choose a Subsystem : ", "Sorry Choice Not Available", str, 1, 5);
            pt.cls();
            switch (choice) {
                case 1:
                    AssignmentAssesmentControl assignmentAssessmentController = new AssignmentAssesmentControl();
                    assignmentAssessmentController.startUI();
                    break;
                case 2:
                    TeachingAssignmentUI teachAssignmentUI = new TeachingAssignmentUI();
                    teachAssignmentUI.startUI();
                    break;
                case 3:
                    ScopeController control = new ScopeController();
                    control.runScope();
                    break;
                case 4:
                    AssignmentTeamUI assignmentTeamUI = new AssignmentTeamUI();
                    assignmentTeamUI.startUI();
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        MainUI ui = new MainUI();
        ui.mainUI();
    }

}
