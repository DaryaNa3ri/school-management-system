package trash;

import util.ApplicationContext;
import util.Util;
import util.printer.Printer;

import java.text.ParseException;

public class ExamMenu {


    public static void examMenu() {
        boolean flag = true;
        while (flag) {
        Printer.print(" > > > > >   EXAM MENU   < < < < < ");
        Printer.print(
                "     1.      add exam\n" +
                        "     2.      update exam\n" +
                        "     3.      delete exam\n" +
                        "     4.      show exam\n" +
                        "     -1.     quit"
        );
        int choice = Util.getIntInput(">>please enter your choice:");

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
                flag = false;
            }
        }
    }

    private static void updateMenu() {
    boolean flag = true;
        while (flag) {
            Printer.print(" > > > > >   UPDATE EXAM MENU   < < < < < ");
            Printer.print(
                    "     1.      update exam\n" +
                            "     -1.     quit"
            );
            int choice = Util.getIntInput(">>please enter your choice:");
            switch (choice) {
                case 1:
                    Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
                    ApplicationContext.getExamService().showAll();
                    int examId = Util.getIntInput(">>please enter exam id: ");
                    Printer.print("please enter new information: ");
                    String title = Util.getStringInput(">> please enter new exam title: ");
                    Integer unit = Util.getIntInput(">> Please enter exam unit: ");
                    Double grade = Util.getDoubleInput(">> Please enter exam grade: ");
                    String date = Util.getStringInput(">> Please enter exam date(in this format 0000-00-00): ");
                    ApplicationContext.getExamService().update(examId,title,unit,grade,date);
                    Printer.print("update exam successful");
                    break;
                    case 2:
                        flag = false;
    }
}}
    private static void addMenu() {
        boolean flag = true;
        while (flag){
        Printer.print(" > > > > >   ADD EXAM MENU   < < < < < ");
        Printer.print(
                "     1.      add new exam\n" +
                        "     2.      add exam for students\n" +
                        "     -1.     quit"
        );
        int choice = Util.getIntInput(">>please enter your choice:");
        switch (choice) {
            case 1:
                Printer.print("> > > > Please enter information: ");
                String title = Util.getStringInput(">> Please enter exam title: ");
                Integer unit = Util.getIntInput(">> Please enter exam unit: ");
                Double grade = Util.getDoubleInput(">> Please enter exam grade: ");
                String date = Util.getStringInput(">> Please enter exam date(in this format 0000-00-00): ");
                try {
                    ApplicationContext.getExamService().addNew(title, unit, grade, date);
                    Printer.print("new exam added successfully :)");
                    break;
                } catch (ParseException e) {
                    System.out.println("wrong date format please try again");
                    addMenu();
                }

            case 2:
                Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
                ApplicationContext.getExamService().showAll();
                Integer examId = Util.getIntInput(">> Please enter exam id: ");
                Printer.print(" > > > > >   ALL STUDENTS   < < < < < ");
                ApplicationContext.getStudentService().showAll();
                Integer studentId = Util.getIntInput(">> Please enter student id: ");
                Integer grade1 = Util.getIntInput( ">> Please enter grade of this student");
                ApplicationContext.getStudentService().addStudentInStudentExamTable(studentId, examId,grade1);
                Printer.print("exam added successfully :)");
                break;
            case -1:
                flag = false;
        }
    }}

    private static void deleteMenu() {
        boolean flag = true;
        while (flag) {
        Printer.print(" > > > > >   DELETE EXAM MENU   < < < < < ");
        Printer.print(
                "     1.      delete exam\n" +
                        "     2.      delete exam from a student exam list\n" +
                        /*"     3.      delete exam from a teacher exam list\n" +
                        "     4.      delete exam from a course exam list\n" +*/
                        "     -1.     quit"
        );
        int choice = Util.getIntInput(">>please enter your choice:");

        switch (choice) {
            case 1:
                Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
                ApplicationContext.getExamService().showAll();
                Integer examId = Util.getIntInput(">> Please enter your choice: ");
                ApplicationContext.getExamService().delete(examId);
                Printer.print("delete exam successfully :)");
                break;
            case 2:
                Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
                ApplicationContext.getExamService().showAll();
                Integer examId1 = Util.getIntInput(">> Please enter your choice: ");
                ApplicationContext.getExamService().deleteExamFromStudentExamList(examId1);
                Printer.print("exam deleted successfully :)");
                break;
            case -1:
                flag = false;

        }
        }
    }

    private static void showMenu() {
        boolean flag = true;
        while (flag) {
        Printer.print(" > > > > >   SHOW EXAM MENU   < < < < < ");
        Printer.print(
                        "     1.     show all exams\n" +
                        "     2.     show exam students\n" +
                        "     3.     show exam teacher\n" +
                        "     4.     show exam course\n" +
                        "     -1.     quit"
        );
        int choice = Util.getIntInput(">>please enter your choice:");

        switch (choice) {
            case 1:
                Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
                ApplicationContext.getExamService().showAll();
                break;
            case 2:
                Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
                ApplicationContext.getExamService().showAll();
                Integer id = Util.getIntInput(">> Please enter your choice: ");
                ApplicationContext.getExamService().showExamStudents(id);

                break;
            case 3:
                Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
                ApplicationContext.getExamService().showAll();
                Integer id1 = Util.getIntInput(">> Please enter your choice: ");
                ApplicationContext.getExamService().showExamTeacher(id1);

                break;
            case 4:
                Printer.print(" > > > > >   ALL EXAMS   < < < < < ");
                ApplicationContext.getExamService().showAll();
                Integer id2 = Util.getIntInput(">> Please enter your choice: ");
                ApplicationContext.getExamService().showExamCourse(id2);
                break;
                case -1:
                    flag = false;
        }
    }
}

}

