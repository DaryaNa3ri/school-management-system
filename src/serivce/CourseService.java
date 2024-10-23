package serivce;

import model.Course;

public interface CourseService {
    void showAll();

    Course findById(Integer id);

    void addNew(String title, Integer unit);

    void addCourseToTeacher(Integer courseId, Integer teacherId);
}
