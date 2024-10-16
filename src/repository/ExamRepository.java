package repository;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExamRepository extends BaseRepository<Exam> {

    Optional<Teacher> getExamsTeacher(int exam_id) throws SQLException;

    Optional<Course> getExamsCourse(int exam_id) throws SQLException;

    //Set<Exam> getAllExamsFull() throws SQLException;

    List<Student> getStudentsForAExam(int exam_id) throws SQLException;

    public void addExamInStudentExamTable(Exam exam,Student student) throws SQLException;
    void removeExamFromStudentExamTable(Integer examId) throws SQLException;



}
