/**
 * @author Loo Wee Kiat
 */
package control;

import adt.ArrayList;
import adt.ListInterface;
import adt.SortedListInterface;
import dao.Addutil;
import dao.TeachingAssignmentDAO;
import entity.Course;
import entity.Tutor;
import entity.TutorialGroup;
import java.util.Iterator;
import utility.InputUtil;
import utility.PrintUtil;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TeachingAssm {

    private TeachingAssignmentDAO dao = new TeachingAssignmentDAO();
    private SortedListInterface<Course> clist;
    private SortedListInterface<Tutor> tlist;
    private SortedListInterface<TutorialGroup> glist;
    private ArrayList<String> history = new ArrayList<>();
    private static int maxOutputWidth = 80;
    private static PrintUtil pt = new PrintUtil(maxOutputWidth);
    private static InputUtil ipt = new InputUtil(maxOutputWidth);
    int coursePlacement = 0;
    int tutorPlacement = 0;
    int tutGrpPlacement = 0;

    public TeachingAssm() {
        Addutil au = new Addutil();
//        clist = au.addCourse();
//        tlist = au.addTutor();
//        glist = au.addTutorialGroup();
        clist = dao.retrieveFromFile("Course.dat");
        tlist = dao.retrieveFromFile("Tutor.dat");
        glist = dao.retrieveFromFile("TutorialGroup.dat");
//        dao.saveToFile(clist, clist.size(), "Course.dat");
//        dao.saveToFile(tlist, tlist.size(), "Tutor.dat");
//        dao.saveToFile(glist, glist.size(), "TutorialGroup.dat");
    }
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    //utility
//    public void resetFile() {
//        dao.saveToFile(clist, clist.size(), "Course.dat");
//        dao.saveToFile(tlist, tlist.size(), "Tutor.dat");
//        dao.saveToFile(glist, glist.size(), "TutorialGroup.dat");
//    }
    public void listCourse() {
        System.out.printf("%-10s %-25s\n", "Course ID", "Course Name");
        System.out.println(clist);
    }

    public void listTutor() {
        System.out.printf("%-8s %-20s %-20s %-10s %-35s\n", "Tutor ID", "Tutor Name", "Tutor Email", "Course ID", "Course Name");
        System.out.println(tlist);
    }

    public void listTutorInfo() {
        Iterator<Tutor> it = tlist.getIterator();
        while (it.hasNext()) {
            Tutor tempTut = it.next();
            System.out.printf("%-8s %-20s %-20s\n", tempTut.getTutorID(), tempTut.getTutorName(), tempTut.getTutorEmail());
        }
    }

    public void listOneTutor(int num) {
        Tutor tempTut = tlist.get(num);
        System.out.printf("%-8s %-20s %-20s\n",
                tempTut.getTutorID(), tempTut.getTutorName(), tempTut.getTutorEmail());
    }

    public void listTutorialGroup() {
        for (int k = 1; k <= glist.size(); k++) {
            TutorialGroup tempTutotialGrp = glist.get(k);
            System.out.printf("%-3s %3s%dS%dG%d\n", tempTutotialGrp.getTutGrpID(), tempTutotialGrp.getProgrammeID(),
                    tempTutotialGrp.getYear(), tempTutotialGrp.getSem(),
                    tempTutotialGrp.getTutorialGrpNum());
        }
    }

    public void searchCourse(String courseID) {
        for (int i = 1; i <= clist.size(); i++) {
            if (clist.get(i).getCourseId().equals(courseID)) {
                System.out.println(clist.get(i) + "\n");
                coursePlacement = i;
            }
        }
    }

    public void searchTutor(String tutorID) {
        for (int j = 1; j <= tlist.size(); j++) {
            if (tlist.get(j).getTutorID().equals(tutorID)) {
                Tutor tempTut = tlist.get(j);
                System.out.printf("%-8s %-15s %-8s %-20s %-12s\n", tempTut.getTutorID(), tempTut.getTutorName(),
                        tempTut.getGender(), tempTut.getTutorEmail(), tempTut.getStudyLevel());
                tutorPlacement = j;
            }
        }
    }

    public void searchTutorialGroup(String tutGrpID) {
        for (int k = 1; k <= glist.size(); k++) {
            if (glist.get(k).getTutGrpID().contains(tutGrpID)) {
                TutorialGroup tempTutotialGrp = glist.get(k);
                System.out.printf("%3s%dS%dG%d\n", tempTutotialGrp.getProgrammeID(),
                        tempTutotialGrp.getYear(), tempTutotialGrp.getSem(),
                        tempTutotialGrp.getTutorialGrpNum());
                tutGrpPlacement = k;
            }
        }
    }

    //1
    public void assignTutorToCourse() {
        LocalDateTime now = LocalDateTime.now();
        boolean assigned = false;
        SortedListInterface<Course> course = tlist.get(tutorPlacement).getCourse();
        if (tlist.get(tutorPlacement).getCourse().contains(clist.get(coursePlacement))) {
            System.out.println("Tutor had assigned to this course");
            assigned = true;
        }
        if (assigned == false) {
            course.add(clist.get(coursePlacement));
            tlist.get(tutorPlacement).setCourse(course);
            System.out.print(tlist.get(tutorPlacement));
            dao.saveToFile(tlist, tlist.size(), "Tutor.dat");
        }
        history.add(String.format(dtf.format(now) + ":  Assigned %5s,%s tutor to %s\n", tlist.get(tutorPlacement).getTutorID(),
                tlist.get(tutorPlacement).getTutorName(), clist.get(coursePlacement)));
    }

    //2 and 3
    public void assignTutorialGrpToTutor() {
        LocalDateTime now = LocalDateTime.now();
        boolean assigned = false;
        SortedListInterface<Tutor> tutor = glist.get(tutGrpPlacement).getTutor();
        if (glist.get(tutGrpPlacement).getTutor().contains(tlist.get(tutorPlacement))) {
            System.out.println("Tutor had assigned to this class");
            assigned = true;
        }
        if (assigned == false) {
            tutor.add(tlist.get(tutorPlacement));
            glist.get(tutGrpPlacement).setTutor(tutor);
            System.out.println(glist.get(tutGrpPlacement));
            dao.saveToFile(glist, glist.size(), "TutorialGroup.dat");
        }
        history.add(String.format(dtf.format(now) + ":  Assigned %s%dS%dG%d tutorial group to %5s,%15s\n", glist.get(tutGrpPlacement).getProgrammeID(),
                glist.get(tutGrpPlacement).getYear(), glist.get(tutGrpPlacement).getSem(), glist.get(tutGrpPlacement).getTutorialGrpNum(),
                tlist.get(tutorPlacement).getTutorID(), tlist.get(tutorPlacement).getTutorName()));
    }

    //4
    public void searchCoursesUnderTutor() {
        SortedListInterface<Course> course = tlist.get(tutorPlacement).getCourse();
        System.out.print(tlist.get(tutorPlacement).getCourse());
    }

    public void searchTutorForCourse() {
        for (int j = 1; j <= tlist.size(); j++) {
            SortedListInterface<Course> course = tlist.get(j).getCourse();
            if (!(tlist.isEmpty()) && tlist.get(j).getCourse().contains(clist.get(coursePlacement))) {
                Tutor tempTut = tlist.get(j);
                System.out.printf("%-8s %-20s %-20s\n", tempTut.getTutorID(), tempTut.getTutorName(), tempTut.getTutorEmail());
            }
        }
    }

    public void listAllTutorForCourse() {
        for (int i = 1; i <= clist.size(); i++) {
            System.out.println(clist.get(i));
            for (int j = 1; j <= tlist.size(); j++) {
                if (!(tlist.isEmpty()) && tlist.get(j).getCourse().contains(clist.get(i))) {
                    Tutor tempTut = tlist.get(j);
                    System.out.printf("%-8s %-20s %-20s\n", tempTut.getTutorID(), tempTut.getTutorName(), tempTut.getTutorEmail());
                }
            }
            System.out.printf("\n");
        }
    }

    public void listAllCourseUnderTutor() {
        for (int i = 1; i <= tlist.size(); i++) {
            listOneTutor(i);
            for (int j = 1; j <= clist.size(); j++) {
                if (!(tlist.isEmpty()) && tlist.get(i).getCourse().contains(clist.get(j))) {
                    System.out.println(clist.get(j));
                }
            }
            System.out.printf("\n");
        }
    }

    public void listAllTutorialGroupUnderTutor() {
        for (int i = 1; i <= tlist.size(); i++) {
            listOneTutor(i);
            for (int j = 1; j <= glist.size(); j++) {
                if (glist.get(j).getTutor().contains(tlist.get(i))) {
                    TutorialGroup tempTutGrp = glist.get(j);
                    System.out.printf("%-8s\n", tempTutGrp.getProgrammeID() + tempTutGrp.getYear()
                            + "S" + tempTutGrp.getSem() + "G" + tempTutGrp.getTutorialGrpNum());
                }
            }
            System.out.printf("\n");
        }
    }

    //5
    public void filterTutorBasedGender() {
        ArrayList<String> male = new ArrayList<>();
        ArrayList<String> female = new ArrayList<>();
        for (int i = 1; i <= tlist.size(); i++) {
            if (tlist.get(i).getGender().equals("Male")) {
                male.add(String.format("%-8s %-15s %-20s %-10s", tlist.get(i).getTutorID(),
                        tlist.get(i).getTutorName(), tlist.get(i).getTutorEmail(),
                        tlist.get(i).getStudyLevel()));
            } else {
                female.add(String.format("%-8s %-15s %-20s %-10s", tlist.get(i).getTutorID(),
                        tlist.get(i).getTutorName(), tlist.get(i).getTutorEmail(),
                        tlist.get(i).getStudyLevel()));
            }
        }
        System.out.println("Male");
        System.out.println(male);
        System.out.println("Female");
        System.out.println(female);
    }

    public void filterTutorBasedStudyLevel() {
        ArrayList<String> degree = new ArrayList<>();
        ArrayList<String> master = new ArrayList<>();
        ArrayList<String> doctoral = new ArrayList<>();
        for (int i = 1; i <= tlist.size(); i++) {
            switch (tlist.get(i).getStudyLevel()) {
                case "Degree":
                    degree.add(String.format("%-8s %-15s %-8s %-20s", tlist.get(i).getTutorID(),
                            tlist.get(i).getTutorName(), tlist.get(i).getGender(),
                            tlist.get(i).getTutorEmail()));
                    break;
                case "Master":
                    master.add(String.format("%-8s %-15s %-8s %-20s", tlist.get(i).getTutorID(),
                            tlist.get(i).getTutorName(), tlist.get(i).getGender(),
                            tlist.get(i).getTutorEmail()));
                    break;
                case "Doctoral":
                    doctoral.add(String.format("%-8s %-15s %-8s %-20s", tlist.get(i).getTutorID(),
                            tlist.get(i).getTutorName(), tlist.get(i).getGender(),
                            tlist.get(i).getTutorEmail()));
                    break;
            }
        }
        System.out.println("Degree graduate");
        System.out.println(degree);
        System.out.println("Master Graduate");
        System.out.println(master);
        System.out.println("Doctoral Graduate");
        System.out.println(doctoral);
    }

    public void filterTutorNotAssignedCourse() {
        System.out.printf("%-8s %-15s %-8s %-20s %-12s\n",
                "Tutor ID", "Name", "Gender", "Email", "Study Level");
        for (int i = 1; i <= tlist.size(); i++) {
            if (tlist.get(i).getCourse().isEmpty()) {
                Tutor tempTut = tlist.get(i);
                System.out.printf("%-8s %-15s %-8s %-20s %-12s\n", tempTut.getTutorID(), tempTut.getTutorName(),
                        tempTut.getGender(), tempTut.getTutorEmail(), tempTut.getStudyLevel());

            }
        }
    }

    public void filterTutorTeachNCourse(int numCourse) {
        for (int i = 1; i <= tlist.size(); i++) {
            if (tlist.get(i).getCourse().size() > numCourse) {
                Tutor tempTut = tlist.get(i);
                System.out.printf("%-8s %-20s %-20s\n", tempTut.getTutorID(), tempTut.getTutorName(), tempTut.getTutorEmail());
            }
        }
    }

    public void filterTutorTeachNTutGrp(int numTutGrp) {
        for (int i = 1; i <= tlist.size(); i++) {
            int total = 0;
            for (int j = 1; j <= glist.size(); j++) {
                if (glist.get(j).getTutor().contains(tlist.get(i))) {
                    total++;
                    if (total > numTutGrp) {
                        Tutor tempTut = tlist.get(i);
                        System.out.printf("%-8s %-20s %-20s\n", tempTut.getTutorID(), tempTut.getTutorName(), tempTut.getTutorEmail());
                        break;
                    }
                }
            }

        }
    }

    //6
    public void dataOverview() {
        System.out.println("\nTotal number of Course         : " + clist.size());
        System.out.println("Total number of Tutor          : " + tlist.size());
        System.out.println("Total number of Tutorial Gorup : " + glist.size());
    }

    public void reportBasedTutor() {
        int totalTutorsForAllCourses = 0;
        //course
        int maxCourses = -1;
        int leastCourses = clist.size();
        int teachCourse = 0;
        //tutGrp
        int leastTutGrp = glist.size();
        int maxTutGrp = -1;
        int mostTeachTutGrp = -1;
        int leastTeachTutGrp = -1;
        ArrayList<String> mostTutGrpArr = new ArrayList<>();
        ArrayList<String> leastTutGrpArr = new ArrayList<>();

        //course avg
        for (int j = 1; j <= clist.size(); j++) {
            int tutorsPerCourse = 0;
            for (int i = 1; i <= tlist.size(); i++) {
                if (tlist.get(i).getCourse().contains(clist.get(j))) {
                    tutorsPerCourse++;
                }
            }
            totalTutorsForAllCourses += tutorsPerCourse;
        }

        //course most least
        for (int i = 1; i <= tlist.size(); i++) {
            int coursesByTutor = tlist.get(i).getCourse().size();

            if (coursesByTutor > maxCourses) {
                maxCourses = coursesByTutor;
            }
            if (coursesByTutor < leastCourses) {
                leastCourses = coursesByTutor;
            }
        }

        //tutGrp most least
        for (int q = 1; q <= tlist.size(); q++) {
            Tutor currentTutor = tlist.get(q);
            int tutGrpByTutor = 0;

            for (int k = 1; k <= glist.size(); k++) {
                if (glist.get(k).getTutor().contains(currentTutor)) {
                    tutGrpByTutor++;
                }
            }
            // Update mostTeachTutGrp if current tutor teaches more groups
            if (tutGrpByTutor > maxTutGrp) {
                maxTutGrp = tutGrpByTutor;
                mostTeachTutGrp = q;
            }
            // Update leastTeachTutGrp if current tutor teaches fewer groups
            if (tutGrpByTutor < leastTutGrp) {
                leastTutGrp = tutGrpByTutor;
                leastTeachTutGrp = q;
            }
        }

        //UI
        //avg
        double averageTutorsPerCourse = (double) totalTutorsForAllCourses / clist.size();
        System.out.println("\nAverage number of tutors per course: " + averageTutorsPerCourse);
        System.out.printf("\n");
        //most course
        System.out.printf("Tutor assigned to most course " + maxCourses + " :\n");
        for (int i = 1; i <= tlist.size(); i++) {
            if (tlist.get(i).getCourse().size() == maxCourses) {
                teachCourse = i;
                Tutor mostCourse = tlist.get(teachCourse);
                System.out.printf("%-8s %-20s %-20s\n", mostCourse.getTutorID(),
                        mostCourse.getTutorName(), mostCourse.getTutorEmail());
            }
        }

        //least course
        System.out.printf("\nTutor assigned to least course " + leastCourses + " :\n");
        for (int i = 1; i <= tlist.size(); i++) {
            if (tlist.get(i).getCourse().size() == leastCourses) {
                teachCourse = i;
                Tutor mostCourse = tlist.get(teachCourse);
                System.out.printf("%-8s %-20s %-20s\n", mostCourse.getTutorID(),
                        mostCourse.getTutorName(), mostCourse.getTutorEmail());
            }
        }
        //most tut grp
        for (int q = 1; q <= tlist.size(); q++) {
            Tutor currentTutor = tlist.get(q);
            int tutGrpByTutor = 0;

            for (int k = 1; k <= glist.size(); k++) {
                if (glist.get(k).getTutor().contains(currentTutor)) {
                    tutGrpByTutor++;
                } else {
                    ;
                }
            }

            if (tutGrpByTutor == maxTutGrp) {
                Tutor mostGroup = tlist.get(q);
                mostTutGrpArr.add(String.format("%-8s %-20s %-20s",
                        mostGroup.getTutorID(), mostGroup.getTutorName(),
                        mostGroup.getTutorEmail()));
            }

            if (tutGrpByTutor == leastTutGrp) {
                Tutor leastGroup = tlist.get(q);
                leastTutGrpArr.add(String.format("%-8s %-20s %-20s",
                        leastGroup.getTutorID(), leastGroup.getTutorName(),
                        leastGroup.getTutorEmail()));
            }
        }

        System.out.printf("\nTutor assigned to most tutorial group %-2d:\n", maxTutGrp);
        System.out.println(mostTutGrpArr);
        System.out.printf("Tutor assigned to least tutorial group %-2d:\n", leastTutGrp);
        System.out.println(leastTutGrpArr);
    }

    public void reportBasedCourse() {
        //tutor gender compare statistic under course
        int total;
        int male;
        int female;

        pt.printBox("Statistic based on tutors' gender in courses", "left", '|', '-');
        for (int i = 1; i <= clist.size(); i++) {
            System.out.println(clist.get(i));
            male = 0;
            female = 0;

            for (int j = 1; j <= tlist.size(); j++) {
                if (tlist.get(j).getCourse().contains(clist.get(i))) {
                    String gender = tlist.get(j).getGender();

                    if ("Male".equals(gender)) {
                        male++;
                    } else if ("Female".equals(gender)) {
                        female++;
                    }
                }
            }

            total = male + female;

            System.out.printf("Total Tutor in this course   : %d\n", total);
            System.out.printf("Male Tutor in this course   : %.2f%%\n", (double) male / total * 100.0);
            System.out.printf("Female Tutor in this course : %.2f%%\n\n", (double) female / total * 100.0);
        }

        pt.printBox("Statistic based on tutors' study level in courses", "left", '|', '-');
        //tutor study level compare statistic under each course
        int degree;
        int master;
        int doctoral;

        for (int i = 1; i <= clist.size(); i++) {
            degree = 0;
            master = 0;
            doctoral = 0;
            System.out.println(clist.get(i));

            for (int j = 1; j <= tlist.size(); j++) {
                if (tlist.get(j).getCourse().contains(clist.get(i))) {
                    String studyLevel = tlist.get(j).getStudyLevel();
                    switch (studyLevel) {
                        case "Degree":
                            degree++;
                            break;
                        case "Master":
                            master++;
                            break;
                        case "Doctoral":
                            doctoral++;
                            break;
                    }
                }
            }

            total = degree + master + doctoral;

            System.out.printf("%2d Degree teaching this course\n", degree);
            System.out.printf("%2d Master teaching this course\n", master);
            System.out.printf("%2d Doctoral teaching this course\n", doctoral);
            System.out.printf("Comparison Statistics : Degree %.2f%% Master %.2f%% Doctoral %.2f%% \n\n",
                    (double) degree / total * 100.0, (double) master / total * 100.0, (double) doctoral / total * 100.0);
        }

        //course teached by most tutor
        int maxTutor = 0;
        int maxTutorPlacement = 0;
        int minTutor = tlist.size();
        int minTutorPlacement = 0;
        int totalTutor;
        pt.printBox("Course teached by most and least tutor", "left", '|', '-');
        for (int i = 1; i <= clist.size(); i++) {
            totalTutor = 0;
            for (int j = 1; j <= tlist.size(); j++) {
                if (tlist.get(j).getCourse().contains(clist.get(i))) {
                    totalTutor++;
                }
            }
            if (totalTutor > maxTutor) {
                maxTutor = totalTutor;
                maxTutorPlacement = i;

            }
            if (totalTutor < minTutor) {
                minTutor = totalTutor;
                minTutorPlacement = i;
            }
        }
        System.out.printf("Course that have most tutor (" + maxTutor + "): " + clist.get(maxTutorPlacement) + "\n");
        System.out.printf("Course that have least tutor(" + minTutor + "): " + clist.get(minTutorPlacement) + "\n");
    }

    public void overallReport() {
        System.out.println(history);
    }

    public void resetFile() {
        //save
        Addutil au = new Addutil();
        clist = au.addCourse();
        tlist = au.addTutor();
        glist = au.addTutorialGroup();
        dao.saveToFile(clist, clist.size(), "Course.dat");
        dao.saveToFile(tlist, tlist.size(), "Tutor.dat");
        dao.saveToFile(glist, glist.size(), "TutorialGroup.dat");
    }
}
