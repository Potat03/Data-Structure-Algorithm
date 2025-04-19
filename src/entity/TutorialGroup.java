/**
 * @author Loo Wee Kiat
 */
package entity;

import adt.SortedCDLinkedList;
import adt.SortedListInterface;
import java.io.Serializable;
import java.util.Objects;

public class TutorialGroup implements Comparable<TutorialGroup>, Serializable {

    String tutGrpID;
    String programmeID;
    String courseID;
    int year;
    int sem;
    int tutorialGrpNum;
    SortedListInterface<Tutor> tutor;

    public TutorialGroup() {
    }

    public SortedListInterface<Tutor> getTutor() {
        return tutor;
    }

    public void setTutor(SortedListInterface<Tutor> tutor) {
        this.tutor = tutor;
    }

    public TutorialGroup(String tutGrpID) {
        this.tutGrpID = tutGrpID;
    }

    public TutorialGroup(String tutGrpID, String programmeID) {
        this.tutGrpID = tutGrpID;
        this.programmeID = programmeID;
    }

    public TutorialGroup(String tutGrpID, String courseID, String programmeID) {
        this.tutGrpID = tutGrpID;
        this.courseID = courseID;
    }

    public TutorialGroup(String tutGrpID, String programmeID, int year, int sem, int tutorialGrpNum) {
        this.tutGrpID = tutGrpID;
        this.programmeID = programmeID;
        this.year = year;
        this.sem = sem;
        this.tutorialGrpNum = tutorialGrpNum;
        this.tutor = new SortedCDLinkedList<>();
    }

    public TutorialGroup(String tutGrpID, String programmeID, String courseID, int year, int sem, int tutorialGrpNum) {
        this.tutGrpID = tutGrpID;
        this.programmeID = programmeID;
        this.courseID = courseID;
        this.year = year;
        this.sem = sem;
        this.tutorialGrpNum = tutorialGrpNum;
        this.tutor = new SortedCDLinkedList<>();
    }

    public String getTutGrpID() {
        return tutGrpID;
    }

    public void setTutGrpID(String tutGrpID) {
        this.tutGrpID = tutGrpID;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(String programmeID) {
        this.programmeID = programmeID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getTutorialGrpNum() {
        return tutorialGrpNum;
    }

    public void setTutorialGrpNum(int tutorialGrpNum) {
        this.tutorialGrpNum = tutorialGrpNum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.tutGrpID);
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
        final TutorialGroup other = (TutorialGroup) obj;
        return Objects.equals(this.tutGrpID, other.tutGrpID);
    }

    @Override
    public String toString() {
        return String.format("%-5s %-8s %-45s", this.tutGrpID, this.programmeID + this.year + "S" + this.sem + "G" + this.tutorialGrpNum, tutor);
    }

    @Override
    public int compareTo(TutorialGroup o) {
        return tutGrpID.compareTo(o.tutGrpID);
    }

}
