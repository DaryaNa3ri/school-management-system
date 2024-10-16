package repository.impl;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import repository.*;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ExamRepositoryImpl implements ExamRepository {

    private TeacherRepository teacherRepository;

    private CourseRepository courseRepository;

    private StudentRepository studentRepository;

    public ExamRepositoryImpl(TeacherRepository teacherRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    private static final String GET_ALL_EXAMS_QUERY = "select * from exams";

    private static final String GET_EXAM_STUDENTS = "select exam_id ,student_id  from student_exam where exam_id = ?";

    private static final String UPDATE_EXAM = "UPDATE exams SET exam_title = ?, exam_unit = ? , grade = ?, exam_date = ? WHERE exam_id = ?";

    private static final String SAVE_EXAM = "insert into exams(exam_title, exam_unit, exam_date ,grade) \n" +
            "values(?,?,?,?)";

    private static final String INSERT_EXAM_STUDENTS = "INSERT INTO student_exam(exam_id,student_id, national_code ) VALUES(?,?,?)";

    private static final String FIND_EXAM_BY_ID = "select * from exams where exam_id = ?";

    private static final String GET_EXAM_COURSE = "SELECT course_id from exams where exam_id = ?";

    private static final String GET_EXAM_TEACHER = "SELECT teacher_id from exams where exam_id = ?";

    private static final String DELETE_EXAM = "delete from exams where exam_id = ?";

    private static final String DELETE_EXAM_FROM_STUDENT_EXAM = "delete from student_exam where exam_id = ?";


    private static final String SET_EXAM_AS_NULL_IN_STUDENT_EXAM = "update student_exam set exam_id = null where exam_id = ?";

    private Database database = new Database();



    public void saveOrUpdate(Exam exam) throws SQLException {
        if (exam.getExamId() == null) {
            saveExam(exam);
        }
        else  updateExam(exam);
    }

    private void updateExam(Exam exam) throws SQLException{
        PreparedStatement ps = database.getPreparedStatement(UPDATE_EXAM);
        ps.setString(1,exam.getExamTitle());
        ps.setInt(2,exam.getExamUnit());
        ps.setDouble(3,exam.getGrade());
        ps.setDate(4, exam.getExamDate());
        ps.setDouble(5,exam.getExamId());
        ps.executeUpdate();
    }

    private void saveExam(Exam exam) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(SAVE_EXAM);
        ps.setString(1,exam.getExamTitle());
        ps.setInt(2,exam.getExamUnit());
        ps.setDate(3,exam.getExamDate());
        ps.setDouble(4,exam.getGrade());
        ps.executeUpdate();
        //addExamInStudentExamTable(exam);
    }

    public void addExamInStudentExamTable(Exam exam,Student student) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(INSERT_EXAM_STUDENTS);
            ps.setInt(1,exam.getExamId());
            ps.setInt(2,student.getStudentId());
            ps.setString(3,student.getNationalCode());
            ps.executeUpdate();
    }


    public Optional<Exam> findById(Integer id) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(FIND_EXAM_BY_ID);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        Optional<Exam> optionalExam = Optional.empty();
        while(rs.next()){
            if(rs.getInt("exam_id") == id){
                Exam exam = new Exam(id,
                        rs.getString("exam_title"),
                        rs.getInt("exam_unit"),
                        rs.getDate("exam_date"),
                        rs.getDouble("grade")
                );
                optionalExam = Optional.of(exam);
            }
        }
        return optionalExam;
    }


    public void delete(Integer id) throws SQLException{

        PreparedStatement ps;

        ps = database.getPreparedStatement(SET_EXAM_AS_NULL_IN_STUDENT_EXAM);
        ps.setInt(1,id);
        ps.executeUpdate();

        ps = database.getPreparedStatement(DELETE_EXAM);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    public void removeExamFromStudentExamTable(Integer examId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(DELETE_EXAM_FROM_STUDENT_EXAM);
        ps.setInt(1,examId);
        ps.executeUpdate();
    }


    public Optional<Teacher> getExamsTeacher(int exam_id) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_EXAM_TEACHER);
        ps.setInt(1, exam_id);
        ResultSet rs = ps.executeQuery();
        Optional<Teacher> optionalTeacher = Optional.empty();
        while (rs.next()){
            if (rs.getInt("exam_id") == exam_id){}
                for (Teacher item : teacherRepository.getAll())
                    if (item.getTeacherId() == rs.getInt("teacher_id"))
                        optionalTeacher = Optional.of(item);
        }
        return optionalTeacher;
    }

    public Optional<Course> getExamsCourse(int exam_id) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_EXAM_COURSE);
        ps.setInt(1,exam_id);
        ResultSet rs = ps.executeQuery();
        Optional<Course> optionalCourse = Optional.empty();
        while(rs.next()){
            if(rs.getInt("exam_id") == exam_id){
                for (Course item: courseRepository.getAll()) {
                    if (item.getCourseId() == rs.getInt("course_id"))
                        optionalCourse = Optional.of(item);
                }
            }
        }
        return optionalCourse;
    }


    public Set<Exam> getAll()throws SQLException{
        ResultSet rs = database.getSQLStatement().executeQuery(GET_ALL_EXAMS_QUERY);
        Set<Exam> exams = new HashSet<>();
        while(rs.next()){
            exams.add(new Exam(
                    rs.getInt("exam_id"),
                    rs.getString("exam_title"),
                    rs.getInt("exam_unit"),
                    rs.getDate("exam_date"),
                    rs.getDouble("grade")));
        }
        return exams;
    }

    /*public Set<Exam> getAllExamsFull() throws SQLException{
        ResultSet rs = database.getSQLStatement().executeQuery(GET_ALL_EXAMS_QUERY);
        Set<Exam> exams = new HashSet<>();
        while (rs.next()){
            exams.add(new Exam(
                    rs.getInt("exam_id"),
                    rs.getString("exam_title"),
                    rs.getInt("exam_unit"),
                    rs.getDate("exam_date"),
                    rs.getInt("grade"),
                    getExamsTeacher(rs.getInt("exam_id")),
                    getExamsCourse(rs.getInt("exam_id")),
                    getStudentsForAExam(rs.getInt("exam_id"))
            ));
        }
        return exams;
    }*/


    public List<Student> getStudentsForAExam(int examId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(GET_EXAM_STUDENTS);
        ps.setInt(1,examId);
        ResultSet rs = ps.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rs.next()){
            for (Student item : studentRepository.getAll())
                if (item.getStudentId() == rs.getInt("student_id"))
                    students.add(item);
            }

        return students;
    }

}
