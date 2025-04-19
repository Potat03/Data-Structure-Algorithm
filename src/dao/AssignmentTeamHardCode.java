/*
 * @author Tan Wei Siang
 */
package dao;

import adt.ArrayList;
import adt.ListInterface;
import adt.SortedCDLinkedList;
import adt.SortedListInterface;
import entity.AssignmentTeam;
import entity.Student;

public class AssignmentTeamHardCode {

    public static void main(String[] args) {
        SortedListInterface<AssignmentTeam> assignmentTeams = new SortedCDLinkedList<>();
        ListInterface<Student> stdlist = new ArrayList<>();
        Student s1 = new Student(1, "Nick");
        Student s2 = new Student(2, "Sam");
        Student s3 = new Student(3, "John");
        Student s4 = new Student(4, "Ning");
        Student s5 = new Student(5, "Cindy");
        Student s6 = new Student(6, "Ken");
        Student s7 = new Student(7, "Jin");
        Student s8 = new Student(8, "Ming");
        Student s9 = new Student(9, "Kai");
        Student s10 = new Student(10, "Lok");

        ListInterface<Student> a1 = new ArrayList<>();
        a1.add(s1);
        a1.add(s2);
        a1.add(s3);
        a1.add(s4);
        a1.add(s9);

        ListInterface<Student> a2 = new ArrayList<>();
        a2.add(s5);
        a2.add(s6);
        a2.add(s7);
        a2.add(s8);
        a2.add(s9);

        ListInterface<Student> a3 = new ArrayList<>();
        a3.add(s10);
        a3.add(s1);
        a3.add(s7);
        a3.add(s8);
        a3.add(s9);

        AssignmentTeam t1 = new AssignmentTeam(1, "Group1", "ERP", true, 100.00, a1);
        AssignmentTeam t2 = new AssignmentTeam(2, "Group2", "CRM", true, 55.00, a2);
        AssignmentTeam t3 = new AssignmentTeam(3, "Group3", "POS", false, 0.00, a3);
        AssignmentTeam t4 = new AssignmentTeam(4, "Group4", "ERP", false, 0.00, a3);
        AssignmentTeam t5 = new AssignmentTeam(5, "Group5", "ERP", true, 80.00, a3);
        AssignmentTeam t6 = new AssignmentTeam(6, "Group9", "CRM", true, 60.00, a2);
        AssignmentTeam t7 = new AssignmentTeam(7, "Group8", "ERP", false, 10.00, a1);
        AssignmentTeam t8 = new AssignmentTeam(8, "Group7", "CRM", true, 100.00, a1);
        AssignmentTeam t9 = new AssignmentTeam(9, "Group6", "POS", false, 20.00, a2);
        AssignmentTeam t10 = new AssignmentTeam(10, "Group10", "POS", true, 50.00, a1);

        stdlist.add(s1);
        stdlist.add(s2);
        stdlist.add(s3);
        stdlist.add(s4);
        stdlist.add(s5);
        stdlist.add(s6);
        stdlist.add(s7);
        stdlist.add(s8);
        stdlist.add(s9);
        stdlist.add(s10);
        
        assignmentTeams.add(t1);
        assignmentTeams.add(t2);
        assignmentTeams.add(t3);
        assignmentTeams.add(t4);
        assignmentTeams.add(t5);
        assignmentTeams.add(t6);
        assignmentTeams.add(t7);
        assignmentTeams.add(t8);
        assignmentTeams.add(t9);
        assignmentTeams.add(t10);
        
        AssignmentTeamDAO dao = new AssignmentTeamDAO();
        dao.saveToFileSort(assignmentTeams, assignmentTeams.size(), "WS_AssignmentTeam.dat");
        dao.saveToFile(stdlist, stdlist.size(), "WS_Student.dat");
    }
}
