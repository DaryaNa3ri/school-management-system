package serivce.impl;

import model.Course;
import repository.CourseRepository;
import repository.impl.CourseRepositoryImpl;
import serivce.CourseService;
import util.printer.Printer;

import java.sql.SQLException;

public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository ;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
