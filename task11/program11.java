package task11;
// Реализовать волновой алгоритм
import java.util.Stack;

public class program11 {

    public static int[][] field = new int[8][12]; // Поле с ячейками 8х12 (или другое).

    public static int[][] moves = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0},
                                    {1, -1}, {0, -1}, {-1, -1}}; // Возможные ходы.

    public static Stack<Integer> way_x = new Stack<>(); // Стек для координат ходов по Х.
    public static Stack<Integer> way_y = new Stack<>(); // Стек для координат ходов по Y.
    
    public static void main(String[] args) {
        // Приписываем атрибут непроходимости ячеек (можно любые координаты).
        field[0][2] = -1; field[1][2] = -1; field[3][8] = -1; field[3][9] = -1;
        field[4][1] = -1; field[4][2] = -1; field[4][3] = -1; field[4][4] = -1;
        field[5][9] = -1; field[5][10] = -1; field[6][9] = -1; field[6][10] = -1;

        Printing(field); // Печатаем исходное поле, звездочки - непроходимые ячейки.

        int startX = 6; int startY = 3; // Стартовая точка (любая проходимая).
        int finX = 0; int finY = 3; // Финишная точка (любая проходимая).

        Wave (field, startX, startY, finX, finY); // Распространение волны.
        Printing(field); // Печатаем поле, цифры - номера шагов до цели.

        Recovery(field, startX, startY, finX, finY); // Восстанавливаем путь до старта при помощи стека.
        Path(way_x, way_y); // Заносим координаты шагов верного пути из стека на поле.
        Printing(field); // Плюсиками отмечаем путь.
    }

    static void Printing (int[][] arr) {
        String a = "---"; String b = "|"; String z = " ";
        for (int k = -1; k <= arr[0].length; k++) {
            System.out.printf("%s", a);
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%2s", b);
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] > 0) z = Integer.toString(arr[i][j]);
                if (arr[i][j] == -1) z = "*";
                if (arr[i][j] == -2) z = "+";
                System.out.printf("%3s", z);
                z = " ";
            }
            System.out.printf("%3s\n", b);
        }
        for (int k = -1; k <= arr[0].length; k++) {
            System.out.printf("%s", a);
        }
        System.out.println();
    }

    static void Wave (int[][] arr, int stX, int stY, int finX, int finY) {
        int count = 0;
        for (int[] move : moves) {
            if (stX + move[0] >= 0 && stX + move[0] < arr.length &&
                stY + move[1] >= 0 && stY + move[1] < arr[0].length 
                && arr [stX + move[0]][stY + move[1]] == 0) {
                    arr [stX + move[0]][stY + move[1]] = count + 1;
            }
        }
        count++;
        while (arr[finX][finY] == 0) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j] == count) {
                        for (int[] move : moves) {
                        if (i + move[0] >= 0 && i + move[0] < arr.length &&
                            j + move[1] >= 0 && j + move[1] < arr[0].length &&
                            arr[i + move[0]][j + move[1]] == 0) {
                                arr[i + move[0]][j + move[1]] = count + 1;
                            }
                        }
                    }
                }
            }
            count++;
            arr[stX][stY] = 0;
        }
    }

    static void Recovery (int[][] arr, int stX, int stY, int finX, int finY) {
        way_x.push(finX); way_y.push(finY);
        if (finX == stX && finY == stY) return;
        for (int[] move : moves) {
            if (finX + move[0] >= 0 && finX + move[0] < arr.length &&
            finY + move[1] >= 0 && finY + move[1] < arr[0].length 
            && arr[finX + move[0]][finY + move[1]] < arr[finX][finY]
            && arr[finX + move[0]][finY + move[1]] != -1) {
                Recovery(arr, stX, stY, finX + move[0], finY + move[1]);
                if ((way_x.peek() == stX && way_y.peek() == stY)) return;
            }
        }
        if ((way_x.peek() != stX && way_y.peek() != stY)) {
            way_x.pop(); way_y.pop();
        }
        return;
    }

    static void Path (Stack<Integer> x, Stack<Integer> y) {
        while (!x.empty()) {
            field[x.pop()][y.pop()] = -2;
        }
    }
}
