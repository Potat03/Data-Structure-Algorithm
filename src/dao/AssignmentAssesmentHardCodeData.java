/*
 * @author Loh Thiam Wei 
 * @ModuleHandle AssignmentAssessment  * 
 * This class belongs to Assignment Assessment Subsystem  * 
 */
package dao;

import adt.*;
import entity.*;

public class AssignmentAssesmentHardCodeData {

    public static void main(String[] args) {
// Need to save courseList, tutorial group
        SortedListInterface<Course> courseList = new SortedCDLinkedList<>();
        courseList.add(new Course("BACS2042", "Research Method"));
        courseList.add(new Course("BACS2053", "Object-Oriented Analysis And Design"));
        courseList.add(new Course("BACS2063", "Data Structures And Algorithms"));
        courseList.add(new Course("BACS2163", "Software Enginerring"));
        System.out.println(courseList);

        ListInterface<Student> memberList1 = new ArrayList<>();
        memberList1.add(new Student(2301, "Thiam Wei"));
        memberList1.add(new Student(2302, "Jun Jie"));
        memberList1.add(new Student(2303, "Tian Chun"));
        memberList1.add(new Student(2304, "Guan Wen"));
        System.out.println(memberList1);

        ListInterface<Student> memberList2 = new ArrayList<>();
        memberList2.add(new Student(2305, "Ivan Gan"));
        memberList2.add(new Student(2306, "Winson Zai"));
        memberList2.add(new Student(2307, "Kaywen"));
        memberList2.add(new Student(2308, "Kai Jian"));
        System.out.println(memberList2);

        ListInterface<Student> memberList3 = new ArrayList<>();
        memberList3.add(new Student(2309, "Dexter Goh"));
        memberList3.add(new Student(2310, "Wei Hong"));
        memberList3.add(new Student(2311, "Zi Yan"));
        memberList3.add(new Student(2312, "Zi Ken"));
        System.out.println(memberList3);

        ListInterface<Student> memberList4 = new ArrayList<>();
        memberList4.add(new Student(2313, "Ken Lee"));
        memberList4.add(new Student(2314, "Terry"));
        memberList4.add(new Student(2315, "Kai Xian"));
        memberList4.add(new Student(2316, "Jun Wei"));
        System.out.println(memberList4);

        ListInterface<Student> memberList5 = new ArrayList<>();
        memberList5.add(new Student(2317, "Chew Ke Ming"));
        memberList5.add(new Student(2318, "Jun Yao"));
        memberList5.add(new Student(2319, "Pin Leong"));
        memberList5.add(new Student(2320, "Ah Bing"));
        System.out.println(memberList5);

        AssignmentRubrics rmTeamRubric = new AssignmentRubrics("R001", "Research Method Team Rubric");
        rmTeamRubric.addCriteria("Title and Abstract", 10);
        rmTeamRubric.addCriteria("Research problem statement, research objective", 10);
        rmTeamRubric.addCriteria("Research question, hypothesis/sub-objective", 10);
        rmTeamRubric.addCriteria("Literature review", 10);
        rmTeamRubric.addCriteria("Research design, methods, organization", 10);
        rmTeamRubric.addCriteria("Results - data analysis", 10);
        rmTeamRubric.addCriteria("Reference and citation", 10);
        rmTeamRubric.addCriteria("Lifelong learning", 10);
        System.out.println(rmTeamRubric);

        AssignmentRubrics rmIndividualRubric = new AssignmentRubrics("R002", "Research Method Individiul Rubric");
        rmIndividualRubric.addCriteria("Understanding On Content", 7);
        rmIndividualRubric.addCriteria("Clear Communication", 2);
        rmIndividualRubric.addCriteria("Proper uniform", 1);
        System.out.println(rmIndividualRubric);

        AssignmentRubrics oopTeamRubric = new AssignmentRubrics("R003", "OOAD Team Rubric");
        oopTeamRubric.addCriteria("Correctness and completeness of overview use case diagram", 5);
        oopTeamRubric.addCriteria("Correctness and completeness of initial/ analysis class diagram", 5);
        oopTeamRubric.addCriteria("Correctness and completeness of final class diagram.", 10);
        oopTeamRubric.addCriteria("Correctness and completeness of deployment diagram", 5);
        oopTeamRubric.addCriteria("Digital skill (Use of UML modelling tool)", 10);
        System.out.println(oopTeamRubric);

        AssignmentRubrics oopIndividualRubric = new AssignmentRubrics("R004", "OOAD Individual Rubric");
        oopIndividualRubric.addCriteria("Problem statement and Functional requirements list", 5);
        oopIndividualRubric.addCriteria("Correctness and completeness of details use case diagrams + use case descriptions", 10);
        oopIndividualRubric.addCriteria("Correctness and completeness of activity diagrams based on the use case scenarios", 10);
        oopIndividualRubric.addCriteria("Correctness and completeness of interaction diagrams", 15);
        oopIndividualRubric.addCriteria("Correctness and completeness of state chart diagram(s) for any ONE problem domain classes", 5);
        oopIndividualRubric.addCriteria("Weekly interaction & Deliverables", 10);
        oopIndividualRubric.addCriteria("Final presentation", 10);
        System.out.println(oopIndividualRubric);

        AssignmentRubrics dsaTeamRubric = new AssignmentRubrics("R005", "DSA Team Rubric");
        dsaTeamRubric.addCriteria("ADT Specification", 10);
        dsaTeamRubric.addCriteria("Collection ADT Implementation", 10);
        dsaTeamRubric.addCriteria("Collection ADT Implementation - Originality", 15);
        System.out.println(dsaTeamRubric);

        AssignmentRubrics dsaIndividualRubric = new AssignmentRubrics("R006", "DSA Individual Rubric");
        dsaIndividualRubric.addCriteria("Use of ADTs - appropriate use", 15);
        dsaIndividualRubric.addCriteria("Use of ADTs - creativity & complexity", 20);
        dsaIndividualRubric.addCriteria("Overall Solution", 15);
        dsaIndividualRubric.addCriteria("Presentation", 15);
        System.out.println(dsaIndividualRubric);

//        AssignmentRubrics seTeamRubric = new AssignmentRubrics("SE Team Rubric");
//        seTeamRubric.addCriteria("Proposal", 20);
//        seTeamRubric.addCriteria("Analysis Report", 30);
//        seTeamRubric.addCriteria("Design & Prototyping", 20);
//        seTeamRubric.addCriteria("Nielsen's Heuristic Evaluation", 20);
//        seTeamRubric.addCriteria("Presentation", 10);
//        System.out.println(seTeamRubric);
        AssignmentRubrics seTeamRubric = new AssignmentRubrics("R007", "SE Team Rubric");
        seTeamRubric.addCriteria("Proposal", 20);
        seTeamRubric.addCriteria("Analysis Report", 30);
        seTeamRubric.addCriteria("Design & Prototyping", 20);
        seTeamRubric.addCriteria("Nielsen's Heuristic Evaluation", 20);
        seTeamRubric.addCriteria("Presentation", 10);
        System.out.println(seTeamRubric);

        AssignmentRubrics rm1TeamRubric = new AssignmentRubrics("R001", "Research Method Team Rubric");
        rm1TeamRubric.addCriteria("Title and Abstract", 10, 3);
        rm1TeamRubric.addCriteria("Research problem statement, research objective", 10, 6);
        rm1TeamRubric.addCriteria("Research question, hypothesis/sub-objective", 10, 9);
        rm1TeamRubric.addCriteria("Literature review", 10, 4);
        rm1TeamRubric.addCriteria("Research design, methods, organization", 10, 7);
        rm1TeamRubric.addCriteria("Results - data analysis", 10, 2);
        rm1TeamRubric.addCriteria("Reference and citation", 10, 5);
        rm1TeamRubric.addCriteria("Lifelong learning", 10, 1);
        rm1TeamRubric.complete();
        System.out.println(rm1TeamRubric);

        AssignmentRubrics rm1TeamRubric1 = new AssignmentRubrics("R001", "Research Method Team Rubric");
        rm1TeamRubric1.addCriteria("Title and Abstract", 10, 7);
        rm1TeamRubric1.addCriteria("Research problem statement, research objective", 10, 4);
        rm1TeamRubric1.addCriteria("Research question, hypothesis/sub-objective", 10, 8);
        rm1TeamRubric1.addCriteria("Literature review", 10, 3);
        rm1TeamRubric1.addCriteria("Research design, methods, organization", 10, 6);
        rm1TeamRubric1.addCriteria("Results - data analysis", 10, 9);
        rm1TeamRubric1.addCriteria("Reference and citation", 10, 2);
        rm1TeamRubric1.addCriteria("Lifelong learning", 10, 5);
        rm1TeamRubric1.complete();
        System.out.println(rm1TeamRubric1);

        AssignmentRubrics rm1TeamRubric2 = new AssignmentRubrics("R001", "Research Method Team Rubric");
        rm1TeamRubric2.addCriteria("Title and Abstract", 10, 1);
        rm1TeamRubric2.addCriteria("Research problem statement, research objective", 10, 2);
        rm1TeamRubric2.addCriteria("Research question, hypothesis/sub-objective", 10, 3);
        rm1TeamRubric2.addCriteria("Literature review", 10, 4);
        rm1TeamRubric2.addCriteria("Research design, methods, organization", 10, 5);
        rm1TeamRubric2.addCriteria("Results - data analysis", 10, 6);
        rm1TeamRubric2.addCriteria("Reference and citation", 10, 7);
        rm1TeamRubric2.addCriteria("Lifelong learning", 10, 8);
        rm1TeamRubric2.complete();
        System.out.println(rm1TeamRubric2);

        AssignmentRubrics rm1TeamRubric3 = new AssignmentRubrics("R001", "Research Method Team Rubric");
        rm1TeamRubric3.addCriteria("Title and Abstract", 10, 9);
        rm1TeamRubric3.addCriteria("Research problem statement, research objective", 10, 8);
        rm1TeamRubric3.addCriteria("Research question, hypothesis/sub-objective", 10, 7);
        rm1TeamRubric3.addCriteria("Literature review", 10, 6);
        rm1TeamRubric3.addCriteria("Research design, methods, organization", 10, 5);
        rm1TeamRubric3.addCriteria("Results - data analysis", 10, 4);
        rm1TeamRubric3.addCriteria("Reference and citation", 10, 3);
        rm1TeamRubric3.addCriteria("Lifelong learning", 10, 2);
        rm1TeamRubric3.complete();
        System.out.println(rm1TeamRubric3);

        AssignmentRubrics rm1TeamRubric4 = new AssignmentRubrics("R001", "Research Method Team Rubric");
        rm1TeamRubric4.addCriteria("Title and Abstract", 10, 5);
        rm1TeamRubric4.addCriteria("Research problem statement, research objective", 10, 7);
        rm1TeamRubric4.addCriteria("Research question, hypothesis/sub-objective", 10, 2);
        rm1TeamRubric4.addCriteria("Literature review", 10, 8);
        rm1TeamRubric4.addCriteria("Research design, methods, organization", 10, 1);
        rm1TeamRubric4.addCriteria("Results - data analysis", 10, 6);
        rm1TeamRubric4.addCriteria("Reference and citation", 10, 3);
        rm1TeamRubric4.addCriteria("Lifelong learning", 10, 9);
        rm1TeamRubric4.complete();
        System.out.println(rm1TeamRubric4);

        AssignmentRubrics rm1TeamRubric5 = new AssignmentRubrics("R001", "Research Method Team Rubric");
        rm1TeamRubric5.addCriteria("Title and Abstract", 10, 4);
        rm1TeamRubric5.addCriteria("Research problem statement, research objective", 10, 9);
        rm1TeamRubric5.addCriteria("Research question, hypothesis/sub-objective", 10, 5);
        rm1TeamRubric5.addCriteria("Literature review", 10, 2);
        rm1TeamRubric5.addCriteria("Research design, methods, organization", 10, 8);
        rm1TeamRubric5.addCriteria("Results - data analysis", 10, 1);
        rm1TeamRubric5.addCriteria("Reference and citation", 10, 7);
        rm1TeamRubric5.addCriteria("Lifelong learning", 10, 6);
        rm1TeamRubric5.complete();
        System.out.println(rm1TeamRubric5);

        AssignmentRubrics rmIndividualRubric1 = new AssignmentRubrics("R002", "Research Method Individual Rubric");
        rmIndividualRubric1.addCriteria("Understanding On Content", 7, 3);
        rmIndividualRubric1.addCriteria("Clear Communication", 2, 1);
        rmIndividualRubric1.addCriteria("Proper uniform", 1, 1);
        rmIndividualRubric1.complete();
        System.out.println(rmIndividualRubric1);
        AssignmentRubrics rmIndividualRubric2 = new AssignmentRubrics("R002", "Research Method Individual Rubric");
        rmIndividualRubric2.addCriteria("Understanding On Content", 7, 5);
        rmIndividualRubric2.addCriteria("Clear Communication", 2, 1);
        rmIndividualRubric2.addCriteria("Proper uniform", 1, 1);
        rmIndividualRubric2.complete();
        System.out.println(rmIndividualRubric2);
        AssignmentRubrics rmIndividualRubric3 = new AssignmentRubrics("R002", "Research Method Individual Rubric");
        rmIndividualRubric3.addCriteria("Understanding On Content", 7, 6);
        rmIndividualRubric3.addCriteria("Clear Communication", 2, 2);
        rmIndividualRubric3.addCriteria("Proper uniform", 1, 1);
        rmIndividualRubric3.complete();
        System.out.println(rmIndividualRubric3);
        AssignmentRubrics rmIndividualRubric4 = new AssignmentRubrics("R002", "Research Method Individual Rubric");
        rmIndividualRubric4.addCriteria("Understanding On Content", 7, 4);
        rmIndividualRubric4.addCriteria("Clear Communication", 2, 1);
        rmIndividualRubric4.addCriteria("Proper uniform", 1, 1);
        rmIndividualRubric4.complete();
        System.out.println(rmIndividualRubric4);
        AssignmentRubrics rmIndividualRubric5 = new AssignmentRubrics("R002", "Research Method Individual Rubric");
        rmIndividualRubric5.addCriteria("Understanding On Content", 7, 2);
        rmIndividualRubric5.addCriteria("Clear Communication", 2, 1);
        rmIndividualRubric5.addCriteria("Proper uniform", 1, 1);
        rmIndividualRubric5.complete();
        System.out.println(rmIndividualRubric5);
        AssignmentRubrics rmIndividualRubric6 = new AssignmentRubrics("R002", "Research Method Individual Rubric");
        rmIndividualRubric6.addCriteria("Understanding On Content", 7, 1);
        rmIndividualRubric6.addCriteria("Clear Communication", 2, 2);
        rmIndividualRubric6.addCriteria("Proper uniform", 1, 1);
        rmIndividualRubric6.complete();
        System.out.println(rmIndividualRubric6);

        SortedListInterface<AssignmentRubrics> rubricsList = new SortedCDLinkedList<>();
        rubricsList.add(rmTeamRubric);
        rubricsList.add(rmIndividualRubric);
        rubricsList.add(oopTeamRubric);
        rubricsList.add(oopIndividualRubric);
        rubricsList.add(dsaTeamRubric);
        rubricsList.add(dsaIndividualRubric);
        rubricsList.add(seTeamRubric);

        SortedMapInterface<Integer, AssignmentRubrics> u1 = new SortedMap<>();
        u1.put(2301, rmIndividualRubric1);
        u1.put(2302, rmIndividualRubric2);
        u1.put(2303, rmIndividualRubric3);
        u1.put(2304, rmIndividualRubric5);

        SortedMapInterface<Integer, AssignmentRubrics> u2 = new SortedMap<>();
        u2.put(2305, rmIndividualRubric4);
        u2.put(2306, rmIndividualRubric5);
        u2.put(2307, rmIndividualRubric2);
        u2.put(2308, rmIndividualRubric6);

        SortedMapInterface<Integer, AssignmentRubrics> u3 = new SortedMap<>();
        u3.put(2309, rmIndividualRubric2);
        u3.put(2310, rmIndividualRubric1);
        u3.put(2311, rmIndividualRubric6);
        u3.put(2312, rmIndividualRubric4);

        SortedMapInterface<Integer, AssignmentRubrics> u4 = new SortedMap<>();
        u4.put(2313, rmIndividualRubric5);
        u4.put(2314, rmIndividualRubric5);
        u4.put(2315, rmIndividualRubric4);
        u4.put(2316, rmIndividualRubric2);

        SortedMapInterface<Integer, AssignmentRubrics> u5 = new SortedMap<>();
        u5.put(2317, rmIndividualRubric5);
        u5.put(2318, rmIndividualRubric5);
        u5.put(2319, rmIndividualRubric4);
        u5.put(2320, rmIndividualRubric2);

        AssignmentRubrics oopTeamRubric1 = new AssignmentRubrics("R003", "OOAD Team Rubric");
        oopTeamRubric1.addCriteria("Correctness and completeness of overview use case diagram", 5, 3);
        oopTeamRubric1.addCriteria("Correctness and completeness of initial/ analysis class diagram", 5, 2);
        oopTeamRubric1.addCriteria("Correctness and completeness of final class diagram.", 10, 8);
        oopTeamRubric1.addCriteria("Correctness and completeness of deployment diagram", 5, 4);
        oopTeamRubric1.addCriteria("Digital skill (Use of UML modelling tool)", 10, 9);
        oopTeamRubric1.complete();
        System.out.println(oopTeamRubric1);

        AssignmentRubrics oopTeamRubric2 = new AssignmentRubrics("R003", "OOAD Team Rubric");
        oopTeamRubric2.addCriteria("Correctness and completeness of overview use case diagram", 5, 1);
        oopTeamRubric2.addCriteria("Correctness and completeness of initial/ analysis class diagram", 5, 2);
        oopTeamRubric2.addCriteria("Correctness and completeness of final class diagram.", 10, 6);
        oopTeamRubric2.addCriteria("Correctness and completeness of deployment diagram", 5, 3);
        oopTeamRubric2.addCriteria("Digital skill (Use of UML modelling tool)", 10, 7);
        oopTeamRubric2.complete();
        System.out.println(oopTeamRubric2);

        AssignmentRubrics oopTeamRubric3 = new AssignmentRubrics("R003", "OOAD Team Rubric");
        oopTeamRubric3.addCriteria("Correctness and completeness of overview use case diagram", 5, 4);
        oopTeamRubric3.addCriteria("Correctness and completeness of initial/ analysis class diagram", 5, 3);
        oopTeamRubric3.addCriteria("Correctness and completeness of final class diagram.", 10, 2);
        oopTeamRubric3.addCriteria("Correctness and completeness of deployment diagram", 5, 1);
        oopTeamRubric3.addCriteria("Digital skill (Use of UML modelling tool)", 10, 5);
        oopTeamRubric3.complete();
        System.out.println(oopTeamRubric3);

        AssignmentRubrics oopTeamRubric4 = new AssignmentRubrics("R003", "OOAD Team Rubric");
        oopTeamRubric4.addCriteria("Correctness and completeness of overview use case diagram", 5, 2);
        oopTeamRubric4.addCriteria("Correctness and completeness of initial/ analysis class diagram", 5, 4);
        oopTeamRubric4.addCriteria("Correctness and completeness of final class diagram.", 10, 7);
        oopTeamRubric4.addCriteria("Correctness and completeness of deployment diagram", 5, 3);
        oopTeamRubric4.addCriteria("Digital skill (Use of UML modelling tool)", 10, 6);
        oopTeamRubric4.complete();
        System.out.println(oopTeamRubric4);

        AssignmentRubrics oopIndividualRubric1 = new AssignmentRubrics("R004", "OOAD Individual Rubric");
        oopIndividualRubric1.addCriteria("Problem statement and Functional requirements list", 5, 1);
        oopIndividualRubric1.addCriteria("Correctness and completeness of details use case diagrams + use case descriptions", 10, 2);
        oopIndividualRubric1.addCriteria("Correctness and completeness of activity diagrams based on the use case scenarios", 10, 3);
        oopIndividualRubric1.addCriteria("Correctness and completeness of interaction diagrams", 15, 4);
        oopIndividualRubric1.addCriteria("Correctness and completeness of state chart diagram(s) for any ONE problem domain classes", 5, 5);
        oopIndividualRubric1.addCriteria("Weekly interaction & Deliverables", 10, 6);
        oopIndividualRubric1.addCriteria("Final presentation", 10, 7);
        oopIndividualRubric1.complete();
        System.out.println(oopIndividualRubric1);

        AssignmentRubrics oopIndividualRubric2 = new AssignmentRubrics("R004", "OOAD Individual Rubric");
        oopIndividualRubric2.addCriteria("Problem statement and Functional requirements list", 5, 1);
        oopIndividualRubric2.addCriteria("Correctness and completeness of details use case diagrams + use case descriptions", 10, 2);
        oopIndividualRubric2.addCriteria("Correctness and completeness of activity diagrams based on the use case scenarios", 10, 3);
        oopIndividualRubric2.addCriteria("Correctness and completeness of interaction diagrams", 15, 4);
        oopIndividualRubric2.addCriteria("Correctness and completeness of state chart diagram(s) for any ONE problem domain classes", 5, 5);
        oopIndividualRubric2.addCriteria("Weekly interaction & Deliverables", 10, 6);
        oopIndividualRubric2.addCriteria("Final presentation", 10, 7);
        oopIndividualRubric2.complete();
        System.out.println(oopIndividualRubric2);

        AssignmentRubrics oopIndividualRubric3 = new AssignmentRubrics("R004", "OOAD Individual Rubric");
        oopIndividualRubric3.addCriteria("Problem statement and Functional requirements list", 5, 1);
        oopIndividualRubric3.addCriteria("Correctness and completeness of details use case diagrams + use case descriptions", 10, 2);
        oopIndividualRubric3.addCriteria("Correctness and completeness of activity diagrams based on the use case scenarios", 10, 3);
        oopIndividualRubric3.addCriteria("Correctness and completeness of interaction diagrams", 15, 4);
        oopIndividualRubric3.addCriteria("Correctness and completeness of state chart diagram(s) for any ONE problem domain classes", 5, 5);
        oopIndividualRubric3.addCriteria("Weekly interaction & Deliverables", 10, 6);
        oopIndividualRubric3.addCriteria("Final presentation", 10, 7);
        oopIndividualRubric3.complete();
        System.out.println(oopIndividualRubric3);

        AssignmentRubrics oopIndividualRubric4 = new AssignmentRubrics("R004", "OOAD Individual Rubric");
        oopIndividualRubric4.addCriteria("Problem statement and Functional requirements list", 5, 1);
        oopIndividualRubric4.addCriteria("Correctness and completeness of details use case diagrams + use case descriptions", 10, 2);
        oopIndividualRubric4.addCriteria("Correctness and completeness of activity diagrams based on the use case scenarios", 10, 3);
        oopIndividualRubric4.addCriteria("Correctness and completeness of interaction diagrams", 15, 4);
        oopIndividualRubric4.addCriteria("Correctness and completeness of state chart diagram(s) for any ONE problem domain classes", 5, 5);
        oopIndividualRubric4.addCriteria("Weekly interaction & Deliverables", 10, 6);
        oopIndividualRubric4.addCriteria("Final presentation", 10, 7);
        oopIndividualRubric4.complete();
        System.out.println(oopIndividualRubric4);

        AssignmentRubrics oopIndividualRubric5 = new AssignmentRubrics("R004", "OOAD Individual Rubric");
        oopIndividualRubric5.addCriteria("Problem statement and Functional requirements list", 5, 1);
        oopIndividualRubric5.addCriteria("Correctness and completeness of details use case diagrams + use case descriptions", 10, 2);
        oopIndividualRubric5.addCriteria("Correctness and completeness of activity diagrams based on the use case scenarios", 10, 3);
        oopIndividualRubric5.addCriteria("Correctness and completeness of interaction diagrams", 15, 4);
        oopIndividualRubric5.addCriteria("Correctness and completeness of state chart diagram(s) for any ONE problem domain classes", 5, 5);
        oopIndividualRubric5.addCriteria("Weekly interaction & Deliverables", 10, 6);
        oopIndividualRubric5.addCriteria("Final presentation", 10, 7);
        oopIndividualRubric5.complete();
        System.out.println(oopIndividualRubric5);

        AssignmentRubrics oopIndividualRubric6 = new AssignmentRubrics("R004", "OOAD Individual Rubric");
        oopIndividualRubric6.addCriteria("Problem statement and Functional requirements list", 5, 1);
        oopIndividualRubric6.addCriteria("Correctness and completeness of details use case diagrams + use case descriptions", 10, 2);
        oopIndividualRubric6.addCriteria("Correctness and completeness of activity diagrams based on the use case scenarios", 10, 3);
        oopIndividualRubric6.addCriteria("Correctness and completeness of interaction diagrams", 15, 4);
        oopIndividualRubric6.addCriteria("Correctness and completeness of state chart diagram(s) for any ONE problem domain classes", 5, 5);
        oopIndividualRubric6.addCriteria("Weekly interaction & Deliverables", 10, 6);
        oopIndividualRubric6.addCriteria("Final presentation", 10, 7);
        oopIndividualRubric6.complete();
        System.out.println(oopIndividualRubric6);

        SortedMapInterface<Integer, AssignmentRubrics> b1 = new SortedMap<>();
        b1.put(2301, oopIndividualRubric1);
        b1.put(2302, oopIndividualRubric2);
        b1.put(2303, oopIndividualRubric3);
        b1.put(2304, oopIndividualRubric5);

        SortedMapInterface<Integer, AssignmentRubrics> b2 = new SortedMap<>();
        b2.put(2305, oopIndividualRubric4);
        b2.put(2306, oopIndividualRubric5);
        b2.put(2307, oopIndividualRubric2);
        b2.put(2308, oopIndividualRubric6);

        SortedMapInterface<Integer, AssignmentRubrics> b3 = new SortedMap<>();
        b3.put(2309, oopIndividualRubric2);
        b3.put(2310, oopIndividualRubric1);
        b3.put(2311, oopIndividualRubric6);
        b3.put(2312, oopIndividualRubric4);

        SortedMapInterface<Integer, AssignmentRubrics> b4 = new SortedMap<>();
        b4.put(2313, oopIndividualRubric5);
        b4.put(2314, oopIndividualRubric5);
        b4.put(2315, oopIndividualRubric4);
        b4.put(2316, oopIndividualRubric2);

        SortedMapInterface<Integer, AssignmentRubrics> b5 = new SortedMap<>();
        b5.put(2317, oopIndividualRubric5);
        b5.put(2318, oopIndividualRubric5);
        b5.put(2319, oopIndividualRubric4);
        b5.put(2320, oopIndividualRubric2);

        SortedListInterface<AssignmentTeam> rmG1AssignmentTeamList = new SortedCDLinkedList<>();
        rmG1AssignmentTeamList.add(new AssignmentTeam(1, "BACS2042G1", memberList1, rm1TeamRubric));
        rmG1AssignmentTeamList.add(new AssignmentTeam(2, "BACS2042G1", memberList2, rm1TeamRubric1));
        rmG1AssignmentTeamList.add(new AssignmentTeam(3, "BACS2042G1", memberList3, rm1TeamRubric2));
        rmG1AssignmentTeamList.get(1).setIndividualRubricList(u1);
        rmG1AssignmentTeamList.get(2).setIndividualRubricList(u2);
        rmG1AssignmentTeamList.get(3).setIndividualRubricList(u3);
        System.out.println(rmG1AssignmentTeamList);

        SortedListInterface<AssignmentTeam> rmG2AssignmentTeamList = new SortedCDLinkedList<>();
        rmG2AssignmentTeamList.add(new AssignmentTeam(1, "BACS2042G2", memberList2, rm1TeamRubric3));
        rmG2AssignmentTeamList.add(new AssignmentTeam(2, "BACS2042G2", memberList5, rm1TeamRubric4));
        rmG2AssignmentTeamList.add(new AssignmentTeam(3, "BACS2042G2", memberList4, rm1TeamRubric5));
        rmG2AssignmentTeamList.get(1).setIndividualRubricList(u2);
        rmG2AssignmentTeamList.get(2).setIndividualRubricList(u5);
        rmG2AssignmentTeamList.get(3).setIndividualRubricList(u4);
        System.out.println(rmG2AssignmentTeamList);

        SortedListInterface<AssignmentTeam> ooadG1AssignmentTeamList = new SortedCDLinkedList<>();
        ooadG1AssignmentTeamList.add(new AssignmentTeam(1, "BACS2053G1", memberList1, oopTeamRubric1));
        ooadG1AssignmentTeamList.add(new AssignmentTeam(2, "BACS2053G1", memberList2, oopTeamRubric3));
        ooadG1AssignmentTeamList.add(new AssignmentTeam(3, "BACS2053G1", memberList3, oopTeamRubric4));
        ooadG1AssignmentTeamList.get(1).setIndividualRubricList(b1);
        ooadG1AssignmentTeamList.get(2).setIndividualRubricList(b2);
        ooadG1AssignmentTeamList.get(3).setIndividualRubricList(b3);
        System.out.println(ooadG1AssignmentTeamList);

        SortedListInterface<AssignmentTeam> ooadG2AssignmentTeamList = new SortedCDLinkedList<>();
        ooadG2AssignmentTeamList.add(new AssignmentTeam(1, "BACS2053G2", memberList5, oopTeamRubric1));
        ooadG2AssignmentTeamList.add(new AssignmentTeam(2, "BACS2053G2", memberList4, oopTeamRubric3));
        ooadG2AssignmentTeamList.add(new AssignmentTeam(3, "BACS2053G2", memberList2, oopTeamRubric4));
        ooadG2AssignmentTeamList.get(1).setIndividualRubricList(b5);
        ooadG2AssignmentTeamList.get(2).setIndividualRubricList(b4);
        ooadG2AssignmentTeamList.get(3).setIndividualRubricList(b2);
        System.out.println(ooadG2AssignmentTeamList);

        SortedListInterface<AssignmentTeam> ooadG3AssignmentTeamList = new SortedCDLinkedList<>();
        ooadG3AssignmentTeamList.add(new AssignmentTeam(1, "BACS2053G3", memberList4, oopTeamRubric3));
        ooadG3AssignmentTeamList.add(new AssignmentTeam(2, "BACS2053G3", memberList1, oopTeamRubric2));
        ooadG3AssignmentTeamList.add(new AssignmentTeam(3, "BACS2053G3", memberList3, oopTeamRubric3));
        ooadG3AssignmentTeamList.get(1).setIndividualRubricList(b4);
        ooadG3AssignmentTeamList.get(2).setIndividualRubricList(b1);
        ooadG3AssignmentTeamList.get(3).setIndividualRubricList(b3);
        System.out.println(ooadG3AssignmentTeamList);

        SortedListInterface<AssignmentTeam> ooadG4AssignmentTeamList = new SortedCDLinkedList<>();
        ooadG4AssignmentTeamList.add(new AssignmentTeam(1, "BACS2053G4", memberList5, oopTeamRubric4));
        ooadG4AssignmentTeamList.add(new AssignmentTeam(2, "BACS2053G4", memberList2, oopTeamRubric2));
        ooadG4AssignmentTeamList.add(new AssignmentTeam(3, "BACS2053G4", memberList1, oopTeamRubric1));
        ooadG4AssignmentTeamList.get(1).setIndividualRubricList(b5);
        ooadG4AssignmentTeamList.get(2).setIndividualRubricList(b2);
        ooadG4AssignmentTeamList.get(3).setIndividualRubricList(b1);
        System.out.println(ooadG4AssignmentTeamList);

        SortedListInterface<AssignmentTeam> dsaG1AssignmentTeamList = new SortedCDLinkedList<>();
        dsaG1AssignmentTeamList.add(new AssignmentTeam(1, "BACS2063G1", memberList1, dsaTeamRubric, dsaIndividualRubric));
        dsaG1AssignmentTeamList.add(new AssignmentTeam(2, "BACS2063G1", memberList2, dsaTeamRubric, dsaIndividualRubric));
        dsaG1AssignmentTeamList.add(new AssignmentTeam(3, "BACS2063G1", memberList3, dsaTeamRubric, dsaIndividualRubric));
        dsaG1AssignmentTeamList.add(new AssignmentTeam(4, "BACS2063G1", memberList3, dsaTeamRubric, dsaIndividualRubric));
        System.out.println(dsaG1AssignmentTeamList);

        SortedListInterface<AssignmentTeam> dsaG2AssignmentTeamList = new SortedCDLinkedList<>();
        dsaG2AssignmentTeamList.add(new AssignmentTeam(1, "BACS2063G2", memberList3, dsaTeamRubric, dsaIndividualRubric));
        dsaG2AssignmentTeamList.add(new AssignmentTeam(2, "BACS2063G2", memberList4, dsaTeamRubric, dsaIndividualRubric));
        dsaG2AssignmentTeamList.add(new AssignmentTeam(3, "BACS2063G2", memberList5, dsaTeamRubric, dsaIndividualRubric));
        System.out.println(dsaG2AssignmentTeamList);

        SortedListInterface<AssignmentTeam> dsaG3AssignmentTeamList = new SortedCDLinkedList<>();
        dsaG3AssignmentTeamList.add(new AssignmentTeam(1, "BACS2063G3", memberList2, dsaTeamRubric, dsaIndividualRubric));
        dsaG3AssignmentTeamList.add(new AssignmentTeam(2, "BACS2063G3", memberList3, dsaTeamRubric, dsaIndividualRubric));
        dsaG3AssignmentTeamList.add(new AssignmentTeam(3, "BACS2063G3", memberList4, dsaTeamRubric, dsaIndividualRubric));
        System.out.println(dsaG3AssignmentTeamList);

        SortedListInterface<AssignmentTeam> dsaG4AssignmentTeamList = new SortedCDLinkedList<>();
        dsaG4AssignmentTeamList.add(new AssignmentTeam(1, "BACS2063G4", memberList1, dsaTeamRubric, dsaIndividualRubric));
        dsaG4AssignmentTeamList.add(new AssignmentTeam(2, "BACS2063G4", memberList5, dsaTeamRubric, dsaIndividualRubric));
        dsaG4AssignmentTeamList.add(new AssignmentTeam(3, "BACS2063G4", memberList4, dsaTeamRubric, dsaIndividualRubric));
        System.out.println(dsaG4AssignmentTeamList);

        SortedListInterface<AssignmentTeam> dsaG5AssignmentTeamList = new SortedCDLinkedList<>();
        dsaG5AssignmentTeamList.add(new AssignmentTeam(1, "BACS2063G5", memberList2));
        dsaG5AssignmentTeamList.add(new AssignmentTeam(2, "BACS2063G5", memberList3));
        dsaG5AssignmentTeamList.add(new AssignmentTeam(3, "BACS2063G5", memberList4));
        System.out.println(dsaG5AssignmentTeamList);

        SortedListInterface<AssignmentTeam> seG1AssignmentTeamList = new SortedCDLinkedList<>();
        seG1AssignmentTeamList.add(new AssignmentTeam(1, "BACS2163G1", memberList1, seTeamRubric));
        seG1AssignmentTeamList.add(new AssignmentTeam(2, "BACS2163G1", memberList2, seTeamRubric));
        seG1AssignmentTeamList.add(new AssignmentTeam(3, "BACS2163G1", memberList3, seTeamRubric));
        System.out.println(seG1AssignmentTeamList);

        SortedListInterface<AssignmentTeam> seG2AssignmentTeamList = new SortedCDLinkedList<>();
        seG2AssignmentTeamList.add(new AssignmentTeam(1, "BACS2163G2", memberList5, seTeamRubric));
        seG2AssignmentTeamList.add(new AssignmentTeam(2, "BACS2163G2", memberList3, seTeamRubric));
        seG2AssignmentTeamList.add(new AssignmentTeam(3, "BACS2163G2", memberList2, seTeamRubric));
        System.out.println(seG2AssignmentTeamList);

        SortedListInterface<AssignmentTeam> seG3AssignmentTeamList = new SortedCDLinkedList<>();
        seG3AssignmentTeamList.add(new AssignmentTeam(1, "BACS2163G3", memberList5, seTeamRubric));
        seG3AssignmentTeamList.add(new AssignmentTeam(2, "BACS2163G3", memberList4, seTeamRubric));
        seG3AssignmentTeamList.add(new AssignmentTeam(3, "BACS2163G3", memberList1, seTeamRubric));
        System.out.println(seG3AssignmentTeamList);

        SortedListInterface<TutorialGroup> tutGroupList = new SortedCDLinkedList<>();
        tutGroupList.add(new TutorialGroup("BACS2042G1", "BACS2042", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2042G2", "BACS2042", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2053G1", "BACS2053", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2053G2", "BACS2053", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2053G3", "BACS2053", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2053G4", "BACS2053", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2063G1", "BACS2063", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2063G2", "BACS2063", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2063G3", "BACS2063", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2063G4", "BACS2063", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2063G5", "BACS2063", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2163G1", "BACS2163", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2163G2", "BACS2163", "DFT"));
        tutGroupList.add(new TutorialGroup("BACS2163G3", "BACS2163", "DFT"));
        System.out.println(tutGroupList);

        SortedListInterface<AssignmentTeam> assignmentTeamList = new SortedCDLinkedList<>();
        assignmentTeamList.merge(rmG1AssignmentTeamList);
        assignmentTeamList.merge(rmG2AssignmentTeamList);
        assignmentTeamList.merge(ooadG1AssignmentTeamList);
        assignmentTeamList.merge(ooadG2AssignmentTeamList);
        assignmentTeamList.merge(ooadG3AssignmentTeamList);
        assignmentTeamList.merge(ooadG4AssignmentTeamList);
        assignmentTeamList.merge(dsaG1AssignmentTeamList);
        assignmentTeamList.merge(dsaG2AssignmentTeamList);
        assignmentTeamList.merge(dsaG3AssignmentTeamList);
        assignmentTeamList.merge(dsaG4AssignmentTeamList);
        assignmentTeamList.merge(dsaG5AssignmentTeamList);
        assignmentTeamList.merge(seG1AssignmentTeamList);
        assignmentTeamList.merge(seG2AssignmentTeamList);
        assignmentTeamList.merge(seG3AssignmentTeamList);

        AssignmentRubrics aTeamRubric = new AssignmentRubrics("R008", "Teast Team Rubric");
        aTeamRubric.addCriteria("Understanding On Content", 7);
        aTeamRubric.addCriteria("Clear Communication", 2);
        AssignmentRubrics aIndividualRubric = new AssignmentRubrics("R009", "Test Individual Rubric");
        aIndividualRubric.addCriteria("Proper uniform", 1);
        assignmentTeamList.add(new AssignmentTeam(1, "BAAA1111G1", memberList1, aTeamRubric, aIndividualRubric));
        assignmentTeamList.add(new AssignmentTeam(2, "BAAA1111G1", memberList2, aTeamRubric, aIndividualRubric));
        courseList.add(new Course("BAAA1111", "Course For Testing"));
        tutGroupList.add(new TutorialGroup("BAAA1111G1", "BAAA1111", "DFT"));
        rubricsList.add(aTeamRubric);
        rubricsList.add(aIndividualRubric);

        AssignmentAssesmentDAO dao = new AssignmentAssesmentDAO();
        System.out.println(dao.saveToFile(courseList, "Course"));
        System.out.println(dao.saveToFile(rubricsList, "AssignmentRubrics"));
        System.out.println(dao.saveToFile(tutGroupList, "TutorialGroup"));
        System.out.println(dao.saveToFile(assignmentTeamList, "AssignmentTeam"));
    }
}
