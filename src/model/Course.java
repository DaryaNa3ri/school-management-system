package model;

import java.util.Objects;

public class Course {
    private Integer CourseId;
    private String courseTitle;
    private Integer courseUnit;

    public Course(Integer courseId, String courseTitle, Integer courseUnit) {
        this.CourseId = courseId;
        this.courseTitle = courseTitle;
        this.courseUnit = courseUnit;
    }

    public Integer getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Integer getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(Integer courseUnit) {
        this.courseUnit = courseUnit;
    }

    //just id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return CourseId == course.CourseId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(CourseId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "CourseId=" + CourseId +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseUnit=" + courseUnit +
                '}';
    }
}
