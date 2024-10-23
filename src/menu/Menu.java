package menu;

import model.Course;
import model.Student;
import model.User;
import util.ApplicationContext;
import util.Util;
import util.printer.Printer;

import java.text.ParseException;

public class Menu {


    public static void startMenu() {
        boolean exit = true;

        while (exit) {
            exit = loginMenu();

        }
    }

    private static boolean loginMenu() {
        while (true) {
            Printer.print("**********   Welcome to School management system   **********");
            Printer.print("      1.      Login\n" +
                    "     -1.      Exit");
            int intInput = Util.getIntInput("choose your choice: ");
            if (intInput == -1) {
                return false;
            }
            String username = Util.getStringInput("Enter your username : ");
            String password = Util.getStringInput("Enter your password : ");
            User user = ApplicationContext.getUserService().login(username, password);
            Printer.print("permission : "+user.getPermission());
            Integer userId = user.getUserId();
            switch (user.getPermission()) {
                case "Admin" -> AdminMainMenu();
                case "Student" -> studentMenu(userId);
            }

        }
    }

    private static void AdminMainMenu() {
        boolean exit = true;
        while (exit) {
            Printer.print(" > > > > >   ADMIN MAIN MENU   < < < < < ");
            Printer.print(">>Which part do you want? \n" +
                    "     1.      add course\n"  +
                    "     2.      add teacher\n" +
                    "     3.      update teacher\n" +
                    "     4.      set course for a teacher\n" +
                    "     5.      adding students for courses\n" +
                    "     6.      add exam\n" +
                    "     7.      set grade for for student exam\n" +
                    "     -1.     logout\n");
            int choice = Util.getIntInput("choose your choice: ");
            if (choice == -1) {
                exit = false;
            }else if (choice == 1) {
                addCourse();
            }else if (choice == 2) {
                addTeacher();
            } else if (choice == 3) {
                updateTeacher();
            } else if (choice == 4) {
                setCourseForTeacher();
            }else if (choice == 5) {
                addStudentInCourse();
            }else if (choice == 6) {
                addExam();
            } else if (choice == 7) {
               setGradeForStudentExam();
            }
        }
    }

    private static void setGradeForStudentExam() {
        Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
        ApplicationContext.getStudentService().showAll();
        Integer studentId = Util.getIntInput(">> Please enter student id: ");
        Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
        ApplicationContext.getExamService().showAll();
        Integer examId = Util.getIntInput(">> Please enter exam id: ");
        Integer grade = Util.getIntInput(">> please enter the grade of the exam: ");
        ApplicationContext.getStudentService().addStudentInStudentExamTable(studentId, examId,grade);
        Printer.print("student added successfully :)");
    }

    private static void addExam() {
        Printer.print("> > > > Please enter information: ");
        String title = Util.getStringInput(">> Please enter exam title: ");
        Integer unit = Util.getIntInput(">> Please enter exam unit: ");
        Double grade = Util.getDoubleInput(">> Please enter exam grade: ");
        String date = Util.getStringInput(">> Please enter exam date(in this format 0000-00-00): ");
        try {
            ApplicationContext.getExamService().addNew(title, unit, grade, date);
            Printer.print("new exam added successfully :)");
        } catch (ParseException e) {
            System.out.println("wrong date format please try again");
        }
    }

    private static void addStudentInCourse() {
        Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
        ApplicationContext.getStudentService().showAll();
        Integer studentId2 = Util.getIntInput(">> Please enter student id: ");
        Printer.print(" > > > > >   ALL COURSES   < < < < < ");
        ApplicationContext.getCourseService().showAll();
        Integer courseId = Util.getIntInput(">> Please enter course id: ");
        ApplicationContext.getStudentService().addStudentInStudentCourseTable(studentId2, courseId);
        Printer.print("student added successfully :)");
    }

    private static void setCourseForTeacher() {
        Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
        ApplicationContext.getTeacherService().showAll();
        Integer teacherId = Util.getIntInput(">> Please enter teacher id: ");
        Printer.print(" > > > > >   ALL COURSE   < < < < < ");
        ApplicationContext.getCourseService().showAll();
        Integer courseId = Util.getIntInput(">> Please enter course id: ");
        ApplicationContext.getCourseService().addCourseToTeacher(courseId, teacherId);
        Printer.print("course set successfully :)");
    }

    private static void updateTeacher() {
        Printer.print(" > > > > >   ALL TEACHERS   < < < < < ");
        ApplicationContext.getTeacherService().showAll();
        Integer teacherId = Util.getIntInput(">> Please enter teacher id: ");
        Printer.print(" > > > > >   ALL COURSE   < < < < < ");
        ApplicationContext.getCourseService().showAll();
        Integer courseId = Util.getIntInput(">> Please enter course id: ");
        ApplicationContext.getCourseService().addCourseToTeacher(courseId, teacherId);
        Printer.print("course set successfully :)");
    }

    private static void addTeacher() {
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
    }

    private static void addCourse() {
        Printer.print("> > > > Please enter information: ");
        String title = Util.getStringInput(">> please enter the title: ");
        Integer unit = Util.getIntInput(">> please enter the unit of course: ");
        ApplicationContext.getCourseService().addNew(title,unit);
        Printer.print("new course added successfully :)");
    }

    private static void studentMenu(Integer userId) {
        boolean flag = true;
        while (flag) {
            Printer.print("**********   STUDENT EXAMS GRADE MENU   **********");
            Printer.print("      1.      check grades\n" +
                    "      -1.      logout");
            Integer choice = Util.getIntInput("choose your choice: ");
            if (choice == -1) {
                flag = false;
            }
            else if (choice == 1) {
                Integer idByUserId = ApplicationContext.getStudentService().findIdByUserId(userId);
                ApplicationContext.getStudentService().showStudentExamsGrade(idByUserId);
            }

        }
    }

}
