package trash;

import model.Course;
import util.ApplicationContext;
import util.Util;
import util.printer.Printer;

public class TeacherMenu {


    public static void teacherMenu() {
        boolean exit = false;
        while (!exit) {
            Printer.print(" > > > > >   TEACHER MENU   < < < < < ");
            Printer.print("     1.      add teacher\n" +
                    "     2.      update teacher\n" +
                    "     3.      delete teacher\n" +
                    "     4.      show teacher\n" +
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
                    exit = true;
            }//end of switch
        }//end of while
    }//end of method

    public static void addMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   ADD TEACHER MENU   < < < < < ");
            Printer.print(
                    "     1.      add new teacher\n" +
                            "     2.      add teacher in student\n" +
                            "     3.      add teacher in course\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");

            switch (choice) {
                case 1:
                    Printer.print("> > > > Please enter information: ");
                    String firstName = Util.getStringInput(">> please enter the first name of the teacher: ");
                    String lastName = Util.getStringInput(">> please enter the last name of the teacher: ");
                    String dob = Util.getStringInput(">> please enter birthday (in this format 0000-00-00):");
                    String nationalCode = Util.getStringInput(">> please enter the national code of the teacher: ");
                    Printer.print(" > > > > >   ALL COURSES   < < < < < ");
                    ApplicationContext.getCourseService().showAll();
                    Integer courseId = Util.getIntInput(">> Please enter teacher course (course id): ");
                    Course teacherCourse = ApplicationContext.getCourseService().findById(courseId);
                    ApplicationContext.getTeacherService().addNew(firstName, lastName, dob, nationalCode,teacherCourse);
                    Printer.print("new teacher added successfully :)");
                    break;
                case 2:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer teacherId = Util.getIntInput(">> Please enter teacher id: ");
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer studentId1 = Util.getIntInput(">> Please enter student id: ");
                    ApplicationContext.getStudentService().addStudentInStudentTeacherTable(studentId1, teacherId);
                    Printer.print("teacher added successfully :)");
                    break;
                case 3:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer teacherId1 = Util.getIntInput(">> Please enter teacher id: ");
                    Printer.print(" > > > > >   ALL COURSES   < < < < < ");
                    ApplicationContext.getCourseService().showAll();
                    Integer courseId1 = Util.getIntInput(">> Please enter course id: ");
                    ApplicationContext.getTeacherService().addCourseInTeacherTable(teacherId1, courseId1);
                    Printer.print("course added to teacher options");
                case -1:
                    flag = false;
                    break;
            }
        }
    }

    public static void updateMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   UPDATE TEACHER MENU   < < < < < ");
            Printer.print(
                    "     1.      update teacher\n" +
                            "     2.      update teacher course\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");
            switch (choice) {
                case 1:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    int teacherId = Util.getIntInput(">>please enter teacher id: ");
                    Printer.print("please enter new information: ");
                    String firstName = Util.getStringInput(">> please enter the first name of the teacher: ");
                    String lastName = Util.getStringInput(">> please enter the last name of the teacher: ");
                    String dob = Util.getStringInput(">> please enter birthday (in this format 0000-00-00): ");
                    String nationalCode = Util.getStringInput(">> please enter the national code of the teacher: ");
                    Printer.print(" > > > > >   ALL COURSES   < < < < < ");
                    ApplicationContext.getCourseService().showAll();
                    Integer courseId = Util.getIntInput(">> Please enter course id: ");
                    Course teacherCourse = ApplicationContext.getCourseService().findById(courseId);

                    ApplicationContext.getTeacherService().update(teacherId, firstName, lastName, dob, nationalCode,teacherCourse);
                    Printer.print("update teacher successful");
                    break;
                case 2:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    int teacherId1 = Util.getIntInput(">>please enter teacher id: ");
                    Printer.print("teacher has this course : ");
                    System.out.println(ApplicationContext.getTeacherService().findTeacherById(teacherId1).getCourse());
                    Printer.print("which course do you want to update teacher course: ");
                    Printer.print(" > > > > >   ALL COURSES   < < < < < ");
                    ApplicationContext.getCourseService().showAll();
                    Integer courseId1 = Util.getIntInput(">> Please enter course id: ");
                    ApplicationContext.getTeacherService().addCourseInTeacherTable(teacherId1, courseId1);
                    Printer.print("teacher course updated successfully :)");
                case -1:
                    flag = false;
            }
        }
    }

    public static void deleteMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   DELETE TEACHER MENU   < < < < < ");
            Printer.print(
                    "     1.      delete teacher\n" +
                            "     2.      delete teacher course\n" +
                            "     3.      delete teacher for a student\n" +
                            "     4.      delete teacher for a exam\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");

            switch (choice) {
                case 1:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer teacherId = Util.getIntInput(">> Please enter your choice: ");
                    ApplicationContext.getTeacherService().delete(teacherId);
                    Printer.print("delete teacher successfully :)");
                    break;


                case 2:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer teacherId1 = Util.getIntInput(">> Please enter your choice: ");
                    ApplicationContext.getTeacherService().deleteTeacherCourse(teacherId1);
                    Printer.print("teacher course deleted successfully :)");
                    break;


                case 3:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer teacherId2 = Util.getIntInput(">> Please enter your choice: ");
                    ApplicationContext.getTeacherService().deleteTeacherFromStudentTeacherTable(teacherId2);
                    Printer.print("teacher deleted successfully :)");
                    break;


                case 4:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer teacherId3 = Util.getIntInput(">> Please enter your choice: ");
                    ApplicationContext.getTeacherService().deleteTeacherFromExam(teacherId3);
                    Printer.print("teacher deleted successfully :)");
                    break;


                case -1:
                    flag = false;


            }
        }
    }

    public static void showMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   SHOW TEACHER MENU   < < < < < ");
            Printer.print(
                    "     1.     show all teachers\n" +
                            "     2.     show a teacher course\n" +
                            "     3.     show a teacher students\n" +
                            "     4.     show a teacher exams\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");

            switch (choice) {
                case 1:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    break;
                case 2:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer id = Util.getIntInput(">> Please enter your choice: ");
                    ApplicationContext.getTeacherService().showTeacherCourse(id);
                    break;
                case 3:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer id1 = Util.getIntInput(">> Please enter your choice: ");
                    ApplicationContext.getTeacherService().showTeacherStudents(id1);
                    break;
                case 4:
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer id2 = Util.getIntInput(">> Please enter your choice: ");
                    ApplicationContext.getTeacherService().showTeacherExams(id2);
                    break;
                case -1:
                    flag = false;
            }

        }
    }
}
