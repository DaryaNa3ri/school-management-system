package model.dto;

public class TeachersForACourseDto {
    private String teacherName;
    private String courseName;

    public TeachersForACourseDto(String teacherName, String courseName) {
        this.teacherName = teacherName;
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "TeachersForACourseDto{" +
                "teacherName='" + teacherName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
