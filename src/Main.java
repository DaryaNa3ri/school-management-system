import model.Student;
import model.Teacher;
import model.dto.TeachersCourseDto;
import repository.TeacherRepository;
import serivce.CourseService;
import serivce.StudentService;
import serivce.TeacherService;
import util.Database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        TeacherService teacherService = new TeacherService();
        teacherService.printTeachersByFirstName("Akbar");



        /*INSERT INTO students (first_name, last_name, dob, national_id, gpu)
VALUES (?, ?, ?, ?, ?)
ON CONFLICT (national_id) DO UPDATE
SET first_name = EXCLUDED.first_name,
last_name = EXCLUDED.last_name,
dob = EXCLUDED.dob,
national_id = EXCLUDED.national_id,
gpu = EXCLUDED.gpu
RETURNING id*/
    }
}