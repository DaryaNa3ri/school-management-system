import menu.Menu;

public class Main {

    public static void main(String[] args) {

        /*کاربر ادمین بتواند درس ایجاد ویرایش کند .
         درس را به مدرس مربوطه ارتباط دهد،
آزمون ایجاد کند
دانش آموزان بتوانند کارنامه نمرات درس هایی که شرکت کرده اند را ببینند. */

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