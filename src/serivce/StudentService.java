package serivce;

import model.Student;
import repository.StudentRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class StudentService {

    private StudentRepository studentRepository = new StudentRepository();

    public void printAllStudentList() {
        try {
            Set<Student> students = this.studentRepository.getAllStudents();
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
