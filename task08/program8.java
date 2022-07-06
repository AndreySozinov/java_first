package task08;
// Шахматную доску размером NxN обойти конём так, чтобы фигура в каждой клетке 
// была строго один раз.

public class program8 {
    public static void main(String[] args) {
        int[][] chessboard = new int[8][8];
        int[][] moves = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, 
                        {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        
        int startX = (int) (Math.random() * (chessboard.length));
        int startY = (int) (Math.random() * (chessboard[0].length));
        chessboard[startX][startY] = 1;

        MoveKnight(chessboard, startX, startY, 1, moves);
    }

    static void MoveKnight (int[][] arr, int x, int y, int count, int[][] moves) {
        if (count == arr.length * arr[0].length) {
            Printing(arr);
            System.out.println();
            return;
        }
        for (int i = 0; i < moves.length; i++) {
            if (CheckMove(arr, x, y, moves[i])) {
                int nextX = x + moves[i][0];
                int nextY = y + moves[i][1];
                count++;
                arr[nextX][nextY] = count;
                MoveKnight(arr, nextX, nextY, count, moves);
                if (count < arr.length * arr[0].length) {
                    arr[nextX][nextY] = 0;
                    count--;            
                }
            }
        }
        if (count < arr.length * arr[0].length) {
            arr[x][y] = 0;
            count--;
        }
        return;
    }

    static boolean CheckMove (int[][] arr, int posX, int posY, int[] step) {
        boolean result = false;
        int nextX = posX + step[0];
        int nextY = posY + step[1];
        if (nextX >=0 && nextY >= 0 && nextX < arr.length && nextY < arr[0].length) {
            if (arr[nextX][nextY] == 0) {
                result = true;
            }
        }
        return result;
    }

    static void Printing (int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf("%3d  ", arr[i][j]);
            }
        System.out.println();
        }
    }
}
