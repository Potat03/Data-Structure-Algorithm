/*
 * @author Loh Thiam Wei 
 * @ModuleHandle AssignmentAssessment  * 
 * This class belongs to Assignment Assessment Subsystem  * 
 */
package control;

import adt.*;
import entity.*;
import entity.AssignmentRubrics.Criteria;
import boundary.AssignmentAssessmentUI;
import dao.AssignmentAssesmentDAO;
import java.util.Iterator;
import utility.*;

public class AssignmentAssesmentControl {

    private final AssignmentAssessmentUI ui = new AssignmentAssessmentUI();
    private final AssignmentAssesmentDAO dao = new AssignmentAssesmentDAO(); // File data handler
    private SortedListInterface<Course> courseList = new SortedCDLinkedList<>();
    private SortedListInterface<AssignmentRubrics> rubricsList = new SortedCDLinkedList<>();
    private SortedListInterface<TutorialGroup> tutGroupList = new SortedCDLinkedList<>();
    private SortedListInterface<AssignmentTeam> mainAssignmentTeamList = new SortedCDLinkedList<>();
    private final PrintUtil pt = new PrintUtil();

    public AssignmentAssesmentControl() {
        readFromFile();
    }

    // File method
    private void readFromFile() {
        SortedListInterface<Course> tempCList = (SortedListInterface<Course>) dao.retrieveFromFile("Course");
        SortedListInterface<AssignmentRubrics> tempRList = (SortedListInterface<AssignmentRubrics>) dao.retrieveFromFile("AssignmentRubrics");
        SortedListInterface<TutorialGroup> tempTList = (SortedListInterface<TutorialGroup>) dao.retrieveFromFile("TutorialGroup");
        SortedListInterface<AssignmentTeam> tempAList = (SortedListInterface<AssignmentTeam>) dao.retrieveFromFile("AssignmentTeam");
        if (tempCList != null) {
            courseList = tempCList;
        }
        if (tempRList != null) {
            rubricsList = tempRList;
        }
        if (tempTList != null) {
            tutGroupList = tempTList;
        }
        if (tempAList != null) {
            mainAssignmentTeamList = tempAList;
        }
    }

    private void saveToFile() {
        dao.saveToFile(rubricsList, "AssignmentRubrics");
        dao.saveToFile(mainAssignmentTeamList, "AssignmentTeam");
//Doesn't need to save because not my part and wont be edited
//        dao.saveToFile(tutGroupList, "TutorialGroup");
//        dao.saveToFile(courseList, "Course");
    }

