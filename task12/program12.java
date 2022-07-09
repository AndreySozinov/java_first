package task12;
// Написать программу, определяющую правильность расстановки скобок в выражении.
// Пример 1: a+(d*3) - истина
// Пример 2: [a+(1*3) - ложь
// Пример 3: [6+(3*3)] - истина
// Пример 4: {a}[+]{(d*3)} - истина
// Пример 5: <{a}+{(d*3)}> - истина
// Пример 6: {a+]}{(d*3)} - ложь

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class program12 {
    public static void main(String[] args) {
        String expression = "a+(d*3)";
        System.out.println(Correct(expression));

        expression = "[a+(1*3)";
        System.out.println(Correct(expression));

        expression = "[6+(3*3)]";
        System.out.println(Correct(expression));

        expression = "{a}[+]{(d*3)}";
        System.out.println(Correct(expression));

        expression = "<{a}+{(d*3)}>";
        System.out.println(Correct(expression));

        expression = "{a+]}{(d*3)}";
        System.out.println(Correct(expression));
    }

    static boolean Correct(String str) {
        Map<String, String> dict = new HashMap<>();
        dict.put("(", ")");
        dict.put("[", "]");
        dict.put("{", "}");
        dict.put("<", ">");
        Stack<String> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '(': 
                    st.push(dict.get("("));
                    break;
                case '[': 
                    st.push(dict.get("["));
                    break;
                case '{': 
                    st.push(dict.get("{"));
                    break;
                case '<': 
                    st.push(dict.get("<"));
                    break;
                case ')': 
                    if (st.peek().equals(Character.toString(str.charAt(i)))) {
                        st.pop();
                    }
                    else {st.push(")");}
                    break;
                case ']': 
                    if (st.peek().equals(Character.toString(str.charAt(i)))) {
                        st.pop();
                    }
                    else {st.push("]");}
                    break;
                case '}': 
                    if (st.peek().equals(Character.toString(str.charAt(i)))) {
                        st.pop();
                    }
                    else {st.push("}");}
                    break;
                case '>': 
                    if (st.peek().equals(Character.toString(str.charAt(i)))) {
                        st.pop();
                    }
                    else {st.push(">");}
                    break;
            }
        }
        return st.empty();
    }
}
