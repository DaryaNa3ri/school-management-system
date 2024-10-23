package serivce.impl;

import exeption.NotFoundException;
import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import model.dto.ExamStudentGradeDto;
import repository.CourseRepository;
import repository.ExamRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import serivce.StudentService;
import util.printer.Printer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Set;

public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository ;
    private ExamRepository examRepository ;
    private TeacherRepository teacherRepository ;
    private CourseRepository courseRepository ;

    public StudentServiceImpl(StudentRepository studentRepository, ExamRepository examRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository ;
        this.examRepository = examRepository ;
        this.teacherRepository = teacherRepository ;
        this.courseRepository = courseRepository ;
    }



    @Override
    public Set<Student> getAll() {
        Set<Student> studentSet = null;
        try {
            studentSet = studentRepository.getAll();
        } catch (SQLException e) {
            Printer.print("something went wrong");
        }
        return studentSet;
    }

    @Override
    public void addNew(String firstName, String lastName, String dob, String nationalCode, Double gpu) {
        try {
            studentRepository.saveOrUpdate(new Student(firstName,lastName, Date.valueOf(dob),nationalCode,gpu));
            }catch (SQLException e) {
            Printer.print("something went wrong");
        }
    }

    @Override
    public void addStudentInStudentExamTable(Integer studentId, Integer examId,Integer grade) {
        try {
            for (Student student : getAll()) {
                if (student.getStudentId()==(studentId)) {
                    for (Exam exam : examRepository.getAll()) {
                        if (exam.getExamId()==(examId)) {
                            studentRepository.addExamInStudent(student,exam,grade);
                        }
                    }
                }
            }
        }catch (SQLException e) {
            Printer.print("something went wrong");
        }
    }

    @Override
    public void addStudentInStudentTeacherTable(Integer studentId, Integer teacherId) {
        try {
            for (Student student : getAll()) {
                if (student.getStudentId()==(studentId)) {
                    for (Teacher teacher : teacherRepository.getAll()) {
                        if (teacher.getTeacherId()==(teacherId)) {
                            studentRepository.addTeachersInStudent(student,teacher);
                        }
                    }
                }
            }
        }catch (SQLException e) {
            Printer.print("There is problem with connecting to database:(");
        }
    }

    @Override
    public void addStudentInStudentCourseTable(Integer studentId, Integer courseId) {
        try {
            for (Student student : getAll()) {
                if (student.getStudentId()==(studentId)) {
                    for (Course course : courseRepository.getAll()) {
                        if (course.getCourseId()==(courseId)) {
                            studentRepository.addCoursesInStudent(student,course);
                        }
                    }
                }
            }
        }catch (SQLException e) {
            Printer.print("There is problem with connecting to database:(");
        }
    }

    @Override
    public void update(int studentId, String firstName, String lastName, String dob, String nationalCode, Double gpu) {
        try {
            studentRepository.saveOrUpdate(new Student(studentId,firstName,lastName,Date.valueOf(dob),nationalCode,gpu));
        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");
        }
    }

    @Override
    public void delete(Integer studentId) {
        try {
            studentRepository.delete(studentId);
        }catch (SQLException | NotFoundException e){
            Printer.print("There is problem with connecting to database:(");}
    }

    @Override
    public void deleteStudentFromStudentExamList(Integer studentId) {
        try {
            studentRepository.removeStudentFromStudentExamTable(studentId);
        } catch (SQLException e) {
            Printer.print("database connection failed");
        }
    }

    @Override
    public void deleteStudentFromStudentTeacherTable(Integer studentId) {
        try {
            studentRepository.removeStudentFromStudentTeacherTable(studentId);
        } catch (SQLException e) {
            Printer.print("There is problem with connecting to database:(");
        }
    }

    @Override
    public void deleteStudentFromStudentCourseTable(Integer studentId) {
        try {
            studentRepository.removeStudentFromStudentCourseTable(studentId);
        } catch (SQLException e) {
            Printer.print("There is problem with connecting to database:(");
        }
    }

    @Override
    public void showStudentcourses(Integer id) {
        try {
            for (Course course : studentRepository.getCoursesForAStudent(id))
                System.out.println("Course id : ".concat(course.getCourseId().toString()).concat(", Course title : ").concat(course.getCourseTitle()));
        }catch (SQLException e){
            Printer.print("database connection failed");
        }
    }

    @Override
    public void showStudentTeachers(Integer id) {
        try {
            for (Teacher teacher : studentRepository.getTeachersForAStudent(id))
                System.out.println("teacher id : ".concat(teacher.getTeacherId().toString()).concat("teacher name : ")
                        .concat(teacher.getFirstName()).concat(" ").concat(teacher.getLastName()));
        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");
        }
    }

    @Override
    public void showStudentExams(Integer id) {
        try {
            for (Exam exam : studentRepository.getExamsForAStudent(id))
                System.out.println("exam id : ".concat(exam.getExamId().toString()).concat(" exam name : "));
        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");
        }
    }

    public void showAll(){
        try {
            for (Student student : studentRepository.getAll()) {
                System.out.println("student id : ".concat(student.getStudentId().toString()).concat(", student name : ").concat(student.getFirstName()).concat(" ").concat(student.getLastName()));
            }

        }catch (SQLException e) {
            Printer.print("There is problem with connecting to database:(");
        }
    }



public Student findById(Integer id) {
    try {
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.findById(id).get();
        }
    }catch (SQLException e) {
        System.out.println("There is problem with connecting to database:(");;
    }
    return null;
}

//todo:maybe its not necessary
    @Override
    public Integer findIdByUserId(Integer userId) {
        try {
            if (studentRepository.findByUserId(userId).isPresent()) {
                return studentRepository.findByUserId(userId).get();
            }
        }catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");;
        }
        return null;
    }

    public void showStudentExamsGrade(Integer id){
        try {
            for (ExamStudentGradeDto e: studentRepository.StudentExamsGrade(id))
                System.out.println( e);
        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");
        }
    }
}