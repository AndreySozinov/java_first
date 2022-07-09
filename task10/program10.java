package task10;

import java.util.LinkedList;

// Реализовать алгоритм перевода из инфиксной записи в постфиксную 
// для арифметического выражения.
// Вычислить запись если это возможно.
public class program10 {
    public static void main(String[] args) {
        String infix_expression = "25+9*5.0/10-4";
        String postfix_expression = "";

        LinkedList<String> elements = GetElements(infix_expression);
        postfix_expression = Translate(elements);
        System.out.println(postfix_expression);
    }

    // Переводим строки чисел и операций в постфиксную запись с учетом приоритета.
    static String Translate(LinkedList<String> enter) {
        String result = "";
        StringBuilder numbers = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        int position = -1;
        while (enter.contains("^")) {
            position = enter.indexOf("^");
            Replace(position, enter, numbers, operations);
        }
        while (enter.contains("*") || enter.contains("/")) {
            if (enter.contains("*") && enter.contains("/")) {
                position = enter.indexOf("*") < enter.indexOf("/")? 
                            enter.indexOf("*") : enter.indexOf("/"); 
                Replace(position, enter, numbers, operations);
            }
            else if (enter.contains("*")) {
                position = enter.indexOf("*");
                Replace(position, enter, numbers, operations);
            }
            else {
                position = enter.indexOf("/");
                Replace(position, enter, numbers, operations);
            }
        }
        while (enter.contains("+") || enter.contains("-")) {
            if (enter.contains("+") && enter.contains("-")) {
                position = enter.indexOf("+") < enter.indexOf("-")? 
                            enter.indexOf("+") : enter.indexOf("-"); 
                Replace(position, enter, numbers, operations);
            }
            else if (enter.contains("+")) {
                position = enter.indexOf("+");
                Replace(position, enter, numbers, operations);
            }
            else {
                position = enter.indexOf("-");
                Replace(position, enter, numbers, operations);
            }
        }
        result = result.concat(numbers.toString());
        result = result.concat(operations.toString());
        result = result.replace(" 0", "");
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
        result.add(source.substring(start));
        return result;
    }

    // Раскидываем список с числами и знаками на строчку чисел и строчку знаков.
    static void Replace(int pos, LinkedList<String> source, StringBuilder nums, StringBuilder signs) {
        nums.append(source.get(pos-1));
        nums.append(" ");
        nums.append(source.get(pos + 1));
        nums.append(" ");
        signs.append(source.get(pos));
        signs.append(" ");
        source.set(pos - 1, "0");
        source.remove(pos);
        source.remove(pos);
    }
}
