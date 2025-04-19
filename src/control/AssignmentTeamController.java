/**
 * @author Tan Wei Siang
 */
package control;

import entity.AssignmentTeam;
import adt.*;
import dao.AssignmentTeamDAO;
import entity.Student;
import java.util.Iterator;
import utility.WKInputUtil;
import utility.PrintUtil;

public class AssignmentTeamController {

    private static int maxOutputWidth = 80;
    private static PrintUtil pt = new PrintUtil(maxOutputWidth);
    private static WKInputUtil ipt = new WKInputUtil(maxOutputWidth);

    private SortedListInterface<AssignmentTeam> assignmentTeams;
    ListInterface<Student> stdlist;
    AssignmentTeamDAO AssignmentTeamDAO = new AssignmentTeamDAO();

    public AssignmentTeamController() {
        assignmentTeams = AssignmentTeamDAO.retrieveFromFileSort("WS_AssignmentTeam.dat");
        if (assignmentTeams == null) {
            assignmentTeams = new SortedCDLinkedList<>();
        }
        stdlist = AssignmentTeamDAO.retrieveFromFile("WS_Student.dat");

    }

    public void createAssignmentTeam() {

        if (assignmentTeams.size() != 0) {
            System.out.println(this.toString());
        }
        int assignmentTeamID = 1;
        boolean checkExits = true;
        if (assignmentTeams.size() != 0) {
            Iterator<AssignmentTeam> it = assignmentTeams.getIterator();
            while (it.hasNext() && checkExits != false) {
                AssignmentTeam e = it.next();
                if (e.getAssignmentTeamID() != assignmentTeamID) {
                    checkExits = false;
                } else {

                    assignmentTeamID++;
                }
            }
        }
        String assignmentTitle = ipt.inputString("Enter Assignment Title: ", "String Please", "POS System, CRM System, Or ERP System");
        String tutorialGroupID = ipt.inputString("Enter Tutorial Group: ", "String Please", "Group1, Group2, Or Group3");

        AssignmentTeam assignmentTeam = new AssignmentTeam(assignmentTeamID, tutorialGroupID, assignmentTitle);

        if (assignmentTeams.add(assignmentTeam)) {
            pt.printBox("Assignment Team " + "Add Complete\n"
                    + "Assignment Teams List Update", "center", '|', '-');
            System.out.println(this.toString());

            AssignmentTeamDAO.saveToFileSort(assignmentTeams, assignmentTeams.size(), "WS_AssignmentTeam.dat");
            assignmentTeams = AssignmentTeamDAO.retrieveFromFileSort("WS_AssignmentTeam.dat");
        } else {
            pt.printBox("Assignment Team " + " Fail to Add, it is already Exits\n", "center", '|', '-');

        }

    }

    public void removeAssignmentTeam() {
        if (assignmentTeams.size() != 0) {
            System.out.println(this.toString());
            int assignmentTeamIDDelete = ipt.inputInt("Enter Assignment Team ID to Delete: ", "Number Please", "1, 2, 3...");
            AssignmentTeam assignmentTeamDelete = new AssignmentTeam(assignmentTeamIDDelete);

            if (assignmentTeams.remove(assignmentTeamDelete)) {

                if (assignmentTeams.size() != 0) {
                    pt.printBox("Assignment Team " + assignmentTeamIDDelete + " Delete Complete\n"
                            + "Assignment Teams List Update", "center", '|', '-');
                    System.out.println(this.toString());
                } else {
                    assignmentTeams = new SortedCDLinkedList<>();
                    pt.printBox("Assignment Teams List is Empty",
                            "center", '|', '-');
                }
            } else {
                pt.printBox("Assignment Team " + assignmentTeamIDDelete + " Fail to Delete,it does not Exits\n", "center", '|', '-');

            }
            AssignmentTeamDAO.saveToFileSort(assignmentTeams, assignmentTeams.size(), "WS_AssignmentTeam.dat");
            assignmentTeams = AssignmentTeamDAO.retrieveFromFileSort("WS_AssignmentTeam.dat");

        } else {
            pt.printBox("Assignment Teams List is Empty",
                    "center", '|', '-');
        }

    }

