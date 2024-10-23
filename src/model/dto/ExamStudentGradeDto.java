package model.dto;

import java.util.Objects;

public class ExamStudentGradeDto {
    private String examTitle;
    private int grade;

    public ExamStudentGradeDto(String examTitle, int grade) {
        this.examTitle = examTitle;
        this.grade = grade;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamStudentGradeDto that = (ExamStudentGradeDto) o;
        return getGrade() == that.getGrade() && Objects.equals(getExamTitle(), that.getExamTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExamTitle(), getGrade());
    }

    @Override
    public String toString() {
        return
                "examTitle='" + examTitle + '\'' +
                ", grade=" + grade;
    }
}
