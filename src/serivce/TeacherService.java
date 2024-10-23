package serivce;

import model.Course;
import model.Teacher;

public interface TeacherService {

    Teacher findTeacherById(int id);

    void showAll();


    void addNew(String firstName, String lastName, String dob, String nationalCode, Course teacherCourse);

    void addCourseInTeacherTable(Integer teacherId1, Integer courseId);

    void update(int teacherId, String firstName, String lastName, String dob, String nationalCode, Course teacherCourse);

    void delete(Integer teacherId);

    void deleteTeacherCourse(Integer teacherId1);

    void deleteTeacherFromStudentTeacherTable(Integer teacherId2);

    void deleteTeacherFromExam(Integer teacherId3);

    void showTeacherCourse(Integer id);

    void showTeacherStudents(Integer id1);

    void showTeacherExams(Integer id2);
}
