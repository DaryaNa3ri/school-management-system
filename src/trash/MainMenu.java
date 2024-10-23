package trash;

import util.Util;
import util.printer.Printer;

public class MainMenu {

    private static void mainMenu() {
        boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   MAIN MENU   < < < < < ");
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
