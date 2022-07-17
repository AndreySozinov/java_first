package task_rome2;

import java.util.TreeMap;

public class program_rome_out {

    public static TreeMap<Integer, Character> numbers = new TreeMap<>();
    public static void main(String[] args) {
        
        numbers.put(1, 'I');
        numbers.put(5, 'V');
        numbers.put(10, 'X');
        numbers.put(50, 'L');
        numbers.put(100, 'C');
        numbers.put(500, 'D');
        numbers.put(1000, 'M');

        int arabic_number = 1949; // Вводим арабское число

        System.out.printf("%d = %s", arabic_number, getRoman(arabic_number));
    }

    static String getRoman(int arabic_number) {
        StringBuilder roman_number = new StringBuilder();

        for (int i : numbers.descendingKeySet()) { // Выстраиваем ключи в порядке убывания.
            if (arabic_number / i > 0) { // Если число больше ключа
                for (int j = 0; j < arabic_number / i; j++) {
                    roman_number.append(numbers.get(i)); // Добавляем ключ в строку столько раз, во сколько число больше ключа.
                }
                arabic_number = arabic_number % i; // Берем остаток исходного числа.
            }
        }
        
        String number = roman_number.toString();
        // Заменяем в строке четверные повторения.
        number = number.replace("DCCCC", "CM");                
        number = number.replace("CCCC", "CD");        
        number = number.replace("LXXXX", "XC");        
        number = number.replace("XXXX", "XL");            
        number = number.replace("VIIII", "IX");                
        number = number.replace("IIII", "IV");

        return number;
    }
}
