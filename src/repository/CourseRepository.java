package repository;

import model.Course;
import util.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CourseRepository {
    //language = SQL
    private static final String GET_ALL_COURSES_QUERY = "select * from courses";
    //language = SQL
    private static final String GET_COUNT_OF_ALL_COURSES_QUERY = "select count(*) from courses";

    private Database database = new Database();

    public Set<Course> getAllCourses() throws SQLException {
        ResultSet courseResult = database.getSQLStatement().executeQuery(GET_ALL_COURSES_QUERY);
        Set<Course> courses = new HashSet<>();
        while(courseResult.next()){
            Course course = new Course(
                    courseResult.getInt("course_id"),
                    courseResult.getString("course_title"),
                    courseResult.getInt("course_unit")
            );
            courses.add(course);
        }
        return courses;
    }

    public int getCountOfAllCourses() throws SQLException {
        ResultSet courseResult = database.getSQLStatement().executeQuery(GET_COUNT_OF_ALL_COURSES_QUERY);
        int count = 0;
        while (courseResult.next()) {
            count = courseResult.getInt("count");
        }
        return count;
    }







}
