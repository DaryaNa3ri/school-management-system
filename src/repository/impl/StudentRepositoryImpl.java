package repository.impl;

import exeption.NotFoundException;
import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import repository.*;
import util.Database;

import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentRepositoryImpl implements StudentRepository {

    private ExamRepository examRepository;

    private CourseRepository courseRepository;

    private TeacherRepository teacherRepository;

    //language=SQL
    private static final String GET_ALL_STUDENTS_QUERY = "SELECT * FROM students";
    //language=SQL
    private static final String GET_COUNT_OF_STUDENTS = "SELECT count(*) FROM students";
    //language=SQL
    private static final String UPDATE_STUDENT = "UPDATE students SET first_name = ?, last_name = ? , dob = ?, national_code = ?, gpu = ? WHERE student_id = ?";
    //language=SQL
    private static final String SAVE_STUDENT = "insert into students(first_name, last_name, dob, national_code, gpu)\n" +
            "values(?,?,?,?,?)";
    //language=SQL
    private static final String FIND_STUDENT_BY_ID = "select * from students where student_id = ?";
    //language=SQL
    private static final String DELETE_STUDENT = "delete from students where student_id = ?";
    //language=SQL
    private static final String DELETE_STUDENT_FROM_STUDENT_COURSE = "delete from student_course where student_id = ?";
    //language=SQL
    private static final String SET_STUDENT_AS_NULL_IN_STUDENT_COURSE = "update student_course set student_id = null where student_id = ?";
    //language=SQL
    private static final String DELETE_STUDENT_FROM_STUDENT_EXAM = "delete from student_exam where student_id = ?";
    //language=SQL
    private static final String SET_STUDENT_AS_NULL_IN_STUDENT_EXAM = "update student_exam set student_id = null where student_id = ?";
    //language=SQL
    private static final String DELETE_STUDENT_FROM_STUDENT_TEACHER = "delete from student_teacher where student_id = ?";
    //language=SQL
    private static final String SET_STUDENT_AS_NULL_IN_STUDENT_TEACHER = "update student_teacher set student_id = null where student_id = ?";
    //language=SQL
    private static final String INSERT_STUDENT_EXAMS = "INSERT INTO student_exam(student_id, national_code, exam_id) VALUES(?,?,?)";
    //language=SQL
    private static final String INSERT_STUDENT_COURSES = "INSERT INTO student_course(course_id, student_id,national_code) VALUES(?,?,?)";
    //language=SQL
    private static final String INSERT_STUDENT_TEACHERS = "INSERT INTO student_teacher(student_id,student_national_code ,teacher_id,teacher_national_code ) VALUES(?,?,?,?)";
    //language=SQL
    private static final String GET_STUDENT_TEACHERS = "select student_id , teacher_id from student_teacher where student_id = ?";
    //language=SQL
    private static final String GET_STUDENT_EXAMS = "select student_id , exam_id from student_exam where student_id = ?";
    //language=SQL
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
        PreparedStatement ps = database.getPreparedStatement(UPDATE_STUDENT);
        ps.setString(1,student.getFirstName());
        ps.setString(2,student.getLastName());
        ps.setDate(3,student.getDob());
        ps.setString(4, student.getNationalCode());
        ps.setDouble(5,student.getGpu());
        ps.setInt(6,student.getStudentId());
        ps.executeUpdate();
    }

    private void saveStudent(Student student) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(SAVE_STUDENT);
        ps.setString(1,student.getFirstName());
        ps.setString(2,student.getLastName());
        ps.setDate(3,student.getDob());
        ps.setString(4,student.getNationalCode());
        ps.setDouble(5,student.getGpu());
        ps.executeUpdate();
        addExamInStudent(student);
        addCoursesInStudent(student);
        addTeachersInStudent(student);
    }

    public void addTeachersInStudent(Student student) throws SQLException{
        PreparedStatement teachers = database.getPreparedStatement(INSERT_STUDENT_TEACHERS);
        for (Teacher teacher : student.getTeachers()) {
            teachers.setInt(1,student.getStudentId());
            teachers.setString(2,student.getNationalCode());
            teachers.setInt(3,teacher.getTeacherId());
            teachers.setString(4,teacher.getFirstName());
            teachers.executeUpdate();
        }
    }

    //todo : service get a list of ids from user and send it in a method in this repository

    public void addCoursesInStudent(Student student) throws SQLException{
        PreparedStatement courses = database.getPreparedStatement(INSERT_STUDENT_COURSES);
        for (Course course : student.getCourses()) {
            courses.setInt(1,course.getCourseId());
            courses.setInt(2,student.getStudentId());
            courses.setString(3,student.getNationalCode());
            courses.executeUpdate();
        }
    }

    public void addExamInStudent(Student student) throws SQLException {
        PreparedStatement exams = database.getPreparedStatement(INSERT_STUDENT_EXAMS);
        for (Exam exam : student.getExams()) {
            exams.setInt(1,student.getStudentId());
            exams.setString(2,student.getNationalCode());
            exams.setInt(3,exam.getExamId());
            exams.executeUpdate();
        }
    }

    @Override
    public Optional<Student> findById(Integer id) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(FIND_STUDENT_BY_ID);
        ps.setInt(1,id);

        //result set is more like type of list
        ResultSet rs = ps.executeQuery();

        //A box contains one or nothing but it's not null
        Optional<Student> optionalStudent = Optional.empty();
        while(rs.next()){
            Student student = new Student(  id,
                                            rs.getString("first_name"),
                                            rs.getString("last_name"),
                                            rs.getDate("dob"),
                                            rs.getString("national_code"),
                                            rs.getDouble("gpu"));
            optionalStudent = Optional.of(student);

            }
        return optionalStudent;
}



    @Override
    public void delete(Integer studentId) throws SQLException , NotFoundException {
        if (this.findById(studentId).isPresent()) {
            PreparedStatement ps ;

            ps = database.getPreparedStatement(SET_STUDENT_AS_NULL_IN_STUDENT_COURSE);
            ps.setInt(1, studentId);
            ps.executeUpdate();

            ps = database.getPreparedStatement(SET_STUDENT_AS_NULL_IN_STUDENT_EXAM);
            ps.setInt(1, studentId);
            ps.executeUpdate();

            ps = database.getPreparedStatement(SET_STUDENT_AS_NULL_IN_STUDENT_TEACHER);
            ps.setInt(1, studentId);
            ps.executeUpdate();

            ps = database.getPreparedStatement(DELETE_STUDENT);
            ps.setInt(1, studentId);
            ps.executeUpdate();

        }else{
            throw new NotFoundException("student with id of " + studentId + "not found!");
        }
    }

    public void removeStudentFromStudentExamTable(Integer studentId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(DELETE_STUDENT_FROM_STUDENT_EXAM);
        ps.setInt(1, studentId);
        ps.executeUpdate();
    }

    public void removeStudentFromStudentCourseTable(Integer studentId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(DELETE_STUDENT_FROM_STUDENT_COURSE);
        ps.setInt(1, studentId);
        ps.executeUpdate();
    }

    public void removeStudentFromStudentTeacherTable(Integer studentId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(DELETE_STUDENT_FROM_STUDENT_TEACHER);
        ps.setInt(1, studentId);
        ps.executeUpdate();
    }

    public Set<Student> getAll() throws SQLException {
        ResultSet studentsResult = database.getSQLStatement().executeQuery(GET_ALL_STUDENTS_QUERY);
        Set<Student> students = new HashSet<>();
        while (studentsResult.next()) {
            students.add(new Student(
                    studentsResult.getInt("student_id"),
                    studentsResult.getString("first_name"),
                    studentsResult.getString("last_name"),
                    studentsResult.getDate("dob"),
                    studentsResult.getString("national_code"),
                    studentsResult.getDouble("gpu")
            ));
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
        PreparedStatement ps = database.getPreparedStatement(GET_STUDENT_EXAMS);
        ResultSet rs = ps.executeQuery();
        List<Exam> exams = new ArrayList<>();
        while (rs.next()){
            if (rs.getInt("student_id") == studentId){
                for (Exam item : examRepository.getAll())
                    if(item.getExamId() == rs.getInt("exam_id"))
                        exams.add(item);
            }
        }
        return exams;
    }

    public Set<Teacher> getTeachersForAStudent(int studentId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(GET_STUDENT_TEACHERS);
        ResultSet rs = ps.executeQuery();
        Set<Teacher> teachers = new HashSet<>();
        while (rs.next()){
            if (rs.getInt("student_id") == studentId){
                for (Teacher item : teacherRepository.getAll())
                    if (item.getTeacherId() == rs.getInt("teacher_id"))
                        teachers.add(item);
            }
        }
        return teachers;
    }

    public Set<Course> getCoursesForAStudent(int studentId) throws SQLException{
        PreparedStatement ps = database.getPreparedStatement(GET_STUDENT_COURSES);
        ResultSet rs = ps.executeQuery();
        Set<Course> courses = new HashSet<>();
        while (rs.next()){
            if (rs.getInt("student_id") == studentId){
                for (Course item : courseRepository.getAll())
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
