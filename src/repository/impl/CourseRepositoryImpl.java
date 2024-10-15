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
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CourseRepositoryImpl implements CourseRepository {

    private TeacherRepository teacherRepository;

    private StudentRepository studentRepository;

    private ExamRepository examRepository;

    private static final String GET_ALL_COURSES_QUERY = "select * from courses";

    private static final String GET_COUNT_OF_ALL_COURSES_QUERY = "select count(*) from courses";

    //language=SQL
    private static final String UPDATE_COURSE = "UPDATE courses SET course_title = ?, course_unit = ?  WHERE course_id = ?";

    //language=SQL
    private static final String SAVE_COURSE = "insert into courses(course_title, course_unit)\n" +
            "values(?,?)";

    //private static final String INSERT_COURSE_TEACHERS = "INSERT INTO student_teacher(student_id,student_national_code ,teacher_id,teacher_national_code ) VALUES(?,?,?,?)";

    //private static final String INSERT_COURSE_EXAMS = "INSERT INTO exams_students(student_id, national_code, exam_id) VALUES(?,?,?)";

    //language=SQL
    private static final String INSERT_COURSE_STUDENTS = "INSERT INTO student_course(student_id,national_code,course_id ) VALUES(?,?,?)";

    //language=SQL
    private static final String FIND_COURSE_BY_ID = "select * from courses where course_id = ?";

    //language=SQL
    private static final String SET_COURSE_AS_NULL_IN_STUDENT_COURSE = "update student_course set course_id = null where course_id = ?";

    //language=SQL
    private static final String DELETE_COURSE_FROM_STUDENT_COURSE = "delete from student_course where course_id = ?";

    //language=SQL
    private static final String SET_COURSE_AS_NULL_IN_EXAM = "update exams set course_id = null where course_id = ?";

    //language=SQL
    private static final String SET_COURSE_AS_NULL_IN_TEACHER = "update teachers set course_id = null where course_id = ?";

    //language=SQL
    private static final String DELETE_COURSE = "delete from courses where course_id = ?";

    //language=SQL
    private static final String GET_COURSE_STUDENTS = "select course_id, student_id from student_course where course_id = ?";

    //language=SQL
    private static final String GET_COURSES_TEACHER = "select t.first_name ,t.last_name ,c.course_title from courses c\n" +
            "join teachers t\n" +
            "on c.course_id = t.course_id\n" +
            "where c.course_id = ?";

    //language=SQL
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
        PreparedStatement ps = database.getPreparedStatement(UPDATE_COURSE);
        ps.setString(1,course.getCourseTitle());
        ps.setInt(2,course.getCourseUnit());
        ps.setInt(3,course.getCourseId());
        ps.executeUpdate();
    }

    private void saveCourse(Course course) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(SAVE_COURSE);
        ps.setString(1,course.getCourseTitle());
        ps.setInt(2,course.getCourseUnit());
        ps.executeUpdate();
        addStudentsInACourse(course);
    }

    //todo it should be in service
    public void addTeachersInCourseObjectTeachersList(Course course,List<Integer> teachersId) throws SQLException {
        for (Integer teacherId : teachersId) {
            Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
            if (optionalTeacher.isPresent()) {
                Teacher teacher = optionalTeacher.get();
                course.getTeachers().add(teacher);
            }
        }
    }

    //todo it should be in service
    public void addExamsInCourseObjectExamsList(Course course,List<Integer> examsId) throws SQLException {
        for (Integer examId : examsId) {
            Optional<Exam> optionalExam = examRepository.findById(examId);
            if (optionalExam.isPresent()) {
                Exam exam = optionalExam.get();
                course.getExams().add(exam);
            }
        }
    }

    public void addStudentsInACourse(Course course) throws SQLException {
        PreparedStatement courses = database.getPreparedStatement(INSERT_COURSE_STUDENTS);
        for (Student item : course.getStudents()) {
            courses.setInt(2,item.getStudentId());
            courses.setString(3,item.getNationalCode());
            courses.setInt(1,course.getCourseId());
            courses.executeUpdate();
        }
    }

    //todo it should be in service
    public void addStudentsInCourseObjectStudentsList(Course course, List<Integer> studentsId) throws SQLException {
        for (Integer studentId : studentsId) {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                course.getStudents().add(student);
            }
        }
    }

    public Optional<Course> findById(Integer id) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(FIND_COURSE_BY_ID);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        Optional<Course> optionalCourse = Optional.empty();
        while(rs.next()){
                Course course = new Course(id,
                        rs.getString("course_title"),
                        rs.getInt("course_unit")
                );
            optionalCourse = Optional.of(course);
        }
        return optionalCourse;
    }

    public void delete(Integer id) throws SQLException {
        PreparedStatement ps;

        ps = database.getPreparedStatement(SET_COURSE_AS_NULL_IN_STUDENT_COURSE);
        ps.setInt(1,id);
        ps.executeUpdate();

        ps = database.getPreparedStatement(SET_COURSE_AS_NULL_IN_EXAM);
        ps.setInt(1,id);
        ps.executeUpdate();

        ps = database.getPreparedStatement(SET_COURSE_AS_NULL_IN_TEACHER);
        ps.setInt(1,id);
        ps.executeUpdate();

        ps = database.getPreparedStatement(DELETE_COURSE);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    public void removeCourseFromStudentCourseTable(Integer courseId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(DELETE_COURSE_FROM_STUDENT_COURSE);
        ps.setInt(1,courseId);
        ps.executeUpdate();
    }

    public void removeCourseFromExamTable(Integer examId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(SET_COURSE_AS_NULL_IN_EXAM);
        ps.setInt(1,examId);
        ps.executeUpdate();
    }

    public void removeCourseFromTeacherTable(Integer teacherId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(SET_COURSE_AS_NULL_IN_TEACHER);
        ps.setInt(1,teacherId);
        ps.executeUpdate();
    }


    public Set<Course> getAll() throws SQLException {
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
        PreparedStatement ps = database.getPreparedStatement(GET_COURSE_EXAMS);
        ResultSet rs = ps.executeQuery();
        Set<Exam> exams = new HashSet<>();
        while(rs.next()){
            if(rs.getInt("course_id") == courseId){
                for (Exam exam : examRepository.getAll()) {
                    if (exam.getExamId() == rs.getInt("exam_id")) {
                        exams.add(exam);
                    }
                }
            }
        }
                return exams;
    }

    public Set<Student> getStudentsForACourse(int courseId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(GET_COURSE_STUDENTS);
        ResultSet rs = ps.executeQuery();
        Set<Student> students = new HashSet<>();
        while (rs.next()){
            if (rs.getInt("course_id") == courseId){
                for (Student item : studentRepository.getAll())
                    if (item.getStudentId() == rs.getInt("student_id"))
                        students.add(item);
            }
        }
        return students;
    }

    public Set<Teacher> getCourseTeachers(int courseId) throws SQLException {
        PreparedStatement ps = database.getPreparedStatement(GET_COURSES_TEACHER);
        ps.setInt(1,courseId);
        ResultSet rs = ps.executeQuery();
        Set<Teacher> teachers = new HashSet<>();
        while(rs.next()){
            if(rs.getInt("course_id") == courseId){
                for (Teacher item: teacherRepository.getAll()) {
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
