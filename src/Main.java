import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import menu.Menu;
import model.Teacher;
import model.dto.SaveTeacherRequest;
import model.dto.TeacherResponse;
import repository.ExamRepository;
import repository.TeacherRepository;
import repository.impl.ExamRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import repository.impl.TeacherRepositoryImpl;
import serivce.StudentService;
import serivce.TeacherService;
import serivce.impl.ExamServiceImpl;
import serivce.impl.StudentServiceImpl;
import serivce.impl.TeacherServiceImpl;
import util.printer.Printer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class Main {
    Double a ;
    Double b ;
    public static void main(String[] args) {

        //Double average = (a, b) -> (a + b) / 2;

       /* BiFunction<Double,Double,Double> average = (a, b) -> (a + b) / 2;
        Double result = average.apply(4.0,10.0);
        System.out.println(result);

        TeacherService teacherService = new TeacherServiceImpl();
        teacherService.findTeacherById(12);*/

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        Menu.startMenu();


















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