package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Course {
    private Integer CourseId;
    private String courseTitle;
    private Integer courseUnit;
    //teachers are in teacher repository
    private Set<Teacher> teachers;
    //junction table
    private Set<Student> students;
    //exams are in exam repository
    private Set<Exam> exams;

    public Course(Integer courseId, String courseTitle, Integer courseUnit) {
        CourseId = courseId;
        this.courseTitle = courseTitle;
        this.courseUnit = courseUnit;
    }

    public Course(String courseTitle, Integer courseUnit) {
        this.courseTitle = courseTitle;
        this.courseUnit = courseUnit;
    }

    public Course(Integer courseId, String courseTitle, Integer courseUnit, Set<Teacher> teachers, Set<Student> students, Set<Exam> exams) {
        CourseId = courseId;
        this.courseTitle = courseTitle;
        this.courseUnit = courseUnit;
        if (teachers != null) {
            this.teachers = teachers;}
        else
            this.teachers = new HashSet<>();
        if (students != null)
            this.students = students;
        else
            this.students = new HashSet<>();
        if (exams != null)
            this.exams = exams;
        else exams = new HashSet<>();
    }

    public Integer getCourseId() {
        return CourseId;
    }

    public void setCourseId(Integer courseId) {
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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(CourseId, course.CourseId) && Objects.equals(courseTitle, course.courseTitle) && Objects.equals(courseUnit, course.courseUnit) && Objects.equals(teachers, course.teachers) && Objects.equals(students, course.students) && Objects.equals(exams, course.exams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CourseId, courseTitle, courseUnit, teachers, students, exams);
    }

    @Override
    public String toString() {
        return
                "CourseId=" + CourseId +
                ", courseTitle='" + courseTitle +
                ", courseUnit=" + courseUnit;
    }
}
