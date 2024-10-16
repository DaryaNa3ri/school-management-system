package serivce;

import model.Exam;

import java.text.ParseException;
import java.util.Set;

public interface ExamService extends BaseService{

    void addNew(String title,Integer unit ,Double grade ,String date) throws ParseException;

    void addExamInStudentExamTable(Integer examId, Integer studentId);

    void deleteExamFromStudentExamList(Integer id);

    void deleteExamFromTeacherExamList();

    void deleteExamFromCourseExamList();

    void showExamStudents(Integer id);

    void showExamTeacher(Integer id);

    void showExamCourse(Integer id);

    Set<Exam> getAll();

    void update(Integer id, String title, Integer unit, Double grade, String date);
}
