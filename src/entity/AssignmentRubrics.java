/*
 * @author Loh Thiam Wei 
 * @ModuleHandle AssignmentAssessment  * 
 * This class belongs to Assignment Assessment Subsystem  * 
 */
package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;
import java.util.Objects;

public class AssignmentRubrics implements Comparable<AssignmentRubrics>, Serializable {

    private String rubricID;
    private String rubricName;
    private boolean isCompleted;
    private ListInterface<Criteria> criteriaList = new ArrayList<>();

    public AssignmentRubrics(String rubricID, String rubricName) {
        this.rubricID = rubricID;
        this.rubricName = rubricName;
        this.isCompleted = false;
    }

    public AssignmentRubrics(String rubricID) {
        this.rubricID = rubricID;
    }

    public AssignmentRubrics(AssignmentRubrics rubric) {
        this.rubricID = rubric.rubricID;
        this.rubricName = rubric.rubricName;
        this.isCompleted = rubric.isCompleted;
        ListInterface<Criteria> tempCriteriaList = rubric.getCriteriaList();
        for (int i = 1; i <= tempCriteriaList.size(); i++) {
            criteriaList.add(new Criteria(tempCriteriaList.get(i)));
        }
    }

    public void complete() {
        setIsCompleted(true);
    }

    public String getStatus() {
        if (isCompleted) {
            return "Finalized";
        }
        return "Not Finizalied";
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    private void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getRubricName() {
        return rubricName;
    }

    public void setRubricName(String rubricName) {
        this.rubricName = rubricName;
    }

    public ListInterface<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(ListInterface<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public boolean addCriteria(String criteriaDesc, double maxScore, double score) {
        if (maxScore < 1) {
            return false;
        }
        return criteriaList.add(new Criteria(criteriaDesc, maxScore, score));
    }

    public boolean addCriteria(String criteriaDesc, double maxScore) {
        if (maxScore < 1) {
            return false;
        }
        return criteriaList.add(new Criteria(criteriaDesc, maxScore));
    }

    public boolean removeCriteria(int index) {
        return criteriaList.remove(index);
    }

    public boolean editCriteria(int index, String criteriaDesc, double maxScore, double score) {
        if (maxScore < 1 || score > maxScore || score < 0) {
            return false;
        }
        return criteriaList.replace(index, new Criteria(criteriaDesc, maxScore, score));
    }

    public boolean moveCriteria(int index, int position) {
        return criteriaList.changePosition(index, position);
    }

    public double getTotalScore() {
        double totalScore = 0;
        for (int i = 1; i <= criteriaList.size(); i++) {
            totalScore += criteriaList.get(i).score;
        }
        return totalScore;
    }

    public double getTotalMaxScore() {
        double totalScore = 0;
        for (int i = 1; i <= criteriaList.size(); i++) {
            totalScore += criteriaList.get(i).maxScore;
        }
        return totalScore;
    }

    public String getRubricID() {
        return rubricID;
    }

    public void setRubricID(String rubricID) {
        this.rubricID = rubricID;
    }

    @Override
    public String toString() {
        return rubricName + "\nCriteria List -\n" + criteriaList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.rubricID);
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
        final AssignmentRubrics other = (AssignmentRubrics) obj;
        return Objects.equals(this.rubricID, other.rubricID);
    }

    @Override
    public int compareTo(AssignmentRubrics o) {
        return rubricID.compareTo(o.getRubricID());
    }

    public class Criteria implements Serializable {

        private String criteriaDesc;
        private double maxScore;
        private double score;

        public Criteria(String criteriaDesc) {
            this.criteriaDesc = criteriaDesc;
        }

        public Criteria(String criteriaDesc, double maxScore) {
            this(criteriaDesc, maxScore, 0);
        }

        public Criteria(String criteriaDesc, double maxScore, double score) {
            this.criteriaDesc = criteriaDesc;
            this.maxScore = maxScore;
            this.score = score;
        }

        //For deep copy
        public Criteria(Criteria criteria) {
            this.criteriaDesc = criteria.criteriaDesc;
            this.maxScore = criteria.maxScore;
            this.score = criteria.score;
        }

        public String getCriteriaDesc() {
            return criteriaDesc;
        }

        public void setCriteriaDesc(String criteriaDesc) {
            this.criteriaDesc = criteriaDesc;
        }

        public double getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(double maxScore) {
            this.maxScore = maxScore;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return String.format("%-60s MaxScore:" + maxScore + " Score:" + score, criteriaDesc);
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 71 * hash + Objects.hashCode(this.criteriaDesc);
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
            final Criteria other = (Criteria) obj;
            return Objects.equals(this.criteriaDesc, other.criteriaDesc);
        }

    }

}
