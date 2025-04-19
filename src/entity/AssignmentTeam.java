/**
 * @author Tan Wei Siang
 */
package entity;

import adt.*;
import java.io.Serializable;
import java.util.Objects;

public class AssignmentTeam implements Comparable<AssignmentTeam>, Serializable {

    private int assignmentTeamID;
    private String tutorialGroup;
    private String assignmentTitle;
    private boolean isComplete;
    private double mark;
    private ListInterface<Student> studentList = new ArrayList<>();

    private AssignmentRubrics teamRubric;
    private SortedMapInterface<Integer, AssignmentRubrics> individualRubricList = new SortedMap<>();

    private AssignmentScope teamScope;
    private SortedMapInterface<Integer, AssignmentScope> individualScopeList = new SortedMap<>();

    public AssignmentTeam() {
    }

    public AssignmentTeam(int assignmentTeamID, String tutorialGroupID, String assignmentTitle, boolean isComplete, double mark, ListInterface<Student> studentList) {
        this.assignmentTeamID = assignmentTeamID;
        this.tutorialGroup = tutorialGroupID;
        this.assignmentTitle = assignmentTitle;
        this.isComplete = isComplete;
        this.mark = mark;
        this.studentList = studentList;
    }

    public AssignmentTeam(int assignmentTeamID, String tutorialGroupID, String assignmentTitle) {
        this.assignmentTeamID = assignmentTeamID;
        this.tutorialGroup = tutorialGroupID;
        this.assignmentTitle = assignmentTitle;
        this.isComplete = false;
        this.mark = 0;
    }

    public AssignmentTeam(int assignmentTeamID) {
        this.assignmentTeamID = assignmentTeamID;
    }

    public AssignmentTeam(int assignmentTeamID, String tutorialGroup) {
        this.assignmentTeamID = assignmentTeamID;
        this.tutorialGroup = tutorialGroup;
    }

    // For hard code purpose
    public AssignmentTeam(int assignmentTeamID, String tutorialGroup, ListInterface<Student> studentList) {
        this.tutorialGroup = tutorialGroup;
        this.assignmentTeamID = assignmentTeamID;
        this.studentList = studentList;
    }

    public AssignmentTeam(int assignmentTeamID, String tutorialGroup, ListInterface<Student> studentList, AssignmentRubrics teamRubric) {
        this.tutorialGroup = tutorialGroup;
        this.assignmentTeamID = assignmentTeamID;
        this.teamRubric = teamRubric;
        this.studentList = studentList;
    }

    public AssignmentTeam(int assignmentTeamID, String tutorialGroup, ListInterface<Student> studentList, AssignmentRubrics teamRubric, AssignmentRubrics individualRubricList) {
        this.assignmentTeamID = assignmentTeamID;
        this.tutorialGroup = tutorialGroup;
        this.teamRubric = teamRubric;
        this.studentList = studentList;
        this.assignIndividualRubric(individualRubricList);
    }

    public AssignmentTeam(int assignmentTeamID, String tutorialGroup, ListInterface<Student> studentList, AssignmentRubrics teamRubric, SortedMapInterface<Integer, AssignmentRubrics> individualRubricList) {
        this.assignmentTeamID = assignmentTeamID;
        this.tutorialGroup = tutorialGroup;
        this.studentList = studentList;
        this.teamRubric = teamRubric;
        this.individualRubricList = individualRubricList;
    }

    public AssignmentTeam(int assignmentTeamID, String tutorialGroup, ListInterface<Student> studentList, AssignmentRubrics teamRubric, SortedMapInterface<Integer, AssignmentRubrics> individualRubricList, AssignmentScope teamScope, SortedMapInterface<Integer, AssignmentScope> individualScopeList) {
        this.assignmentTeamID = assignmentTeamID;
        this.tutorialGroup = tutorialGroup;
        this.studentList = studentList;
        this.teamRubric = teamRubric;
        this.individualRubricList = individualRubricList;
        this.teamScope = teamScope;
        this.individualScopeList = individualScopeList;
    }

    public void assignIndividualRubric(AssignmentRubrics individualRubric) {
        for (int i = 1; i <= studentList.size(); i++) {
            individualRubricList.put(studentList.get(i).getStudentID(), individualRubric);
        }
    }

    @Override
    public String toString() {
        return String.format("%16d %16s %16s %12b %12.2f\n", assignmentTeamID, tutorialGroup, assignmentTitle, isComplete, mark);
    }

    @Override
    public int compareTo(AssignmentTeam o) {

        if (this.assignmentTeamID > o.assignmentTeamID) {
            return 1;
        } else if (this.assignmentTeamID == o.assignmentTeamID) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.assignmentTeamID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssignmentTeam other = (AssignmentTeam) obj;
        return Objects.equals(this.assignmentTeamID, other.assignmentTeamID);
    }

    public int getAssignmentTeamID() {
        return assignmentTeamID;
    }

    public String getTutorialGroup() {
        return tutorialGroup;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public double getMark() {
        return mark;
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setAssignmentTeamID(int assignmentTeamID) {
        this.assignmentTeamID = assignmentTeamID;
    }

    public void setTutorialGroup(String tutorialGroupID) {
        this.tutorialGroup = tutorialGroupID;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public int getTotalMember() {
        return studentList.size();
    }

    public AssignmentRubrics getTeamRubric() {
        return teamRubric;
    }

    public void setTeamRubric(AssignmentRubrics teamRubric) {
        this.teamRubric = teamRubric;
    }

    public SortedMapInterface<Integer, AssignmentRubrics> getIndividualRubricList() {
        return individualRubricList;
    }

    public void setIndividualRubricList(SortedMapInterface<Integer, AssignmentRubrics> individualRubricList) {
        this.individualRubricList = individualRubricList;
    }

    public AssignmentScope getTeamScope() {
        return teamScope;
    }

    public void setTeamScope(AssignmentScope teamScope) {
        this.teamScope = teamScope;
    }

    public void setTeamRubric(AssignmentScope teamScope) {
        this.teamScope = teamScope;
    }

    public SortedMapInterface<Integer, AssignmentScope> getIndividualScopeList() {
        return individualScopeList;
    }

    public void setIndividualScopeList(SortedMapInterface<Integer, AssignmentScope> individualScopeList) {
        this.individualScopeList = individualScopeList;
    }
}
