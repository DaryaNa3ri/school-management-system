package repository;

import model.Student;

import java.sql.SQLException;
import java.util.Set;

public interface StudentRepository {

    void saveOrUpdate(Student student) throws SQLException;

    Student findStudentById(Integer id) throws SQLException;

    void deleteStudent(Student student) throws SQLException;

    Set<Student> getAllStudents() throws SQLException;
}
