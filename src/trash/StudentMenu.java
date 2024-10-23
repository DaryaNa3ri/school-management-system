package trash;

import util.ApplicationContext;
import util.Util;
import util.printer.Printer;

public class StudentMenu {
    public static void studentMenuForAdmin() {
        boolean exit = false;
        while (!exit) {
            Printer.print(" > > > > >   STUDENT MENU   < < < < < ");
            Printer.print(
                    "     1.      add student\n" +
                    "             *                     add STUDENT, EXAM, COURSE, TEACHER\n" +
                    "     2.      update student\n" +
                    "             *                     update student information\n" +
                    "     3.      delete student\n" +
                    "             *                     delete STUDENT, EXAM, COURSE, TEACHER\n" +
                    "     4.      show student\n" +
                    "             *                     show STUDENTS, EXAMS, COURSE, TEACHER\n" +
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

    private static void addMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   ADD STUDENT MENU   < < < < < ");
            Printer.print(
                            "     1.      add new student\n" +
                            "     2.      add student in exam\n" +
                            "     3.      add student in teacher\n" +
                            "     4.      add student in course\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");
            switch (choice) {
                case 1:
                    Printer.print("> > > > Please enter information: ");
                    String firstName = Util.getStringInput(">> please enter the first name of the student: ");
                    String lastName = Util.getStringInput(">> please enter the last name of the student: ");
                    String dob = Util.getStringInput("please enter birthday (in this format 0000-00-00):");
                    String nationalCode = Util.getStringInput(">> please enter the national code of the student: ");
                    Double gpu = Util.getDoubleInput(">> please enter the gpu number of the student: ");
                    ApplicationContext.getStudentService().addNew(firstName, lastName, dob, nationalCode, gpu);
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
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer studentId1 = Util.getIntInput(">> Please enter student id: ");
                    Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
                    ApplicationContext.getTeacherService().showAll();
                    Integer teacherId = Util.getIntInput(">> Please enter teacher id: ");
                    ApplicationContext.getStudentService().addStudentInStudentTeacherTable(studentId1, teacherId);
                    Printer.print("student added successfully :)");
                    break;
                case 4:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer studentId2 = Util.getIntInput(">> Please enter student id: ");
                    Printer.print(" > > > > >   ALL COURSES   < < < < < ");
                    ApplicationContext.getCourseService().showAll();
                    Integer courseId = Util.getIntInput(">> Please enter course id: ");
                    ApplicationContext.getStudentService().addStudentInStudentCourseTable(studentId2, courseId);
                    Printer.print("student added successfully :)");
                    break;
                case -1:
                    flag = false;
            }
        }
    }

    private static void updateMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   UPDATE STUDENT MENU   < < < < < ");
            Printer.print(
                    "     1.      update student\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");
            switch (choice) {
                case 1:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    int studentId = Util.getIntInput(">>please enter student id: ");
                    Printer.print("please enter new information: ");
                    String firstName = Util.getStringInput(">> please enter the first name of the student: ");
                    String lastName = Util.getStringInput(">> please enter the last name of the student: ");
                    String dob = Util.getStringInput("please enter birthday (in this format 0000-00-00):");
                    String nationalCode = Util.getStringInput(">> please enter the national code of the student: ");
                    Double gpu = Util.getDoubleInput(">> please enter the gpu number of the student: ");
                    ApplicationContext.getStudentService().update(studentId, firstName, lastName, dob, nationalCode, gpu);
                    Printer.print("update student successful");
                    break;
                case -1:
                    flag = false;
            }
        }
    }

    private static void deleteMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   DELETE STUDENT MENU   < < < < < ");
            Printer.print(
                    "     1.      delete student\n" +
                            "     2.      delete course for a student\n" +
                            "     3.      delete teacher for a student\n" +
                            "     4.      delete exam for a student\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");

            switch (choice) {
                case 1:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer studentId = Util.getIntInput(">> Please enter student id choice: ");
                    ApplicationContext.getStudentService().delete(studentId);
                    Printer.print("delete student successfully :)");
                    break;


                case 2:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer studentId3 = Util.getIntInput(">> Please enter student id choice: ");
                    ApplicationContext.getStudentService().deleteStudentFromStudentCourseTable(studentId3);
                    Printer.print("student deleted successfully :)");
                    break;


                case 3:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer studentId2 = Util.getIntInput(">> Please enter student id choice: ");
                    ApplicationContext.getStudentService().deleteStudentFromStudentTeacherTable(studentId2);
                    Printer.print("student deleted successfully :)");
                    break;


                case 4:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer studentId1 = Util.getIntInput(">> Please enter student id choice: ");
                    ApplicationContext.getStudentService().deleteStudentFromStudentExamList(studentId1);
                    Printer.print("student deleted successfully :)");
                    break;


                case -1:
                    flag = false;

            }
        }
    }

    public static void showMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   SHOW STUDENT MENU   < < < < < ");
            Printer.print(
                            "     1.      show all students\n" +
                            "     2.      show a student courses\n" +
                            "     3.      show a student teachers\n" +
                            "     4.      show a student exams\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");

            switch (choice) {
                case 1:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    break;
                case 2:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer id = Util.getIntInput(">> Please enter student id : ");
                    ApplicationContext.getStudentService().showStudentcourses(id);

                    break;
                case 3:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer id1 = Util.getIntInput(">> Please enter student id : ");
                    ApplicationContext.getStudentService().showStudentTeachers(id1);

                    break;
                case 4:
                    Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                    ApplicationContext.getStudentService().showAll();
                    Integer id2 = Util.getIntInput(">> Please enter student id : ");
                    ApplicationContext.getStudentService().showStudentExams(id2);
                    break;
                case -1:
                    flag = false;
            }
        }
    }

}
