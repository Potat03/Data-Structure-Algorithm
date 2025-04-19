/**
 * @author Nicholas Yap Jia Wey
 */
package control;

import adt.ArrayList;
import adt.ListInterface;
import adt.SortedMapInterface;
import adt.SortedCDLinkedList;
import adt.SortedListInterface;
import adt.SortedMap;
import boundary.ScopeUI;
import dao.*;
import entity.AssignmentRubrics;
import entity.AssignmentTeam;
import entity.AssignmentScope;
import entity.Course;
import entity.Student;
import entity.TutorialGroup;
import java.util.Iterator;
import utility.InputUtil;
import utility.Table;

public class ScopeController {

    // for selection purposes
    private SortedListInterface<Course> courseList = new SortedCDLinkedList<>();
    private SortedListInterface<TutorialGroup> tutorialGroupList = new SortedCDLinkedList<>();
    private SortedListInterface<AssignmentTeam> assignmentTeamList = new SortedCDLinkedList<>();
    private ListInterface<Student> studentList = new ArrayList<>();

    // selected variables
    private Course courseOfChoice = new Course();
    private TutorialGroup tutorialGroupOfChoice = new TutorialGroup();
    private AssignmentTeam assignmentTeamOfChoice = new AssignmentTeam();
    private Student studentOfChoice = new Student();
    private int courseIndex = -1;
    private int tutorialGroupIndex = -1;
    private int assignmentTeamIndex = -1;
    private int studentIndex = -1;

    //For data storing purposes
    private SortedListInterface<Course> allCourseList = new SortedCDLinkedList<>();
    private SortedListInterface<TutorialGroup> allTutorialGroupList = new SortedCDLinkedList<>();
    private SortedListInterface<AssignmentTeam> allAssignmentTeamList = new SortedCDLinkedList<>();

    //UI objects
    private final ScopeUI scopeUI = new ScopeUI();
    private final InputUtil ipt = new InputUtil(80);

    public void runScope() {
        readFromFile();

        int option;

        do {
            option = scopeUI.getMainMenu();

            switch (option) {
                case 1:
                    teamMenu();
                    break;
                case 2:
                    individualMenu();
                    break;
                case 3:
                    createDummyData();
                    break;
                case 4:
                    saveToFile();
                    break;
                default:
                    break;
            }
        } while (option != 4);
    }

    //Team Scope Menu
    public void teamMenu() {
        int option;
        do {
            option = scopeUI.getModifyMethodMenu("Team Scope");
            switch (option) {
                case 1:
                    addTeamScope();
                    break;
                case 2:
                    retrieveTeamScopeMenu();
                    break;
                case 3:
                    updateTeamScope();
                    break;
                case 4:
                    deleteTeamScope();
                    break;
                case 5:
                    teamScopeReports();
                    break;
                case 6:
                    scopeUI.returnMessage();
                    break;
                default:
                    break;
            }
        } while (option != 6);
    }

    public void retrieveTeamScopeMenu() {
        int option;
        do {
            option = scopeUI.getTeamScopeRetrieveMethodMenu();
            switch (option) {
                case 1:
                    retrieveAllTeamScope();
                    break;
                case 2:
                    retrieveTeamScopeByStudent();
                    break;
                case 3:
                    retrieveTeamScopeByScopeName();
                    break;
                case 4:
                    retrieveTeamScopeByTeamNumber();
                    break;
                case 5:
                    scopeUI.returnMessage();
                    break;
                default:
                    break;
            }
        } while (option != 5);
    }

    //Individual Scope Menu
    public void individualMenu() {
        int option;
        do {
            option = scopeUI.getModifyMethodMenu("Individual Scope");
            switch (option) {
                case 1:
                    addIndividualScope();
                    break;
                case 2:
                    retrieveIndividualScopeMenu();
                    break;
                case 3:
                    updateIndividualScope();
                    break;
                case 4:
                    deleteIndividualScope();
                    break;
                case 5:
                    individualScopeReports();
                    break;
                case 6:
                    scopeUI.returnMessage();
                    break;
                default:
                    break;
            }
        } while (option != 6);
    }

    public void retrieveIndividualScopeMenu() {
        int option;
        do {
            option = scopeUI.getIndividualScopeRetrieveMethodMenu();
            switch (option) {
                case 1:
                    retrieveAllIndividualScope();
                    break;
                case 2:
                    retrieveIndividualScopeByStudent();
                    break;
                case 3:
                    retrieveIndividualScopeByScopeName();
                    break;
                case 4:
                    scopeUI.returnMessage();
                    break;
                default:
                    break;
            }
        } while (option != 4);
    }

