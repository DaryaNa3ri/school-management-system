package model.dto;

import util.Database;

public class TeachersCourseDto {
    private String teacherName;
    private String courseName;

    public TeachersCourseDto(String teacherName, String courseName) {
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
        return "TeachersCourseDto{" +
                "teacherName='" + teacherName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
