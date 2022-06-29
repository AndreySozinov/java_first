// Сортировка слиянием
package task06;

import java.util.Arrays;
import java.util.Scanner;

public class program6 {
    public static void main(String[] args) {
        // Ввод данных.
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите размер массива: ");
        int n = iScanner.nextInt();
        System.out.printf("Введите минимальное число: ");
        int min = iScanner.nextInt();
        System.out.printf("Введите максимальное число: ");
        int max = iScanner.nextInt();
        iScanner.close();
        
        int[] rawArray = new int [n];
        for (int i = 0; i < n; i++) {
            rawArray[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
        
        TestSorting(MergeSort(rawArray));
        //System.out.println(Arrays.toString(rawArray));
        //System.out.println(Arrays.toString(ArrayMerging(rawArray)));
    }

    static int[] MergeSort (int[] arr) {
        if (arr.length <= 1) {return arr;}
        int[] left = MergeSort(Arrays.copyOfRange(arr, 0, arr.length/2));
        int[] right = MergeSort(Arrays.copyOfRange(arr, arr.length/2, arr.length));
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < left.length && j < right.length) {
            if (left[i] >= right[j]) {
                arr[index] = right[j];
                j++;
                index++;
            }
            else {
                arr[index] = left[i];
                i++;
                index++;
            }
        }
        while (i < left.length) {
            arr[index] = left[i];
            i++;
            index++;
        }
        while (j < right.length) {
            arr[index] = right[j];
            j++;
            index++;
        }
        return arr;
    }

    static void TestSorting (int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length-1; i++) {
            if (arr[i] > arr[i+1]) {
                System.out.println("wrong!");
                return;
            }
        }
    System.out.println("true");
    }
}
