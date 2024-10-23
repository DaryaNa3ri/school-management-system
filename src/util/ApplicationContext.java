package util;

import repository.CourseRepository;
import repository.ExamRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import repository.impl.*;
import serivce.CourseService;
import serivce.ExamService;
import serivce.StudentService;
import serivce.TeacherService;
import serivce.impl.*;

public class ApplicationContext {

        private static StudentRepository studentRepository;
        private static TeacherRepository teacherRepository;
        private static CourseRepository courseRepository;
        private static ExamRepository examRepository;
        private static UserRepositoryImpl userRepository;

        private static CourseService courseService;
        private static ExamService examService;
        private static StudentService studentService;
        private static TeacherService teacherService;
        private static UserServiceImpl userServiceImpl;

        static {
            courseRepository = new CourseRepositoryImpl(teacherRepository,studentRepository,examRepository);
            examRepository = new ExamRepositoryImpl(teacherRepository,studentRepository,courseRepository);
            studentRepository = new StudentRepositoryImpl(examRepository,courseRepository,teacherRepository);
            teacherRepository = new TeacherRepositoryImpl(courseRepository,studentRepository,examRepository);
            userRepository = new UserRepositoryImpl();

            courseService = new CourseServiceImpl(courseRepository,teacherRepository);
            examService = new ExamServiceImpl(examRepository,studentRepository);
            studentService = new StudentServiceImpl(studentRepository,examRepository,teacherRepository,courseRepository);
            teacherService = new TeacherServiceImpl(teacherRepository,courseService);
            userServiceImpl = new UserServiceImpl(userRepository);

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

        public static UserServiceImpl getUserService() {
            return userServiceImpl;
        }
}
