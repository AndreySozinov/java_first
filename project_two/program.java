package project_two;
/* 2. Даны два файла, в каждом из которых находится запись многочлена.
 * Сформировать файл, содержащий сумму многочленов.
 * */

import java.io.*;
import java.util.ArrayList;

public class program {
    public static void main(String[] args) throws Exception {
        // Читаем файлы.
        BufferedReader br1 = new BufferedReader(new FileReader("project_two/polynom1.txt"));
        String polynom1 = br1.readLine();
        BufferedReader br2 = new BufferedReader(new FileReader("project_two/polynom2.txt"));
        String polynom2 = br2.readLine();
        br1.close();
        br2.close();

        // Переводим строки в массивы и удаляем лишнее (+, = 0).
        String[] poly_arr1 = Str_to_arr(polynom1);
        String[] poly_arr2 = Str_to_arr(polynom2);

        // Складываем элементы многочленов и записываем в список.
        ArrayList<String> list_of_sum = Summing(poly_arr1, poly_arr2);

        // Формируем строку.
        String finish = "";
        for (int i = 0; i < list_of_sum.size() - 1; i++) {
            finish = finish + list_of_sum.get(i) + " + ";
        }
        finish = finish + list_of_sum.get(-1) + " = 0";

        System.out.println(finish);

        // Записываем в файл.
        try (FileWriter fw = new FileWriter("result_polynom.txt", false)) {
            fw.write(finish);
            fw.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static String[] Str_to_arr(String str) {
        str = str.substring(0, str.length()-4); // Отрезаем "= 0".
        String[] arr = str.split(" "); // Разбиваем строку по пробелам.
        String[] res = new String[(arr.length+1)/2];
        int index = 0;
        for (int i = 0; i < arr.length; i += 2) { // Убираем плюсы.
            res[index] = arr[i];
            index++;
        }
        return res;
    }

    static ArrayList<String> Summing(String[] poly1, String[] poly2) {
        ArrayList<String> result = new ArrayList<String>();
        int length = 0;
        if (poly1.length > poly2.length) length = poly1.length;
        else length = poly2.length;
        int i = 0;
        int j = 0;
        while (i < length & j < length) {
            if (poly1[i].contains("^") & poly2[j].contains("^")) {
                int pow1 = Integer.parseInt(poly1[i].substring(poly1[i].indexOf('^'), poly1[i].length()));
                int pow2 = Integer.parseInt(poly2[j].substring(poly2[j].indexOf('^'), poly2[j].length()));
                if (pow1 == pow2) {
                    result.add(Sum(poly1[i], poly2[j]));
                    i++;
                    j++;
                }
                else if (pow1 > pow2) {
                    result.add(poly1[i]);
                    i++;
                }
                else {
                    result.add(poly2[j]);
                    j++;
                }
            }
            else if (poly1[i].contains("^")) {
                result.add(poly1[i]);
                i++;
            }
            else if (poly2[j].contains("^")) {
                result.add(poly2[j]);
                j++;
            }
            else if (poly1[i].contains("*") & poly2[j].contains("*")) {
                result.add(Sum(poly1[i], poly2[j]));
                i++;
                j++;
            }
            else if (poly1[i].contains("*")) {
                result.add(poly1[i]);
                i++;
            }
            else if (poly2[j].contains("*")) {
                result.add(poly2[j]);
                j++;
            }
            else if (!poly1[i].contains("x") & !poly2[j].contains("x")) {
                result.add(Sum(poly1[i], poly2[j]));
                i++;
                j++;
            }
            else if (!poly1[i].contains("x")) {
                result.add(poly1[i]);
                i++;
            }
            else if (!poly2[j].contains("x")) {
                result.add(poly2[j]);
                j++;
            }
        }
        return result;
    }

    static String Sum(String elem1, String elem2) {
        String result = "";
        int coef = 0;
        if (elem1.contains("*")){
            int coef1 = Integer.parseInt(elem1.substring(0, elem1.indexOf('*')));
            int coef2 = Integer.parseInt(elem2.substring(0, elem2.indexOf('*')));
            coef = coef1 + coef2;
        if (elem1.contains("^")){
            int power = Integer.parseInt(elem1.substring(elem1.indexOf('^'), elem1.length()));
            result = coef + "*x^" + power;
        }
        else if (elem1.contains("x")){
            result = coef + "*x";
        }
        else {
            coef1 = Integer.parseInt(elem1);
            coef2 = Integer.parseInt(elem2);
            coef = coef1 + coef2;
            result = result + coef;
        }
        return result;
    }
}}
