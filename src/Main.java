import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import model.Teacher;
import repository.impl.TeacherRepositoryImpl;
import serivce.impl.TeacherServiceImpl;

public class Main {
    public static void main(String[] args) {

        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
        teacherServiceImpl.printTeachersByFirstName("Akbar");

        TeacherRepositoryImpl teacherRepository = new TeacherRepositoryImpl();
        try {
        Teacher teacher = teacherRepository.findTeacherById(2);
            System.out.println(teacher);

        }catch (Exception e){
            System.out.println("ridi");
        }


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