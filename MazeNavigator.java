import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeNavigator {

    private static final int[] ROW_DIRECTIONS = {-1, 1, 0, 0}; // Up, Down, Left, Right
    private static final int[] COL_DIRECTIONS = {0, 0, -1, 1}; // Up, Down, Left, Right

    // Method to print the path
    private static void showPath(Stack<int[]> pathSequence) {
        if (pathSequence == null || pathSequence.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path: ");
            for (int[] position : pathSequence) {
                System.out.print("(" + position[0] + "," + position[1] + ") ");
            }
            System.out.println();
        }
    }

    // Stack-based Maze Solver (Depth-First Search)
    public static Stack<int[]> depthFirstSolve(int[][] labyrinth, int[] entrance, int[] exit) {
        Stack<int[]> discoveredPath = new Stack<>();
        Stack<int[]> frontier = new Stack<>();
        boolean[][] examined = new boolean[labyrinth.length][labyrinth[0].length];

        frontier.push(entrance);
        while (!frontier.isEmpty()) {
            int[] location = frontier.pop();
            int x = location[0];
            int y = location[1];

            if (x < 0 || y < 0 || x >= labyrinth.length || y >= labyrinth[0].length || labyrinth[x][y] == 1 || examined[x][y]) {
                continue;
            }

            examined[x][y] = true;
            discoveredPath.push(location);

            if (x == exit[0] && y == exit[1]) {
                return discoveredPath;
            }

            for (int dir = 0; dir < ROW_DIRECTIONS.length; dir++) {
                frontier.push(new int[]{x + ROW_DIRECTIONS[dir], y + COL_DIRECTIONS[dir]});
            }
        }
        return null;
    }

    // Queue-based Maze Solver (Breadth-First Search)
    public static Queue<int[]> breadthFirstSolve(int[][] labyrinth, int[] entrance, int[] exit) {
        Queue<int[]> discoveredPath = new LinkedList<>();
        Queue<int[]> frontier = new LinkedList<>();
        boolean[][] examined = new boolean[labyrinth.length][labyrinth[0].length];

        frontier.offer(entrance);
        while (!frontier.isEmpty()) {
            int[] location = frontier.poll();
            int x = location[0];
            int y = location[1];

            if (x < 0 || y < 0 || x >= labyrinth.length || y >= labyrinth[0].length || labyrinth[x][y] == 1 || examined[x][y]) {
                continue;
            }

            examined[x][y] = true;
            discoveredPath.add(location);

            if (x == exit[0] && y == exit[1]) {
                return discoveredPath;
            }

            for (int dir = 0; dir < ROW_DIRECTIONS.length; dir++) {
                frontier.offer(new int[]{x + ROW_DIRECTIONS[dir], y + COL_DIRECTIONS[dir]});
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] labyrinth = {
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 0},
            {0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 0}
        };

        int[] entrance = {0, 2}; // Starting position
        int[] exit = {5, 4}; // Ending position

        // Solve maze using DFS
        System.out.println("Solving the maze using DFS:");
        Stack<int[]> dfsPathResult = depthFirstSolve(labyrinth, entrance, exit);
        showPath(dfsPathResult);

        // Solve maze using BFS
        System.out.println("Solving the maze using BFS:");
        Queue<int[]> bfsPathResult = breadthFirstSolve(labyrinth, entrance, exit);
        showPath(new Stack<>(bfsPathResult)); // Convert Queue to Stack to reuse showPath method
    }
}