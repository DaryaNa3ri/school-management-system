package repository.impl;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import repository.CourseRepository;
import repository.ExamRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ExamRepositoryImpl implements ExamRepository {

    private TeacherRepository teacherRepository;

    private CourseRepository courseRepository;

    private StudentRepository studentRepository;

    private static final String GET_ALL_EXAMS_QUERY = "select * from exams";

    private static final String GET_EXAM_STUDENTS = "select exam_id ,student_id  from exams_students where exam_id = ?";

    private static final String UPDATE_EXAM = "UPDATE exams SET exam_title = ?, exam_unit = ? , grade = ?, exam_date = ? WHERE exam_id = ?";

    private static final String SAVE_EXAM = "insert into exams(exam_title, exam_unit, exam_date ,grade) \n" +
            "values(?,?,?,?)";

    private static final String FIND_EXAM_BY_ID = "select * from exams where exam_id = ?";

    private static final String GET_EXAM_COURSE = "SELECT course_id from exams where exam_id = ?";

    private static final String GET_EXAM_TEACHER = "SELECT teacher_id from exams where exam_id = ?";

    private static final String DELETE_EXAM = "delete from teachers where teacher_id = ?";


    private Database database = new Database();

    public void saveOrUpdate(Exam exam) throws SQLException {
        if (exam.getExamId() == null) {
            saveExam(exam);
        }
        else  updateExam(exam);
    }

    private void updateExam(Exam exam) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(UPDATE_EXAM);
        ps.setString(1,exam.getExamTitle());
        ps.setInt(2,exam.getExamUnit());
        ps.setInt(3,exam.getGrade());
        ps.setDate(4, exam.getExamDate());
        ps.setDouble(5,exam.getExamId());
        ps.executeUpdate();
    }

    private void saveExam(Exam exam) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(SAVE_EXAM);
        ps.setString(1,exam.getExamTitle());
        ps.setInt(2,exam.getExamUnit());
        ps.setDate(3,exam.getExamDate());
        ps.setInt(4,exam.getGrade());
        ps.executeUpdate();
    }


    public Exam findExamById(int id) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(FIND_EXAM_BY_ID);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("exam_id") == id){
                return new Exam(id,
                        rs.getString("exam_title"),
                        rs.getInt("exam_unit"),
                        rs.getDate("exam_date"),
                        rs.getInt("grade")
                );
            }
        }
        return null;
    }


    public void deleteExam(Exam exam) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(DELETE_EXAM);
        ps.setInt(1,exam.getExamId());
        ps.executeUpdate();
    }


    public Teacher getExamsTeacher(int exam_id) throws SQLException{
        PreparedStatement prs = database.getDatabaseConnection().prepareStatement(GET_EXAM_TEACHER);
        prs.setInt(1, exam_id);
        ResultSet rs = prs.executeQuery();
        while (rs.next()){
            if (rs.getInt("exam_id") == exam_id){}
                for (Teacher item : teacherRepository.getAllTeachers())
                    if (item.getTeacherId() == rs.getInt("teacher_id"))
                        return item;
        }
        return null;
    }

    public Course getExamsCourse(int exam_id) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_EXAM_COURSE);
        ps.setInt(1,exam_id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("exam_id") == exam_id){
                for (Course item: courseRepository.getAllCourses()) {
                    if (item.getCourseId() == rs.getInt("course_id"))
                        return item;
                }
            }
        }
        return null;
    }


    public Set<Exam> getAllExams()throws SQLException{
        ResultSet rs = database.getSQLStatement().executeQuery(GET_ALL_EXAMS_QUERY);
        Set<Exam> exams = new HashSet<>();
        while(rs.next()){
            exams.add(new Exam(
                    rs.getInt("exam_id"),
                    rs.getString("exam_title"),
                    rs.getInt("exam_unit"),
                    rs.getDate("exam_date"),
                    rs.getInt("grade")));
        }
        return exams;
    }

    public Set<Exam> getAllExamsFull() throws SQLException{
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
    }


    public Set<Student> getStudentsForAExam(int exam_id) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_EXAM_STUDENTS);
        ResultSet rs = ps.executeQuery();
        Set<Student> students = new HashSet<>();
        while (rs.next()){
            if (rs.getInt("exam_id") == exam_id){
                for (Student item : studentRepository.getAllStudents())
                    if (item.getStudentId() == rs.getInt("student_id"))
                        students.add(item);
            }
        }
        return students;
    }

}
