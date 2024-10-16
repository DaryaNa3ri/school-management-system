package util;

import repository.CourseRepository;
import repository.ExamRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import repository.impl.CourseRepositoryImpl;
import repository.impl.ExamRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import repository.impl.TeacherRepositoryImpl;
import serivce.CourseService;
import serivce.ExamService;
import serivce.StudentService;
import serivce.TeacherService;
import serivce.impl.CourseServiceImpl;
import serivce.impl.ExamServiceImpl;
import serivce.impl.StudentServiceImpl;
import serivce.impl.TeacherServiceImpl;

public class ApplicationContext {

        private static StudentRepository studentRepository;
        private static TeacherRepository teacherRepository;
        private static CourseRepository courseRepository;
        private static ExamRepository examRepository;

        private static CourseService courseService;
        private static ExamService examService;
        private static StudentService studentService;
        private static TeacherService teacherService;

        static {
            studentRepository = new StudentRepositoryImpl(examRepository,courseRepository,teacherRepository);
            teacherRepository = new TeacherRepositoryImpl(courseRepository,studentRepository,examRepository);
            courseRepository = new CourseRepositoryImpl(teacherRepository,studentRepository,examRepository);
            examRepository = new ExamRepositoryImpl(teacherRepository,studentRepository,courseRepository);

            studentService = new StudentServiceImpl(studentRepository,examRepository,teacherRepository,courseRepository);
            teacherService = new TeacherServiceImpl(teacherRepository);
            courseService = new CourseServiceImpl(courseRepository);
            examService = new ExamServiceImpl(examRepository,studentRepository);
        }

        public static StudentService getStudentService() {
            return studentService;
        }

        public static TeacherService getTeacherService() {
            return teacherService;
        }

        public static CourseService getCourseService() {
            return courseService;
        }

        public static ExamService getExamService() {
            return examService;
        }
}
