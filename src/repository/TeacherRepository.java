package repository;

import model.Exam;
import model.Teacher;
import model.dto.TeachersForACourseDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface TeacherRepository {
    //crud create read update delete

    //because id for object will be serial so we have to show the id of that person

    void saveOrUpdateTeacher(Teacher teacher) throws SQLException;

    Teacher findTeacherById(int id) throws SQLException;

    Set<Teacher> getAllTeachers() throws SQLException;

    void deleteTeacher(Teacher teacher)throws SQLException;

    List<TeachersForACourseDto> getTeachersForACourse() throws SQLException;


    List<Exam> getTeacherExams(int id) throws SQLException;
    //==========================
    int getCountOfTeachers() throws SQLException;



}
