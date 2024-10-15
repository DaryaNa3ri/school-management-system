package repository;

import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.Set;

public interface ExamRepository extends BaseRepository<Exam> {

    Teacher getExamsTeacher(int exam_id) throws SQLException;

    Course getExamsCourse(int exam_id) throws SQLException;

    Set<Exam> getAllExamsFull() throws SQLException;

    Set<Student> getStudentsForAExam(int exam_id) throws SQLException;






}
