package repository;

import model.Course;
import model.Teacher;
import model.dto.TeachersCourseDto;
import util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeacherRepository {

    //language = SQL
    private static final String GET_ALL_TEACHERS_QUERY = "SELECT * FROM teachers";
    //language = SQL

    private static final String GET_ALL_TEACHERS_BY_SPECIFIC_ENTITY = "select * from teachers where first_name =?" ;

    private static final String GET_COUNT_OF_TEACHERS = "SELECT count(*) FROM teachers";

    private static final String GET_TEACHERS_COURSE = "select concat(t.first_name, ' ' , t.last_name) as full_name , c.course_title \n" +
            "from teachers as t \n" +
            "LEFT JOIN courses as c \n" +
            "on t.course_id = c.course_id";

    private Database database = new Database();


    public Set<Teacher> getAllTeachers() throws SQLException{
        ResultSet teachersResult = database.getSQLStatement().executeQuery(GET_ALL_TEACHERS_QUERY);
        Set<Teacher> teachers = new HashSet<>();
        while (teachersResult.next()) {
            Teacher teacher = new Teacher(
                    teachersResult.getLong("teacher_id"),
                    teachersResult.getString("first_name"),
                    teachersResult.getString("last_name"),
                    teachersResult.getDate("dob"),
                    teachersResult.getString("national_code"),
                    teachersResult.getInt("course_id")
            );
            teachers.add(teacher);
        }
        return teachers;
    }

    public int getCountOfTeachers() throws SQLException{
        ResultSet teacherResult = database.getSQLStatement().executeQuery(GET_COUNT_OF_TEACHERS);
        int teacherCount = 0;
        while (teacherResult.next()){
            teacherCount = teacherResult.getInt("count");
        }
        return teacherCount;
    }

    public List<Teacher> getTeacherByFirstName(String firstName) throws SQLException{
        Connection connection1 = database.getDatabaseConnection();
        PreparedStatement preparedStatement = connection1.prepareStatement(GET_ALL_TEACHERS_BY_SPECIFIC_ENTITY);
        preparedStatement.setString(1, firstName);
        ResultSet teacherResult = preparedStatement.executeQuery();
        List<Teacher> teachers = new ArrayList<>();
        while (teacherResult.next()){
            Teacher teacher = new Teacher(
                    teacherResult.getLong("teacher_id"),
                    teacherResult.getString("first_name"),
                    teacherResult.getString("last_name"),
                    teacherResult.getDate("dob"),
                    teacherResult.getString("national_code"),
                    teacherResult.getInt("course_id")
            );
            teachers.add(teacher);
        }
        return teachers;
    }

    public List<TeachersCourseDto> getTeachersCourse() throws SQLException{
        ResultSet result = database.getSQLStatement().executeQuery(GET_TEACHERS_COURSE);
        List<TeachersCourseDto> teachersCourseDtos = new ArrayList<>();
        while (result.next()){
            TeachersCourseDto teachersCourseDto = new TeachersCourseDto(
                    result.getString("full_name"),
                    result.getString("course_title")
            );
            teachersCourseDtos.add(teachersCourseDto);
        }
        return teachersCourseDtos;
    }

















}
