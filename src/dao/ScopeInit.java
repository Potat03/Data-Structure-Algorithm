/**
 * @author Nicholas Yap Jia Wey
 */
package dao;

import adt.*;
import entity.*;

public class ScopeInit {

    public SortedListInterface<Course> initCourse() {
        //course
        SortedListInterface<Course> cList = new SortedCDLinkedList<>();
        cList.add(new Course("AACS2284", "Operating Systems"));
        cList.add(new Course("BACS2063", "Data Structure and Algorithms"));
        ElementsDAO file = new ElementsDAO();
        file.saveToFile(cList, "CourseForScope.dat");
        return cList;
    }

    public SortedListInterface<AssignmentTeam> initializeTeams() {
        //scope
        SortedMapInterface<Integer, AssignmentScope> sList = new SortedMap<>();
        sList.put(1, new AssignmentScope("tutor subsystem"));
        sList.put(2, new AssignmentScope("student subsystem"));
        sList.put(3, new AssignmentScope("course subsystem"));

        SortedMapInterface<Integer, AssignmentScope> sList2 = new SortedMap<>();
        sList2.put(1, new AssignmentScope("cataloging subsystem"));
        sList2.put(2, new AssignmentScope("circulation subsystem"));
        sList2.put(3, new AssignmentScope("acquisitions subsystem"));

        SortedMapInterface<Integer, AssignmentScope> sList3 = new SortedMap<>();
        sList3.put(1, new AssignmentScope("proposals subsystem"));
        sList3.put(2, new AssignmentScope("lab management subsystem"));
        sList3.put(3, new AssignmentScope("data analysis subsystem"));

        SortedMapInterface<Integer, AssignmentScope> sList4 = new SortedMap<>();
        sList4.put(1, new AssignmentScope("tutor subsystem"));
        sList4.put(2, new AssignmentScope("student subsystem"));
        sList4.put(3, new AssignmentScope("course subsystem"));

        SortedMapInterface<Integer, AssignmentScope> sList5 = new SortedMap<>();
        sList5.put(1, new AssignmentScope("job posting subsystem"));
        sList5.put(2, new AssignmentScope("interviews subsystem"));
        sList5.put(3, new AssignmentScope("onboarding subsystem"));

        SortedMapInterface<Integer, AssignmentScope> sList6 = new SortedMap<>();
        sList6.put(1, new AssignmentScope("proposals subsystem"));
        sList6.put(2, new AssignmentScope("lab management subsystem"));
        sList6.put(3, new AssignmentScope("report subsystem"));

        SortedMapInterface<Integer, AssignmentScope> sList7 = new SortedMap<>();
        sList7.put(1, new AssignmentScope("tutor subsystem"));
        sList7.put(2, new AssignmentScope("rubrics subsystem"));
        sList7.put(3, new AssignmentScope("course subsystem"));

        SortedMapInterface<Integer, AssignmentScope> sList8 = new SortedMap<>();
        sList8.put(1, new AssignmentScope("cataloging subsystem"));
        sList8.put(2, new AssignmentScope("circulation subsystem"));
        sList8.put(3, new AssignmentScope("borrowing subsystem"));

        AssignmentScope s1 = new AssignmentScope("school system");
        AssignmentScope s2 = new AssignmentScope("library management");
        AssignmentScope s3 = new AssignmentScope("research projects");
        AssignmentScope s4 = new AssignmentScope("school system");
        AssignmentScope s5 = new AssignmentScope("staff recruitment");
        AssignmentScope s6 = new AssignmentScope("research projects");
        AssignmentScope s7 = new AssignmentScope("school system");
        AssignmentScope s8 = new AssignmentScope("library management");
        //rubrics
        //team 1
        SortedMapInterface<Integer, AssignmentRubrics> rList = new SortedMap<>();
        AssignmentRubrics r1 = new AssignmentRubrics("test1");
        r1.addCriteria("first", 20, 10);
        AssignmentRubrics r2 = new AssignmentRubrics("test2");
        r2.addCriteria("first", 20, 15);
        AssignmentRubrics r3 = new AssignmentRubrics("test3");
        r3.addCriteria("first", 20, 20);
        rList.put(1, r1);
        rList.put(2, r2);
        rList.put(3, r3);

        //team 2
        SortedMapInterface<Integer, AssignmentRubrics> rList2 = new SortedMap<>();
        AssignmentRubrics r4 = new AssignmentRubrics("test1");
        r4.addCriteria("second", 20, 16);
        AssignmentRubrics r5 = new AssignmentRubrics("test2");
        r5.addCriteria("second", 20, 17);
        AssignmentRubrics r6 = new AssignmentRubrics("test3");
        r6.addCriteria("second", 20, 15);
        rList2.put(1, r4);
        rList2.put(2, r5);
        rList2.put(3, r6);

        //team 3
        SortedMapInterface<Integer, AssignmentRubrics> rList3 = new SortedMap<>();
        AssignmentRubrics r7 = new AssignmentRubrics("test1");
        r7.addCriteria("third", 20, 19);
        AssignmentRubrics r8 = new AssignmentRubrics("test2");
        r8.addCriteria("third", 20, 17);
        AssignmentRubrics r9 = new AssignmentRubrics("test3");
        r9.addCriteria("third", 20, 17);
        rList3.put(1, r7);
        rList3.put(2, r8);
        rList3.put(3, r9);

        //team 4
        SortedMapInterface<Integer, AssignmentRubrics> rList4 = new SortedMap<>();
        AssignmentRubrics r10 = new AssignmentRubrics("test1");
        r10.addCriteria("third", 20, 19);
        AssignmentRubrics r11 = new AssignmentRubrics("test2");
        r11.addCriteria("third", 20, 20);
        AssignmentRubrics r12 = new AssignmentRubrics("test3");
        r12.addCriteria("third", 20, 17);
        rList4.put(1, r10);
        rList4.put(2, r11);
        rList4.put(3, r12);

        //team 5
        SortedMapInterface<Integer, AssignmentRubrics> rList5 = new SortedMap<>();
        AssignmentRubrics r13 = new AssignmentRubrics("test1");
        r13.addCriteria("third", 20, 13);
        AssignmentRubrics r14 = new AssignmentRubrics("test2");
        r14.addCriteria("third", 20, 17);
        AssignmentRubrics r15 = new AssignmentRubrics("test3");
        r15.addCriteria("third", 20, 18);
        rList5.put(1, r7);
        rList5.put(2, r8);
        rList5.put(3, r9);

        //team 6
        SortedMapInterface<Integer, AssignmentRubrics> rList6 = new SortedMap<>();
        AssignmentRubrics r16 = new AssignmentRubrics("test1");
        r16.addCriteria("third", 20, 19);
        AssignmentRubrics r17 = new AssignmentRubrics("test2");
        r17.addCriteria("third", 20, 17);
        AssignmentRubrics r18 = new AssignmentRubrics("test3");
        r18.addCriteria("third", 20, 17);
        rList6.put(1, r16);
        rList6.put(2, r17);
        rList6.put(3, r18);

        //team 7
        SortedMapInterface<Integer, AssignmentRubrics> rList7 = new SortedMap<>();
        AssignmentRubrics r19 = new AssignmentRubrics("test1");
        r19.addCriteria("third", 20, 15);
        AssignmentRubrics r20 = new AssignmentRubrics("test2");
        r20.addCriteria("third", 20, 20);
        AssignmentRubrics r21 = new AssignmentRubrics("test3");
        r21.addCriteria("third", 20, 14);
        rList7.put(1, r19);
        rList7.put(2, r20);
        rList7.put(3, r21);

        //team 8
        SortedMapInterface<Integer, AssignmentRubrics> rList8 = new SortedMap<>();
        AssignmentRubrics r22 = new AssignmentRubrics("test1");
        r22.addCriteria("third", 20, 0);
        AssignmentRubrics r23 = new AssignmentRubrics("test2");
        r23.addCriteria("third", 20, 0);
        AssignmentRubrics r24 = new AssignmentRubrics("test3");
        r24.addCriteria("third", 20, 0);
        rList8.put(1, r22);
        rList8.put(2, r23);
        rList8.put(3, r24);

        //team 9
        SortedMapInterface<Integer, AssignmentRubrics> rList9 = new SortedMap<>();
        AssignmentRubrics r25 = new AssignmentRubrics("test1");
        r25.addCriteria("third", 20, 0);
        AssignmentRubrics r26 = new AssignmentRubrics("test2");
        r26.addCriteria("third", 20, 0);
        AssignmentRubrics r27 = new AssignmentRubrics("test3");
        r27.addCriteria("third", 20, 0);
        rList9.put(1, r25);
        rList9.put(2, r26);
        rList9.put(3, r27);

        //team scope
        AssignmentRubrics r28 = new AssignmentRubrics("test3");
        r28.addCriteria("third", 40, 34);
        AssignmentRubrics r29 = new AssignmentRubrics("test3");
        r29.addCriteria("third", 40, 10);
        AssignmentRubrics r30 = new AssignmentRubrics("test3");
        r30.addCriteria("third", 40, 25);
        AssignmentRubrics r31 = new AssignmentRubrics("test3");
        r31.addCriteria("third", 40, 27);
        AssignmentRubrics r32 = new AssignmentRubrics("test3");
        r32.addCriteria("third", 40, 26);
        AssignmentRubrics r33 = new AssignmentRubrics("test3");
        r33.addCriteria("third", 40, 37);
        AssignmentRubrics r34 = new AssignmentRubrics("test3");
        r34.addCriteria("third", 40, 40);
        AssignmentRubrics r35 = new AssignmentRubrics("test3");
        r35.addCriteria("third", 40, 35);
        AssignmentRubrics r36 = new AssignmentRubrics("test3");
        r36.addCriteria("third", 40, 0);

        //students
        ListInterface<Student> pList = new ArrayList<>();
        pList.add(new Student(1, "John"));
        pList.add(new Student(2, "Ken"));
        pList.add(new Student(3, "Keith"));

        ListInterface<Student> pList2 = new ArrayList<>();
        pList2.add(new Student(4, "Leon"));
        pList2.add(new Student(5, "Bryan"));
        pList2.add(new Student(6, "Casper"));

        ListInterface<Student> pList3 = new ArrayList<>();
        pList3.add(new Student(7, "Eleanor"));
        pList3.add(new Student(8, "Felix"));
        pList3.add(new Student(9, "Grace"));

        ListInterface<Student> pList4 = new ArrayList<>();
        pList4.add(new Student(10, "Alice"));
        pList4.add(new Student(11, "Bob"));
        pList4.add(new Student(12, "Charlie"));

        ListInterface<Student> pList5 = new ArrayList<>();
        pList5.add(new Student(13, "David"));
        pList5.add(new Student(14, "Eva"));
        pList5.add(new Student(15, "Frank"));

        ListInterface<Student> pList6 = new ArrayList<>();
        pList6.add(new Student(16, "Grace"));
        pList6.add(new Student(17, "Hannah"));
        pList6.add(new Student(18, "Ivy"));

        ListInterface<Student> pList7 = new ArrayList<>();
        pList7.add(new Student(19, "Jacob"));
        pList7.add(new Student(20, "Katherine"));
        pList7.add(new Student(21, "Liam"));

        ListInterface<Student> pList8 = new ArrayList<>();
        pList8.add(new Student(22, "Mia"));
        pList8.add(new Student(23, "Noah"));
        pList8.add(new Student(24, "Olivia"));

        ListInterface<Student> pList9 = new ArrayList<>();
        pList9.add(new Student(25, "Ethan"));
        pList9.add(new Student(26, "Sophia"));
        pList9.add(new Student(27, "Latina"));

        //assignment team
        SortedListInterface<AssignmentTeam> assmList = new SortedCDLinkedList<>();
        assmList.add(new AssignmentTeam(1, "1", pList, r34, rList, s1, sList));
        assmList.add(new AssignmentTeam(2, "1", pList2, r35, rList2, s2, sList2));
        assmList.add(new AssignmentTeam(3, "1", pList3, r36, rList3, s3, sList3));
        assmList.add(new AssignmentTeam(4, "2", pList4, r34, rList4, s4, sList4));
        assmList.add(new AssignmentTeam(5, "2", pList5, r35, rList5, s5, sList5));
        assmList.add(new AssignmentTeam(6, "2", pList6, r36, rList6, s6, sList6));
        assmList.add(new AssignmentTeam(7, "3", pList7, r34, rList7, s7, sList7));
        assmList.add(new AssignmentTeam(8, "3", pList8, r35, rList8, s8, sList8));
        assmList.add(new AssignmentTeam(9, "3", pList9, r36, rList9));

        ElementsDAO file = new ElementsDAO();
        file.saveToFile(assmList, "AssmTeamForScope.dat");

        return assmList;
    }

    public SortedListInterface<TutorialGroup> initGroup() {
        //tutorial group
        SortedListInterface<TutorialGroup> tList = new SortedCDLinkedList<>();
        tList.add(new TutorialGroup("1", "RSD", "AACS2284", 2023, 1, 1));
        tList.add(new TutorialGroup("2", "RSD", "AACS2284", 2023, 1, 2));
        tList.add(new TutorialGroup("3", "RSD", "AACS2284", 2023, 1, 3));
        tList.add(new TutorialGroup("4", "RSD", "AACS2284", 2023, 1, 4));
        tList.add(new TutorialGroup("5", "RSD", "BACS2063", 2023, 1, 1));
        tList.add(new TutorialGroup("6", "RSD", "BACS2063", 2023, 1, 2));
        tList.add(new TutorialGroup("7", "RSD", "BACS2063", 2023, 1, 3));
        tList.add(new TutorialGroup("8", "RSD", "BACS2063", 2023, 1, 4));

        ElementsDAO file = new ElementsDAO();
        file.saveToFile(tList, "GroupForScope.dat");

        return tList;
    }
}
