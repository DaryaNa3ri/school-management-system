package repository;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface StudentRepository extends BaseRepository<Student>{

    Set<Student> getAllStudentsFull() throws SQLException;

    void addTeachersInStudent(Student student) throws SQLException;

    void addCoursesInStudent(Student student) throws SQLException;

    void addExamInStudent(Student student) throws SQLException;

    void removeStudentFromStudentExamTable(Integer studentId) throws SQLException;

    void removeStudentFromStudentCourseTable(Integer studentId) throws SQLException;

    void removeStudentFromStudentTeacherTable(Integer studentId) throws SQLException;

    List<Exam> getExamsForAStudent(int studentId) throws SQLException;

    Set<Teacher> getTeachersForAStudent(int studentId) throws SQLException;

    Set<Course> getCoursesForAStudent(int studentId) throws SQLException;

    int getCountOfStudents() throws SQLException;


}
