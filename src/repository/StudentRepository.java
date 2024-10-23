package repository;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import model.dto.ExamStudentGradeDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository extends BaseRepository<Student>{

    Set<Student> getAllStudentsFull() throws SQLException;

    void addTeachersInStudent(Student student,Teacher teacher) throws SQLException;

    void addCoursesInStudent(Student student,Course course) throws SQLException;

    void addExamInStudent(Student student,Exam exam,Integer grade) throws SQLException;

    void removeStudentFromStudentExamTable(Integer studentId) throws SQLException;

    void removeStudentFromStudentCourseTable(Integer studentId) throws SQLException;

    void removeStudentFromStudentTeacherTable(Integer studentId) throws SQLException;

    List<Exam> getExamsForAStudent(int studentId) throws SQLException;

    Set<Teacher> getTeachersForAStudent(int studentId) throws SQLException;

    Set<Course> getCoursesForAStudent(int studentId) throws SQLException;

    int getCountOfStudents() throws SQLException;


    Optional<Integer> findByUserId(Integer userId) throws SQLException;

    List<ExamStudentGradeDto> StudentExamsGrade(Integer id) throws SQLException;
}
