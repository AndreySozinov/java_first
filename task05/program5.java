// Задано уравнение вида q + w = e, q, w, e >= 0. 
// Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69. 
// Требуется восстановить выражение до верного равенства.

package task05;

public class program5 {
    public static void main(String[] args) {
        String q = "2?";
        String w = "?5";
        String e = "69";
        
        Repair(q, w, e);
        
    }
    static void Repair(String a, String b, String sum) {
        // Будем использовать индексы положения "?" в числах.
        int a_mark_pos = -1;
        int b_mark_pos = -1;
        int sum_mark_pos = -1;
        // Будем использовать StringBuilder, чтобы изменять строки.
        StringBuilder sb_a = new StringBuilder(a);
        StringBuilder sb_b = new StringBuilder(b);
        StringBuilder sb_sum = new StringBuilder(sum);
        // Ищем индексы вопросительных знаков в наших числах.
        if (a.contains("?")) {a_mark_pos = a.indexOf("?");}
        if (b.contains("?")) {b_mark_pos = b.indexOf("?");}
        if (sum.contains("?")) {sum_mark_pos = sum.indexOf("?");}
        // Перебираем цифры и подставляем их в наши числа.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (a_mark_pos != -1) {
                        sb_a.setCharAt(a_mark_pos, (char)(i + '0'));
                        a = sb_a.toString();
                    }
                    if (b_mark_pos != -1) {
                        sb_b.setCharAt(b_mark_pos, (char)(j + '0'));
                        b = sb_b.toString();
                    }
                    if (sum_mark_pos != -1) {
                        sb_sum.setCharAt(sum_mark_pos, (char)(k + 0));
                        sum = sb_sum.toString();
                    }
                // Проверяем равенство.
                if (Integer.parseInt(a) + Integer.parseInt(b) == Integer.parseInt(sum)) {
                    System.out.printf("%s + %s = %s", a, b, sum);
                    return;
                }
            }
            }
        }
        System.out.println("Решения нет");
        return;
    }
}

