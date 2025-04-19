/**
 * @author Loo Wee Kiat
 */
package boundary;

import control.TeachingAssm;
import utility.PrintUtil;
import utility.WKInputUtil;

public class TeachingAssignmentUI {

    private static int maxOutputWidth = 80;
    private static PrintUtil pt = new PrintUtil(maxOutputWidth);
    private static WKInputUtil ipt = new WKInputUtil(maxOutputWidth);
    private static TeachingAssm ta = new TeachingAssm();

//    public static void main(String[] args) {
//        //reset file
//        ta.resetFile();
//    }

    public static void main(String[] args) {
        TeachingAssignmentUI ui = new TeachingAssignmentUI();
        ui.startUI();
    }
    
    public void startUI(){
        int choice = 0;
        while (choice != 7) {
            pt.printBox("Teaching Assingment", "center", '|', '-');
            pt.printCenter("--- Input the option ---");
            pt.printBox("1. Assign Tutor to Course        \n"
                    + "2. Assign Tutor to Tutorial Group\n"
                    + "3. Assign Tutorial Group to Tutor\n"
                    + "4. Search and List               \n"
                    + "5. Filter tutor                  \n"
                    + "6. Generate Report               \n"
                    + "7. Exit                          \n", "center", "center", '|', '-', 45, 80);
            choice = ipt.inputInt("Enter your choice : ", "Integer Please", "");

            switch (choice) {
                case 1:
                    assignCourse();
                    break;
                case 2:
                    assignTutorToTutGrp();
                    break;
                case 3:
                    assignTutorialGrp();
                    break;
                case 4:
                    searchAndList();
                    break;
                case 5:
                    filter();
                    break;
                case 6:
                    generateReport();
                    break;
                case 7:
                    break;
            }
        }
    }

    //1
    public static void assignCourse() {
        char cont = 'Y';
        while (Character.toUpperCase(cont) == 'Y') {
            pt.printBox("Assign Course To Tutor", "left", '|', '-');
            //course part
            ta.listCourse();
            String courseID = ipt.inputString("Enter Course ID (AACS1234): ", "Course ID Please", "");
            ta.searchCourse(courseID);
            //tutor part
            ta.listTutorInfo();
            String tutorID = ipt.inputString("Enter Tutor ID (p1234): ", "Tutor ID Please", "");
            ta.searchTutor(tutorID);
            char confirmation = ipt.inputChar("Confirm Assign?(Y=yes/N=no) : ", "Y / N Please", "");
            if (Character.toUpperCase(confirmation) == 'Y') //list tutor
            {
                ta.assignTutorToCourse();
            }
            cont = ipt.inputChar("Add another tutor to course? : ", "Y/N Please", "");
        }
    }

    //2
    public static void assignTutorToTutGrp() {
        char cont = 'Y';
        while (Character.toUpperCase(cont) == 'Y') {
            pt.printBox("Assign Tutor to Tutorial group", "left", '|', '-');
            //tutor part
            System.out.printf("%-8s %-20s %-20s\n", "Tutor ID", "Tutor Name", "Tutor Email");
            ta.listTutorInfo();
            String tutorID = ipt.inputString("Enter Tutor ID (p1234): ", "Tutor ID Please", "");
            ta.searchTutor(tutorID);
            System.out.printf("\n");
            //tutorial group part
            System.out.printf("%-15s\n", "Tutorial Group");
            ta.listTutorialGroup();
            String tutGrpSelect = ipt.inputString("Enter Tutorial Group (1,2,3): ", "Tutorial Group Please", "");
            ta.searchTutorialGroup(tutGrpSelect);
            char confirmation = ipt.inputChar("Confirm Assign?(Y=yes/N=no) : ", "Y / N Please", "");
            if (Character.toUpperCase(confirmation) == 'Y') {
                ta.assignTutorialGrpToTutor();
            }
            cont = ipt.inputChar("Add another Tutor to Group? : ", "Y/N Please", "");
        }
    }

    //3
    public static void assignTutorialGrp() {
        char cont = 'Y';
        while (Character.toUpperCase(cont) == 'Y') {
            pt.printBox("Assign Tutorial Group to Tutor", "left", '|', '-');
            System.out.printf("%-15s\n", "Tutorial Group");
            //tutorial group part
            ta.listTutorialGroup();
            String tutGrpSelect = ipt.inputString("Enter Tutorial Group (1,2,3): ", "Tutorial Group Please", "");
            ta.searchTutorialGroup(tutGrpSelect);
            System.out.printf("\n");
            //tutor part
            System.out.printf("%-8s %-20s %-20s\n", "Tutor ID", "Tutor Name", "Tutor Email");
            ta.listTutorInfo();
            String tutorID = ipt.inputString("Enter Tutor ID (p1234): ", "Tutor ID Please", "");
            ta.searchTutor(tutorID);
            char confirmation = ipt.inputChar("Confirm Assign?(Y=yes/N=no) : ", "Y / N Please", "");
            if (Character.toUpperCase(confirmation) == 'Y') {
                ta.assignTutorialGrpToTutor();
            }
            cont = ipt.inputChar("Add another Group to Tutor? : ", "Y/N Please", "");
        }
    }

