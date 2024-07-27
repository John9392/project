public class MazeSolver {

    private static final int PATH = 0;
    private static final int WALL = 1;
    private static final int VISITED = 2;
    private static final int PATH_TAKEN = 3;

    public static void main(String[] args) {
        int[][] maze = {
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 0},
            {0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0},
            {1, 1, 0, 1, 0, 0},
        };

        int startX = 0;
        int startY = 0;
        int endX = 5;
        int endY = 5;

        if (solveMaze(maze, startX, startY, endX, endY)) {
            System.out.println("Path found:");
            printMaze(maze);
        } else {
            System.out.println("No path found.");
        }
    }

    private static boolean solveMaze(int[][] maze, int x, int y, int endX, int endY) {
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] != PATH) {
            return false;
        }

        // Mark the current cell as part of the path
        maze[x][y] = PATH_TAKEN;

        // If we've reached the end point, return true
        if (x == endX && y == endY) {
            return true;
        }

        // Try to move in each direction (right, down, left, up)
        if (solveMaze(maze, x + 1, y, endX, endY) || // move right
            solveMaze(maze, x, y + 1, endX, endY) || // move down
            solveMaze(maze, x - 1, y, endX, endY) || // move left
            solveMaze(maze, x, y - 1, endX, endY)) { // move up
            return true;
        }

        // If none of the moves work, backtrack: unmark the current cell
        maze[x][y] = VISITED;

        return false;
    }

    private static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                switch (cell) {
                    case WALL:
                        System.out.print("# ");
                        break;
                    case PATH_TAKEN:
                        System.out.print("P ");
                        break;
                    default:
                        System.out.print(". ");
                        break;
                }
            }
            System.out.println();
        }
    }
}