import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import model.Teacher;
import model.dto.SaveTeacherRequest;
import model.dto.TeacherResponse;
import repository.impl.TeacherRepositoryImpl;
import serivce.impl.TeacherServiceImpl;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
        for (TeacherResponse teacherResponse : teacherServiceImpl.getAllTeachers()) {
            System.out.println(teacherResponse);
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