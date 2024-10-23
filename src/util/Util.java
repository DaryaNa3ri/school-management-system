package util;

import java.util.Scanner;

public class Util {
    private static Scanner sc = new Scanner(System.in);

    public static Integer getIntInput(String prompt) {
        System.out.print("->   " + prompt);
        return Integer.parseInt(sc.nextLine());
    }

    public static String getStringInput(String prompt) {
        System.out.print("->   " + prompt);
        return sc.nextLine();
    }

    public static double getDoubleInput(String message) {
        System.out.print("->   " +message);
        return Double.parseDouble(sc.nextLine());
    }

}
