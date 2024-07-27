public class SudokuSolver {

    // Size of the grid
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
            {7, 0, 0, 4, 0, 0, 1, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 9},
            {3, 0, 9, 0, 7, 5, 0, 0, 8},
            {0, 5, 7, 9, 2, 6, 0, 1, 0},
            {0, 6, 0, 0, 0, 7, 0, 0, 4},
            {0, 0, 0, 0, 1, 0, 8, 0, 0},
            {0, 0, 0, 0, 0, 8, 6, 3, 0},
            {0, 1, 0, 0, 0, 0, 0, 9, 0},
            {0, 0, 0, 7, 6, 0, 4, 0, 0}
        };

        if (solveBoard(board)) {
            System.out.println("Sudoku solved successfully!");
            printBoard(board);
        } else {
            System.out.println("Unsolvable board.");
        }
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    // Try all numbers from 1 to 9
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;

                            // Recursively attempt to solve the rest of the board
                            if (solveBoard(board)) {
                                return true;
                            }
                            // Backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false;  // none of the numbers from 1 to 9 work
                }
            }
        }
        return true;  // no unassigned position found, board is solved
    }

    private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
        return !isInRow(board, num, row) && 
               !isInCol(board, num, col) &&
               !isInBox(board, num, row, col);
    }

    private static boolean isInRow(int[][] board, int num, int row) {
        for (int col = 0; col < GRID_SIZE; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInCol(int[][] board, int num, int col) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInBox(int[][] board, int num, int row, int col) {
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;

        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");  // Row separator
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");  // Column separator
                }
                System.out.print(board[row][col] == 0 ? "." : board[row][col]);
            }
            System.out.println();
        }
    }
}