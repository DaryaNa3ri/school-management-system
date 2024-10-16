package serivce;

import model.Teacher;
import model.dto.SaveStudentRequest;
import model.dto.SaveTeacherRequest;
import model.dto.TeacherResponse;

public interface TeacherService {

    void save(SaveTeacherRequest saveTeacherRequest);

    TeacherResponse findTeacherById(int id);

    void showAll();


    //void deleteTeacherById(int id);









}
