import java.util.LinkedList;
import java.util.List;

public class NQueens {

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static List<char[][]> nQueensSolutions(int n) {

        char[][] board = new char[n][n];
        for (int row = 0; row < n; row++)
            for (int col = 0; col < n; col++)
                board[row][col] = '.';
        LinkedList<char[][]> fin = new LinkedList<>();
        return solveNQUtil(board, 0, n, fin);

    }

    public static List<char[][]> solveNQUtil(char board[][], int col, int n, LinkedList<char[][]> fin) {
        if (col >= n) {
            char[][] Re;
            Re = copyOf(board);
            fin.add(Re);
        } else {
            for (int i = 0; i < n; i++) {
                if (isSafe(board, i, col)) {
                    board[i][col] = 'Q';
                    solveNQUtil(board, col + 1, n, fin);
                    board[i][col] = '.'; // BACKTRACK
                }
            }
        }

        return fin;
    }

    public static boolean isSafe(char board[][], int row, int col) {
        int i, j;

        for (i = 0; i < col; i++)
            if (board[row][i] == 'Q')
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q')
                return false;

        for (i = row, j = col; j >= 0 && i < board.length; i++, j--)
            if (board[i][j] == 'Q')
                return false;

        return true;
    }


}
