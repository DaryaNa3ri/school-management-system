package serivce.impl;

import model.Course;
import repository.impl.CourseRepositoryImpl;

import java.sql.SQLException;

public class CourseServiceImpl {
    private CourseRepositoryImpl courseRepository = new CourseRepositoryImpl();

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
