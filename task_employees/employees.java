package task_employees;
// Список сотрудников некой фирмы хранится в виде, например, такой HashMap
// HashMap<Integer, ArrayList<String>> persons = new HashMap<>();
// где ключ - серия и номер паспорта сотрудника, а в списке хранятся его параметры
// 1) Написать метод, возвращающий всех однофамильцев (или тёзок)
// 2) Написать метод, возвращающий сотрудников, старше (младше) определенного возраста
// Note: Усложнение: хранить дату рождения в виде String определенного формата 
// (например, DD-MM-YYYY), при вычислении преобразовывать в "датовый" тип 
// и вычислять возраст.
// Упрощение: хранить "готовый" возраст

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class employees {

    public static HashMap<Integer, ArrayList<String>> persons = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<String> Petrov = new ArrayList<>(Arrays.asList("Петров","Петр","12-09-1999"));
        ArrayList<String> Ivanov = new ArrayList<>(Arrays.asList("Иванов","Василий","21-12-2005"));
        ArrayList<String> Semenov = new ArrayList<>(Arrays.asList("Семенов","Сергей","01-01-2002"));
        ArrayList<String> Semenov2 = new ArrayList<>(Arrays.asList("Семенов","Василий","19-08-2005"));
        ArrayList<String> Andreev = new ArrayList<>(Arrays.asList("Андреев","Петр","30-09-2006"));
        persons.put(1234567890, Petrov);
        persons.put(1258965156, Ivanov);
        persons.put(1222566988, Semenov);
        persons.put(1111265368, Semenov2);
        persons.put(1068559333, Andreev);

        Namesake(persons); // Ищем все повторяющиеся имена, т.е. всех тезок.

        Age(persons, 18); // Указываем возраст, например 18 лет.
    }

    static void Namesake (HashMap<Integer, ArrayList<String>> data) {
        ArrayList<String> names = new ArrayList<>(); // Создаем список имен
        for (int i : data.keySet()) {
            names.add(data.get(i).get(1)); 
        }
        for (var item : data.values()) {
            if (Collections.frequency(names, item.get(1)) > 1) { // Если имя встречается больше одного раза, выводим сотрудника.
                System.out.println(item);
            }
        }
    }

    static void Age(HashMap<Integer, ArrayList<String>> data, int years) {
        Date datenow = Calendar.getInstance().getTime(); // Текущая дата
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); // Шаблон даты которую будем получать из Map в виде String
        for (var item : data.values()) {
            try {
                Date birthdate = formatter.parse(item.get(2)); // Парсим поступившую строку
                long milliseconds = datenow.getTime() - birthdate.getTime(); // Количество миллисекунд, прошедших со дня рождения
                var age = milliseconds / (1000*60*60*24) / 365; // Возраст в годах
                if (age > years) { // Если возраст больше интересующего, выводим работника в консоль.
                    System.out.println(item);
                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
