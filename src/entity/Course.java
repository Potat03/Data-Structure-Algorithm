/**
 * @author Loo Wee Kiat
 */
package entity;

import java.io.Serializable;
import java.util.Objects;

public class Course implements Comparable<Course>, Serializable {

    private String courseId;
    private String courseName;

    public Course() {
    }

    public Course(String courseId) {
        this.courseId = courseId;
    }

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public int compareTo(Course o) {
        return courseId.toUpperCase().compareTo(o.courseId.toUpperCase());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.courseId);
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
        final Course other = (Course) obj;
        return Objects.equals(this.courseId, other.courseId);
    }

    @Override
    public String toString() {
        return courseId + " - " + courseName;
    }

}
