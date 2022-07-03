package task07;

// На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
public class program7 {
    public static void main(String[] args) {
        int[][] chessboard = new int[8][8];
        
        // Ищем решения для любого расположения ферзя в первой горизонтали.
        for (int y = 0; y < 8; y++) {
            chessboard[0][y] = 1;
            Eight_queens(chessboard, 1);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    chessboard[i][j] = 0;
                }
            }
        System.out.println();
        }
    }

    static void Eight_queens (int[][] arr, int x) {
        if (x == 8) {
            Printing(arr);
            return;}
            
        for (int y = 0; y < 8; y++) {
        // Ищем уже поставленные ферзи.
        int sum = Checking(arr, x, y);
            
        // Если горизонталь, вертикаль и обе диагонали не заняты - ставим ферзя.
        if (sum == 0) {
            arr[x][y] = 1;
            Eight_queens(arr, x + 1);
            }
        }
        // Если поставить некуда, убираем ферзя с предыдущей горизонтали.
        if (x > 0) {
            for (int y = 0; y < 8; y++) {
                arr[x-1][y] = 0;
            }
        }
        return;
    }

    static void Printing (int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%d  ", arr[i][j]);
            }
        System.out.println();
        }
    }

    static int Checking(int[][] arr, int x, int y) {
        int result = 0;
            for (int i = 0; i < arr.length; i++) {
                // Пробегаем по горизонтали и вертикали.
                result += arr[x][i];
                result += arr[i][y];
            }
            // Пробегаем по диагоналям.
            int i = x;
            int j = y;
            while (i >= 0 && j >= 0) {
                result += arr[i][j];
                i-=1;
                j-=1;
            }
            i = x;
            j = y;
            while (i >= 0 && j < arr.length) {
                result += arr[i][j];
                i-=1;
                j+=1;
            }
            i = x;
            j = y;
            while (i < arr.length && j < arr.length) {
                result += arr[i][j];
                i+=1;
                j+=1;
            }
            i = x;
            j = y;
            while (i < arr.length && j >= 0) {
                result += arr[i][j];
                i+=1;
                j-=1;
            }
        return result;
    }
}
