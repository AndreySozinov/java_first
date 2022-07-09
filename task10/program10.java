package task10;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// Реализовать алгоритм перевода из инфиксной записи в постфиксную 
// для арифметического выражения.
// Вычислить запись если это возможно.
public class program10 {
    public static void main(String[] args) {
        String infix_expression = "25+9*5.0-100";
        String postfix_expression = "";

        Map<String, Integer> priority = new HashMap<String, Integer>();
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);
        priority.put("^", 3);

        LinkedList<String> elements = GetElements(infix_expression);
        System.out.println(elements);
        postfix_expression = Translate(elements, priority);
        //System.out.println(postfix_expression);
    }

    static String Translate(LinkedList<String> enter, Map<String, Integer> priority) {
        String result = "";

        return result;
    }

    // Разбиваем выражение на числа и знаки операций.
    static LinkedList<String> GetElements (String source) {
        LinkedList<String> result = new LinkedList<>();
        int start = 0;
        for (int pos = 0; pos < source.length(); pos++) {
            if (!Character.isDigit(source.charAt(pos)) && 
                    source.charAt(pos) != (char) '.') {
                if (pos != start) {
                    result.add(source.substring(start, pos));
                    result.add(String.valueOf(source.charAt(pos)));
                    start = pos + 1;
                }
                else {
                    result.add(String.valueOf(source.charAt(pos)));
                    start++;
                }
            }
        }
        result.add(source.substring(start));
        return result;
    }
}
