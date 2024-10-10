package repository;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import model.dto.ExamsForATeacherDto;
import model.dto.TeachersForACourseDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface TeacherRepository {
    //crud create read update delete

    void saveOrUpdateTeacher(Teacher teacher) throws SQLException;

    Teacher findTeacherById(int id) throws SQLException;

    Set<Teacher> getAllTeachers() throws SQLException;

    Set<Teacher> getAllTeachersFull() throws SQLException;

    void deleteTeacher(Teacher teacher)throws SQLException;

    List<ExamsForATeacherDto> getExamsForATeacher() throws SQLException;

    List<Exam> getTeacherExams(int id) throws SQLException;

    int getCountOfTeachers() throws SQLException;

    List<TeachersForACourseDto> getTeachersForACourse() throws SQLException;

    Course getTeacherCourse(int teacher_id) throws SQLException;

    Set<Student> getStudentsOfATeacher(int teacher_id) throws SQLException;

}
