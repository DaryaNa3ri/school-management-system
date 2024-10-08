package repository;

import model.Teacher;
import sun.font.ScriptRunData;

import java.sql.SQLException;
import java.util.Set;

public interface TeacherRepository {
    //crud create read update delete

    //because id for object will be serial so we have to show the id of that person

    void saveOrUpdateTeacher(Teacher teacher) throws SQLException;

    Teacher findTeacherById(int id) throws SQLException;

    Set<Teacher> getAllTeachers() throws SQLException;

    void deleteTeacher(Teacher teacher)throws SQLException;

}
