package repository;

import model.Course;

import java.sql.SQLException;
import java.util.Set;

public interface CourseRepository {
    Set<Course> getAllCourses() throws SQLException;
}
