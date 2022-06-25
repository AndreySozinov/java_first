// Ханойская башня.

package task04;

import java.util.Scanner;

public class program {
    public static void main(String[] args) {
        // Ввод данных.
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите количество блинов Ханойской башни: ");
        int n = iScanner.nextInt();
        iScanner.close();

        Hanoi_tower("A", "C", "B", n);
    }
    static void Hanoi_tower (String from, String to, String buffer, int x) {
        if (x == 0) return;
        Hanoi_tower(from, buffer, to, x - 1);
        Move (from, to, x);
        Hanoi_tower(buffer, to, from, x - 1);
    }

    static void Move (String from, String to, int x) {
        System.out.printf("Диск № %d с %s на %s\n", x, from, to);
    }
}
