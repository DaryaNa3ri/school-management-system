package repository;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import trash.TeachersForACourseDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeacherRepository extends BaseRepository<Teacher> {
    //crud create read update delete

    Set<Teacher> getAllTeachersFull() throws SQLException;

    //List<Exam> getExamsForATeacher() throws SQLException;

    List<Exam> getATeacherExams(int id) throws SQLException;

    int getCountOfTeachers() throws SQLException;

    List<TeachersForACourseDto> getTeachersForACourse() throws SQLException;

    Optional<Course> getTeacherCourse(int teacher_id) throws SQLException;

    Set<Student> getStudentsOfATeacher(int teacher_id) throws SQLException;

    void deleteTeacherCourse(Integer teacherId) throws SQLException;

    void removeTeacherFromStudentTeacherTable(Integer teacherId) throws SQLException;
    void removeTeacherFromExamTable(Integer teacherId) throws SQLException;
}
