package project_one;

/* 1. Задана натуральная степень k. Сформировать случайным образом список
 * коэффициентов (от 0 до 100) многочлена степени k.
 * Пример: k = 2 => 2*х^2 + 4*x + 5 = 0 или x^2 + 5 = 0 или 10*х^2 = 0
*/

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class program {
    public static void main(String[] args) {
        String polynomial = "";
        int k = Power_k(); // Вводим степень k.
        int[] array_coeffs = Coeffs(k); // Получаем массив с случайными коэффициентами.
        for (int i = k; i > 0; i--) { // Собираем строку с многочленом.
            polynomial = polynomial + Elements(array_coeffs[k-i], i); // Метод Elements выдает готовые элементы многочлена.
            if (Elements(array_coeffs[k-i], i) != "") polynomial = polynomial + " + ";
        }
        if (array_coeffs[k] == 0) polynomial = polynomial.substring(0, -1);
        else polynomial = polynomial + array_coeffs[k]; // Добавляем последний элемент.
        polynomial = polynomial + " = 0"; // Дописываем окончание многочлена.
        System.out.println(polynomial);
    }

    static int Power_k() {
        Scanner iScanner = new Scanner(System.in);
        boolean flag = false;
        while (flag == false) {
            System.out.printf("Степень k: ");
            flag = iScanner.hasNextInt();
        }
        int k = iScanner.nextInt();
        iScanner.close();
        return k;
    }

    static int[] Coeffs(int k) {
        int[] arr = new int[k+1];
        for (int i = 0; i <= k; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(0, 101);
        }
        return arr;
    }

    static String Elements(int coef, int power) {
        String result = "";
        if (coef >= 2 & power >= 2) {
            result = String.format("%d*x^%d", coef, power);
        }
        else if (coef == 1 & power >= 2) result = String.format("x^%d", power);
        else if (coef == 1 & power == 1) result = "x";
        else if (power == 1 & coef >=2) result = String.format("%d*x", coef);
        else if (power == 0 & coef >= 1) result = String.format("%d", coef);
        else if (coef == 0) result = "";
        return result;
    }
}
