/**
 * @author Tan Wei Siang
 */
package entity;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable{

    private int studentID;
    private String tutorialGroup;
    private int assignmentTeamID;
    private String studentName;
    private String studentEmail;

    public Student() {
    }

    public Student(int studentID) {
        this.studentID = studentID;
    }

    public Student(int studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
    
    return  String.format("%12d %12s %16s %12s %12d", studentID,studentName,studentEmail,tutorialGroup,assignmentTeamID);

    }

    
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setTutorialGroupID(String tutorialGroupID) {
        this.tutorialGroup = tutorialGroupID;
    }

    public void setAssignmentTeamID(int assignmentTeamID) {
        this.assignmentTeamID = assignmentTeamID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getTutorialGroupID() {
        return tutorialGroup;
    }

    public int getAssignmentTeamID() {
        return assignmentTeamID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    @Override
    public int compareTo(Student o) {
     if (this.studentID > o.studentID) {
            return 1;
        }
        else if (this.studentID == o.studentID) {
            return 0;
        } else {
            return -1;
        }    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.studentID;
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
        final Student other = (Student) obj;
        return this.studentID == other.studentID;
    }

}
