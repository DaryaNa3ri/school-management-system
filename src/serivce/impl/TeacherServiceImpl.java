package serivce.impl;

import exeption.NotFoundException;
import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import model.dto.SaveTeacherRequest;
import model.dto.TeacherResponse;
import repository.CourseRepository;
import repository.TeacherRepository;
import repository.impl.TeacherRepositoryImpl;
import serivce.CourseService;
import serivce.TeacherService;
import util.printer.Printer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository ;
    private CourseService courseService ;

    public TeacherServiceImpl(TeacherRepository teacherRepository,CourseService courseService) {
        this.teacherRepository = teacherRepository;
        this.courseService = courseService;

    }



    public Teacher findTeacherById(int id) {
        try {
            Optional<Teacher> item = teacherRepository.findById(id);
            if (item != null) {
                TeacherResponse teacher ;

                Printer.print("<<teacher found>>");
                return null;
            }
            else if (item == null) {
                Printer.print("teacher not found");
                return null;}

        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");
        }
        return null;
    }

    public Set<TeacherResponse> getAll() {
        Set<TeacherResponse> teachers = new HashSet<>();
        try{
            for (Teacher item : teacherRepository.getAll()) {
                teachers.add(new TeacherResponse(
                        item.getTeacherId(),
                        item.getFirstName(),
                        item.getLastName()
                ));
            }
        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");
        }
        return teachers;
    }

    public void showAll(){
        try {
            Printer.printAllTeachers(teacherRepository.getAll());
        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");
        }
    }

    @Override
    public void addNew(String firstName, String lastName, String dob, String nationalCode, Course teacherCourse) {
        try {
            teacherRepository.saveOrUpdate(new Teacher(firstName,lastName, Date.valueOf(dob),nationalCode, teacherCourse));
        }catch (SQLException e) {
            Printer.print("something went wrong");
        }
    }

    @Override
    public void addCourseInTeacherTable(Integer teacherId, Integer courseId) {
        Teacher teacher = findTeacherById(teacherId);
        Course course = courseService.findById(courseId);
        teacher.setCourse(course);
    }

    @Override
    public void update(int teacherId, String firstName, String lastName, String dob, String nationalCode, Course teacherCourse) {
        try {
            teacherRepository.saveOrUpdate(new Teacher(teacherId,firstName,lastName, Date.valueOf(dob) ,nationalCode, teacherCourse));
        } catch (SQLException e) {
            Printer.print("something went wrong");
        }
    }

    @Override
    public void delete(Integer teacherId) {
        try {
            teacherRepository.delete(teacherId);
        } catch (SQLException | NotFoundException e) {
            System.out.println("There is problem with connecting to database:(");;
        }
    }

    @Override
    public void deleteTeacherCourse(Integer teacherId) {
        try {
            teacherRepository.deleteTeacherCourse(teacherId);
        }catch (SQLException e){
            System.out.println("There is problem with connecting to database:(");;
        }
    }

    @Override
    public void deleteTeacherFromStudentTeacherTable(Integer teacherId) {
        try {
            teacherRepository.removeTeacherFromStudentTeacherTable(teacherId);
        }catch (SQLException e){
            System.out.println("There is problem with connecting to database:(");;
        }
    }

    @Override
    public void deleteTeacherFromExam(Integer teacherId) {
        try {
            teacherRepository.removeTeacherFromExamTable(teacherId);
        }catch (SQLException e){
            System.out.println("There is problem with connecting to database:(");;
        }
    }

    @Override
    public void showTeacherCourse(Integer id) {
        try {
            if (teacherRepository.getTeacherCourse(id).isPresent()) {
                System.out.println(teacherRepository.getTeacherCourse(id).get());
            }

        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");;
        }
    }

    @Override
    public void showTeacherStudents(Integer id1) {
        try {
            for (Student student : teacherRepository.getStudentsOfATeacher(id1)) {System.out.println(student);}

        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");;
        }
    }

    @Override
    public void showTeacherExams(Integer id2) {
        try {
            for (Exam exam : teacherRepository.getATeacherExams(id2)) {System.out.println(exam);}
        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");;
        }
    }

    private void CheckTeacher(SaveTeacherRequest saveTeacherRequest) {
        if (saveTeacherRequest.getFirstName() == null || saveTeacherRequest.getLastName() == null || saveTeacherRequest.getNationalCode() == null) {
            throw new IllegalArgumentException("firstName and lastName and national code cannot be null");
        }
    }

}
