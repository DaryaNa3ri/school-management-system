package model;

import java.util.Date;
import java.util.Objects;

public class Exam {
    private int exam_id;
    private Date exam_date;
    private Integer grade;
    private Teacher teacher;
    private Course course;

    public Exam(int exam_id, Date exam_date, Integer grade, Teacher teacher, Course course) {
        this.exam_id = exam_id;
        this.exam_date = exam_date;
        this.grade = grade;
        this.teacher = teacher;
        this.course = course;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public Date getExam_date() {
        return exam_date;
    }

    public void setExam_date(Date exam_date) {
        this.exam_date = exam_date;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    //just id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return exam_id == exam.exam_id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exam_id);
    }
}
