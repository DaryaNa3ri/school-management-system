package model;

import java.sql.Date;
import java.util.*;

public class Teacher {
    private Integer teacherId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String nationalCode;
    //it most be in teacher repository
    private Course course;
    //junction table
    private Set<Student> students;
    //it most be in exam repository
    private List<Exam> exams;

    public Teacher(Integer teacherId, String firstName, String lastName, Date dob, String nationalCode,Course teacherCourse) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.course = teacherCourse;
    }

    public Teacher(Integer teacherId, String firstName, String lastName, Date dob, String nationalCode) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
    }

    public Teacher(String firstName, String lastName, Date dob, String nationalCode, Course teacherCourse) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.course = teacherCourse;
    }

    public Teacher(Integer teacherId, String firstName, String lastName, Date dob, String nationalCode, Course course, Set<Student> students, List<Exam> exams) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.course = course;
        if (students != null)
            this.students = students;
        else
            this.students = new HashSet<>();
        if (exams != null)
            this.exams = exams;
        else
            this.exams = new ArrayList<>();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
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

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId) && Objects.equals(nationalCode, teacher.nationalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, nationalCode);
    }

    @Override
    public String toString() {
        return
                "teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", nationalCode='" + nationalCode ;
    }
}
