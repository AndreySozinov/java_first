package task10;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

// Реализовать алгоритм перевода из инфиксной записи в постфиксную 
// для арифметического выражения.
// Вычислить запись если это возможно.
public class program10 {
    public static void main(String[] args) {
        String infix_expression = "(25+9)*10-8^2/5";

        System.out.println(Translate(GetElements(infix_expression)));
    }

    // Переводим строки чисел и операций в постфиксную запись с учетом приоритета.
    static StringBuilder Translate(LinkedList<String> enter) {
        StringBuilder result = new StringBuilder();
        Stack<String> st = new Stack<>();

        Map<String, Integer> priors = new HashMap<>();
        priors.put("+", 1);
        priors.put("-", 1);
        priors.put("*", 2);
        priors.put("/", 2);
        priors.put("^", 3);

        Map<String, String> brackets = new HashMap<>();
        brackets.put(")", "(");
        brackets.put("]", "[");
        brackets.put("}", "{");
        brackets.put(">", "<");

        String functions = "sincostanasinacosatansqrtexplog";

        for (String item : enter) { // Пока не кончится входной список
            if (isNumeric(item) || item.equals("!")) { // Если элемент это число или ! кладем в выходную строку.
                result = result.append(item + " ");
            }
            if (functions.contains(item)) { // Если элемент - функция кладём в стек.
                    st.push(item);
            }
            if (brackets.containsValue(item)) { // Если элемент открывающая скобка кладём в стек.
                st.push(item);
            }
            if (brackets.containsKey(item)) { // Если элемент закрывающая скобка из стека выталкиваем в выходную строку до открывающей скобки.
                while (!brackets.get(item).equals(st.peek())) {
                    result = result.append(st.pop() + " ");
                }
                st.pop(); // Открывающую скобку в выходную строку не кладем.
            }
            if (priors.containsKey(item)) { // Если элемент ^ * / + или - выталкиваем функции и более приоритетные операции из стека в выходную строку.
                while (!st.empty() && priors.containsKey(st.peek()) && (functions.contains(st.peek()) || priors.get(item) <= priors.get(st.peek()))) {
                    result = result.append(st.pop() + " ");
                }
                st.push(item); // После чего помещаем операцию в стек.
            }
        }
        while (!st.empty()) {
            result = result.append(st.pop() + " ");
        }
        return result;
    }

    // Разбиваем строку с исходным выражением на список чисел и знаков операций.
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
        if (start < source.length()) {
            result.add(source.substring(start));
        }
        return result;
    }

    // Проверка на число с десятичной точкой и минусом.
    static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
      }
}
