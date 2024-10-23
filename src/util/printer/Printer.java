package util.printer;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import repository.TeacherRepository;
import serivce.ExamService;
import serivce.StudentService;

import java.sql.SQLException;
import java.util.Set;

public class Printer {
    private TeacherRepository teacherRepository;
    private ExamService examService;
    private StudentService studentService;

    public static void print(String message){
        System.out.println(message);
    }

    public static void printAllExams(Set<Exam> exams){
        try {
            exams.forEach(System.out::println);
        }catch (Exception e){
            print("Error");
        }
    }

    public static void printAllTeachers(Set<Teacher> teachers){
        try {
            teachers.forEach(System.out::println);
        }catch (Exception e){
            print("Error");
        }
    }

    public static void printAllCourses(Set<Course> courses){
        try {
            courses.forEach(System.out::println);
        }catch (Exception e){
            print("Error");
        }
    }

    public static void printAllStudents(Set<Student> students){
        try{
            students.forEach(student -> System.out.println(student));
        }catch (Exception e){
            print("Error");
        }
    }

    /*public void printAllTeacherList() {
        try {
            for (Teacher teacher : teacherRepository.getAllTeachers()) {
                System.out.println(teacher);
            }
        } catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }
*/
    public void printCountOfTeachers() {
        try {
            System.out.println("# teachers : " + teacherRepository.getCountOfTeachers());
        } catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }

    /*public void printTeacherCourse() {
        try {
            for (Teacher teacher : teacherRepository.getAllTeachers()) {
                for (Teacher item : teacherRepository.getTeacherCourse(teacher.getTeacherId()))
                System.out.println(item);
            }
        } catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }*/

}
