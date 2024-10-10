import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import model.Teacher;
import model.dto.SaveTeacherRequest;
import model.dto.TeacherResponse;
import repository.impl.TeacherRepositoryImpl;
import serivce.impl.TeacherServiceImpl;

import java.sql.Date;
import java.util.function.BiFunction;

public class Main {
    Double a ;
    Double b ;
    public static void main(String[] args) {

        //Double average = (a, b) -> (a + b) / 2;

        BiFunction<Double,Double,Double> average = (a, b) -> (a + b) / 2;
        Double result = average.apply(4.0,10.0);
        System.out.println(result);













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