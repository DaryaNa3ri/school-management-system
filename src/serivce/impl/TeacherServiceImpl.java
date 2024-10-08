package serivce.impl;

import model.Teacher;
import model.dto.SaveTeacherRequest;
import model.dto.TeachersCourseDto;
import repository.impl.TeacherRepositoryImpl;
import serivce.TeacherService;

import java.sql.SQLException;

public class TeacherServiceImpl implements TeacherService {
    private TeacherRepositoryImpl teacherRepository = new TeacherRepositoryImpl();


    @Override
    public void save(SaveTeacherRequest saveTeacherRequest) {
        CheckTeacher(saveTeacherRequest);
        try {
            teacherRepository.saveOrUpdateTeacher(new Teacher(
                    saveTeacherRequest.getTeacherId(),
                    saveTeacherRequest.getFirstName(),
                    saveTeacherRequest.getLastName(),
                    saveTeacherRequest.getDob(),
                    saveTeacherRequest.getNationalCode(),
                    saveTeacherRequest.getCourseId()
            ));
        }catch (SQLException e) {
            System.out.println("enter information again");
        }
    }

    private void CheckTeacher(SaveTeacherRequest saveTeacherRequest) {
        if (saveTeacherRequest.getFirstName() == null || saveTeacherRequest.getLastName() == null || saveTeacherRequest.getNationalCode() == null) {
            throw new IllegalArgumentException("firstName and lastName and national code cannot be null");
        }
    }




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