    public static void searchAndList() {
        int choice = 0;
        while (choice != 6) {
            pt.printBox("Search and List", "left", '|', '-');
            pt.printCenter("--- Input the option ---");
            pt.printBox("1. Search Courses under tutor      \n"
                    + "2. Search Tutor for course        \n"
                    + "3. List all tutor for courses     \n"
                    + "4. List all course under tutors   \n"
                    + "5. List Tutorial Group under tutor\n"
                    + "6. Back                           \n", "center", "center", '|', '-', 45, 80);
            choice = ipt.inputInt("Enter your choice : ", "Integer Please", "");

            switch (choice) {
                case 1:
                    pt.printBox("Search Courses under Tutor", "center", '|', '-');
                    ta.listTutorInfo();
                    String tutorID = ipt.inputString("Enter Tutor ID (p1234): ", "Tutor ID Please", "");
                    ta.searchTutor(tutorID);
                    System.out.printf("\n");
                    pt.printFrame("Course Under :  " + tutorID, "left", '|', '-');
                    ta.searchCoursesUnderTutor();
                    break;
                case 2:
                    pt.printBox("Search Tutor for course", "center", '|', '-');
                    ta.listCourse();
                    String courseID = ipt.inputString("Enter Course ID (AACS1234): ", "Tutor ID Please", "");
                    ta.searchCourse(courseID);
                    System.out.printf("\n");
                    pt.printFrame("Tutor Under :  " + courseID, "left", '|', '-');
                    ta.searchTutorForCourse();
                    break;
                case 3:
                    ta.listAllTutorForCourse();
                    break;
                case 4:
                    ta.listAllCourseUnderTutor();
                    break;
                case 5:
                    ta.listAllTutorialGroupUnderTutor();
                    break;
                case 6:
                    return;
            }
        }
    }

    public static void filter() {
        int choice = 0;
        while (choice != 6) {
            pt.printBox("Filter Tutor", "left", '|', '-');
            pt.printCenter("--- Input the option ---");
            pt.printBox("1. Tutor based on gender                     \n"
                    + "2. Tutor based on study level                \n"
                    + "3. Tutor haven't assigned to course          \n"
                    + "4. Tutor teached more than N course          \n"
                    + "5. Tutor teached more than N tutorial gorup  \n"
                    + "6. Back                                      \n", "center", "center", '|', '-', 45, 80);
            choice = ipt.inputInt("Enter your choice : ", "Integer Please", "");

            switch (choice) {
                case 1:
                    ta.filterTutorBasedGender();
                    break;
                case 2:
                    ta.filterTutorBasedStudyLevel();
                    break;
                case 3:
                    ta.filterTutorNotAssignedCourse();
                    break;
                case 4:
                    int numCourse = ipt.inputInt("Tutor teached more than : ", "Integer Please", "");
                    System.out.printf("\n");
                    ta.filterTutorTeachNCourse(numCourse);
                    break;
                case 5:
                    int numtutGrp = ipt.inputInt("Tutor teached more than : ", "Integer Please", "");
                    System.out.printf("\n");
                    ta.filterTutorTeachNTutGrp(numtutGrp);
                    break;
                case 6:
                    return;
            }
        }

    }

    public static void generateReport() {

        int choice = 0;
        while (choice != 5) {
            pt.printBox("Generate Report", "left", '|', '-');
            pt.printCenter("--- Input the option ---");
            pt.printBox("1. Data overview                 \n"
                    + "2. Report based on tutor         \n"
                    + "3. Report based on Course        \n"
                    + "4. History                       \n"
                    + "5. Back                          \n", "center", "center", '|', '-', 45, 80);
            choice = ipt.inputInt("Enter your choice : ", "Integer Please", "");

            switch (choice) {
                case 1:
                    ta.dataOverview();
                    break;
                case 2:
                    ta.reportBasedTutor();
                    break;
                case 3:
                    ta.reportBasedCourse();
                    break;
                case 4:
                    ta.overallReport();
                    break;
                case 5:
                    return;
            }
        }
    }

}
