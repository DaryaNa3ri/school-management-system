package repository;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.Set;

public interface CourseRepository extends BaseRepository<Course> {

    void addStudentsInACourse(Course course) throws SQLException;

    Set<Course> getAllCoursesFull() throws SQLException;

    Set<Exam> getExamsForACourse(int courseId) throws SQLException;

    Set<Student> getStudentsForACourse(int courseId) throws SQLException;

    Set<Teacher> getCourseTeachers(int courseId) throws SQLException;

    int getCountOfAllCourses() throws SQLException;

}
