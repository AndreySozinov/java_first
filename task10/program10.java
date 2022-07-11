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
        String infix_expression = "(52-8)*2^3+4";

        StringBuilder postfix_expression = Translate(GetElements(infix_expression));

        System.out.println(postfix_expression);

        Calculator(postfix_expression.toString());
    }

    // Переводим список чисел и операций в постфиксную запись с учетом приоритета.
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

    static void Calculator(String str) {
        String[] items = str.split(" ");
        Stack<Double> stack = new Stack<>();
        double result = 0;
        for (String item : items) {
            if (isNumeric(item)) {
                stack.push(Double.parseDouble(item));
            } else {
                result = 0;
                switch (item) {
                    case "+":
                        result = stack.pop() + stack.pop();
                        stack.push(result);
                        break;
                    case "-":
                        result = stack.pop();
                        result = stack.pop() - result;
                        stack.push(result);
                        break;
                    case "*":
                        result = stack.pop() * stack.pop();
                        stack.push(result);
                        break;
                    case "/":
                        result = stack.pop();
                        result = stack.pop() / result;
                        stack.push(result);
                        break;
                    case "^":
                        result = stack.pop();
                        result = Math.pow(stack.pop(), result);
                        stack.push(result);
                        break;
                    case "sin":
                        result = Math.sin(stack.pop());
                        stack.push(result);
                        break;
                    case "cos":
                        result = Math.cos(stack.pop());
                        stack.push(result);
                        break;
                    case "tan":
                        result = Math.tan(stack.pop());
                        stack.push(result);
                        break;
                    case "asin":
                        result = Math.asin(stack.pop());
                        stack.push(result);
                        break;
                    case "acos":
                        result = Math.acos(stack.pop());
                        stack.push(result);
                        break;
                    case "atan":
                        result = Math.atan(stack.pop());
                        stack.push(result);
                        break;
                    case "sqrt":
                        result = Math.sqrt(stack.pop());
                        stack.push(result);
                        break;
                    case "exp":
                        result = Math.exp(stack.pop());
                        stack.push(result);
                        break;
                    case "log":
                        result = Math.log(stack.pop());
                        stack.push(result);
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println(String.format("%.2f", stack.pop()));
    }

}
