package repository.impl;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import model.dto.ExamsForATeacherDto;
import model.dto.TeachersForACourseDto;
import repository.CourseRepository;
import repository.ExamRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TeacherRepositoryImpl implements TeacherRepository {

    private CourseRepository courseRepository;

    private StudentRepository studentRepository;

    private ExamRepository examRepository;

    private static final String SAVE_TEACHER = "insert into teachers(first_name, last_name, dob, national_code, course_id)\n" +
            "values(?,?,?,?,?)";

    private static final String UPDATE_TEACHER = "UPDATE teachers SET first_name = ?, last_name = ? , dob = ?, national_code = ?, course_id = ? WHERE teacher_id = ?";

    private static final String FIND_TEACHER_BY_ID = "select * from teachers where teacher_id = ?";

    private static final String DELETE_TEACHER = "delete from teachers where teacher_id = ?";

    private static final String ALL_TEACHERS = "select * from teachers";

    private static final String GET_TEACHERS_COURSE = "select concat(t.first_name, ' ' , t.last_name) as full_name , c.course_title \n" +
            "from teachers as t \n" +
            "LEFT JOIN courses as c \n" +
            "on t.course_id = c.course_id";

    private static final String GET_EXAMS_TEACHER = "select concat(t.first_name, ' ' , t.last_name) as full_name , e.exam_title \n" +
            "from teachers as t \n" +
            "LEFT JOIN exams as e \n" +
            "on t.teacher_id = e.teacher_id";

    private static final String GET_TEACHER_COURSE = "SELECT course_id from teachers where teacher_id = ?";

    private static final String GET_TEACHER_EXAMS = "SELECT exam_id from exams where teacher_id = ?";

    private static final String GET_COUNT_OF_TEACHERS = "SELECT count(*) FROM teachers";

    private static final String GET_TEACHER_STUDENTS = "select student_id , teacher_id from student_teacher where teacher_id = ?";

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
        ps.setInt(5,teacher.getCourse().getCourseId());
        ps.setInt(6,teacher.getTeacherId());
        ps.executeUpdate();
    }

    private void saveTeacher(Teacher teacher) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(SAVE_TEACHER);
        ps.setString(1,teacher.getFirstName());
        ps.setString(2,teacher.getLastName());
        ps.setDate(3,teacher.getDob());
        ps.setString(4,teacher.getNationalCode());
        ps.setInt(5,teacher.getCourse().getCourseId());
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
                        rs.getString("national_code")
                        );
            }
        }
        return null;
    }

    @Override
    public Set<Teacher> getAllTeachers() throws SQLException{
        ResultSet rs = database.getSQLStatement().executeQuery(ALL_TEACHERS);
        Set<Teacher> teachers = new HashSet<>();
        while(rs.next()){
            teachers.add(new Teacher(
                    rs.getInt("teacher_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("dob"),
                    rs.getString("national_code")
            ));
        }
        return teachers;
    }

    public Set<Teacher> getAllTeachersFull() throws SQLException{
        ResultSet rs = database.getSQLStatement().executeQuery(ALL_TEACHERS);
        Set<Teacher> teachers = new HashSet<>();
        while(rs.next()){
            teachers.add(new Teacher(
                    rs.getInt("teacher_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("dob"),
                    rs.getString("national_code"),
                    getTeacherCourse(rs.getInt("teacher_id")),
                    getStudentsOfATeacher(rs.getInt("teacher_id")),
                    getTeacherExams(rs.getInt("teacher_id"))
            ));
        }
        return teachers;
    }

    @Override
    public void deleteTeacher(Teacher teacher) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(DELETE_TEACHER);
        ps.setInt(1,teacher.getTeacherId());
        ps.executeUpdate();

    }

    public Course getTeacherCourse(int teacher_id) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_TEACHER_COURSE);
        ps.setInt(1,teacher_id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("teacher_id") == teacher_id){
                for (Course item: courseRepository.getAllCourses()) {
                    if (item.getCourseId() == rs.getInt("course_id"))
                        return item;
                }
            }
        }
        return null;
    }

    public Set<Student> getStudentsOfATeacher(int teacher_id) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_TEACHER_STUDENTS);
        ps.setInt(1,teacher_id);
        ResultSet rs = ps.executeQuery();
        Set<Student> students = new HashSet<>();
        while(rs.next()){
            if(rs.getInt("teacher_id") == teacher_id){
                for (Student item: studentRepository.getAllStudents()) {
                    if (item.getStudentId() == rs.getInt("student_id"))
                        students.add(item);
                }
            }
        }
        return students;
    }

    public List<TeachersForACourseDto> getTeachersForACourse() throws SQLException{
        ResultSet result = database.getSQLStatement().executeQuery(GET_TEACHERS_COURSE);
        List<TeachersForACourseDto> teachersForACourseDtos = new ArrayList<>();
        while (result.next()){
            TeachersForACourseDto teachersForACourseDto = new TeachersForACourseDto(
                    result.getString("full_name"),
                    result.getString("exam_title")
            );
            teachersForACourseDtos.add(teachersForACourseDto);
        }
        return teachersForACourseDtos;
    }

    public List<ExamsForATeacherDto> getExamsForATeacher() throws SQLException{
        ResultSet result = database.getSQLStatement().executeQuery(GET_EXAMS_TEACHER);
        List<ExamsForATeacherDto> examsForATeacherDtos = new ArrayList<>();
        while (result.next()){
            ExamsForATeacherDto examsForATeacherDto = new ExamsForATeacherDto(
                    result.getString("full_name"),
                    result.getString("course_title")
            );
            examsForATeacherDtos.add(examsForATeacherDto);
        }
        return examsForATeacherDtos;
    }

    public List<Exam> getTeacherExams(int teacher_id) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_TEACHER_EXAMS);
        ResultSet rs = ps.executeQuery();
        List<Exam> exams = new ArrayList<>();
        while(rs.next()){
            if(rs.getInt("teacher_id") == teacher_id){
                for (Exam item: examRepository.getAllExams()) {
                    if (item.getExamId() == rs.getInt("course_id"))
                        exams.add(item);
                }
            }
        }
        return exams;
    }


    public int getCountOfTeachers() throws SQLException{
        ResultSet teacherResult = database.getSQLStatement().executeQuery(GET_COUNT_OF_TEACHERS);
        int teacherCount = 0;
        while (teacherResult.next()){
            teacherCount = teacherResult.getInt("count");
        }
        return teacherCount;
    }

}