package model.dto;

public class ExamsForATeacherDto {
    private String examTitle;
    private String teacherFullName;

    public ExamsForATeacherDto(String examTitle, String teacherFullName) {
        this.examTitle = examTitle;
        this.teacherFullName = teacherFullName;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
    }

    @Override
    public String toString() {
        return "ExamsForATeacherDto{" +
                "examTitle='" + examTitle + '\'' +
                ", teacherFullName='" + teacherFullName + '\'' +
                '}';
    }
}
