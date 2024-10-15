package serivce.impl;

import model.Teacher;
import model.dto.SaveTeacherRequest;
import model.dto.TeacherResponse;
import repository.impl.TeacherRepositoryImpl;
import serivce.TeacherService;
import util.printer.Printer;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TeacherServiceImpl implements TeacherService {
    private TeacherRepositoryImpl teacherRepository = new TeacherRepositoryImpl();


    @Override
    public void save(SaveTeacherRequest saveTeacherRequest) {
        CheckTeacher(saveTeacherRequest);
        try {
            teacherRepository.saveOrUpdate(new Teacher(
                    saveTeacherRequest.getTeacherId(),
                    saveTeacherRequest.getFirstName(),
                    saveTeacherRequest.getLastName(),
                    saveTeacherRequest.getDob(),
                    saveTeacherRequest.getNationalCode()
            ));
        } catch (SQLException e) {
            Printer.print("enter information again");
        }


    }

    public TeacherResponse findTeacherById(int id) {
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

    public void deleteTeacherById(int id) {
        try {
            for (Teacher item : teacherRepository.getAll())
                if (item.getTeacherId() == id) {
                    teacherRepository.delete(null);
                    Printer.print("teacher deleted successfully");
                }
        }catch (SQLException e){
            Printer.print("There is problem with connecting to database:(");
        }
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

    private void CheckTeacher(SaveTeacherRequest saveTeacherRequest) {
        if (saveTeacherRequest.getFirstName() == null || saveTeacherRequest.getLastName() == null || saveTeacherRequest.getNationalCode() == null) {
            throw new IllegalArgumentException("firstName and lastName and national code cannot be null");
        }
    }

}