    public void amendAssignmentTeamDetails() {
        if (assignmentTeams.size() != 0) {
            System.out.println(this.toString());
            int assignmentTeamIDEdit = ipt.inputInt("Enter Assignment Team ID to Amend: ", "Number Please", "1, 2, 3...");
            AssignmentTeam assignmentTeamCompare = new AssignmentTeam(assignmentTeamIDEdit);
            if (assignmentTeams.contains(assignmentTeamCompare) == true) {
                pt.printBox("1. Assignment Team Title\n"
                        + "2.  Tutorial Group of Assignment Team\n"
                        + "3. Complete Status\n"
                        + "4. Marks\n",
                        "center", '|', '-');
                int selection = ipt.inputInt("Enter a Number to select attribute want to Amend: ", "Number Please", "1, 2, 3 OR 4");

                String str = "";
                if (selection == 1) {
                    String titleEdit = ipt.inputString("Enter new Assignment Title : ", "String Please", "POS System, CRM System, OR ERP System");
                    assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).setAssignmentTitle(titleEdit);
                    pt.printBox("Title of Assignment Team Amend Complete\n"
                            + "Assignment Team Update", "center", '|', '-');
                    str += String.format("%16s %16s %16s %12s %12s\n", "assignmentTeamID", "tutorialGroupID", "assignmentTitle", "isComplete", "mark");
                    System.out.println(str + assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).toString());
                } else if (selection == 2) {
                    String tutorialGroupEdit = ipt.inputString("Enter new Tutorial Group for the Assignment Team: ", "String Please", "Group1, Group2, Or Group3");
                    assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).setTutorialGroup(tutorialGroupEdit);
                    pt.printBox("Tutorial Group of Assignment Team Amend Complete\n"
                            + "Assignment Team Update", "center", '|', '-');

                    str += String.format("%16s %16s %16s %12s %12s\n", "assignmentTeamID", "tutorialGroupID", "assignmentTitle", "isComplete", "mark");
                    System.out.println(str + assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).toString());

                } else if (selection == 3) {
                    pt.printBox("Complete Status Menu\n"
                            + "1. true\n"
                            + "2. false", "center", '|', '-');

                    int completeStatusEdit = ipt.inputInt("Select the new Complete Status for the Assignment Team: ", "Integer Please", "1 OR 2");
                    if (completeStatusEdit == 1) {
                        assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).setIsComplete(true);
                    } else if (completeStatusEdit == 2) {
                        assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).setIsComplete(false);
                    }
                    pt.printBox("Complete Status of Assignment Team Amend Complete\n"
                            + "Assignment Team Update", "center", '|', '-');

                    str += String.format("%16s %16s %16s %12s %12s\n", "assignmentTeamID", "tutorialGroupID", "assignmentTitle", "isComplete", "mark");
                    System.out.println(str + assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).toString());
                } else if (selection == 4) {
                    double markEdit = ipt.inputDouble("Enter new mark for the Assignment Team: ", "Double Please", "0-100");
                    assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).setMark(markEdit);
                    pt.printBox("Mark of Assignment Team Amend Complete\n"
                            + "Assignment Team Update", "center", '|', '-');

                    str += String.format("%16s %16s %16s %12s %12s\n", "assignmentTeamID", "tutorialGroupID", "assignmentTitle", "isComplete", "mark");
                    System.out.println(str + assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).toString());

                } else {
                    System.out.println("Error Input");
                }

                AssignmentTeamDAO.saveToFileSort(assignmentTeams, assignmentTeams.size(), "WS_AssignmentTeam.dat");
                assignmentTeams = AssignmentTeamDAO.retrieveFromFileSort("WS_AssignmentTeam.dat");
            } else {
                pt.printBox("The Assignment Team " + assignmentTeamIDEdit + " no Exits in Assignment Teams List",
                        "center", '|', '-');
            }
        } else {
            pt.printBox("Assignment Teams List is Empty",
                    "center", '|', '-');
        }
    }

    public void addStudent() {
        if (assignmentTeams.size() != 0) {
            System.out.println(this.toString());
            int assignmentTeamIDEdit = ipt.inputInt("Enter Assignment Team ID to Add Student: ", "Number Please", "1, 2, 3...");
            AssignmentTeam assignmentTeamCompare = new AssignmentTeam(assignmentTeamIDEdit);
            if (assignmentTeams.contains(assignmentTeamCompare) == true) {
                ListInterface assmStdList = assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).getStudentList();

                pt.printBox("Assignment Team " + assignmentTeamIDEdit + " Student List", "center", '|', '-');
                if (assmStdList.size() != 0) {
                    System.out.println(printStudentListLabel() + assmStdList.toString());
                } else {
                    pt.printCenter("Assignment Team " + assignmentTeamIDEdit + " is Empty");
                }
                pt.printBox("Student List", "center", '|', '-');
                if (stdlist.size()!=0) {
                    System.out.println(printStudentListLabel() + stdlist.toString());
                } else {
                    pt.printCenter("Student List is Empty");
                }
                int studentNo = ipt.inputInt("Enter Student No to Add the Student into Assignment Team: ", "Number Please", "1, 2, 3...");
                Student studentAdd = stdlist.get(studentNo);
                if (studentAdd != null) {
                    assmStdList.add(studentAdd);
                    assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).setStudentList(assmStdList);
                    pt.printBox(studentAdd.getStudentName() + " Success Add into Assignment Team " + assignmentTeamIDEdit, "center", '|', '-');
                    pt.printBox("Assignment Team " + assignmentTeamIDEdit + "Student List Updated" + "\n", "center", '|', '-');
                    System.out.println(printStudentListLabel() + assmStdList.toString());
                    AssignmentTeamDAO.saveToFileSort(assignmentTeams, assignmentTeams.size(), "WS_AssignmentTeam.dat");
                    assignmentTeams = AssignmentTeamDAO.retrieveFromFileSort("WS_AssignmentTeam.dat");
                } else {
                    pt.printBox("Fail to Add the Student into Assignment Team, the student is no Exits", "center", '|', '-');

                }
            } else {
                pt.printBox("The Assignment Team " + assignmentTeamIDEdit + " no Exits in Assignment Teams List",
                        "center", '|', '-');
            }
        } else {
            pt.printBox("Assignment Teams List is Empty",
                    "center", '|', '-');
        }
    }

    public void removeStudent() {
        if (assignmentTeams.size() != 0) {
            System.out.println(this.toString());
            int assignmentTeamIDEdit = ipt.inputInt("Enter Assignment Team ID to Delete Student: ", "Number Please", "1, 2, 3...");
            AssignmentTeam assignmentTeamCompare = new AssignmentTeam(assignmentTeamIDEdit);
            if (assignmentTeams.contains(assignmentTeamCompare) == true) {
                pt.printBox("Assignment Team " + assignmentTeamIDEdit + " Student List", "center", '|', '-');
                ListInterface assmStdList = assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).getStudentList();
                if (assmStdList.size() != 0) {
                    System.out.println(printStudentListLabel() + assmStdList.toString());
                } else {
                    pt.printCenter("Assignment Team " + assignmentTeamIDEdit + " is Empty");
                }
                int studentID = ipt.inputInt("Enter Student ID to Remove the Student from Assignment Team: ", "Number Please", "1, 2, 3...");

                Student studentCompare = new Student(studentID);
                int noOfStudent = assmStdList.indexOf(studentCompare);
                if (noOfStudent != -1) {
                    Student studentDelete = (Student)assmStdList.get(noOfStudent);
                    if (assmStdList.remove(studentDelete) == true) {
                        pt.printBox(studentDelete.getStudentName() + " Success Remove the Student from Assignment Team " + assignmentTeamIDEdit, "center", '|', '-');
                        pt.printBox(" Assignment Team " + assignmentTeamIDEdit + " Student List Updated" + "\n", "center", '|', '-');
                        System.out.println(printStudentListLabel() + assmStdList.toString());
                    } else {
                        pt.printBox(studentDelete.getStudentName() + "does not exits in Assignment Team" + assignmentTeamIDEdit, "center", '|', '-');
                    }

                    assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).setStudentList(assmStdList);

                    AssignmentTeamDAO.saveToFileSort(assignmentTeams, assignmentTeams.size(), "WS_AssignmentTeam.dat");
                    assignmentTeams = AssignmentTeamDAO.retrieveFromFileSort("WS_AssignmentTeam.dat");
                } else {
                    pt.printBox("Fail to Remove the from Assignment Team, the student does not Exits" + assignmentTeamIDEdit, "center", '|', '-');

                }
            } else {
                pt.printBox("The Assignment Team " + assignmentTeamIDEdit + " no Exits in Assignment Teams List",
                        "center", '|', '-');
            }
        } else {
            pt.printBox("Assignment Teams List is Empty",
                    "center", '|', '-');
        }
    }

    public void filterAssignmentTeams() {
        if (assignmentTeams.size() != 0) {
            pt.printBox(
                    "Filter Assignment Teams Menu\n"
                    + "1. Assignment Complete Status\n"
                    + "2. Assignment Marks\n",
                    "center", '|', '-');
            int filterSelection = ipt.inputInt("Enter a Number to Select the Filter Criteria: ", "Number Please", "1 OR 2");
            if (filterSelection == 1) {
                pt.printBox(
                        "Assignment Complete Status Menu\n"
                        + "1. Assignment Completed\n"
                        + "2. Assignment Incompleted\n",
                        "center", '|', '-');
                int completeStatusSelection = ipt.inputInt("Enter a Number to select the complete status to Filter: ", "Number Please", "1 OR 2");
                if (completeStatusSelection == 1) {
                    SortedListInterface<AssignmentTeam> completeList = filterCompleteAssignment();
                    pt.printBox("Assignment Teams Complete",
                            "center", '|', '-');
                    System.out.println(printAssignmentTeamLabel() + completeList.toString());
                } else if (completeStatusSelection == 2) {
                    SortedListInterface<AssignmentTeam> incompleteList = filterIncompleteAssignment();
                    pt.printBox("Assignment Teams Incomplete",
                            "center", '|', '-');
                    System.out.println(printAssignmentTeamLabel() + incompleteList.toString());
                }
            } else if (filterSelection == 2) {
                pt.printBox(
                        "Assignment Complete Status Menu\n"
                        + "1. Mark > 74\n"
                        + "2. Mark 60-74\n"
                        + "3. Mark 50-59\n"
                        + "4. Mark < 50\n",
                        "center", '|', '-');
                int marksSelection = ipt.inputInt("Enter a Number to select the mark tange to Filter: ", "Number Please", "1, 2, 3 OR 4");

                if (marksSelection == 1) {
                    filterMarks('A');
                } else if (marksSelection == 2) {
                    filterMarks('B');
                } else if (marksSelection == 3) {
                    filterMarks('C');
                } else if (marksSelection == 4) {
                    filterMarks('F');
                }
            }
        } else {
            pt.printBox("Assignment Teams List is Empty",
                    "center", '|', '-');
        }
    }

    private SortedListInterface<AssignmentTeam> filterCompleteAssignment() {
        Iterator<AssignmentTeam> it = assignmentTeams.getIterator();
        SortedListInterface<AssignmentTeam> completeList = new SortedCDLinkedList<>();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (e.isIsComplete() == true) {
                completeList.add(e);
            }
        }

        return completeList;

    }

    private SortedListInterface<AssignmentTeam> filterIncompleteAssignment() {
        Iterator<AssignmentTeam> it = assignmentTeams.getIterator();
        SortedListInterface<AssignmentTeam> incompleteList = new SortedCDLinkedList<>();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (e.isIsComplete() != true) {
                incompleteList.add(e);
            }
        }

        return incompleteList;
    }

    private void filterMarks(char grade) {
        Iterator<AssignmentTeam> it = assignmentTeams.getIterator();
        SortedListInterface<AssignmentTeam> filterList;
        if (grade == 'A') {
            filterList = filterGradeA(it);
            pt.printBox("Assignment Teams Mark > 74",
                    "center", '|', '-');
            System.out.println(printAssignmentTeamLabel() + filterList.toString());
        } else if (grade == 'B') {
            filterList = filterGradeB(it);

            pt.printBox("Assignment Teams Mark 60 - 74",
                    "center", '|', '-');
            System.out.println(printAssignmentTeamLabel() + filterList.toString());
        } else if (grade == 'C') {
            filterList = filterGradeC(it);

            pt.printBox("Assignment Teams Mark 50 - 59",
                    "center", '|', '-');
            System.out.println(printAssignmentTeamLabel() + filterList.toString());
        } else if (grade == 'F') {
            filterList = filterGradeF(it);

            pt.printBox("Assignment Teams Mark < 50",
                    "center", '|', '-');
            System.out.println(printAssignmentTeamLabel() + filterList.toString());

        }
    }

    private SortedListInterface<AssignmentTeam> filterGradeA(Iterator<AssignmentTeam> it) {
        SortedListInterface<AssignmentTeam> filterList = new SortedCDLinkedList<>();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (e.getMark() > 74) {
                filterList.add(e);
            }
        }
        return filterList;
    }

    private SortedListInterface<AssignmentTeam> filterGradeB(Iterator<AssignmentTeam> it) {
        SortedListInterface<AssignmentTeam> filterList = new SortedCDLinkedList<>();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (e.getMark() >= 60 && e.getMark() <= 74) {
                filterList.add(e);
            }
        }
        return filterList;
    }

    private SortedListInterface<AssignmentTeam> filterGradeC(Iterator<AssignmentTeam> it) {
        SortedListInterface<AssignmentTeam> filterList = new SortedCDLinkedList<>();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (e.getMark() >= 50 && e.getMark() <= 59) {
                filterList.add(e);
            }
        }
        return filterList;
    }

    private SortedListInterface<AssignmentTeam> filterGradeF(Iterator<AssignmentTeam> it) {
        SortedListInterface<AssignmentTeam> filterList = new SortedCDLinkedList<>();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (e.getMark() < 50) {
                filterList.add(e);
            }
        }
        return filterList;
    }

    public void listStudentUnderAssignmentTeam() {
        if (assignmentTeams.size() != 0) {
            System.out.println(this.toString());
            int assignmentTeamIDEdit = ipt.inputInt("Enter Assignment Team ID to Add Student: ", "Number Please", "1,2, or 3");
            AssignmentTeam assignmentTeamCompare = new AssignmentTeam(assignmentTeamIDEdit);
            ListInterface assmStdList = assignmentTeams.get(assignmentTeams.indexOf(assignmentTeamCompare)).getStudentList();

            pt.printBox("Assignment Team " + assignmentTeamIDEdit + " Student List", "center", '|', '-');
            if (assmStdList.size() != 0) {
                System.out.println(printStudentListLabel() + assmStdList.toString());
            } else {
                pt.printCenter("Assignment Team " + assignmentTeamIDEdit + " is Empty");
            }
        } else {
            pt.printBox("Assignment Teams List is Empty",
                    "center", '|', '-');
        }
    }

    public void generateReport() {
        if (assignmentTeams.size() != 0) {
            Iterator<AssignmentTeam> it = assignmentTeams.getIterator();

            pt.printBox(
                    "Assignment Teams Report Menu\n"
                    + "1. Complete Assignment Percentage\n"
                    + "2. Incomplete Assignment Percentage\n"
                    + "3. A Grade Assignment Percentage\n"
                    + "4. B Grade Assignment Percentage\n"
                    + "5. C Grade Assignment Percentage\n"
                    + "6. F Grade Assignment Percentage\n",
                    "center", '|', '-');
            int reportSelection = ipt.inputInt("Enter a Number to Select the Report: ", "Number Please", "1, 2, 3, 4, 5 OR 6");
            if (reportSelection == 1) {
                int total = assignmentTeams.size();
                int element = filterCompleteAssignment().size();
                pt.printBox("Complete Assignment Teams Percentage is " + String.format("%5.2f", calculatePercentage(total, element)) + "%",
                        "center", '|', '-');
            } else if (reportSelection == 2) {
                int total = assignmentTeams.size();
                int element = filterIncompleteAssignment().size();
                pt.printBox("Incomplete Assignment Teams Percentage is " + String.format("%5.2f", calculatePercentage(total, element)) + "%",
                        "center", '|', '-');
            } else if (reportSelection == 3) {
                int total = assignmentTeams.size();
                int element = filterGradeA(it).size();
                pt.printBox("Grade A Assignment Teams Percentage is " + String.format("%5.2f", calculatePercentage(total, element)) + "%",
                        "center", '|', '-');
            } else if (reportSelection == 4) {
                int total = assignmentTeams.size();
                int element = filterGradeB(it).size();
                pt.printBox("Grade B Assignment Teams Percentage is " + String.format("%5.2f", calculatePercentage(total, element)) + "%",
                        "center", '|', '-');
            } else if (reportSelection == 5) {
                int total = assignmentTeams.size();
                int element = filterGradeC(it).size();
                pt.printBox("Grade C Assignment Teams Percentage is " + String.format("%5.2f", calculatePercentage(total, element)) + "%",
                        "center", '|', '-');
            } else if (reportSelection == 6) {
                int total = assignmentTeams.size();
                int element = filterGradeF(it).size();
                pt.printBox("Grade F Assignment Teams Percentage is " + String.format("%5.2f", calculatePercentage(total, element)) + "%",
                        "center", '|', '-');
            }
        } else {
            pt.printBox("Assignment Teams List is Empty",
                    "center", '|', '-');
        }
    }

    private double calculatePercentage(int total, int element) {
        return 100.0 * element / total;
    }

    @Override
    public String toString() {
        Iterator<AssignmentTeam> it = assignmentTeams.getIterator();
        String str = printAssignmentTeamLabel();
        pt.printBox("Assignment Teams List\n",
                "center", '|', '-');

        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            str += e.toString() + "\n";
        }
        return str;
    }

    private String printAssignmentTeamLabel() {
        return String.format("%16s %16s %16s %12s %12s\n", "assignmentTeamID", "tutorialGroupID", "assignmentTitle", "isComplete", "mark");
    }

    private String printStudentListLabel() {
        return String.format("%12s %12s %16s %12s %12s\n", "stduentID", "studentName", "studentEmail", "tutorialGroup", "assignmentTeamID");
    }

}
