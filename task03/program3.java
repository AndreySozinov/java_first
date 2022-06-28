// Треугольные числа.

package task03;

import java.util.Scanner;

public class program3 {
    public static void main(String[] args) {
        // Ввод данных.
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите порядковый номер n треугольного числа: ");
        int n = iScanner.nextInt();
        iScanner.close();

        System.out.println(Trinumber(n));

    }
    static int Trinumber (int a) {
        //System.out.println((a * (a + 1)) / 2);
        if (a == 1) return 1;
        return a + Trinumber(a - 1);
    }
}
