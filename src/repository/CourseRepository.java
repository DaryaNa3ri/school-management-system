package repository;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.Set;

public interface CourseRepository {
    void saveOrUpdate(Course course) throws SQLException;

    void addStudentsInCourse(Course course) throws SQLException;

    Course findCourseById(Integer id) throws SQLException;

    void deleteCourse(Course course) throws SQLException;

    Set<Course> getAllCoursesFull() throws SQLException;

    Set<Exam> getExamsForACourse(int courseId) throws SQLException;

    Set<Student> getStudentsForACourse(int courseId) throws SQLException;

    Set<Teacher> getCourseTeachers(int courseId) throws SQLException;

    int getCountOfAllCourses() throws SQLException;

    Set<Course> getAllCourses() throws SQLException;


}
