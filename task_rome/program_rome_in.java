package task_rome;
// Перевод римских чисел в арабские.
import java.util.HashMap;
import java.util.Map;

public class program_rome_in {
    public static void main(String[] args) {
        Map<Character, Integer> numbers = new HashMap<>();
        numbers.put('I', 1);
        numbers.put('V', 5);
        numbers.put('X', 10);
        numbers.put('L', 50);
        numbers.put('C', 100);
        numbers.put('D', 500);
        numbers.put('M', 1000);
        
        String roman_number = "MCMLXXVI";
        int result = 0;
        
        int i = 1;

        while (i < roman_number.length()) {
            if (numbers.get(roman_number.charAt(i - 1)) < numbers.get(roman_number.charAt(i))) {
                result = result - numbers.get(roman_number.charAt(i - 1));
            }
            else {
            result = result + numbers.get(roman_number.charAt(i - 1));
            }
            i++;
        }
        result = result + numbers.get(roman_number.charAt(i-1));

        System.out.printf("%s = %d", roman_number, result);
    }
    
}
