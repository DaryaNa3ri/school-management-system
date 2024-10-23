package menu;

import util.ApplicationContext;
import util.Util;
import util.printer.Printer;

public class CourseMenu {
    public static void courseMenu() {

        boolean exit = false;
        while (!exit) {
            Printer.print(" > > > > >   COURSE MENU   < < < < < ");
            Printer.print("     1.      add course\n" +
                    "     2.      update student\n" +
                    "     3.      delete student\n" +
                    "     4.      show student\n" +
                    "     -1.     quit");

            int choice = Util.getIntInput(">> please enter your choice:");

            switch (choice) {
                case 1:
                    addMenu();
                    break;
                case 2:
                    updateMenu();
                    break;
                case 3:
                    deleteMenu();
                    break;
                case 4:
                    showMenu();
                    break;
                case -1:
                    exit = false;
            }
        }
    }

    public static void addMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   ADD COURSE MENU   < < < < < ");
            Printer.print(
                    "     1.      add new course\n" +
                            "     2.      add course in exam\n" +
                            "     3.      add course in teacher\n" +
                            "     4.      add course in student\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");
            switch (choice) {
                case 1:
                    Printer.print("> > > > Please enter information: ");
                    String title = Util.getStringInput(">> please enter the title: ");
                    Integer unit = Util.getIntInput(">> please enter the unit of course: ");
                    ApplicationContext.getCourseService().addNew(title,unit);
                    Printer.print("new student added successfully :)");
                    break;
                case 2:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer studentId = Util.getIntInput(">> Please enter student id: ");
                    Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
                    ApplicationContext.getExamService().showAll();
                    Integer examId = Util.getIntInput(">> Please enter exam id: ");
                    Integer grade = Util.getIntInput(">> please enter the grade of the exam: ");
                    ApplicationContext.getStudentService().addStudentInStudentExamTable(studentId, examId,grade);
                    Printer.print("student added successfully :)");
                    break;
                case 3:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer teacherId = Util.getIntInput(">> Please enter teacher id: ");
                    Printer.print(" > > > > >   ALL COURSE   < < < < < ");
                    ApplicationContext.getCourseService().showAll();
                    Integer courseId = Util.getIntInput(">> Please enter course id: ");
                    ApplicationContext.getCourseService().addCourseToTeacher(courseId, teacherId);
                    Printer.print("course set successfully :)");
                    break;
                case 4:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer studentId2 = Util.getIntInput(">> Please enter student id: ");
                    Printer.print(" > > > > >   ALL COURSES   < < < < < ");
                    ApplicationContext.getCourseService().showAll();
                    Integer courseId1 = Util.getIntInput(">> Please enter course id: ");
                    ApplicationContext.getStudentService().addStudentInStudentCourseTable(studentId2, courseId1);
                    Printer.print("student added successfully :)");
                    break;
                case -1:
                    flag = false;
            }
        }


    }
    public static void updateMenu() {

    }
    public static void deleteMenu() {

    }
    public static void showMenu() {

    }
}
