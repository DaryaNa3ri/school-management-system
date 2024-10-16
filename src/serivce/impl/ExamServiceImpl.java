package serivce.impl;

import exeption.NotFoundException;
import model.Course;
import model.Exam;
import model.Student;
import model.Teacher;
import repository.ExamRepository;
import repository.StudentRepository;
import serivce.ExamService;
import util.ApplicationContext;
import util.printer.Printer;

import java.sql.SQLException;
import java.sql.Date;
import java.util.Optional;
import java.util.Set;

public class ExamServiceImpl implements ExamService {
    private ExamRepository examRepository;
    private StudentRepository studentRepository;

    public ExamServiceImpl(ExamRepository examRepository, StudentRepository studentRepository) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public void addNew(String title, Integer unit, Double grade, String date) {
        try {
            examRepository.saveOrUpdate(new Exam(title, unit, Date.valueOf(date), grade));
        } catch (SQLException e) {
            Printer.print("Please enter information again :");
        }
    }

    @Override
    public void addExamInStudentExamTable(Integer examId, Integer studentId) {
        try {
            for (Exam exam : examRepository.getAll())
                if (exam.getExamId() == examId)
                    for (Student student : studentRepository.getAll())
                        if (student.getStudentId() == studentId)
                            examRepository.addExamInStudentExamTable(exam, student);
        } catch (SQLException e) {
            Printer.print("database connection failed");
        }
    }

    @Override
    public void addNew() {

    }

    @Override
    public void update() {

    }

    @Override
    public void update(Integer id, String title, Integer unit, Double grade, String date) {
        try {
            examRepository.saveOrUpdate(new Exam(id, title, unit, Date.valueOf(date), grade));

        } catch (SQLException e) {
            Printer.print("database connection failed");
        }
    }



@Override
public void delete(Integer id) {
    try {
        examRepository.delete(id);
    } catch (SQLException | NotFoundException e) {
        Printer.print("database connection failed");
    }
}

@Override
public void deleteExamFromStudentExamList(Integer id) {
    try {
        examRepository.removeExamFromStudentExamTable(id);
    } catch (SQLException e) {
        Printer.print("database connection failed");
    }
}

@Override
public void deleteExamFromTeacherExamList() {

}

@Override
public void deleteExamFromCourseExamList() {

}

@Override
public void showAll() {
    try {
        Printer.printAllExams(examRepository.getAll());
    } catch (SQLException e) {
        Printer.print("database connection failed");
    }
}

@Override
public void showExamStudents(Integer id) {
    try {
        for (Student student : examRepository.getStudentsForAExam(id))
            System.out.println(student);
    } catch (SQLException e) {
        Printer.print("database connection failed");
    }

}

@Override
public void showExamTeacher(Integer id) {
    try {
        Optional<Teacher> optionalTeacher = examRepository.getExamsTeacher(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            System.out.println(teacher);
        } else System.out.println("No teacher found");
    } catch (SQLException e) {
        Printer.print("database connection failed");
    }
}

@Override
public void showExamCourse(Integer id) {
    try {

        Optional<Course> optionalCourse = examRepository.getExamsCourse(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            System.out.println(course);
        } else System.out.println("No course found");
    } catch (SQLException e) {
        Printer.print("database connection failed");
    }
}

@Override
public Set<Exam> getAll() {
    Set<Exam> examSet = null;
    try {
        examSet = examRepository.getAll();
    } catch (SQLException e) {
        Printer.print("something went wrong");
    }
    return examSet;
}
}
