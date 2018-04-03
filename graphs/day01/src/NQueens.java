import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }


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
        List<char[][]> answers = new ArrayList<>();
        char[][] board = new char[n][n];
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                board[r][c] = '.';
            }
        }
        Set<Integer> columns = new HashSet<>();
        for(int i = 0; i < n; i++) columns.add(i);
        placeQueens(0, columns, board, answers);
        return answers;
    }

    private static void placeQueens(int row, Set<Integer> columns, char[][] board, List<char[][]> answers){
        if(columns.isEmpty()) {
            answers.add(copyOf(board));
        } else{
            Set<Integer> temp = new HashSet<>(columns);
            for(int column: temp){
                if(!checkDiagonal(board, row, column)){
                    columns.remove(column);
                    board[row][column] = 'Q';
                    placeQueens(row + 1, columns, board, answers);
                    board[row][column] = '.';
                    columns.add(column);
                }
            }
        }
    }
}
