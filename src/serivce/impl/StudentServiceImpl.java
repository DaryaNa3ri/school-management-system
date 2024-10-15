package serivce.impl;

import model.Student;
import repository.impl.StudentRepositoryImpl;

import java.sql.SQLException;
import java.util.Set;

public class StudentServiceImpl {

    private StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();

    public void printAllStudentList() {
        try {
            Set<Student> students = this.studentRepository.getAll();
            for (Student student: students) {
                System.out.println(student);
            }
        } catch (SQLException sqlException) {
            System.out.println("There is problem with connecting to database.");
        }
    }

    public void printCountOfStudents() {
        try {
            int countOfStudents = studentRepository.getCountOfStudents();
            System.out.println("# students:".concat(String.valueOf(countOfStudents)));
        } catch (SQLException sqlException) {
            System.out.println("There is problem with connecting to database.");
        }
    }
}
