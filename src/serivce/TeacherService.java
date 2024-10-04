package serivce;

import model.Teacher;
import model.dto.TeachersCourseDto;
import repository.TeacherRepository;

import java.sql.SQLException;

public class TeacherService {
    private TeacherRepository teacherRepository = new TeacherRepository();

    public void printAllTeacherList() {
        try {
            for (Teacher teacher : teacherRepository.getAllTeachers()) {
                System.out.println(teacher);
            }
        } catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }

    public void printCountOfTeachers() {
        try {
            System.out.println("# teachers : " + teacherRepository.getCountOfTeachers());
        } catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }

    public void printTeachersCourse(){
        try {
            for (TeachersCourseDto item : teacherRepository.getTeachersCourse())
                System.out.println(item);
        }catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }

    public void printTeachersByFirstName(String firstName) {
        try {
            for (Teacher item : teacherRepository.getTeacherByFirstName(firstName))
                System.out.println(item);

        }catch (SQLException e) {
            System.out.println("There is problem with connecting to database:(");
        }
    }


}
