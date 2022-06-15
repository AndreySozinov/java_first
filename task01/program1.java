package task01;
import java.util.Scanner;

public class program1 {
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

    static double Pow_method(int x, int y) {
        double xx = x; // Иначе не считает отрицательные степени.
        if (y < 0) {
            y = -y;
            xx = 1/xx;
        }
        double result = xx;
        if (xx == 1 || y == 0) result = 1;
        else do {
            if (y % 2 == 0) result = result * result; 
            else result = result * result * xx;
            y = y / 2;
        } while (y > 1);
        return result;
    }
}