    public void startUI() {
        TutorialGroup selectedTutGroup = null;
        AssignmentTeam selectedTeam = null;

        int choice = 1;
        while (choice != 0) {
            String selectedInfo = "No Selected Team Yet";
            if (selectedTutGroup != null && selectedTeam != null) {
                selectedInfo = selectedTutGroup.getTutGrpID() + " Team " + selectedTeam.getAssignmentTeamID();
            }
            choice = ui.mainMenu(selectedInfo);
            pt.cls();

            if ((choice == 2 || choice == 3 || choice == 4) && selectedTeam == null) {
                ui.mustSelectGroup();
                ui.pressToContinue();
            } else {
                switch (choice) {
                    case 1:
                        if (courseList.isEmpty()) {
                            ui.courseNotFound();
                            ui.pressToContinue();
                            choice = 0; // Force to exit program if not course found
                        } else {
                            // Select a course
                            Course selectedCourse = selectCourse();
                            SortedListInterface<TutorialGroup> availableTutGroup = findAllTutGroupByCourse(selectedCourse.getCourseId());
                            if (availableTutGroup.isEmpty()) { // If no available tutorial group for the course
                                ui.tutGroupNotFound();
                                ui.pressToContinue();
                            } else {
                                // Select a tutorial group related to selected course
                                selectedTutGroup = selectTutorialGroup(availableTutGroup);
                                // Get all assignment teams of the tutorial group
                                SortedListInterface<AssignmentTeam> assignmentTeamList = findAllAssignmentTeamByTutGroup(selectedTutGroup.getTutGrpID());
                                if (assignmentTeamList.isEmpty()) { // If no assignment team for the tutorial group
                                    ui.assignmentTeamNotFound();
                                    ui.pressToContinue();
                                } else {
                                    // Select a assignment team from the tutorial group
                                    selectedTeam = selectAssignmentTeam(assignmentTeamList);
                                    if (!checkAssignmentTeam(selectedTeam)) {
                                        // Set back to null when the team is invalid, asking user to choose again
                                        selectedTutGroup = null;
                                        selectedTeam = null;
                                        ui.invalidTeam();
                                        ui.pressToContinue();
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        viewRubric(selectedTeam);
                        break;
                    case 3:
                        enterMark(selectedTeam);
                        break;
                    case 4:
                        generateMarkByTeam(selectedTeam);
                        break;
                    case 5:
                        listAllRubric();
                        break;
                    case 6:
                        createRubric();
                        break;
                    case 7:
                        editRubric();
                        break;
                    case 8:
                        removeRubric();
                        break;
                    case 9:
                        generateMarkByTutGroup();
                        break;
                    case 10:
                        generateReport();
                        break;
                    default:
                        break;
                }
            }
        }
        ui.exitMessage();
    }

//    
//    
//    All Select Method
//
//
    private Course selectCourse() {
        String courseInfo = "";
        int count = 1;
        Iterator<Course> it = courseList.getIterator();
        while (it.hasNext()) {
            Course currCourse = it.next();
            courseInfo += pt.toFrame(String.format("%-2d | %8s | %s", count, currCourse.getCourseId(), currCourse.getCourseName()), "left", '|', 60);
            count++;
        }
        int courseNo = ui.selectCourse(courseInfo, count - 1);
        pt.cls();
        return courseList.get(courseNo);
    }

    private TutorialGroup selectTutorialGroup(SortedListInterface<TutorialGroup> availableTutGroup) {
        String tutGroupInfo = "";
        int count = 1;
        Iterator<TutorialGroup> it = availableTutGroup.getIterator();
        while (it.hasNext()) {
            TutorialGroup currTutGroup = it.next();
            tutGroupInfo += pt.toFrame(String.format("%-2d | %-4s | %s", count, currTutGroup.getTutGrpID(), currTutGroup.getCourseID() + " Tutorial Group " + count), "left", '|', 60);
            count++;
        }
        int tutGroupNo = ui.selectTutorialGroup(tutGroupInfo, count - 1);
        pt.cls();
        return availableTutGroup.get(tutGroupNo);
    }

    private AssignmentTeam selectAssignmentTeam(SortedListInterface<AssignmentTeam> assignmentTeamList) {
        String assignmentTeamInfo = "";
        int count = 1;
        Iterator<AssignmentTeam> it = assignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam currAssignmentTeam = it.next();
            assignmentTeamInfo += pt.toFrame(String.format("%-2d | %s", count, "Team " + currAssignmentTeam.getAssignmentTeamID()), "left", '|', 60);
            count++;
        }
        int assignmentTeamNo = ui.selectAssignmentTeam(assignmentTeamInfo, count - 1);
        pt.cls();
        return assignmentTeamList.get(assignmentTeamNo);
    }

    private AssignmentRubrics selectRubric() {
        if (rubricsList.isEmpty()) {
            ui.rubricNotFound();
            ui.pressToContinue();
            return null;
        }
        String rubricInfo = "";
        int count = 1;
        Iterator<AssignmentRubrics> it = rubricsList.getIterator();
        while (it.hasNext()) {
            AssignmentRubrics currRubric = it.next();
            rubricInfo += pt.toFrame(String.format("%-2d | %s ", count, currRubric.getRubricID() + " - " + currRubric.getRubricName()), "left", '|', 60);
            count++;
        }
        int rubricNo = ui.selectAssignmentRubrics(rubricInfo, count - 1);

        pt.cls();
        return rubricsList.get(rubricNo);
    }

    private Student selectMember(ListInterface<Student> memberList, SortedMapInterface<Integer, AssignmentRubrics> individualRubricList) {
        String teamMemberInfo = "";
        int count = 1;
        Iterator<Student> teamMemberIT = memberList.getIterator();
        while (teamMemberIT.hasNext()) {
            Student currMember = teamMemberIT.next();
            AssignmentRubrics currMemberRubric = individualRubricList.get(currMember.getStudentID());
            String rubricFinalized = "No";
            String rubricScore = "";
            if (currMemberRubric.isCompleted()) {
                rubricFinalized = "Yes";
                rubricScore = String.format("%.2f", currMemberRubric.getTotalScore());
            }
            teamMemberInfo += pt.toFrame(String.format("%-2d | %-30s | %-8s | %8s", count, currMember.getStudentName(), rubricFinalized, rubricScore), "left", '|', 60);
            count++;
        }
        int memberNo = ui.selectMember(teamMemberInfo, count - 1);
        pt.cls();
        return memberList.get(memberNo);
    }

//
//
//   Main Function Method
//
//
    private void listAllRubric() {
        AssignmentRubrics selectedRubric = selectRubric();
        if (selectedRubric != null) {
            displayRubric(selectedRubric, selectedRubric.getRubricName());
            ui.pressToContinue();
        }
    }

    private void createRubric() {
        String info = "";
        String rubricName = ui.enterRubricName();
        int rubricsNo = rubricsList.size() + 1;
        info += "Rubric Name : " + rubricName + "\n";
        AssignmentRubrics newRubrics = new AssignmentRubrics("R" + String.format("%03d", rubricsNo), rubricName);
        int option = 1;
        do {
            if (option == 1) {
                String rubricStr = info + rubricToString(newRubrics);
                String criteriaDesc = ui.enterCriteria(rubricStr);
                String tempInfo = "Criteria Entered : " + criteriaDesc + "\n";
                rubricStr = info + tempInfo + rubricToString(newRubrics);
                double maxScore = ui.enterMaxScore(rubricStr);
                newRubrics.addCriteria(criteriaDesc, maxScore);
                rubricStr = info + rubricToString(newRubrics);
                option = ui.addNewOption(rubricStr);
            }

            if (option == 2) {
                rubricsList.add(newRubrics);
                saveToFile();
                ui.changesRecorded();
                ui.pressToContinue();
            } else if (option == 3) {
                ui.changesCanceled();
                ui.pressToContinue();
            }
        } while (option == 1);

    }

    private void editRubric() {
        AssignmentRubrics selectedRubric = selectRubric();
        if (selectedRubric != null) {
            AssignmentRubrics tempRubric = new AssignmentRubrics(selectedRubric);
            ListInterface<Criteria> criteriaList = tempRubric.getCriteriaList();
            String rubricString = rubricToString(tempRubric);
            rubricString = ui.getRubricTable(tempRubric.getRubricName(), rubricString, tempRubric.getTotalScore());
            int choice = ui.editConfirm(rubricString);
            while (choice == 1 || choice == 2) { // 1 Enter more, 2 save , 3 cancel
                if (choice == 1) {
                    int selectedCriteriaNo = ui.selectCriteria(rubricString, criteriaList.size());
                    Criteria selectedCriteria = criteriaList.get(selectedCriteriaNo);
                    if (selectedCriteria != null) {
                        int editOption = ui.askEditOption(rubricString);
                        switch (editOption) {
                            case 1:
                                String criteria = ui.enterCriteria(rubricString);
                                selectedCriteria.setCriteriaDesc(criteria);
                                break;
                            case 2:
                                double maxScore = ui.enterMaxScore(rubricString);
                                selectedCriteria.setMaxScore(maxScore);
                                break;
                            default:
                                if (criteriaList.size() == 1) {
                                    if (ui.removeLastCiteriaConfirmation() == 1) {
                                        rubricsList.remove(new AssignmentRubrics(selectedRubric.getRubricID()));
                                        ui.rubricDeleted();
                                        ui.pressToContinue();
                                        saveToFile();
                                        return;
                                    }
                                } else {
                                    criteriaList.remove(selectedCriteria);
                                }
                                break;
                        }
                    }
                } else {
                    String criteriaDesc = ui.enterCriteria(rubricString);
                    double maxScore = ui.enterMaxScore(rubricString);
                    tempRubric.addCriteria(criteriaDesc, maxScore);
                }
                rubricString = rubricToString(tempRubric);
                rubricString = ui.getRubricTable(tempRubric.getRubricName(), rubricString, tempRubric.getTotalScore());
                choice = ui.editConfirm(rubricString);
                if (choice == 3) {
                    selectedRubric.setCriteriaList(tempRubric.getCriteriaList());
                    saveToFile();
                    ui.changesRecorded();
                    ui.pressToContinue();
                } else if (choice == 4) {
                    ui.changesCanceled();
                    ui.pressToContinue();
                }
            }
        }
    }

    private void removeRubric() {
        AssignmentRubrics selectedRubric = selectRubric();
        if (selectedRubric != null) {
            String rubricString = rubricToString(selectedRubric);
            int removeConfirmation = ui.removeConfirm(rubricString);
            if (removeConfirmation == 1) {
                rubricsList.remove(selectedRubric);
                saveToFile();
                ui.changesRecorded();
            } else {
                ui.changesCanceled();
            }
            ui.pressToContinue();
        }
    }

    private void viewRubric(AssignmentTeam selectedTeam) {
        int rubricOption = 0; // 1 - list team rubrics, 2 - list individual rubics, 3 - back to menu
        while (rubricOption != 3) {
            rubricOption = ui.chooseRubricOption(); // Ask for option 1,2,3
            pt.cls();
            if (rubricOption == 1) { // Display the team rubric
                AssignmentRubrics teamRubric = selectedTeam.getTeamRubric();
                if (teamRubric == null) {
                    ui.teamRubricNotFound();
                } else {
                    String rubricTitle = teamRubric.getRubricName() + " - Team " + selectedTeam.getAssignmentTeamID() + " (" + teamRubric.getStatus() + ")";
                    displayRubric(teamRubric, rubricTitle);
                }
                ui.pressToContinue();
            } else if (rubricOption == 2) { // Ask which member rubrics to be displayed
                ListInterface<Student> memberList = selectedTeam.getStudentList();
                if (memberList.isEmpty()) { // If no member exist in the team
                    ui.memberNotFound();
                    ui.pressToContinue();
                } else {
                    // Get all individual rubrics first
                    SortedMapInterface<Integer, AssignmentRubrics> individualRubricList = selectedTeam.getIndividualRubricList();
                    if (individualRubricList.isEmpty()) {
                        ui.individualRubricNotFound();
                    } else {
                        // Ask to select a member
                        Student selectedMember = selectMember(memberList, individualRubricList);
                        // Select the member's individual rubrics 
                        AssignmentRubrics selectedRubric = individualRubricList.get(selectedMember.getStudentID());
                        // Display
                        String rubricTitle = selectedRubric.getRubricName() + " - " + selectedMember.getStudentName() + " (" + selectedRubric.getStatus() + ")";
                        displayRubric(selectedRubric, rubricTitle);
                    }
                    ui.pressToContinue();
                }
            }
        }
    }

    private void generateMarkByTeam(AssignmentTeam selectedTeam) {
        if (!checkRubricFinalized(selectedTeam)) {
            ui.rubricIsNotFinalized();
            ui.pressToContinue();
        } else {
            AssignmentRubrics teamRubric = selectedTeam.getTeamRubric();
            if (teamRubric == null) {
                ui.teamRubricNotFound();
            } else {
                String rubricTitle = teamRubric.getRubricName();
                String rubricStr = ""
                        + pt.toLeft("Rubric Name : " + rubricTitle)
                        + pt.toLeft("\nTeam : " + selectedTeam.getTutorialGroup() + " - Team " + selectedTeam.getAssignmentTeamID() + "\n")
                        + rubricToString(teamRubric)
                        + pt.toLeft(String.format("Total Score  : %.2f out of %.2f", teamRubric.getTotalScore(), teamRubric.getTotalMaxScore()));
                ui.displayGenerateTeamMark(rubricStr);

                ListInterface<Student> memberList = selectedTeam.getStudentList();
                SortedMapInterface<Integer, AssignmentRubrics> individualRubricsList = selectedTeam.getIndividualRubricList();

                int rowCount = 0;
                String[][] tbData = new String[30][4];
                int[] colWidth = {3, 30, 20, 20};
                double maxIndividualScore = 0;
                double maxTeamScore = teamRubric.getTotalMaxScore();
                Iterator<Student> memberIT = memberList.getIterator();
                while (memberIT.hasNext()) {
                    Student currMember = memberIT.next();
                    AssignmentRubrics currRubric = individualRubricsList.get(currMember.getStudentID());
                    double individualScore = currRubric == null ? 0 : currRubric.getTotalScore();
                    double totalScore = teamRubric.getTotalScore() + individualScore;
                    maxIndividualScore = currRubric != null ? currRubric.getTotalMaxScore() : 0;
                    tbData[rowCount + 1] = new String[]{(rowCount + 1) + "",
                        currMember.getStudentName(),
                        String.format("%.2f", individualScore),
                        String.format("%10.2f", totalScore)
                    };
                    rowCount++;
                }
                Table tb = new Table(rowCount + 1, 4);
                tb.setColWidth(colWidth);
                tbData[0] = new String[]{"No", "Student Name", "Individual Score (" + String.format("%.2f", maxIndividualScore) + ")", "Total Score (" + String.format("%.2f", maxIndividualScore + maxTeamScore) + ")"};
                tb.setData(tbData);
                ui.printTable("\n\n" + tb + "");
            }
            ui.pressToContinue();
        }
    }

    private void generateMarkByTutGroup() { // Will ask to select a tut group again
        Course selectedCourse = selectCourse();
        SortedListInterface<TutorialGroup> availableTutGroup = findAllTutGroupByCourse(selectedCourse.getCourseId());
        if (availableTutGroup.isEmpty()) { // If no available tutorial group for the course
            ui.tutGroupNotFound();
            ui.pressToContinue();
        } else {
            // Select a tutorial group related to selected course
            TutorialGroup selectedTutGroup = selectTutorialGroup(availableTutGroup);

            if (!checkRubricFinalized(selectedTutGroup)) { // If any rubric not finalized in tutorialGroup, dun allow to generate
                ui.rubricIsNotFinalized();
                ui.pressToContinue();
            } else {
                // Get all assignment teams of the tutorial group
                SortedListInterface<AssignmentTeam> assignmentTeamList = findAllAssignmentTeamByTutGroup(selectedTutGroup.getTutGrpID());
                if (assignmentTeamList.isEmpty()) { // If no assignment team for the tutorial group
                    ui.assignmentTeamNotFound();
                    ui.pressToContinue();
                } else {
                    String groupName = selectedTutGroup.getTutGrpID() + " ";
                    String[][] tbData = new String[30][5];
                    int[] colWidth = {12, 30, 10, 10, 12};

                    int rowCount = 0;
                    double totalScoreByAllStudent = 0;
                    double totalIndividualScoreByAllStudent = 0;
                    double totalScoreByAllTeam = 0;
                    double[] avgScore = new double[3]; // 0 - average score for all team, 1, average score for all student, 2 average score for individual score
                    double maxIndividualScore = 0;
                    double maxTeamScore = 0;

                    Iterator<AssignmentTeam> assignmentTeamIT = assignmentTeamList.getIterator();
                    while (assignmentTeamIT.hasNext()) {
                        AssignmentTeam currTeam = assignmentTeamIT.next();
                        ListInterface<Student> memberList = currTeam.getStudentList();
                        SortedMapInterface<Integer, AssignmentRubrics> individualRubricsList = currTeam.getIndividualRubricList();
                        AssignmentRubrics teamRubric = currTeam.getTeamRubric();

                        maxTeamScore = teamRubric.getTotalMaxScore();

                        Iterator<Student> memberIT = memberList.getIterator();
                        while (memberIT.hasNext()) {
                            Student currMember = memberIT.next();
                            AssignmentRubrics currRubric = individualRubricsList.get(currMember.getStudentID());
                            maxIndividualScore = currRubric == null ? 0 : currRubric.getTotalMaxScore();
                            double individualScore = currRubric == null ? 0 : currRubric.getTotalScore();
                            double totalScore = teamRubric.getTotalScore() + individualScore;
                            tbData[rowCount + 1] = new String[]{currTeam.getAssignmentTeamID() + "",
                                currMember.getStudentName(),
                                String.format("%.2f", teamRubric.getTotalScore()),
                                String.format("%.2f", individualScore),
                                String.format("%10.2f", totalScore)
                            };
                            totalScoreByAllStudent += totalScore;
                            totalIndividualScoreByAllStudent += individualScore;
                            totalScoreByAllTeam += teamRubric.getTotalScore();
                            rowCount++;
                        }
                    }
                    Table tb = new Table(rowCount + 1, 5);
                    tb.setColWidth(colWidth);
                    tbData[0] = new String[]{"Team No", "Student Name", "Team Rubric Score (" + String.format("%.2f", maxTeamScore) + ")", "Individual Score (" + String.format("%.2f", maxIndividualScore) + ")", "Total Score (" + String.format("%.2f", maxIndividualScore + maxTeamScore) + ")"};
                    tb.setData(tbData);

                    avgScore[0] = totalScoreByAllTeam / assignmentTeamList.size();
                    avgScore[1] = totalScoreByAllStudent / rowCount;
                    avgScore[2] = totalIndividualScoreByAllStudent / rowCount;

                    ui.tutGroupMarkHeader(selectedCourse.getCourseId() + " " + selectedCourse.getCourseName(), groupName, assignmentTeamList.size(), rowCount, avgScore);
                    ui.printTable(tb + "");
                    ui.pressToContinue();
                }
            }
        }
    }

    private void generateReport() {
        int mode = ui.chooseReportType();
        pt.cls();
        if (mode == 1) {
            displayRubricScoreReport();
        } else if (mode == 2) {
            displayReportOfRubricUsage();
        }
    }

    private void enterMark(AssignmentTeam selectedTeam) {
        int rubricOption = 0; // 1 -  team rubrics, 2 -  individual rubics, 3 - back to menu
        while (rubricOption != 3) {
            rubricOption = ui.chooseRubricOption(); // Ask for option 1,2,3
            pt.cls();
            if (rubricOption == 1) {
                AssignmentRubrics teamRubric = selectedTeam.getTeamRubric(); // Get team rubrics
                if (teamRubric == null) { // If no rubric
                    ui.rubricNotFound();
                    ui.pressToContinue();
                } else {
                    if (teamRubric.isCompleted()) { // If the rubrics is finalized
                        ui.rubricIsFinalized();
                        ui.pressToContinue();
                    } else {
                        String rubricTitle = teamRubric.getRubricName() + " - Team " + selectedTeam.getAssignmentTeamID() + " (" + teamRubric.getStatus() + ")";
                        teamRubric = enterScore(teamRubric, rubricTitle); // Ask to enter score for criteria
                        selectedTeam.setTeamRubric(teamRubric); // Update the rubric, or update to same rubric(no changes)
                        saveToFile();
                    }
                }

            } else if (rubricOption == 2) {
                ListInterface<Student> memberList = selectedTeam.getStudentList();
                if (memberList.isEmpty()) {
                    ui.memberNotFound();
                    ui.pressToContinue();
                } else {
                    SortedMapInterface<Integer, AssignmentRubrics> individualRubricList = selectedTeam.getIndividualRubricList();
                    if (individualRubricList.isEmpty()) {
                        ui.individualRubricNotFound();
                        ui.pressToContinue();
                    } else {
                        Student selectedMember = selectMember(memberList, individualRubricList);
                        AssignmentRubrics selectedRubric = individualRubricList.get(selectedMember.getStudentID());
                        if (selectedRubric == null) { // If no rubric
                            ui.rubricNotFound();
                            ui.pressToContinue();
                        } else {
                            if (selectedRubric.isCompleted()) { // If the rubrics is finalized
                                ui.rubricIsFinalized();
                                ui.pressToContinue();
                            } else {
                                String rubricTitle = selectedRubric.getRubricName() + " - " + selectedMember.getStudentName();
                                selectedRubric = enterScore(selectedRubric, rubricTitle);
                                individualRubricList.put(selectedMember.getStudentID(), selectedRubric);
                                saveToFile();
                            }
                        }
                    }
                }
            }
        }
    }

//
//
//   Sub Function For Main Function Method
//
//
    private AssignmentRubrics enterScore(AssignmentRubrics rubric, String rubricTitle) {
        // Make a deep copy, only assign back when confirm to save
        AssignmentRubrics tempRubric = new AssignmentRubrics(rubric);
        ListInterface<Criteria> criteriaList = tempRubric.getCriteriaList();
        int choice = 1;
        while (choice == 1) { // 1 Enter more, 2 save , 3 cancel
            String rubricString = rubricToString(tempRubric);
            rubricString = ui.getRubricTable(rubricTitle, rubricString, rubric.getTotalScore());
            int selectedCriteriaNo = ui.selectCriteria(rubricString, criteriaList.size());
            Criteria selectedCriteria = criteriaList.get(selectedCriteriaNo);
            if (selectedCriteria != null) {
                double scoreEntered = ui.enterScore(rubricString, selectedCriteria.getMaxScore());
                selectedCriteria.setScore(scoreEntered);
            }
            rubricString = rubricToString(tempRubric);
            rubricString = ui.getRubricTable(rubricTitle, rubricString, rubric.getTotalScore());
            choice = ui.enterMoreScore(rubricString);
            if (choice == 2) {
                if (ui.enterScoreConfirmation(rubricString) == 1) {
                    tempRubric.complete();
                    ui.changesRecorded();
                    ui.pressToContinue();
                    return tempRubric;
                } else {
                    choice = 3;
                }
            }
        }
        ui.changesCanceled();
        ui.pressToContinue();
        return rubric;
    }

    public void displayReportOfRubricUsage() {
        ui.reportHeader(courseList.size());
        if (rubricsList.isEmpty()) {
            ui.rubricNotFound();
        } else {
            String reportInfo = "";
            int count = 1;
            int mostUsage = 0;
            String mostUsageRubricInfo = "";
            Iterator<AssignmentRubrics> rubricIT = rubricsList.getIterator();
            while (rubricIT.hasNext()) {
                AssignmentRubrics currRubric = rubricIT.next();
                String rubricsID = currRubric.getRubricID();
                String rubricsInfo = rubricsID + " - " + currRubric.getRubricName();
                int usage = countRubricsUsage(rubricsID);
                reportInfo += pt.toFrame(String.format("%-2d | %-5d | %s ", count, usage, rubricsInfo), "left", '|', 60);
                count++;

                if (usage > mostUsage) {
                    mostUsageRubricInfo = rubricsInfo;
                    mostUsage = usage;
                }

            }
            ui.displayRubricsReport(reportInfo);
            ui.displayMostUsage(mostUsageRubricInfo, mostUsage);
        }
        ui.pressToContinue();
    }

    private void displayRubricScoreReport() {
        int confirmation = ui.reportConfirmationMessage();
        pt.cls();

        if (confirmation == 1) {

            ui.reportHeader(courseList.size());
            Iterator<Course> courseIT = courseList.getIterator();
            SortedListInterface<Course> notFinalizedCourseList = new SortedCDLinkedList<>();
            while (courseIT.hasNext()) {
                Course currCourse = courseIT.next();
                if (!checkRubriFinalized(currCourse)) {
                    notFinalizedCourseList.add(currCourse);
                } else {
                    SortedListInterface<TutorialGroup> availableTutGroup = findAllTutGroupByCourse(currCourse.getCourseId());

                    double bestGroupAvgScore = 0;
                    String bestGroupName = "";

                    double bestStudentScore = 0;
                    String bestStudentInfo = "";

                    double totalMaxScore = 0;

                    double allStudentScore = 0;
                    int totalStudentInCourse = 0;

                    Iterator<TutorialGroup> tutGroupIT = availableTutGroup.getIterator();
                    while (tutGroupIT.hasNext()) {
                        int totalStudent = 0;
                        double allStudentInGroupScore = 0;
                        TutorialGroup currGroup = tutGroupIT.next();
                        SortedListInterface<AssignmentTeam> teamList = findAllAssignmentTeamByTutGroup(currGroup.getTutGrpID());
                        Iterator<AssignmentTeam> teamIT = teamList.getIterator();
                        while (teamIT.hasNext()) {
                            AssignmentTeam currTeam = teamIT.next();
                            AssignmentRubrics teamRubric = currTeam.getTeamRubric();
                            ListInterface<Student> memberList = currTeam.getStudentList();
                            totalStudent += memberList.size();
                            SortedMapInterface<Integer, AssignmentRubrics> individualRubricList = currTeam.getIndividualRubricList();
                            Iterator<Student> memberIT = memberList.getIterator();
                            while (memberIT.hasNext()) {
                                Student currMember = memberIT.next();
                                AssignmentRubrics currMemberRubric = individualRubricList.get(currMember.getStudentID());
                                double currStudentScore = currMemberRubric.getTotalScore() + teamRubric.getTotalScore();
                                totalMaxScore = currMemberRubric.getTotalMaxScore() + teamRubric.getTotalMaxScore();
                                allStudentInGroupScore += currStudentScore;
                                if (currStudentScore > bestStudentScore) {
                                    bestStudentScore = currStudentScore;
                                    bestStudentInfo = currMember.getStudentName() + " " + currGroup.getTutGrpID();
                                }
                            }
                        }
                        allStudentScore += allStudentInGroupScore;
                        totalStudentInCourse += totalStudent;
                        double groupAvgScore = allStudentInGroupScore / totalStudent;
                        if (groupAvgScore > bestGroupAvgScore) {
                            bestGroupAvgScore = groupAvgScore;
                            bestGroupName = currGroup.getTutGrpID();
                        }
                    }
                    double allStudentAvg = allStudentScore / totalStudentInCourse;

                    int[] avgAnalysis = findTotalStudentAvgAnalysis(currCourse, allStudentAvg);
                    String courseDetail = currCourse.toString() + "\n" + availableTutGroup.size() + " Group  " + totalStudentInCourse + " Student";
                    ui.printReport(courseDetail, allStudentAvg, bestGroupName, bestGroupAvgScore, bestStudentInfo, bestStudentScore, avgAnalysis[0], avgAnalysis[1], totalMaxScore);
                }
            }
            showNotFinalizedCourse(notFinalizedCourseList);
            ui.pressToContinue();
        }
    }

//
//
//   Other Method
//
//
    private SortedListInterface<TutorialGroup> findAllTutGroupByCourse(String courseID) {
        SortedListInterface<TutorialGroup> found = new SortedCDLinkedList<>();
        Iterator<TutorialGroup> it = tutGroupList.getIterator();
        while (it.hasNext()) {
            TutorialGroup currTutGroup = it.next();
            if (currTutGroup.getCourseID().equals(courseID)) {
                found.add(currTutGroup);
            }
        }
        return found;
    }

    private SortedListInterface<AssignmentTeam> findAllAssignmentTeamByTutGroup(String tutorialGroupID) {
        SortedListInterface<AssignmentTeam> found = new SortedCDLinkedList<>();
        Iterator<AssignmentTeam> it = mainAssignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam currTeam = it.next();
            if (currTeam.getTutorialGroup().equals(tutorialGroupID)) {
                found.add(currTeam);
            }
        }
        return found;
    }

    private int countRubricsUsage(String rubricID) {
        int count = 0;
        Iterator<AssignmentTeam> teamIT = mainAssignmentTeamList.getIterator();
        while (teamIT.hasNext()) {
            AssignmentTeam currTeam = teamIT.next();
            AssignmentRubrics teamRubric = currTeam.getTeamRubric();
            if (teamRubric != null && teamRubric.equals(new AssignmentRubrics(rubricID))) {
                count++;
            } else {
                SortedMapInterface<Integer, AssignmentRubrics> individualRubrics = currTeam.getIndividualRubricList();
                if (!individualRubrics.isEmpty()) {
                    AssignmentRubrics indiRubric = individualRubrics.getIterator().next();
                    if (indiRubric.equals(new AssignmentRubrics(rubricID))) {
                        count += individualRubrics.size();
                    }
                }
            }
        }
        return count;
    }

    // Make sure the data is not currupted / invalid assignment team
    private boolean checkAssignmentTeam(AssignmentTeam selectedTeam) {
        //Check if the assignment team have invalid data
        // ex 5 member but only 3 individual rubics
        // ex no member
        if (selectedTeam == null) {
            return false;
        }
        ListInterface<Student> memberList = selectedTeam.getStudentList();
        if (memberList == null) {
            return false;
        }
        if (memberList.isEmpty()) {
            return false;
        }
        SortedMapInterface<Integer, AssignmentRubrics> individualRubricsList = selectedTeam.getIndividualRubricList();
        // Only check when the rubrics is assign to the team, to make sure each member have its individual rubrics
        if (individualRubricsList.size() != 0) {
            if (individualRubricsList.size() != memberList.size()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRubriFinalized(Course currCourse) {
        SortedListInterface<TutorialGroup> availableTutGroup = findAllTutGroupByCourse(currCourse.getCourseId());
        if (availableTutGroup == null || availableTutGroup.isEmpty()) {
            return false;
        }
        for (int i = 1; i <= availableTutGroup.size(); i++) {
            if (!checkRubricFinalized(availableTutGroup.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRubricFinalized(TutorialGroup availableTutGroup) {
        SortedListInterface<AssignmentTeam> assignmentTeamList = findAllAssignmentTeamByTutGroup(availableTutGroup.getTutGrpID());
        if (assignmentTeamList == null || assignmentTeamList.isEmpty()) {
            return false;
        }
        for (int i = 1; i <= assignmentTeamList.size(); i++) {
            if (!checkRubricFinalized(assignmentTeamList.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRubricFinalized(AssignmentTeam assignmentTeam) {
        AssignmentRubrics teamRubric = assignmentTeam.getTeamRubric();
        ListInterface<Student> memberList = assignmentTeam.getStudentList();
        SortedMapInterface<Integer, AssignmentRubrics> individualRubricsList = assignmentTeam.getIndividualRubricList();
        if (teamRubric == null || !teamRubric.isCompleted() || individualRubricsList == null) {
            return false;
        }
        if (!individualRubricsList.isEmpty()) {
            for (int i = 1; i <= memberList.size(); i++) {
                AssignmentRubrics currRubric = individualRubricsList.get(memberList.get(i).getStudentID());
                if (currRubric == null || !currRubric.isCompleted()) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] findTotalStudentAvgAnalysis(Course course, double avg) {
        int[] count = {0, 0}; // [0] count student below avg , [1] above avg
        SortedListInterface<TutorialGroup> availableTutGroup = findAllTutGroupByCourse(course.getCourseId());
        Iterator<TutorialGroup> tutGroupIT = availableTutGroup.getIterator();
        while (tutGroupIT.hasNext()) {
            TutorialGroup currGroup = tutGroupIT.next();
            SortedListInterface<AssignmentTeam> teamList = findAllAssignmentTeamByTutGroup(currGroup.getTutGrpID());
            Iterator<AssignmentTeam> teamIT = teamList.getIterator();
            while (teamIT.hasNext()) {
                AssignmentTeam currTeam = teamIT.next();
                AssignmentRubrics teamRubric = currTeam.getTeamRubric();
                ListInterface<Student> memberList = currTeam.getStudentList();
                SortedMapInterface<Integer, AssignmentRubrics> individualRubricList = currTeam.getIndividualRubricList();
                Iterator<Student> memberIT = memberList.getIterator();
                while (memberIT.hasNext()) {
                    Student currMember = memberIT.next();
                    AssignmentRubrics currMemberRubric = individualRubricList.get(currMember.getStudentID());
                    double currStudentScore = currMemberRubric.getTotalScore() + teamRubric.getTotalScore();
                    if (currStudentScore < avg) {
                        count[0]++;
                    } else {
                        count[1]++;
                    }
                }
            }
        }
        return count;
    }

    private void displayRubric(AssignmentRubrics rubric, String rubricTitle) {
        if (rubric == null) {
            ui.rubricNotFound();
        } else {
            ListInterface<Criteria> criteriaList = rubric.getCriteriaList();
            if (criteriaList.isEmpty()) {
                ui.criteriaNotFound();
            } else {
                String rubricString = rubricToString(rubric);
                rubricString = ui.getRubricTable(rubricTitle, rubricString, rubric.getTotalScore());
                ui.printRubricTable(rubricString);
            }
        }
    }

    private void showNotFinalizedCourse(SortedListInterface<Course> notFinalizedCourseList) {
        String str = "";
        Iterator<Course> it = notFinalizedCourseList.getIterator();
        while (it.hasNext()) {
            str += it.next();
            if (it.hasNext()) {
                str += "\n";
            }
        }
        ui.printNotFinalizedCourse(str);
    }

    private String rubricToString(AssignmentRubrics rubric) {
        if (rubric == null) {
            ui.rubricNotFound();
        } else {
            ListInterface<Criteria> criteriaList = rubric.getCriteriaList();
            if (criteriaList.isEmpty()) {
                ui.criteriaNotFound();
            } else {
                Table teamRubricTable = new Table(criteriaList.size() + 1, 4);
                int[] colWidth = {2, 58, 12, 8};
                teamRubricTable.setColWidth(colWidth);
                String[][] tableData = new String[criteriaList.size() + 1][4];
                tableData[0] = new String[]{"No", "Criteria", "Max Score", "Score"};
                Iterator<Criteria> it = criteriaList.getIterator();
                int count = 1;
                while (it.hasNext()) {
                    Criteria criteria = it.next();
                    tableData[count][0] = count + "";
                    tableData[count][1] = criteria.getCriteriaDesc();
                    tableData[count][2] = String.format("%.2f", criteria.getMaxScore());
                    tableData[count][3] = String.format("%.2f", criteria.getScore());
                    count++;
                }
                teamRubricTable.setData(tableData);
                return teamRubricTable.toString();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        AssignmentAssesmentControl aac = new AssignmentAssesmentControl();
        aac.startUI();
    }
}
