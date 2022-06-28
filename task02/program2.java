package task02;

import java.util.Scanner;

public class program2 {
    public static void main(String[] args) {
        // Ввод данных.
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите исходные число a: ");
        int a = iScanner.nextInt();
        System.out.printf("Введите целевое число b: ");
        int b = iScanner.nextInt();
        System.out.printf("Введите множитель c: ");
        int mult = iScanner.nextInt();
        System.out.printf("Введите слагаемое d: ");
        int sumend = iScanner.nextInt();
        iScanner.close();

        if (a > b) System.out.println("Решения нет");
        
        Transformation(a, b, mult, sumend, "");

    }

    static void Transformation (int a, int b, int c, int d, String res) {
        if (a > b) return;
        if (a == b) System.out.println(res);
        Transformation(a * c, b, c, d, res + "k1 ");
        Transformation(a + d, b, c, d, res + "k2 ");
    }
}
