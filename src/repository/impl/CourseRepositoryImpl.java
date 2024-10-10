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

public class CourseRepositoryImpl implements CourseRepository {

    private TeacherRepository teacherRepository;

    private StudentRepository studentRepository;

    private ExamRepository examRepository;

    private static final String GET_ALL_COURSES_QUERY = "select * from courses";

    private static final String GET_COUNT_OF_ALL_COURSES_QUERY = "select count(*) from courses";

    private static final String UPDATE_COURSE = "UPDATE courses SET course_title = ?, course_unit = ?  WHERE course_id = ?";

    private static final String SAVE_COURSE = "insert into courses(course_title, course_unit)\n" +
            "values(?,?)";

    private static final String INSERT_COURSE_TEACHERS = "INSERT INTO student_teacher(student_id,student_national_code ,teacher_id,teacher_national_code ) VALUES(?,?,?,?)";

    private static final String INSERT_COURSE_EXAMS = "INSERT INTO exams_students(student_id, national_code, exam_id) VALUES(?,?,?)";

    private static final String INSERT_COURSE_STUDENTS = "INSERT INTO student_course(student_id,national_code,course_id ) VALUES(?,?,?)";

    private static final String FIND_COURSE_BY_ID = "select * from courses where course_id = ?";

    private static final String DELETE_COURSE = "delete from courses where course_id = ?";

    private static final String GET_COURSE_STUDENTS = "select course_id, student_id from student_course where course_id = ?";

    private static final String GET_COURSES_TEACHER = "select t.first_name ,t.last_name ,c.course_title from courses c\n" +
            "join teachers t\n" +
            "on c.course_id = t.course_id\n" +
            "where c.course_id = ?";

    private static final String GET_COURSE_EXAMS = "select e.exam_title , c.course_title from courses c\n" +
            "    join exams e\n" +
            "on c.course_id = e.course_id\n" +
            "where c.course_id = ?";


    private Database database = new Database();

    public void saveOrUpdate(Course course) throws SQLException {
        if (course.getCourseId() == null) {
            saveCourse(course);
        }
        else  updateCourse(course);
    }

    private void updateCourse(Course course) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(UPDATE_COURSE);
        ps.setString(1,course.getCourseTitle());
        ps.setInt(2,course.getCourseUnit());
        ps.setInt(3,course.getCourseId());
        ps.executeUpdate();
    }

    private void saveCourse(Course course) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(SAVE_COURSE);
        ps.setString(1,course.getCourseTitle());
        ps.setInt(2,course.getCourseUnit());
        ps.executeUpdate();
        /*addTeachersInCourse(course);
        addStudentsInCourse(course);
        addExamsInCourse(course);*/
    }

    private void addExamsInCourse(Course course) throws SQLException {

    }

    public void addStudentsInCourse(Course course) throws SQLException {
        PreparedStatement courses = database.getDatabaseConnection().prepareStatement(INSERT_COURSE_STUDENTS);
        for (Student item : course.getStudents()) {
            courses.setInt(2,item.getStudentId());
            courses.setString(3,item.getNationalCode());
            courses.setInt(1,course.getCourseId());
            courses.executeUpdate();
        }
    }

    private void addTeachersInCourse(Course course) {
        for (Teacher item : course.getTeachers()) {
            if (course.getCourseId() == item.getCourse().getCourseId()) {

            }
        }
    }

    public Course findCourseById(Integer id) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(FIND_COURSE_BY_ID);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("course_id") == id){
                return new Course(id,
                        rs.getString("course_title"),
                        rs.getInt("course_unit")
                );
            }
        }
        return null;
    }

    public void deleteCourse(Course course) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(DELETE_COURSE);
        ps.setInt(1,course.getCourseId());
        ps.executeUpdate();
    }

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

    public Set<Course> getAllCoursesFull() throws SQLException {
        ResultSet courseResult = database.getSQLStatement().executeQuery(GET_ALL_COURSES_QUERY);
        Set<Course> courses = new HashSet<>();
        while(courseResult.next()){
            Course course = new Course(
                    courseResult.getInt("course_id"),
                    courseResult.getString("course_title"),
                    courseResult.getInt("course_unit"),
                    getCourseTeachers(courseResult.getInt("course_id")),
                    getStudentsForACourse(courseResult.getInt("course_id")),
                    getExamsForACourse(courseResult.getInt("course_id")
            ));
            courses.add(course);
        }
        return courses;
    }

    public Set<Exam> getExamsForACourse(int courseId) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_COURSE_EXAMS);
        ResultSet rs = ps.executeQuery();
        Set<Exam> exams = new HashSet<>();
        while(rs.next()){
            if(rs.getInt("course_id") == courseId){
                for (Exam exam : examRepository.getAllExams()) {
                    if (exam.getExamId() == rs.getInt("exam_id")) {
                        exams.add(exam);
                    }
                }
            }
        }
                return exams;
            }

    public Set<Student> getStudentsForACourse(int courseId) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_COURSE_STUDENTS);
        ResultSet rs = ps.executeQuery();
        Set<Student> students = new HashSet<>();
        while (rs.next()){
            if (rs.getInt("course_id") == courseId){
                for (Student item : studentRepository.getAllStudents())
                    if (item.getStudentId() == rs.getInt("student_id"))
                        students.add(item);
            }
        }
        return students;
    }

    public Set<Teacher> getCourseTeachers(int courseId) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(GET_COURSES_TEACHER);
        ps.setInt(1,courseId);
        ResultSet rs = ps.executeQuery();
        Set<Teacher> teachers = new HashSet<>();
        while(rs.next()){
            if(rs.getInt("course_id") == courseId){
                for (Teacher item: teacherRepository.getAllTeachers()) {
                    if (item.getTeacherId() == rs.getInt("teacher_id"))
                        teachers.add(item);
                }
            }
        }
        return teachers;
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
