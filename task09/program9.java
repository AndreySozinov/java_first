package task09;
// Реализовать алгоритм пирамидальной сортировки (HeapSort)

import java.util.Arrays;
import java.util.Scanner;

public class program9 {
    public static void main(String[] args) {
        // Ввод параметров массива.
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите размер массива: ");
        int n = iScanner.nextInt();
        System.out.printf("Введите максимальное число: ");
        int max = iScanner.nextInt();
        iScanner.close();
        
        // Заполнение массива случайными числами и вывод на экран.
        int[] rawArray = new int [n];
        for (int i = 0; i < n; i++) {
            rawArray[i] = (int) (Math.random() * (max + 1));
        }
        System.out.println(Arrays.toString(rawArray));

        // Построение бинарного дерева и вывод на экран.
        int start = rawArray.length / 2 - 1;
        while (start >= 0) {
            HeapBuilding(rawArray, start, rawArray.length);
            start--;
        }
        System.out.println(Arrays.toString(rawArray));

        // Сортировка массива.
        System.out.println(Arrays.toString(HeapSorting(rawArray, rawArray.length)));
    }

    static int[] HeapBuilding (int[] arr, int parent, int arrayLength) {
        int child1 = parent * 2 + 1;
        int child2 = parent * 2 + 2;
        int max = 0;
        if (child1 >= arrayLength) {return arr;}
        if (child2 < arrayLength) {
            if (arr[child1] > arr[child2]) {max = child1;}
            else {max = child2;}
            if (arr[parent] < arr[max]) {
                Swap(arr, parent, max);
                HeapBuilding(arr, max, arrayLength);
            }
        }
        else {
            if (arr[parent] < arr[child1]) {
                Swap(arr, parent, child1);
                HeapBuilding(arr, child1, arrayLength);
            }
        }
        return arr;
    }

    static int[] HeapSorting (int[] arr, int arrayLength) {
        if (arrayLength == 1 || arrayLength == 0) {return arr;}
        Swap(arr, 0, arrayLength-1);
        HeapBuilding(arr, 0, arrayLength-1);
        HeapSorting(arr, arrayLength-1);
        return arr;
    }

    static void Swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
