package serivce.impl;

import model.Course;
import model.Teacher;
import repository.CourseRepository;
import repository.TeacherRepository;
import repository.impl.CourseRepositoryImpl;
import serivce.CourseService;
import util.printer.Printer;

import java.sql.SQLException;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository ;
    private TeacherRepository teacherRepository ;

    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public Course findById(Integer id) {
        try {
            if (courseRepository.findById(id).isPresent()) {
                return courseRepository.findById(id).get();
            }

        }catch (SQLException e) {
            System.out.println("something went wrong");;
        }
        return null;
    }

    @Override
    public void addNew(String title, Integer unit) {
        try {
            courseRepository.saveOrUpdate(new Course(title,unit));
        }catch (SQLException e) {
            System.out.println("");;
        }
    }

    @Override
    public void addCourseToTeacher(Integer courseId, Integer teacherId) {
        Course course = findById(courseId);
        try {
            Optional<Teacher> teacher = teacherRepository.findById(teacherId);
            if (teacher.isPresent()) {
                //course.getTeachers().add(teacher.get());
                teacher.get().setCourse(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public void printAllCourses(){
        try{
            for (Course course : courseRepository.getAllCourses()){
                System.out.println(course);
            }
        }catch (SQLException e){
            System.out.println("there is problem to connecting to the database:(");
        }
    }*/

    public void printCountOfCourses(){
        try{
            System.out.println("# courses : " + courseRepository.getCountOfAllCourses());
        }catch (SQLException e){
            System.out.println("there is problem to connecting to the database:(");
        }
    }

    public void showAll(){
        try {
            Printer.printAllCourses(courseRepository.getAll());
        }catch (SQLException e){
            System.out.println("there is problem to connecting to the database:(");
        }
    }


}
