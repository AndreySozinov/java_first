package project_one;

/* 1. Задана натуральная степень k. Сформировать случайным образом список
 * коэффициентов (от 0 до 100) многочлена степени k.
 * Пример: k = 2 => 2*х^2 + 4*x + 5 = 0 или x^2 + 5 = 0 или 10*х^2 = 0
 * 
 * 2. Даны два файла, в каждом из которых находится запись многочлена.
 * Сформировать файл, содержащий сумму многочленов.
*/
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class program {
    public static void main(String[] args) {
        int k = Power_k(); // Вводим степень k.
        int[] array_coeffs = Coeffs(k); // Получаем случайные коэффициенты.
        for (int el : array_coeffs) {
            System.out.println(el);
        }
        
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
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(0, 101);
        }
        return arr;
    }
}
