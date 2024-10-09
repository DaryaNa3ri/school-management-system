package util;

import model.Teacher;
import model.dto.TeachersForACourseDto;
import repository.TeacherRepository;

import java.sql.SQLException;

public class Printer {
    private TeacherRepository teacherRepository;

    public static void print(String message){
        System.out.println(message);
    }


    public void printAllTeacherList() {
        try {
            for (Teacher teacher : teacherRepository.getAllTeachers()) {
                System.out.println(teacher);
            }
        } catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }

    public void printCountOfTeachers() {
        try {
            System.out.println("# teachers : " + teacherRepository.getCountOfTeachers());
        } catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }

    public void printTeachersCourse() {
        try {
            for (TeachersForACourseDto item : teacherRepository.getTeachersCourse())
                System.out.println(item);
        } catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }

}
