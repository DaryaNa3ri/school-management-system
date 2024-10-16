package model;

import javax.security.auth.Subject;
import java.sql.Date;
import java.util.*;

public class Student {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String nationalCode;
    private Double gpu;
    //junction table
    private List<Exam> exams;
    //junction table
    private Set<Course> courses;
    //junction table
    private Set<Teacher> teachers;

    public Student(Integer studentId, String firstName, String lastName, Date dob, String nationalCode, Double gpu) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.gpu = gpu;
    }

    public Student(String firstName, String lastName, Date dob, String nationalCode, Double gpu) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.gpu = gpu;
    }

    public Student(Integer studentId, String firstName, String lastName, Date dob, String nationalCode, Double gpu, List<Exam> exams, Set<Course> courses, Set<Teacher> teachers) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.gpu = gpu;
        if (exams != null)
            this.exams = exams;
        else
            this.exams = new ArrayList<>();
        if (courses != null)
            this.courses = courses;
        else
            this.courses = new HashSet<>();
        if (teachers != null)
            this.teachers = teachers;
        else
            this.teachers = new HashSet<>();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public Double getGpu() {
        return gpu;
    }

    public void setGpu(Double gpu) {
        this.gpu = gpu;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(nationalCode, student.nationalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, nationalCode);
    }

    @Override
    public String toString() {
        return
                "studentId = " + studentId +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", dob = " + dob +
                ", nationalCode = " + nationalCode +
                ", gpu = " + gpu;
    }
}
