package model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Exam {

    private Integer examId;
    private String examTitle;
    private Integer examUnit;
    private Date examDate;
    private Double grade;

    //then it should be in exam repository
    private Teacher teacher;

    //then it should be in exam repository
    private Course course;

    //junction table
    private Set<Student> students;

    public Exam(String examTitle) {
        this.examTitle = examTitle;
    }

    public Exam(Integer examId, String examTitle, Integer examUnit, Date examDate, Double grade) {
        this.examId = examId;
        this.examTitle = examTitle;
        this.examUnit = examUnit;
        this.examDate = examDate;
        this.grade = grade;
    }

    public Exam(String examTitle, Integer examUnit, Date examDate, Double grade) {
        this.examTitle = examTitle;
        this.examUnit = examUnit;
        this.examDate = examDate;
        this.grade = grade;
    }

    public Exam(Integer examId, String examTitle, Integer examUnit, Date examDate, Double grade, Teacher teacher, Course course, Set<Student> students) {
        this.examId = examId;
        this.examTitle = examTitle;
        this.examUnit = examUnit;
        this.examDate = examDate;
        this.grade = grade;
        this.teacher = teacher;
        this.course = course;
        if (students != null)
            this.students = students;
        else
            this.students = new HashSet<>();
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Integer getExamUnit() {
        return examUnit;
    }

    public void setExamUnit(Integer examUnit) {
        this.examUnit = examUnit;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return examId == exam.examId && Objects.equals(examTitle, exam.examTitle) && Objects.equals(examUnit, exam.examUnit) && Objects.equals(examDate, exam.examDate) && Objects.equals(grade, exam.grade) && Objects.equals(teacher, exam.teacher) && Objects.equals(course, exam.course) && Objects.equals(students, exam.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, examTitle, examUnit, examDate, grade, teacher, course, students);
    }

    @Override
    public String toString() {
        return
                "examId=" + examId +
                ", examTitle='" + examTitle +
                ", examUnit='" + examUnit +
                ", examDate=" + examDate +
                ", grade=" + grade;
    }
}
