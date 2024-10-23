package menu;

import model.Student;
import model.User;
import util.ApplicationContext;
import util.Util;
import util.printer.Printer;

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
                case "Admin" -> mainMenu();
                case "Student" -> studentMenu(userId);
            }

        }
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


    private static void mainMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   ADMIN MAIN MENU   < < < < < ");
            Printer.print(">>Which part do you want? \n" +
                    "     1.      Student\n" +
                    "     2.      Teacher\n" +
                    "     3.      Course\n" +
                    "     4.      Exam\n" +
                    "     -1.     Quit");

            int choice = Util.getIntInput("Please select an option: ");

            switch (choice) {
                case 1:
                    StudentMenu.studentMenuForAdmin();
                    break;
                case 2:

                    TeacherMenu.teacherMenu();
                    break;
                case 3:
                    CourseMenu.courseMenu();
                    break;
                case 4:
                    ExamMenu.examMenu();
                    break;
                case -1:
                    Printer.print("program exit ,have a great day :)");
                    flag = false;


            }
        }
    }
}
