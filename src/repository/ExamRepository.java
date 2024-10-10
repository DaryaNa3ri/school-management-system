package repository;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.Set;

public interface ExamRepository {

    void saveOrUpdate(Exam exam) throws SQLException;

    Exam findExamById(int id) throws SQLException;

    void deleteExam(Exam exam) throws SQLException;

    Teacher getExamsTeacher(int exam_id) throws SQLException;

    Course getExamsCourse(int exam_id) throws SQLException;

    Set<Exam> getAllExamsFull() throws SQLException;

    Set<Exam> getAllExams() throws SQLException;

    Set<Student> getStudentsForAExam(int exam_id) throws SQLException;






}
