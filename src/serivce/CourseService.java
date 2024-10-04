package serivce;

import model.Course;
import repository.CourseRepository;

import java.sql.SQLException;

public class CourseService {
    private CourseRepository courseRepository = new CourseRepository();

    public void printAllCourses(){
        try{
            for (Course course : courseRepository.getAllCourses()){
                System.out.println(course);
            }
        }catch (SQLException e){
            System.out.println("there is problem to connecting to the database:(");
        }
    }

    public void printCountOfCourses(){
        try{
            System.out.println("# courses : " + courseRepository.getCountOfAllCourses());
        }catch (SQLException e){
            System.out.println("there is problem to connecting to the database:(");
        }
    }
}
