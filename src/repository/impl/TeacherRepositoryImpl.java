package repository.impl;

import model.Teacher;
import model.dto.TeachersCourseDto;
import repository.TeacherRepository;
import util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TeacherRepositoryImpl implements TeacherRepository {
    private static final String SAVE_TEACHER = "insert into teachers(first_name, last_name, dob, national_code, course_id)\n" +
            "values(?,?,?,?,?)";

    private static final String UPDATE_TEACHER = "update teachers (first_name, last_name, dob, national_code, course_id)\n" +
            "values(?,?,?,?,?) where teacher_id = ? ";

    private static final String FIND_TEACHER_BY_ID = "select * from teachers where teacher_id = ?";

    private static final String DELETE_TEACHER = "delete from teachers where teacher_id = ?";

    private Database database = new Database();





    @Override
    public void saveOrUpdateTeacher(Teacher teacher) throws SQLException{
        if (teacher.getTeacherId() == null) {
             saveTeacher(teacher);
        }
        else  updateTeacher(teacher);
    }

    private void updateTeacher(Teacher teacher) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(UPDATE_TEACHER);
        ps.setString(1,teacher.getFirstName());
        ps.setString(2,teacher.getLastName());
        ps.setDate(3,teacher.getDob());
        ps.setString(4,teacher.getNationalCode());
        ps.setInt(5,teacher.getCourseId());
        ps.setInt(6,teacher.getTeacherId());
        ps.executeUpdate();
    }

    private void saveTeacher(Teacher teacher) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(SAVE_TEACHER);
        ps.setString(1,teacher.getFirstName());
        ps.setString(2,teacher.getLastName());
        ps.setDate(3,teacher.getDob());
        ps.setString(4,teacher.getNationalCode());
        ps.setInt(5,teacher.getCourseId());
        ps.executeUpdate();
    }

    @Override
    public Teacher findTeacherById(int id) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(FIND_TEACHER_BY_ID);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("teacher_id") == id){
                return new Teacher(id,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("dob"),
                        rs.getString("national_code"),
                        rs.getInt("course_id")
                        );
            }
        }
        return null;
    }

    @Override
    public Set<Teacher> getAllTeachers() throws SQLException{

        return Collections.emptySet();
    }

    @Override
    public void deleteTeacher(Teacher teacher) throws SQLException{

    }




    //=====================================================================================
    //language = SQL
    private static final String GET_ALL_TEACHERS_QUERY = "SELECT * FROM teachers";
    //language = SQL

    private static final String GET_ALL_TEACHERS_BY_SPECIFIC_ENTITY = "select * from teachers where first_name =?" ;

    private static final String GET_COUNT_OF_TEACHERS = "SELECT count(*) FROM teachers";

    private static final String GET_TEACHERS_COURSE = "select concat(t.first_name, ' ' , t.last_name) as full_name , c.course_title \n" +
            "from teachers as t \n" +
            "LEFT JOIN courses as c \n" +
            "on t.course_id = c.course_id";


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
                    teacherResult.getInt("teacher_id"),
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