    //Add Methods
    public void addTeamScope() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                if (assignmentTeamSelection()) {
                    if (assignmentTeamOfChoice.getTeamScope() == null) {
                        String scopeName = scopeUI.getStringMenu("~~ Add ~~\nAssignment Team Scope\nTeam Number : " + assignmentTeamIndex, "Enter Scope Name", "Scope Cannot Be Left Empty");
                        AssignmentScope newScope = new AssignmentScope(assignmentTeamIndex + "", assignmentTeamOfChoice.getAssignmentTeamID() + "", scopeName);
                        assignmentTeamOfChoice.setTeamScope(newScope);
                        scopeUI.completionMessage(scopeName + " successfully added!");
                    } else {
                        scopeUI.errorMessage("Scope has already been set!");
                    }
                }
            }
        }
    }

    public void addIndividualScope() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                if (assignmentTeamSelection()) {
                    if (studentSelection()) {
                        SortedMapInterface<Integer, AssignmentScope> map = (assignmentTeamOfChoice.getIndividualScopeList() != null) ? assignmentTeamOfChoice.getIndividualScopeList() : new SortedMap<>();
                        if (!map.containsKey(studentIndex)) {
                            String scopeName = scopeUI.getStringMenu("~~ Add ~~\nAssignment Individual Scope\nStudent : " + studentOfChoice.getStudentName(), "Enter Scope Name", "Scope Cannot Be Left Empty");
                            AssignmentScope newScope = new AssignmentScope("1", studentOfChoice.getStudentID() + "", scopeName);
                            map.put(studentIndex, newScope);
                            assignmentTeamOfChoice.setIndividualScopeList(map);
                            scopeUI.completionMessage(scopeName + " successfully added!");
                        } else {
                            scopeUI.errorMessage("Scope has already been set!");
                        }
                    }
                }
            }
        }
    }

    //Retrieve Methods
    public void retrieveAllTeamScope() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                ListInterface<String[]> dataList = new ArrayList<>();
                Iterator it = assignmentTeamList.getIterator();
                int count = 1;
                if (assignmentTeamList.isEmpty()) {
                    scopeUI.emptyList("Assignment Team");
                    return;
                } else {
                    dataList.add(new String[]{"Assignment Team", "Team Scope Name"});
                }

                while (it.hasNext()) {
                    AssignmentTeam assignmentTeam = (AssignmentTeam) it.next();
                    String scopeName = (assignmentTeam.getTeamScope() == null) ? "Not Set" : assignmentTeam.getTeamScope().getScopeName();
                    if (assignmentTeam.getTutorialGroup() != null) {
                        if (assignmentTeam.getTutorialGroup().equals(tutorialGroupOfChoice.getTutGrpID())) {
                            dataList.add(new String[]{count + "", scopeName});
                            count++;
                        }
                    }
                }

                String title = "All Team Scope";
                int col = 2;
                int[] colWidth = {20, 20};
                String[][] data = new String[dataList.size()][col];
                for (int i = 0; i < dataList.size(); i++) {
                    data[i] = dataList.get(i + 1);
                }
                scopeUI.displayTable(title, data, dataList.size(), col, colWidth, 0);
            }
        }
    }

    public void retrieveTeamScopeByStudent() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                String studentName = scopeUI.getStringMenu("Searching By Student Name\n From Tutorial Group : " + tutorialGroupOfChoice.getTutorialGrpNum(), "Enter Student Name", "Please Enter A Student Name");
                ListInterface<String> dataList = new ArrayList<>();
                Iterator it = assignmentTeamList.getIterator();
                int count = 1;

                if (assignmentTeamList.isEmpty()) {
                    scopeUI.emptyList("Assignment Team");
                    return;
                }

                while (it.hasNext()) {
                    AssignmentTeam assignmentTeam = (AssignmentTeam) it.next();
                    if (assignmentTeam.getStudentList() != null) {
                        Iterator sit = assignmentTeam.getStudentList().getIterator();
                        String scopeName = (assignmentTeam.getTeamScope() == null) ? "Not Set" : assignmentTeam.getTeamScope().getScopeName();
                        while (sit.hasNext()) {
                            Student student = (Student) sit.next();
                            if (student.getStudentName().toUpperCase().equals(studentName.toUpperCase())) {
                                dataList.add(String.format("%-60s%-60s", "Assignment Team No : " + count, "Scope Name : " + scopeName));
                            }
                        }
                        count++;
                    }
                }

                String title = "Team Scope\nFilter By Student Name - " + studentName;
                int col = 1;
                int[] colWidth = {60};
                String[][] data = new String[dataList.size()][col];
                for (int i = 0; i < dataList.size(); i++) {
                    data[i][0] = dataList.get(i + 1);
                }
                scopeUI.displayTable(title, data, dataList.size(), col, colWidth, 0);
            }
        }
    }

    public void retrieveTeamScopeByScopeName() {
        if (courseSelection()) {
            String scopeName = scopeUI.getStringMenu("Searching By Scope Name\n From Course : " + courseOfChoice.getCourseName(), "Enter Scope name", "Please Enter A Scope Name");
            ListInterface<String> dataList = new ArrayList<>();
            Iterator it = tutorialGroupList.getIterator();

            if (tutorialGroupList.isEmpty()) {
                scopeUI.emptyList("Tutorial Group");
                return;
            } else if (allAssignmentTeamList.isEmpty()) {
                scopeUI.emptyList("Assignment Team");
                return;
            }

            while (it.hasNext()) {
                TutorialGroup tutorialGroup = (TutorialGroup) it.next();
                Iterator ait = allAssignmentTeamList.getIterator();
                while (ait.hasNext()) {
                    AssignmentTeam assignmentTeam = (AssignmentTeam) ait.next();
                    int count = 1;
                    if (assignmentTeam.getTeamScope() != null) {
                        if (assignmentTeam.getTutorialGroup().toUpperCase().equals(tutorialGroup.getTutGrpID().toUpperCase()) && assignmentTeam.getTeamScope().getScopeName().toUpperCase().equals(scopeName.toUpperCase())) {
                            dataList.add(String.format("%-60s%-60s%-60s", "Tutorial Group No : " + tutorialGroup.getTutGrpID(), "Assignment Team No : " + count, "Scope Name : " + scopeName));
                            if (assignmentTeam.getStudentList() != null) {
                                Iterator sit = assignmentTeam.getStudentList().getIterator();
                                int studentCount = 1;
                                String str = String.format("%-60s", "Student Name : ");
                                while (sit.hasNext()) {
                                    Student student = (Student) sit.next();
                                    str += String.format("%-60s", studentCount + ". " + student.getStudentName());
                                    studentCount++;
                                }
                                dataList.add(str);
                            }
                        }
                    }
                    count++;
                }
            }

            String title = "Team Scope\nFilter By Scope Name - " + scopeName;
            int col = 1;
            int[] colWidth = {60};
            String[][] data = new String[dataList.size()][col];
            for (int i = 0; i < dataList.size(); i++) {
                data[i][0] = dataList.get(i + 1);
            }
            scopeUI.displayTable(title, data, dataList.size(), col, colWidth, 0);
        }
    }

    public void retrieveTeamScopeByTeamNumber() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                if (assignmentTeamSelection()) {
                    ListInterface<String> dataList = new ArrayList<>();

                    String scopeName = (assignmentTeamOfChoice.getTeamScope() == null) ? "Not Set" : assignmentTeamOfChoice.getTeamScope().getScopeName();
                    dataList.add(String.format("%-60s%-60s", "Assignment Team No: " + assignmentTeamIndex, "Scope Name : " + scopeName));

                    String title = "Team Scope\nFilter By Assignment Team No - " + assignmentTeamIndex;
                    int col = 1;
                    int[] colWidth = {60};
                    String[][] data = new String[dataList.size()][col];
                    for (int i = 0; i < dataList.size(); i++) {
                        data[i][0] = dataList.get(i + 1);
                    }
                    scopeUI.displayTable(title, data, dataList.size(), col, colWidth, 2);

                }
            }
        }
    }

    public void retrieveAllIndividualScope() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                ListInterface<String[]> dataList = new ArrayList<>();
                Iterator it = assignmentTeamList.getIterator();
                int count = 1;
                if (assignmentTeamList.isEmpty()) {
                    scopeUI.emptyList("Assignment Team");
                    return;
                } else {
                    dataList.add(new String[]{"Assignment Team", "Student name", "Individual Scope Name"});
                }

                while (it.hasNext()) {
                    AssignmentTeam assignmentTeam = (AssignmentTeam) it.next();
                    if (assignmentTeam.getTutorialGroup() != null) {
                        if (assignmentTeam.getTutorialGroup().equals(tutorialGroupOfChoice.getTutGrpID())) {
                            if (assignmentTeam.getStudentList() != null) {
                                dataList.add(new String[]{count + "", "", ""});
                                Iterator sit = assignmentTeam.getStudentList().getIterator();
                                int studCount = 1;
                                while (sit.hasNext()) {
                                    Student student = (Student) sit.next();
                                    String scopeName = "Not Set";
                                    if (assignmentTeam.getIndividualScopeList() != null) {
                                        SortedMapInterface<Integer, AssignmentScope> map = assignmentTeam.getIndividualScopeList();
                                        if (map.containsKey(studCount)) {
                                            scopeName = map.get(studCount).getScopeName();
                                        }
                                    }
                                    dataList.add(new String[]{"", student.getStudentName(), scopeName});
                                    studCount++;
                                }
                            }
                        }
                    }
                    count++;
                }

                String title = "All Individual Scope";
                int col = 3;
                int[] colWidth = {10, 20, 20};
                String[][] data = new String[dataList.size()][col];
                for (int i = 0; i < dataList.size(); i++) {
                    data[i] = dataList.get(i + 1);
                }
                scopeUI.displayTable(title, data, dataList.size(), col, colWidth, 13);
            }
        }
    }

    public void retrieveIndividualScopeByStudent() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                String studentName = scopeUI.getStringMenu("Searching By Student Name\n From Tutorial Group : " + tutorialGroupOfChoice.getTutorialGrpNum(), "Enter Student Name", "Please Enter A Student Name");
                ListInterface<String> dataList = new ArrayList<>();
                Iterator it = assignmentTeamList.getIterator();
                int count = 1;

                if (assignmentTeamList.isEmpty()) {
                    scopeUI.emptyList("Assignment Team");
                    return;
                }

                while (it.hasNext()) {
                    AssignmentTeam assignmentTeam = (AssignmentTeam) it.next();
                    if (assignmentTeam.getStudentList() != null && assignmentTeam.getIndividualScopeList() != null) {
                        SortedMapInterface<Integer, AssignmentScope> map = assignmentTeam.getIndividualScopeList();
                        Iterator sit = assignmentTeam.getStudentList().getIterator();
                        int studCount = 1;
                        while (sit.hasNext()) {
                            Student student = (Student) sit.next();
                            if (student.getStudentName().toUpperCase().equals(studentName.toUpperCase())) {
                                String scopeName = (map.containsKey(studCount)) ? map.get(studCount).getScopeName() : "Not Set";
                                dataList.add(String.format("%-60s%-60s", "Assignment Team No : " + count, "Scope Name : " + scopeName));
                            }
                            studCount++;
                        }
                        count++;
                    }
                }

                String title = "Individual Scope\nFilter By Student Name - " + studentName;
                int col = 1;
                int[] colWidth = {60};
                String[][] data = new String[dataList.size()][col];
                for (int i = 0; i < dataList.size(); i++) {
                    data[i][0] = dataList.get(i + 1);
                }
                scopeUI.displayTable(title, data, dataList.size(), col, colWidth, 0);
            }
        }
    }

    public void retrieveIndividualScopeByScopeName() {
        if (courseSelection()) {
            String scopeName = scopeUI.getStringMenu("Searching By Scope Name\n From Course : " + courseOfChoice.getCourseName(), "Enter Scope name", "Please Enter A Scope Name");
            ListInterface<String> dataList = new ArrayList<>();
            Iterator it = tutorialGroupList.getIterator();

            if (tutorialGroupList.isEmpty()) {
                scopeUI.emptyList("Tutorial Group");
                return;
            } else if (allAssignmentTeamList.isEmpty()) {
                scopeUI.emptyList("Assignment Team");
                return;
            }

            while (it.hasNext()) {
                TutorialGroup tutorialGroup = (TutorialGroup) it.next();
                Iterator ait = allAssignmentTeamList.getIterator();
                while (ait.hasNext()) {
                    AssignmentTeam assignmentTeam = (AssignmentTeam) ait.next();
                    int count = 1;
                    if (assignmentTeam.getIndividualScopeList() != null && assignmentTeam.getStudentList() != null) {
                        if (assignmentTeam.getTutorialGroup().toUpperCase().equals(tutorialGroup.getTutGrpID().toUpperCase())) {
                            SortedMapInterface<Integer, AssignmentScope> map = assignmentTeam.getIndividualScopeList();
                            Iterator sit = assignmentTeam.getStudentList().getIterator();
                            int studentCount = 1;
                            while (sit.hasNext()) {
                                Student student = (Student) sit.next();
                                if (map.containsKey(studentCount)) {
                                    String scopeCmp = (map.get(studentCount).getScopeName() != null) ? map.get(studentCount).getScopeName().toUpperCase() : "";
                                    if (scopeCmp.equals(scopeName.toUpperCase())) {
                                        dataList.add(String.format("%-60s%-60s%-60s%-60s", "Tutorial Group No : " + tutorialGroup.getTutorialGrpNum(), "Assignment Team No : " + count, "Student Name : " + student.getStudentName(), "Scope Name : " + scopeCmp));
                                    }
                                }
                                studentCount++;
                            }
                        }
                    }
                    count++;
                }
            }

            String title = "Individual Scope\nFilter By Scope Name - " + scopeName;
            int col = 1;
            int[] colWidth = {60};
            String[][] data = new String[dataList.size()][col];
            for (int i = 0; i < dataList.size(); i++) {
                data[i][0] = dataList.get(i + 1);
            }
            scopeUI.displayTable(title, data, dataList.size(), col, colWidth, 0);

        }
    }

    //Update Methods
    public void updateTeamScope() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                if (assignmentTeamSelection()) {
                    if (assignmentTeamOfChoice.getTeamScope() != null) {
                        String currentScopeName = assignmentTeamOfChoice.getTeamScope().getScopeName();
                        String scopeName = scopeUI.getStringMenu("~~ Edit ~~\nAssignment Team Scope\nTeam Number : " + assignmentTeamIndex + " Current Scope Name : " + currentScopeName, "Enter Scope Name", "Scope Cannot Be Left Empty");
                        AssignmentScope updatedScope = new AssignmentScope(assignmentTeamIndex + "", assignmentTeamOfChoice.getAssignmentTeamID() + "", scopeName);
                        assignmentTeamOfChoice.setTeamScope(updatedScope);
                        scopeUI.completionMessage(scopeName + " successfully updated!");
                    } else {
                        scopeUI.errorMessage("Scope has not been set!");
                    }
                }
            }
        }
    }

    public void updateIndividualScope() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                if (assignmentTeamSelection()) {
                    if (studentSelection()) {
                        if (assignmentTeamOfChoice.getIndividualScopeList() != null) {
                            SortedMapInterface<Integer, AssignmentScope> map = assignmentTeamOfChoice.getIndividualScopeList();
                            if (map.containsKey(studentIndex)) {
                                String scopeName = scopeUI.getStringMenu("~~ Edit ~~\nAssignment Individual Scope\nStudent : " + studentOfChoice.getStudentName() + " Current Scope Name : " + map.get(courseIndex).getScopeName(), "Enter Scope Name", "Scope Cannot Be Left Empty");
                                AssignmentScope updatedScope = new AssignmentScope(studentIndex + "", studentOfChoice.getStudentID() + "", scopeName);
                                map.put(studentIndex, updatedScope);
                                assignmentTeamOfChoice.setIndividualScopeList(map);
                                scopeUI.completionMessage(scopeName + " successfully updated!");
                            } else {
                                scopeUI.errorMessage("Scope has not been set!");
                            }
                        }
                    }
                }
            }
        }
    }

    //Delete Methods
    public void deleteTeamScope() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                if (assignmentTeamSelection()) {
                    if (assignmentTeamOfChoice.getTeamScope() != null) {
                        assignmentTeamOfChoice.setTeamScope(null);
                        scopeUI.completionMessage("Scope For Assignment Team No " + assignmentTeamIndex + " Successfully Deleted!");
                    } else {
                        scopeUI.errorMessage("Scope Has Not Been Sett!");
                    }
                }
            }
        }
    }

    public void deleteIndividualScope() {
        if (courseSelection()) {
            if (tutorialGroupSelection()) {
                if (assignmentTeamSelection()) {
                    if (studentSelection()) {
                        if (assignmentTeamOfChoice.getIndividualScopeList() != null) {
                            SortedMapInterface<Integer, AssignmentScope> map = assignmentTeamOfChoice.getIndividualScopeList();
                            if (map.containsKey(studentIndex)) {
                                map.remove(studentIndex);
                                assignmentTeamOfChoice.setIndividualScopeList(map);
                                scopeUI.completionMessage("Scope For Student " + studentOfChoice.getStudentName() + " Successfully Deleted!");
                            } else {
                                scopeUI.errorMessage("Scope Has Not Been Set!");
                            }
                        }
                    }
                }
            }
        }
    }

    //Report Methods
    public void teamScopeReports() {
        if (!allCourseList.isEmpty() && !allAssignmentTeamList.isEmpty()) {
            Iterator cit = allCourseList.getIterator();
            while (cit.hasNext()) {
                String header = "";
                String details = "";
                int tutorialGroupCount = 1;
                int assignmentTeamCount = 1;
                int totalScope = 0;
                double avgScore = 0;
                SortedMapInterface<AssignmentScope, Integer> pickQty = new SortedMap<>();
                SortedListInterface<AssignmentScope> pickQtyRef = new SortedCDLinkedList<>();
                ListInterface<String[]> noScope = new ArrayList<>();
                Course course = (Course) cit.next();
                if (!allTutorialGroupList.isEmpty()) {
                    Iterator git = allTutorialGroupList.getIterator();
                    while (git.hasNext()) {
                        TutorialGroup tutorialGroup = (TutorialGroup) git.next();
                        if (tutorialGroup.getCourseID().toUpperCase().equals(course.getCourseId().toUpperCase())) {
                            if (!allAssignmentTeamList.isEmpty()) {
                                Iterator ait = allAssignmentTeamList.getIterator();
                                while (ait.hasNext()) {
                                    AssignmentTeam assignmentTeam = (AssignmentTeam) ait.next();
                                    if (assignmentTeam.getTutorialGroup().toUpperCase().equals(tutorialGroup.getTutGrpID().toUpperCase())) {
                                        assignmentTeamCount++;
                                        if (assignmentTeam.getTeamScope() != null) {
                                            AssignmentScope scope = assignmentTeam.getTeamScope();
                                            avgScore = assignmentTeam.getTeamRubric().getTotalScore() / assignmentTeamCount;
                                            totalScope++;
                                            if (pickQty.containsKey(scope)) {
                                                pickQty.put(scope, pickQty.get(scope) + 1);
                                            } else {
                                                pickQty.put(scope, 1);
                                                pickQtyRef.add(scope);
                                            }
                                        } else {
                                            noScope.add(new String[]{tutorialGroup.getTutorialGrpNum() + "", "Team No " + assignmentTeamCount});
                                        }
                                    }
                                }
                            }
                            tutorialGroupCount++;
                        }
                    }
                    String[][] pickRateData = new String[pickQty.size() + 1][3];
                    pickRateData[0] = new String[]{"Scope Name", "Quantity", "Pick Rate"};
                    for (int i = 1; i <= pickQty.size(); i++) {
                        AssignmentScope ref = pickQtyRef.get(i);
                        double pickRate = Double.parseDouble(pickQty.get(ref) + "") / totalScope * 100;
                        pickRateData[i] = new String[]{ref.getScopeName(), pickQty.get(ref) + "", String.format("%.2f", pickRate)};
                    }
                    quickSortDouble(pickRateData, 1, pickRateData.length - 1, 2);
                    for (int i = 1; i < pickRateData.length; i++) {
                        pickRateData[i][2] += " %";
                    }
                    int[] pickColWidth = {30, 30, 30};
                    Table pickRateTable = scopeUI.getTable("Pick Rate", pickRateData, pickQty.size() + 1, 3, pickColWidth);

                    String[][] reminderData = new String[noScope.size() + 1][2];
                    reminderData[0] = new String[]{"Tutorial Group Number", "Assignment Team Number"};
                    for (int i = 1; i <= noScope.size(); i++) {
                        reminderData[i] = noScope.get(i);
                    }
                    int[] reminderColWidth = {30, 30};
                    Table reminderTable = scopeUI.getTable("Reminder", reminderData, noScope.size() + 1, 2, reminderColWidth);

                    header += String.format("%-50s%-35s%-35s", "Course Name : " + course.getCourseName(), "Total Tutorial Group Qty : " + (tutorialGroupCount - 1), "Total Assignment Team Qty : " + (assignmentTeamCount - 1));
                    String footer = "Average Team Score : " + String.format("%.2f", avgScore);
                    String data = header + "\n" + details + "\n~ Pick Rate Of All Scopes ~\n" + pickRateTable + "\n~ Teams That Have Not Set Scope ~\n" + reminderTable + "\n\n";
                    scopeUI.displayFrame("Team Report For " + course.getCourseName(), data, footer, 130);
                }

            }
        }
    }

    public void individualScopeReports() {
        if (!allCourseList.isEmpty() && !allAssignmentTeamList.isEmpty()) {
            Iterator cit = allCourseList.getIterator();
            while (cit.hasNext()) {
                String header = "";
                String details = "";
                int tutorialGroupCount = 1;
                int totalAssignmentTeamCount = 1;
                int totalScope = 0;
                double totalScore = 0;
                SortedMapInterface<AssignmentScope, Integer> pickQty = new SortedMap<>();
                SortedListInterface<AssignmentScope> pickQtyRef = new SortedCDLinkedList<>();
                ListInterface<String[]> noScope = new ArrayList<>();
                Course course = (Course) cit.next();
                if (!allTutorialGroupList.isEmpty()) {
                    Iterator git = allTutorialGroupList.getIterator();
                    while (git.hasNext()) {
                        TutorialGroup tutorialGroup = (TutorialGroup) git.next();
                        if (tutorialGroup.getCourseID().toUpperCase().equals(course.getCourseId().toUpperCase())) {
                            if (!allAssignmentTeamList.isEmpty()) {
                                Iterator ait = allAssignmentTeamList.getIterator();
                                int assignmentTeamCount = 1;
                                while (ait.hasNext()) {
                                    AssignmentTeam assignmentTeam = (AssignmentTeam) ait.next();
                                    if (assignmentTeam.getTutorialGroup().toUpperCase().equals(tutorialGroup.getTutGrpID().toUpperCase())) {
                                        ListInterface students = assignmentTeam.getStudentList();
                                        System.out.println("enter");
                                        SortedMapInterface<Integer, AssignmentScope> map = (assignmentTeam.getIndividualScopeList() != null) ? assignmentTeam.getIndividualScopeList() : new SortedMap<>();
                                        SortedMapInterface<Integer, AssignmentRubrics> rubricMap = (assignmentTeam.getIndividualRubricList() != null) ? assignmentTeam.getIndividualRubricList() : new SortedMap<>();
                                        if (!map.isEmpty()) {
                                            for (int i = 1; i <= students.size(); i++) {
                                                AssignmentScope scope = map.get(i);
                                                totalScope++;
                                                double score = 0;
                                                if (scope != null) {
                                                    if (rubricMap.containsKey(i)) {
                                                        score = rubricMap.get(i).getTotalScore();
                                                    }
                                                    totalScore += score;

                                                    if (pickQty.containsKey(scope)) {
                                                        pickQty.put(scope, pickQty.get(scope) + 1);
                                                    } else {
                                                        pickQty.put(scope, 1);
                                                        pickQtyRef.add(scope);
                                                    }
                                                } else {
                                                    System.out.println("no set");
                                                    Student student = (Student) students.get(i);
                                                    noScope.add(new String[]{tutorialGroup.getTutorialGrpNum() + "", "Team No " + assignmentTeamCount, student.getStudentName()});
                                                }
                                            }
                                        } else {
                                            System.out.println("Enter no set");
                                            for (int i = 1; i <= students.size(); i++) {
                                                Student student = (Student) students.get(i);
                                                noScope.add(new String[]{tutorialGroup.getTutorialGrpNum() + "", "Team No " + assignmentTeamCount, student.getStudentName()});
                                            }
                                        }
                                        System.out.println(assignmentTeamCount);
                                        System.out.println("exit");
                                        assignmentTeamCount++;
                                    }
                                    totalAssignmentTeamCount++;
                                }
                            }
                            tutorialGroupCount++;
                        }
                    }

                    String[][] pickRateData = new String[pickQty.size() + 1][3];
                    pickRateData[0] = new String[]{"Scope Name", "Quantity", "Pick Rate"};
                    for (int i = 1; i <= pickQty.size(); i++) {
                        AssignmentScope ref = pickQtyRef.get(i);
                        double pickRate = Double.parseDouble(pickQty.get(ref) + "") / totalScope * 100;
                        pickRateData[i] = new String[]{ref.getScopeName(), pickQty.get(pickQtyRef.get(i)) + "", String.format("%.2f", pickRate)};
                    }
                    quickSortDouble(pickRateData, 1, pickRateData.length - 1, 2);
                    for (int i = 1; i < pickRateData.length; i++) {
                        pickRateData[i][2] += " %";
                    }
                    int[] pickColWidth = {30, 30, 30};
                    Table pickRateTable = scopeUI.getTable("Pick Rate", pickRateData, pickQty.size() + 1, 3, pickColWidth);

                    String[][] reminderData = new String[noScope.size() + 1][2];
                    reminderData[0] = new String[]{"Tutorial Group Number", "Assignment Team Number", "Student Name"};
                    for (int i = 1; i <= noScope.size(); i++) {
                        reminderData[i] = noScope.get(i);
                    }
                    int[] reminderColWidth = {30, 30, 30};
                    Table reminderTable = scopeUI.getTable("Reminder", reminderData, noScope.size() + 1, 3, reminderColWidth);
                    double avg = 0;
                    if (totalScore != 0 && totalScope != 0) {
                        avg = (totalScore / totalScope);
                    }
                    header += String.format("%-50s%-35s%-35s", "Course Name : " + course.getCourseName(), "Total Tutorial Group Qty : " + (tutorialGroupCount - 1), "Total Assignment Team Qty : " + (totalAssignmentTeamCount - 1));
                    String footer = "Average Individual Score : " + String.format("%.2f", avg);
                    String data = header + "\n" + details + "\n~ Pick Rate Of All Scopes ~\n" + pickRateTable + "\n~ Teams That Have Not Set Scope ~\n" + reminderTable + "\n\n";
                    scopeUI.displayFrame("Individual Report For " + course.getCourseName(), data, footer, 130);
                }

            }
        }
    }

    //Shared
    public boolean courseSelection() {
        Iterator it = allCourseList.getIterator();
        String title = "Course";
        String options = "";
        int count = 1;
        if (allCourseList.isEmpty()) {
            scopeUI.emptyList(title);
            return false;
        }

        while (it.hasNext()) {
            Course course = (Course) it.next();
            options += count + ". " + course.getCourseName() + "\n";
            count++;
        }

        courseIndex = scopeUI.getSelectionMenu(title, options, allCourseList.size());
        if (courseIndex == 0) {
            scopeUI.returnMessage();
            return false;
        }
        courseOfChoice = allCourseList.get(courseIndex);
        Iterator tutIt = allTutorialGroupList.getIterator();

        if (allTutorialGroupList.isEmpty()) {
            scopeUI.emptyList("Tutorial Group");
            return false;
        } else {
            tutorialGroupList.clear();
        }

        while (tutIt.hasNext()) {
            TutorialGroup tutorialGroup = (TutorialGroup) tutIt.next();
            if (tutorialGroup.getCourseID().equals(courseOfChoice.getCourseId())) {
                tutorialGroupList.add(tutorialGroup);
            }
        }
        return true;
    }

    public boolean tutorialGroupSelection() {
        Iterator it = tutorialGroupList.getIterator();
        String title = "Tutorial Group";
        String options = "";
        int count = 1;

        if (tutorialGroupList.isEmpty()) {
            scopeUI.emptyList(title);
            return false;
        }

        while (it.hasNext()) {
            TutorialGroup tutorialGroup = (TutorialGroup) it.next();
            options += count + ". Tutorial Group " + tutorialGroup.getTutorialGrpNum() + "\n";
            count++;
        }

        tutorialGroupIndex = scopeUI.getSelectionMenu(title, options, tutorialGroupList.size());

        if (tutorialGroupIndex == 0) {
            scopeUI.returnMessage();
            return false;
        }

        tutorialGroupOfChoice = tutorialGroupList.get(tutorialGroupIndex);
        Iterator assmIt = allAssignmentTeamList.getIterator();

        if (allAssignmentTeamList.isEmpty()) {
            scopeUI.emptyList("Assignment Team");
            return false;
        } else {
            assignmentTeamList.clear();
        }

        while (assmIt.hasNext()) {
            AssignmentTeam assignmentTeam = (AssignmentTeam) assmIt.next();
            if (assignmentTeam.getTutorialGroup().equals(tutorialGroupIndex + "")) {
                assignmentTeamList.add(assignmentTeam);
            }
        }
        return true;
    }

    public boolean assignmentTeamSelection() {
        Iterator it = assignmentTeamList.getIterator();
        String title = "Assignment Team";
        String options = "";
        int count = 1;

        if (assignmentTeamList.isEmpty()) {
            scopeUI.emptyList(title);
            return false;
        }

        while (it.hasNext()) {
            AssignmentTeam assignmentTeam = (AssignmentTeam) it.next();
            options += count + ". Assignment Team " + count + "\n";
            count++;
        }

        assignmentTeamIndex = scopeUI.getSelectionMenu(title, options, assignmentTeamList.size());

        if (assignmentTeamIndex == 0) {
            scopeUI.returnMessage();
            return false;
        }

        assignmentTeamOfChoice = assignmentTeamList.get(assignmentTeamIndex);
        studentList = assignmentTeamOfChoice.getStudentList();
        return true;
    }

    public boolean studentSelection() {
        Iterator it = studentList.getIterator();
        String title = "Students";
        String options = "";
        int count = 1;

        if (studentList.isEmpty()) {
            scopeUI.emptyList(title);
            return false;
        }

        while (it.hasNext()) {
            Student student = (Student) it.next();
            options += count + ". " + student.getStudentName() + "\n";
            count++;
        }

        studentIndex = scopeUI.getSelectionMenu(title, options, studentList.size());

        if (studentIndex == 0) {
            scopeUI.returnMessage();
            return false;
        }

        studentOfChoice = studentList.get(studentIndex);
        return true;
    }

    public void createDummyData() {
        ScopeInit init = new ScopeInit();
        allCourseList = init.initCourse();
        allTutorialGroupList = init.initGroup();
        allAssignmentTeamList = init.initializeTeams();
        System.out.println(allCourseList);
        System.out.println(allTutorialGroupList);
        System.out.println(allAssignmentTeamList);
        ipt.enterToContinue("test");
    }

    public void readFromFile() {
        ElementsDAO test = new ElementsDAO();
        allCourseList = test.retrieveAllFromFile("CourseForScope.dat");
        allTutorialGroupList = test.retrieveAllFromFile("GroupForScope.dat");
        allAssignmentTeamList = test.retrieveAllFromFile("AssmTeamForScope.dat");
    }

    public void saveToFile() {
        ElementsDAO test = new ElementsDAO();
        test.saveToFile(allCourseList, "CourseForScope.dat");
        test.saveToFile(allTutorialGroupList, "GroupForScope.dat");
        test.saveToFile(allAssignmentTeamList, "AssmTeamForScope.dat");
        scopeUI.exitMessage();

    }

    //Sorting algorithms
    public void quickSortDouble(String[][] list, int first, int last, int markIndex) {
        if (first < last) {
            int pivot = partitionDouble(list, first, last, markIndex);

            quickSortDouble(list, first, pivot - 1, markIndex);
            quickSortDouble(list, pivot + 1, last, markIndex);
        }
    }

    public int partitionDouble(String[][] list, int first, int last, int markIndex) {
        double pivot = Double.parseDouble(list[last][markIndex]);

        int i = first - 1;

        for (int j = first; j <= last - 1; j++) {
            if (Double.parseDouble(list[j][markIndex]) > pivot) {
                i++;
                String[] temp = list[i];
                list[i] = list[j];
                list[j] = temp;

            }
        }

        String[] temp = list[i + 1];
        list[i + 1] = list[last];
        list[last] = temp;

        return (i + 1);
    }

    public void quickSortString(String[][] array, int first, int last, int columnToSort) {
        if (first < last) {
            int pivotIndex = partitionString(array, first, last, columnToSort);

            quickSortString(array, first, pivotIndex - 1, columnToSort);
            quickSortString(array, pivotIndex + 1, last, columnToSort);
        }
    }

    public int partitionString(String[][] array, int first, int last, int columnToSort) {
        String pivot = array[last][columnToSort];
        int i = first - 1;

        for (int j = first; j <= last - 1; j++) {
            if (array[j][columnToSort].compareTo(pivot) < 0) {
                i++;

                // Swap rows
                String[] temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap rows for the pivot
        String[] temp = array[i + 1];
        array[i + 1] = array[last];
        array[last] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        ScopeController control = new ScopeController();
        control.runScope();
    }
}
