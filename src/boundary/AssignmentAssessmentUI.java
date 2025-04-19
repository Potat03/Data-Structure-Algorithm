/*
 * @author Loh Thiam Wei 
 * @ModuleHandle AssignmentAssessment  * 
 * This class belongs to Assignment Assessment Subsystem  * 
 */
package boundary;

import utility.*;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class AssignmentAssessmentUI {

    private PrintUtil pt;
    private InputUtil ipt;
    private final GregorianCalendar calender = new GregorianCalendar();

    public AssignmentAssessmentUI() {
        this(100);
    }

    public AssignmentAssessmentUI(int maxOutputWidth) {
        pt = new PrintUtil(maxOutputWidth);
        ipt = new InputUtil(maxOutputWidth);
    }

    // Main UI
    public int mainMenu(String selectedInfo) {
        String menuStr = ""
                + pt.toBox("Assignment Assesment Sub System\n" + selectedInfo, "center", "center", '|', '=', 40, 50)
                + pt.toCenter(pt.toFrame("1.Select Assignment Team", "left", '|', 40), 50)
                + pt.toCenter(pt.toFrame("2.View Rubric", "left", '|', 40), 50)
                + pt.toCenter(pt.toFrame("3.Enter Mark", "left", '|', 40), 50)
                + pt.toCenter(pt.toFrame("4.Generate Mark List By Team", "left", '|', 40), 50)
                + pt.toCenter(pt.getVerticalLine('-', 40), 50)
                + pt.toCenter(pt.toFrame("5.List Rubrics", "left", '|', 40), 50)
                + pt.toCenter(pt.toFrame("6.Create Rubric", "left", '|', 40), 50)
                + pt.toCenter(pt.toFrame("7.Edit Rubric", "left", '|', 40), 50)
                + pt.toCenter(pt.toFrame("8.Remove Rubric", "left", '|', 40), 50)
                + pt.toCenter(pt.getVerticalLine('-', 40), 50)
                + pt.toCenter(pt.toFrame("9.Generate Mark List By TutorialGrp", "left", '|', 40), 50)
                + pt.toCenter(pt.toFrame("10.Generate Report", "left", '|', 40), 50)
                + pt.toCenter(pt.toFrame("0.Exit", "left", '|', 40), 50)
                + pt.toCenter(pt.getVerticalLine('=', 40), 50);
        return ipt.inputIntRange("Enter Your Choice (0-10) : ", "Sorry, choice not available", menuStr, 0, 10);
    }

    // Other UI
    public void displayGenerateTeamMark(String rubricStr) {
        String header = ""
                + pt.getVerticalLine('-', 93)
                + pt.toCenter("Generate Team Mark", 93)
                + pt.getVerticalLine('-', 93)
                + "\n";
        pt.print(header + rubricStr);
    }

    public void printReport(String courseDetail, double allStudentAvg, String bestGroupName, double bestGroupAvgScore, String bestStudentInfo, double bestStudentScore, int belowAvg, int aboveAvg, double totalMaxScore) {
        String info = ""
                + pt.toBox(courseDetail, "center", '|', '=', 60)
                + pt.toFrame(String.format("%-25s | %.2f", "Avg Score of whole course", allStudentAvg), "left", '|', 60)
                + pt.getVerticalLine('-', 60)
                + pt.toFrame(String.format("%-25s | %d", "Student Above Avg", aboveAvg), "left", '|', 60)
                + pt.toFrame(String.format("%-25s | %d", "Student Below Avg", belowAvg), "left", '|', 60)
                + pt.getVerticalLine('=', 60)
                + pt.toFrame("Best Tutorial Group", "left", '|', 60)
                + pt.getVerticalLine('-', 60)
                + pt.toFrame(String.format("%-25s | %s", "Group Name", bestGroupName), "left", '|', 60)
                + pt.toFrame(String.format("%-25s | %.2f", "Group Avg Score", bestGroupAvgScore), "left", '|', 60)
                + pt.getVerticalLine('=', 60)
                + pt.toFrame("Best Student", "left", '|', 60)
                + pt.getVerticalLine('-', 60)
                + pt.toFrame(String.format("%-25s | %s", "Student Name", bestStudentInfo), "left", '|', 60)
                + pt.toFrame(String.format("%-25s | %.2f", "Score (" + String.format("%.2f", totalMaxScore) + ")", bestStudentScore), "left", '|', 60)
                + pt.getVerticalLine('=', 60);
        System.out.println(pt.toCenter(info, 70));
    }

    public void displayRubricsReport(String reportInfo) {
        String tempStr = ""
                + pt.toCenter("Team Rubric Usage will count as total team using it", 60)
                + pt.toBox(String.format("%-2s | %5s | %s", "No", "Usage", "Rubric Detail"), "left", '|', '=', 60)
                + reportInfo
                + pt.getVerticalLine('=', 60);
        pt.println(tempStr);
    }

    public void displayMostUsage(String rubricsInfo, int usage) {
        pt.printCenter("Most Usage : " + usage + " > " + rubricsInfo, 60);
    }

    public void reportHeader(int totalCourse) {
        String header = ""
                + pt.getVerticalLine('-', 70)
                + pt.toCenter("Assignment Rubrics Report", 70)
                + pt.getVerticalLine('-', 70)
                + pt.toLeft("Total Course : " + totalCourse)
                + "\n\n";
        pt.println(header);
    }

    // Selection UI
    public int selectAssignmentRubrics(String rubricInfo, int maxChoice) {
        String tempStr = ""
                + pt.toBox(String.format("%-2s | %s", "No", "Assignment Rubric Name"), "left", '|', '=', 60)
                + rubricInfo
                + pt.getVerticalLine('=', 60);
        return ipt.inputIntRange("Select Rubric No : ", "Sorry, choice not available", tempStr, 1, maxChoice);
    }

    public int selectCourse(String courseInfo, int maxChoice) {
        String tempStr = ""
                + pt.toBox(String.format("%-2s | %-8s | %s", "No", "ID", "Course Name"), "left", '|', '=', 60)
                + courseInfo
                + pt.getVerticalLine('=', 60);
        return ipt.inputIntRange("Select Course No : ", "Sorry, choice not available", tempStr, 1, maxChoice);
    }

    public int selectTutorialGroup(String tutGroupInfo, int maxChoice) {
        String tempStr = ""
                + pt.toBox(String.format("%-2s | %-10s | %s", "No", "ID", "Group Description"), "left", '|', '=', 60)
                + tutGroupInfo
                + pt.getVerticalLine('=', 60);
        return ipt.inputIntRange("Select Tutorial Group No : ", "Sorry, choice not available", tempStr, 1, maxChoice);
    }

    public int selectAssignmentTeam(String assignmentTeamInfo, int maxChoice) {
        String tempStr = ""
                + pt.toBox(String.format("%-2s | %s", "No", "Team Description"), "left", '|', '=', 60)
                + assignmentTeamInfo
                + pt.getVerticalLine('=', 60);
        return ipt.inputIntRange("Select Assignment Team No : ", "Sorry, choice not available", tempStr, 1, maxChoice);
    }

    public int selectMember(String teamMemberInfo, int maxChoice) {
        String tempStr = ""
                + pt.toBox(String.format("%-2s | %-30s | %-8s | %8s", "No", "Student Name", "Done ?", "Score "), "left", '|', '=', 60)
                + teamMemberInfo
                + pt.getVerticalLine('=', 60);
        return ipt.inputIntRange("Select Member No : ", "Sorry, choice not available", tempStr, 1, maxChoice);
    }

    public int selectCriteria(String rubricString, int maxChoice) {
        return ipt.inputIntRange("Select Criteria No : ", "Sorry, choice not available", rubricString, 1, maxChoice);
    }

    // Input UI
    public double enterScore(String rubricString, double maxScore) {
        return ipt.inputDoubleRange("Enter Score ( 0 - " + maxScore + " ) : ", "Sorry, must between '0 - " + maxScore + "'", rubricString, 0, maxScore);
    }

    public String enterRubricName() {
        return ipt.inputString("Enter Rubric Name : ", "Sorry, No Empty Entry Allowed", "");
    }

    public String enterCriteria(String rubricStr) {
        return ipt.inputString("Enter Creteria Description : ", "Sorry, No Empty Entry Allowed", rubricStr);
    }

    public double enterMaxScore(String rubricStr) {
        return ipt.inputDouble("Enter Maximum Score : ", "Sorry, must more than 0", rubricStr);
    }

    // Short Messages
    public void exitMessage() {
        pt.cls();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        pt.printBox("Bye Have A Nice Day\n" + dateFormat.format(calender.getTime()) + "  " + timeFormat.format(calender.getTime()), "center", '|', '-', 60);
        pt.printNewLine(5);
    }

    public void mustSelectGroup() {
        pt.printBox("Sorry, You must select a group first for these action", "center", '|', '-', 60);
    }

    public void courseNotFound() {
        pt.printBox("Sorry, No Course Record Found", "center", '|', '-', 60);
    }

    public void tutGroupNotFound() {
        pt.printBox("Sorry, No Tutorial Group Record Found", "center", '|', '-', 60);
    }

    public void assignmentTeamNotFound() {
        pt.printBox("Sorry, No Assignment Team Record Found", "center", '|', '-', 60);
    }

    public void rubricNotFound() {
        pt.printBox("Sorry, No Existing Rubric", "center", '|', '-', 60);
    }

    public void teamRubricNotFound() {
        pt.printBox("Sorry, No Existing Team Rubric", "center", '|', '-', 60);
    }

    public void individualRubricNotFound() {
        pt.printBox("Sorry, No Existing Individual Rubric", "center", '|', '-', 60);
    }

    public void criteriaNotFound() {
        pt.printBox("Sorry, No Existing Criteria In This Rubric", "center", '|', '-', 60);
    }

    public void memberNotFound() {
        pt.printBox("Sorry, No Existing Member In This Team", "center", '|', '-', 60);
    }

    public void rubricIsFinalized() {
        pt.printBox("Sorry, This rubrics has been finalized", "center", '|', '-', 60);
    }

    public void rubricIsNotFinalized() {
        pt.printBox("Sorry, Rubrics are not finalized", "center", '|', '-', 60);
    }

    public void invalidTeam() {
        pt.printBox("Sorry, This Assignment Team is invalid", "center", '|', '-', 60);
    }

    public void rubricDeleted() {
        pt.printBox("This Rubric Will Be Removed", "center", '|', '-', 60);
    }

    public void changesCanceled() {
        pt.printBox("Changes Will Not Be Updated", "center", '|', '-', 60);
    }

    public void changesRecorded() {
        pt.printBox("Changes Will Be Updated", "center", '|', '-', 60);
    }

    public void pressToContinue() {
        ipt.pressToContinue("Press Any Key To Continue");
    }

    // Formatter UI
    public String getRubricTable(String rubricTitle, String table, double totalScore) {
        String str = ""
                + pt.getVerticalLine('=', 93)
                + pt.toFrame(rubricTitle, "center", '|', 93)
                + table
                + pt.toRight(String.format("Total Score  : %8.2f", totalScore), 90);
        return str;
    }

    public String rubricTableToString(String tableTitle, String table, double totalScore) {
        return ""
                + pt.getVerticalLine('=', 93)
                + pt.toFrame(tableTitle, "center", '|', 93)
                + table
                + pt.toRight(String.format("Total Score  : %8.2f", totalScore), 90);
    }

    public void printRubricTable(String rubricTitle, String table, double totalScore) {
        pt.printVerticalLine('=', 93);
        pt.printFrame(rubricTitle, "center", '|', 93);
        pt.print(table);
        pt.printRight(String.format("Total Score  : %8.2f", totalScore), 90);
    }

    public void printRubricTable(String table, double totalScore, double maxScore) {
        pt.printVerticalLine('=', 93);
        pt.print(table);
        pt.printLeft(String.format("Total Score  : %.2f out of %.2f", totalScore, maxScore));
    }

    public void printRubricTable(String rubricStr) {
        pt.print(rubricStr);
    }

    public void teamMarkHeader(String rubricName, String teamName) {
        String header = ""
                + pt.getVerticalLine('-', 93)
                + pt.toCenter("Generate Team Mark", 93)
                + pt.getVerticalLine('-', 93)
                + pt.toLeft("Rubric Name : " + rubricName)
                + pt.toLeft("\nTeam : " + teamName);
        pt.println(header);
    }

    public void tutGroupMarkHeader(String assignmentName, String groupName, int totalTeam, int totalStudent, double[] avgScore) {
        String header = ""
                + pt.getVerticalLine('-', 93)
                + pt.toCenter("Generate Team Mark By Tutorial Group", 93)
                + pt.getVerticalLine('-', 93)
                + pt.toLeft("Assignment Name : " + assignmentName)
                + pt.toLeft("\n\nTutorial Group  : " + groupName)
                + pt.toLeft("\n\nTotal Student         : " + totalStudent)
                + pt.toLeft("\nTotal Assignment Team : " + totalTeam)
                + pt.toLeft("\n\nAvg Team Score       : " + String.format("%8.2f", avgScore[0]))
                + pt.toLeft("\nAvg Individual Score : " + String.format("%8.2f", avgScore[2]))
                + pt.toLeft("\nAvg Total Score      : " + String.format("%8.2f", avgScore[1]));
        pt.println(header);
    }

    public void printTable(String tbStr) {
        pt.println(tbStr);
    }

    // Option & Confirmation UI
    public int chooseRubricOption() {
        String promptInfo = ""
                + pt.toBox("Options", "center", "center", '|', '-', 40, 60)
                + pt.toCenter(pt.toFrame("1. Team Rubrics", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("2. Individual Rubrics", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("3. Back to Menu", "left", '|', 40), 60)
                + pt.toCenter(pt.getVerticalLine('-', 40), 60);

        return ipt.inputIntRange("Enter Your Option (1-3) : ", "Sorry, option not available", promptInfo, 1, 3);
    }

    public int enterMoreScore(String updatedTable) {
        String promptInfo = updatedTable + "\n"
                + pt.toBox("Options", "center", "center", '|', '-', 40, 60)
                + pt.toCenter(pt.toFrame("1. Enter More Score", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("2. Confirm and Save", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("3. Back Without Saving", "left", '|', 40), 60)
                + pt.toCenter(pt.getVerticalLine('-', 40), 60);

        return ipt.inputIntRange("Enter Your Option (1-3) : ", "Sorry, option not available", promptInfo, 1, 3);
    }

    public int enterScoreConfirmation(String updatedTable) {
        String promptInfo = updatedTable + "\n"
                + pt.toBox("Once Confirm, the rubrics will be finalized\nWon't be able to change anymore", "center", "center", '|', '-', 50, 60)
                + pt.toCenter(pt.toFrame("1. Confirm", "left", '|', 50), 60)
                + pt.toCenter(pt.toFrame("2. Cancel", "left", '|', 50), 60)
                + pt.toCenter(pt.getVerticalLine('-', 50), 60);

        return ipt.inputIntRange("Enter Your Option (1-2) : ", "Sorry, option not available", promptInfo, 1, 2);
    }

    public int reportConfirmationMessage() {
        String str = ""
                + pt.toBox("Only Show Course With all finalized Rubrics\nPlease Finalized Before Generate", "center", "center", '|', '=', 60, 70)
                + pt.toCenter(pt.toFrame("1.Proceed", "left", '|', 60), 70)
                + pt.toCenter(pt.toFrame("2.Back To Menu", "left", '|', 60), 70)
                + pt.toCenter(pt.getVerticalLine('=', 60), 70);
        return ipt.inputIntRange("Enter Your Choice (1-2) : ", "Sorry, choice not available", str, 1, 2);
    }

    public int chooseReportType() {
        String str = ""
                + pt.toBox("Report Type", "center", "center", '|', '=', 60, 70)
                + pt.toCenter(pt.toFrame("1.Rubrics Score Report", "left", '|', 60), 70)
                + pt.toCenter(pt.toFrame("2.Rubrics Usage Report", "left", '|', 60), 70)
                + pt.toCenter(pt.toFrame("3.Back To Menu", "left", '|', 60), 70)
                + pt.toCenter(pt.getVerticalLine('=', 60), 70);
        return ipt.inputIntRange("Enter Your Choice (1-3) : ", "Sorry, choice not available", str, 1, 3);
    }

    public void printNotFinalizedCourse(String courseStr) {
        String str = ""
                + pt.toBox("Course That Are Not Finalized", "center", "center", '|', '=', 60, 70)
                + pt.toCenter(pt.toFrame(courseStr, "left", '|', 60), 70)
                + pt.toCenter(pt.getVerticalLine('=', 60), 70);
        pt.print(str);
    }

    public int addNewOption(String rubricStr) {
        String promptInfo = rubricStr
                + pt.toBox("Options", "center", "center", '|', '-', 40, 60)
                + pt.toCenter(pt.toFrame("1. Add More Criteria", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("2. Confirm and Save", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("3. Back Without Saving", "left", '|', 40), 60)
                + pt.toCenter(pt.getVerticalLine('-', 40), 60);

        return ipt.inputIntRange("Enter Your Option (1-3) : ", "Sorry, option not available", promptInfo, 1, 3);
    }

    public int removeConfirm(String updatedTable) {
        String promptInfo = updatedTable + "\n"
                + pt.toBox("Confirm ? \n Once Removed It will be remove permenantly", "center", "center", '|', '=', 60, 70)
                + pt.toCenter(pt.toFrame("1.Confirm", "left", '|', 60), 70)
                + pt.toCenter(pt.toFrame("2.Cancel", "left", '|', 60), 70)
                + pt.toCenter(pt.getVerticalLine('=', 60), 70);

        return ipt.inputIntRange("Enter Your Option (1-2) : ", "Sorry, option not available", promptInfo, 1, 2);
    }

    public int askEditOption(String updatedTable) {
        String promptInfo = updatedTable + "\n"
                + pt.toBox("Options", "center", "center", '|', '-', 40, 60)
                + pt.toCenter(pt.toFrame("1. Edit Criteria", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("2. Edit Max Score", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("3. Remove Criteria", "left", '|', 40), 60)
                + pt.toCenter(pt.getVerticalLine('-', 40), 60);
        return ipt.inputIntRange("Enter Your Option (1-3) : ", "Sorry, option not available", promptInfo, 1, 3);
    }

    public int editConfirm(String updatedTable) {
        String promptInfo = updatedTable + "\n"
                + pt.toBox("Options", "center", "center", '|', '-', 40, 60)
                + pt.toCenter(pt.toFrame("1. Edit Criteria", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("2. Add Criteria", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("3. Confirm and Save", "left", '|', 40), 60)
                + pt.toCenter(pt.toFrame("4. Back Without Saving", "left", '|', 40), 60)
                + pt.toCenter(pt.getVerticalLine('-', 40), 60);

        return ipt.inputIntRange("Enter Your Option (1-4) : ", "Sorry, option not available", promptInfo, 1, 4);
    }

    public int removeLastCiteriaConfirmation() {
        String str = ""
                + pt.toBox("This Rubrics will be deleted if remove last criteria", "center", "center", '|', '=', 60, 70)
                + pt.toCenter(pt.toFrame("1.Confirm", "left", '|', 60), 70)
                + pt.toCenter(pt.toFrame("2.Cancel", "left", '|', 60), 70)
                + pt.toCenter(pt.getVerticalLine('=', 60), 70);
        return ipt.inputIntRange("Enter Your Choice (1-2) : ", "Sorry, choice not available", str, 1, 2);
    }

}
