package task13;

// Дописать код задания 13. 
// Можно без функций типа sin/cos/log, сделать только для простых 
// арифметических операций (+-*/), также можно добавить возведение в степень ^. 
// Учитывать наличие скобок.

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class program13 {
    public static void main(String[] args) {
        String expression = "(52-8)*2^3+4";

        StringBuilder postfix_expression = Translate(GetElements(expression));

        Calculator(postfix_expression.toString());
    }

    // Перевод в постфиксную запись с учетом приоритета.
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
                while (!st.empty() && ((functions.contains(st.peek())) || (priors.containsKey(st.peek()) && 
                        priors.get(item) <= priors.get(st.peek())))) {
                    if (!st.empty() && priors.containsKey(st.peek()) && priors.get(item) <= priors.get(st.peek())) {
                        result = result.append(st.pop() + " ");        
                        }
                    if (!st.empty() && functions.contains(st.peek())) {
                        result = result.append(st.pop() + " ");
                    }
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
        StringBuilder func = new StringBuilder();
        int start = 0;
        for (int pos = 0; pos < source.length(); pos++) {
            if (Character.isLetter(source.charAt(pos))) {
                func = func.append(source.charAt(pos));
            }
            if (Character.isDigit(source.charAt(pos)) && func.length() > 0) {
                result.add(func.toString());
                func.delete(0, func.length());
                start = pos;
            }
            if (!Character.isDigit(source.charAt(pos)) && !Character.isLetter(source.charAt(pos)) &&
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

    // Сам расчет.
      static void Calculator(String str) {
        String[] items = str.split(" ");
        Stack<Double> stack = new Stack<>();
        double result = 0;
        for (String item : items) {
            if (isNumeric(item)) {
                stack.push(Double.parseDouble(item));
            } else {
                switch (item) {
                    case "+":
                        result = stack.pop() + stack.pop();
                        break;
                    case "-":
                        result = stack.pop();
                        result = stack.pop() - result;
                        break;
                    case "*":
                        result = stack.pop() * stack.pop();
                        break;
                    case "/":
                        result = stack.pop();
                        result = stack.pop() / result;
                        break;
                    case "^":
                        result = stack.pop();
                        result = Math.pow(stack.pop(), result);
                        break;
                    case "sin":
                        result = Math.sin(stack.pop());
                        break;
                    case "cos":
                        result = Math.cos(stack.pop());
                        break;
                    case "tan":
                        result = Math.tan(stack.pop());
                        break;
                    case "asin":
                        result = Math.asin(stack.pop());
                        break;
                    case "acos":
                        result = Math.acos(stack.pop());
                        break;
                    case "atan":
                        result = Math.atan(stack.pop());
                        break;
                    case "sqrt":
                        result = Math.sqrt(stack.pop());
                        break;
                    case "exp":
                        result = Math.exp(stack.pop());
                        break;
                    case "log":
                        result = Math.log(stack.pop());
                        break;
                }
                stack.push(result);
            }
        }
        System.out.println(String.format("%.2f", stack.pop()));
    }

}