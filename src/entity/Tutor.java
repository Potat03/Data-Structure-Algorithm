/**
 * @author Loo Wee Kiat
 */
package entity;

import adt.SortedCDLinkedList;
import adt.SortedListInterface;
import java.io.Serializable;
import java.util.Objects;

public class Tutor implements Comparable<Tutor>, Serializable {

    String tutorID;
    String tutorName;
    String tutorEmail;
    String courseID;
    String studyLevel;
    String gender;
    SortedListInterface<Course> course;

    public SortedListInterface<Course> getCourse() {
        return course;
    }

    public void setCourse(SortedListInterface<Course> course) {
        this.course = course;
    }

    public Tutor() {
    }

    public Tutor(String tutorID, String tutorName, String gender, String tutorEmail, String studyLevel) {
        this.tutorID = tutorID;
        this.tutorName = tutorName;
        this.gender = gender;
        this.tutorEmail = tutorEmail;
        this.studyLevel = studyLevel;
        this.course = new SortedCDLinkedList<>();
    }

    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-15s %-8s %-20s %-10s %-40s", tutorID, tutorName, gender, tutorEmail, studyLevel, course);
    }

    @Override
    public int compareTo(Tutor o) {
        return this.tutorID.compareTo(o.tutorID);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.tutorID);
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
        final Tutor other = (Tutor) obj;
        return Objects.equals(this.tutorID, other.tutorID);
    }

}
