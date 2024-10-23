package serivce;

import model.Student;

import java.util.Set;

public interface StudentService  {

    Set<Student> getAll();
    void showAll();
     void addNew(String firstName, String lastName, String dob , String nationalCode, Double gpu);

    void addStudentInStudentExamTable(Integer studentId, Integer examId,Integer grade);

    void addStudentInStudentTeacherTable(Integer studentId1, Integer teacherId);

    void addStudentInStudentCourseTable(Integer studentId2, Integer courseId);

    void update(int studentId, String firstName, String lastName, String dob, String nationalCode, Double gpu);

    void delete(Integer studentId);

    void deleteStudentFromStudentExamList(Integer studentId1);

    void deleteStudentFromStudentTeacherTable(Integer studentId2);

    void deleteStudentFromStudentCourseTable(Integer studentId3);

    void showStudentcourses(Integer id);

    void showStudentTeachers(Integer id1);

    void showStudentExams(Integer id2);

    Student findById(Integer id);

    Integer findIdByUserId(Integer userId);

    void showStudentExamsGrade(Integer id);
}
