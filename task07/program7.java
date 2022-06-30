package task07;
//На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
public class program7 {
    public static void main(String[] args) {
        int[][] chessboard = new int[8][8];
        Printing(chessboard);
    }

    static void Eight_queens (int[][] arr, int k) {
        if (k == 8) {
            Printing(arr);
            return;}
        int x = (int) (Math.random() * (arr.length));
        int y = (int) (Math.random() * (arr[0].length));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][y] == 1 || arr[x][i] == 1) {return;}
        }
        int a = x;
        int b = y;
        while (a >= 0 || b >= 0) {
            if (arr[a][b] == 1) {return;}
            a-=1;
            b-=1;
        }
        a = x;
        b = y;
        while (a >= 0 || b < arr.length) {
            if (arr[a][b] == 1) {return;}
            a-=1;
            b+=1;
        }
        a = x;
        b = y;
        while (a < arr.length || b < arr.length) {
            if (arr[a][b] == 1) {return;}
            a+=1;
            b+=1;
        }
        a = x;
        b = y;
        while (a < arr.length || b >= 0) {
            if (arr[a][b] == 1) {return;}
            a+=1;
            b-=1;
        }
        arr[x][y] = 1;
        Eight_queens(arr, k + 1);
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
}
