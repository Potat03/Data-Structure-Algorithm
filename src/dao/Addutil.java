/**
 * @author Loo Wee Kiat
 */
package dao;

import adt.SortedCDLinkedList;
import adt.SortedListInterface;
import entity.Course;
import entity.Tutor;
import entity.TutorialGroup;

public class Addutil {

    public SortedListInterface<Course> addCourse() {
        SortedListInterface<Course> course = new SortedCDLinkedList();

        course.add(new Course("AACS1234", "English"));
        course.add(new Course("AACS4321", "Malaysia Issue"));
        course.add(new Course("AACS1928", "Sivik"));
        course.add(new Course("AACS1111", "Programming Concept"));

        return course;
    }

    public SortedListInterface<Tutor> addTutor() {
        SortedListInterface<Tutor> tutor = new SortedCDLinkedList();

        tutor.add(new Tutor("p2234", "Hoh Chif", "Male", "p2234@tarc.edu.my", "Degree"));
        tutor.add(new Tutor("p2345", "Nicola Lee", "Female", "p2345@tarc.edu.my", "Master"));
        tutor.add(new Tutor("p2734", "Chua E Ric", "Male", "p2734@tarc.edu.my", "Master"));
        tutor.add(new Tutor("p2873", "Khoo Nam Lim", "Male", "p2873@tarc.edu.my", "Master"));
        tutor.add(new Tutor("p2193", "Teh Li Poh", "Male", "p2193@tarc.edu.my", "Doctoral"));
        tutor.add(new Tutor("p2657", "Natasha", "Female", "p2657@tarc.edu.my", "Master"));
        tutor.add(new Tutor("p2436", "Harimun", "Female", "p2436@tarc.edu.my", "Degree"));
        tutor.add(new Tutor("p2216", "Jenny", "Female", "p2216@tarc.edu.my", "Degree"));

        return tutor;
    }

    public SortedListInterface<TutorialGroup> addTutorialGroup() {
        SortedListInterface<TutorialGroup> tutorialGrp = new SortedCDLinkedList();

        tutorialGrp.add(new TutorialGroup("1", "RSD", 1, 1, 1));
        tutorialGrp.add(new TutorialGroup("2", "RSD", 1, 1, 2));
        tutorialGrp.add(new TutorialGroup("3", "RSD", 1, 1, 3));
        tutorialGrp.add(new TutorialGroup("4", "RSD", 1, 1, 4));
        tutorialGrp.add(new TutorialGroup("5", "RSD", 2, 1, 1));
        tutorialGrp.add(new TutorialGroup("6", "RSD", 1, 2, 1));
        tutorialGrp.add(new TutorialGroup("7", "RIS", 1, 1, 1));
        tutorialGrp.add(new TutorialGroup("8", "RIS", 1, 1, 2));
        tutorialGrp.add(new TutorialGroup("9", "RIS", 1, 1, 3));

        return tutorialGrp;
    }

}
