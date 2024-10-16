package menu;

import util.Util;
import util.printer.Printer;

public class Menu {

    public static void startMenu() {
        Printer.print("**********   Welcome to School management system   **********");
        mainMenu();
    }

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
                    StudentMenu.studentMenu();
                    break;
                case 2:
                    Printer.print("not available");
                    //teacherMenu();
                    break;
                case 3:
                    Printer.print("not available");
                    //courseMenu();
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
