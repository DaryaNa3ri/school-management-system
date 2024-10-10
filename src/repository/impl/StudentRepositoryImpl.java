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

import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentRepositoryImpl implements StudentRepository {

    private ExamRepository examRepository;

    private CourseRepository courseRepository;

    private TeacherRepository teacherRepository;

    //language=SQL
    private static final String GET_ALL_STUDENTS_QUERY = "SELECT * FROM students";
    //language=SQL
    private static final String GET_COUNT_OF_STUDENTS = "SELECT count(*) FROM students";

    private static final String UPDATE_STUDENT = "UPDATE students SET first_name = ?, last_name = ? , dob = ?, national_code = ?, gpu = ? WHERE student_id = ?";

    private static final String SAVE_STUDENT = "insert into students(first_name, last_name, dob, national_code, gpu)\n" +
            "values(?,?,?,?,?)";

    private static final String FIND_STUDENT_BY_ID = "select * from students where student_id = ?";

    private static final String DELETE_STUDENT = "delete from students where student_id = ?";

    private static final String INSERT_STUDENT_EXAMS = "INSERT INTO exams_students(student_id, national_code, exam_id) VALUES(?,?,?)";

    private static final String INSERT_STUDENT_COURSES = "INSERT INTO student_course(course_id, student_id,national_code) VALUES(?,?,?)";

    private static final String INSERT_STUDENT_TEACHERS = "INSERT INTO student_teacher(student_id,student_national_code ,teacher_id,teacher_national_code ) VALUES(?,?,?,?)";

    private static final String GET_STUDENT_TEACHERS = "select student_id , teacher_id from student_teacher where student_id = ?";

    private static final String GET_STUDENT_EXAMS = "select student_id , exam_id from exams_students where student_id = ?";

    private static final String GET_STUDENT_COURSES = "select student_id , course_id from student_course where student_id = ?";

    private Database database = new Database();


    @Override
    public void saveOrUpdate(Student student) throws SQLException {
        if (student.getStudentId() == null) {
            saveStudent(student);
        }
        else  updateStudent(student);
    }

    private void updateStudent(Student student) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(UPDATE_STUDENT);
        ps.setString(1,student.getFirstName());
        ps.setString(2,student.getLastName());
        ps.setDate(3,student.getDob());
        ps.setString(4, student.getNationalCode());
        ps.setDouble(5,student.getGpu());
        ps.setInt(6,student.getStudentId());
        ps.executeUpdate();
    }

    private void saveStudent(Student student) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(SAVE_STUDENT);
        ps.setString(1,student.getFirstName());
        ps.setString(2,student.getLastName());
        ps.setDate(3,student.getDob());
        ps.setString(4,student.getNationalCode());
        ps.setDouble(5,student.getGpu());
        ps.executeUpdate();
        /*addExamInStudent(student);
        addCoursesInStudent(student);
        addTeachersInStudent(student);*/
    }

    public void addTeachersInStudent(Student student) throws SQLException{
        PreparedStatement teachers = database.getDatabaseConnection().prepareStatement(INSERT_STUDENT_TEACHERS);
        for (Teacher teacher : student.getTeachers()) {
            teachers.setInt(1,student.getStudentId());
            teachers.setString(2,student.getNationalCode());
            teachers.setInt(3,teacher.getTeacherId());
            teachers.setString(4,teacher.getFirstName());
            teachers.executeUpdate();
        }
    }

    public void addCoursesInStudent(Student student) throws SQLException{
        PreparedStatement courses = database.getDatabaseConnection().prepareStatement(INSERT_STUDENT_COURSES);
        for (Course course : student.getCourses()) {
            courses.setInt(1,course.getCourseId());
            courses.setInt(2,student.getStudentId());
            courses.setString(3,student.getNationalCode());
            courses.executeUpdate();
        }
    }

    public void addExamInStudent(Student student) throws SQLException {
        PreparedStatement exams = database.getDatabaseConnection().prepareStatement(INSERT_STUDENT_EXAMS);
        for (Exam exam : student.getExams()) {
            exams.setInt(1,student.getStudentId());
            exams.setString(2,student.getNationalCode());
            exams.setInt(3,exam.getExamId());
            exams.executeUpdate();
        }
    }

    @Override
    public Student findStudentById(Integer id) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(FIND_STUDENT_BY_ID);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("student_id") == id){
                return new Student(id,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("dob"),
                        rs.getString("national_code"),
                        rs.getDouble("gpu")
                );
            }
        }
        return null;
    }

    @Override
    public void deleteStudent(Student student) throws SQLException {
            PreparedStatement ps = database.getDatabaseConnection().prepareStatement(DELETE_STUDENT);
            ps.setInt(1,student.getStudentId());
            ps.executeUpdate();
        }


    public Set<Student> getAllStudents() throws SQLException {
        ResultSet studentsResult = database.getSQLStatement().executeQuery(GET_ALL_STUDENTS_QUERY);
        Set<Student> students = new HashSet<>();
        while (studentsResult.next()) {
            Student student = new Student(
                    studentsResult.getInt("student_id"),
                    studentsResult.getString("first_name"),
                    studentsResult.getString("last_name"),
                    studentsResult.getDate("dob"),
                    studentsResult.getString("national_code"),
                    studentsResult.getDouble("gpu")
            );
            students.add(student);
        }
        return students;
    }

    public Set<Student> getAllStudentsFull() throws SQLException{
        ResultSet rs = database.getSQLStatement().executeQuery(GET_ALL_STUDENTS_QUERY);
        Set<Student> students = new HashSet<>();
        while(rs.next()){
            students.add(new Student(
                    rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("dob"),
                    rs.getString("national_code"),
                    rs.getDouble("gpu"),
                    getExamsForAStudent(rs.getInt("student_id")),
                    getCoursesForAStudent(rs.getInt("student_id")),
                    getTeachersForAStudent(rs.getInt("student_id"))

            ));
        }
        return students;
    }

    public List<Exam> getExamsForAStudent(int studentId) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_STUDENT_EXAMS);
        ResultSet rs = ps.executeQuery();
        List<Exam> exams = new ArrayList<>();
        while (rs.next()){
            if (rs.getInt("student_id") == studentId){
                for (Exam item : examRepository.getAllExams())
                    if(item.getExamId() == rs.getInt("exam_id"))
                        exams.add(item);
            }
        }
        return exams;
    }

    public Set<Teacher> getTeachersForAStudent(int studentId) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_STUDENT_TEACHERS);
        ResultSet rs = ps.executeQuery();
        Set<Teacher> teachers = new HashSet<>();
        while (rs.next()){
            if (rs.getInt("student_id") == studentId){
                for (Teacher item : teacherRepository.getAllTeachers())
                    if (item.getTeacherId() == rs.getInt("teacher_id"))
                        teachers.add(item);
            }
        }
        return teachers;
        }

    public Set<Course> getCoursesForAStudent(int studentId) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_STUDENT_COURSES);
        ResultSet rs = ps.executeQuery();
        Set<Course> courses = new HashSet<>();
        while (rs.next()){
            if (rs.getInt("student_id") == studentId){
                for (Course item : courseRepository.getAllCourses())
                    if (item.getCourseId() == rs.getInt("course_id"))
                        courses.add(item);
            }
        }
        return courses;
    }

    public int getCountOfStudents() throws SQLException {
        ResultSet countResult = database.getSQLStatement().executeQuery(GET_COUNT_OF_STUDENTS);
        int studentCount = 0;
        while (countResult.next()) {
            studentCount = countResult.getInt("count");
        }
        return studentCount;
    }


}
