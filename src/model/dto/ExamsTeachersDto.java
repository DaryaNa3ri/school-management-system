package model.dto;

import java.util.Set;

public class ExamsTeachersDto {
    private String teacherName;
    private String examTitle;

    public ExamsTeachersDto(String teacherName, String examTitle) {
        this.teacherName = teacherName;
        this.examTitle = examTitle;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    @Override
    public String toString() {
        return "ExamsTeachersDto{" +
                "teacherName='" + teacherName + '\'' +
                ", examTitle='" + examTitle + '\'' +
                '}';
    }
}
