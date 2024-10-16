package serivce.impl;

import exeption.NotFoundException;
import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import repository.CourseRepository;
import repository.ExamRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import repository.impl.StudentRepositoryImpl;
import serivce.StudentService;
import util.printer.Printer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository ;
    private ExamRepository examRepository ;
    private TeacherRepository teacherRepository ;
    private CourseRepository courseRepository ;

    public StudentServiceImpl(StudentRepository studentRepository, ExamRepository examRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository ;
        this.examRepository = examRepository ;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
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
    public void addStudentInStudentExamTable(Integer studentId, Integer examId) {
        try {
            for (Student student : getAll()) {
                if (student.getStudentId()==(studentId)) {
                    for (Exam exam : examRepository.getAll()) {
                        if (exam.getExamId()==(examId)) {
                            studentRepository.addExamInStudent(student,exam);
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
            Printer.print("something went wrong");
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
            Printer.print("something went wrong");
        }
    }

    @Override
    public void update(int studentId, String firstName, String lastName, String dob, String nationalCode, Double gpu) {
        try {
            studentRepository.saveOrUpdate(new Student(studentId,firstName,lastName,Date.valueOf(dob),nationalCode,gpu));
        }catch (SQLException e){
            Printer.print("something went wrong");
        }
    }

    @Override
    public void delete(Integer studentId) {
        try {
            studentRepository.delete(studentId);
        }catch (SQLException | NotFoundException e){
            Printer.print("database connection problem");}
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
            Printer.print("database connection failed");
        }
    }

    @Override
    public void deleteStudentFromStudentCourseTable(Integer studentId) {
        try {
            studentRepository.removeStudentFromStudentCourseTable(studentId);
        } catch (SQLException e) {
            Printer.print("database connection failed");
        }
    }

    @Override
    public void showStudentcourses(Integer id) {
        try {
            for (Course course : studentRepository.getCoursesForAStudent(id))
                System.out.println(course);
        }catch (SQLException e){
            Printer.print("database connection failed");
        }
    }

    @Override
    public void showStudentTeachers(Integer id) {
        try {
            for (Teacher teacher : studentRepository.getTeachersForAStudent(id))
                System.out.println(teacher);
        }catch (SQLException e){
            Printer.print("database connection failed");
        }
    }

    @Override
    public void showStudentExams(Integer id) {
        try {
            for (Exam exam : studentRepository.getExamsForAStudent(id))
                System.out.println(exam);
        }catch (SQLException e){
            Printer.print("database connection failed");
        }
    }

    public void showAll(){
        try {
            Printer.printAllStudents(studentRepository.getAll());

        }catch (SQLException e) {
            Printer.print("database connection failed");
        }
    }
}
