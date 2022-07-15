package task_rome2;

// import java.util.HashMap;
// import java.util.Map;

public class program_rome_out {
    public static void main(String[] args) {
        // Map<Character, Integer> numbers = new HashMap<>();
        // numbers.put('I', 1);
        // numbers.put('V', 5);
        // numbers.put('X', 10);
        // numbers.put('L', 50);
        // numbers.put('C', 100);
        // numbers.put('D', 500);
        // numbers.put('M', 1000);

        int arabic_number = 949;
        System.out.print(arabic_number);

        StringBuilder roman_number = new StringBuilder();

        if (arabic_number / 1000 > 0) {
            for (int i = 0; i < arabic_number / 1000; i++) {
                roman_number.append("M");
            }
            arabic_number = arabic_number - 1000 * (arabic_number / 1000);
        }
        if (arabic_number / 500 > 0) {
            for (int i = 0; i < arabic_number / 500; i++) {
                roman_number.append("D");
            }
            arabic_number = arabic_number - 500 * (arabic_number / 500);
        }
        if (arabic_number / 100 > 0) {
            for (int i = 0; i < arabic_number / 100; i++) {
                roman_number.append("C");
            }
            arabic_number = arabic_number - 100 * (arabic_number / 100);
        }
        if (arabic_number / 50 > 0) {
            for (int i = 0; i < arabic_number / 50; i++) {
                roman_number.append("L");
            }
            arabic_number = arabic_number - 50 * (arabic_number / 50);
        }
        if (arabic_number / 10 > 0) {
            for (int i = 0; i < arabic_number / 10; i++) {
                roman_number.append("X");
            }
            arabic_number = arabic_number - 10 * (arabic_number / 10);
        }
        if (arabic_number / 5 > 0) {
            for (int i = 0; i < arabic_number / 5; i++) {
                roman_number.append("V");
            }
            arabic_number = arabic_number - 5 * (arabic_number / 5);
        }
        if (arabic_number  > 0) {
            for (int i = 0; i < arabic_number; i++) {
                roman_number.append("I");
            }
        }
        
        String number = roman_number.toString();
        
        if (number.contains("DCCCC")) {
            number = number.replace("DCCCC", "CM");
            }
        if (number.contains("CCCC")) {
            number = number.replace("CCCC", "CD");
            }
        if (number.contains("LXXXX")) {
            number = number.replace("LXXXX", "XC");
            }
        if (number.contains("XXXX")) {
            number = number.replace("XXXX", "XL");
            }
        if (number.contains("VIIII")) {
            number = number.replace("VIIII", "IX");
            }
        if (number.contains("IIII")) {
            number = number.replace("IIII", "IV");
            }

        System.out.printf(" = %s", number);
    }
}
