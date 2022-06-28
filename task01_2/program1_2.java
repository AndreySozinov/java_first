package task01_2;

import java.util.Scanner;

public class program {
    public static void main(String[] args) {
        // Ввод данных.
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите основание степени a: ");
        int a = iScanner.nextInt();
        System.out.printf("Введите показатель степени b: ");
        int b = iScanner.nextInt();
        iScanner.close();

        // Условия остановки вычисления.
        if (a == 0 && b == 0) System.out.println("Результат неопределен.");

        // Возведение в степень.
        else System.out.printf("Результат: %f", Pow_method(a, b));
    }

    static double Pow_method(double x, int y) {
        if (y == 0) return 1;
        if (y < 0) {
            x = 1 / x;
            y = -y;}
        double result = Pow_method(x, y/2);
        return y % 2 == 0 ? result * result : result * result * x;
    }
}
