package repository;

import model.Exam;

import java.sql.SQLException;
import java.util.Set;

public interface ExamRepository {
    Set<Exam> getAllExams() throws SQLException;
}
