package util;

import java.util.Scanner;

public class Util {
    private static Scanner sc = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        System.out.print("->   " + prompt);
        return sc.nextInt();
    }

    public static String getStringInput(String prompt) {
        System.out.print("->   " + prompt);
        return sc.nextLine();
    }

}
