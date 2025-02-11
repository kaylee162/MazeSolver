import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int[] rowDirections = {-1, 1, 0, 0}; // Up, Down, Left, Right
    private static final int[] colDirections = {0, 0, -1, 1};

    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0}
        };

        List<String> path = new ArrayList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        if (findShortestPath(maze, 0, 0, path, visited)) {
            System.out.println("Shortest Path: " + path);
        } else {
            System.out.println("No path found.");
        }
    }

    private static boolean findShortestPath(int[][] maze, int row, int col, List<String> path, boolean[][] visited) {
        // Check if we are out of bounds or at a wall or already visited
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length || maze[row][col] == 1 || visited[row][col]) {
            return false;
        }

        // If we reached the destination (bottom-right corner)
        if (row == maze.length - 1 && col == maze[0].length - 1) {
            path.add("(" + row + "," + col + ")");
            return true;
        }

        // Mark the cell as visited
        visited[row][col] = true;
        path.add("(" + row + "," + col + ")");

        // Explore all possible directions
        for (int i = 0; i < 4; i++) {
            int newRow = row + rowDirections[i];
            int newCol = col + colDirections[i];

            if (findShortestPath(maze, newRow, newCol, path, visited)) {
                return true; // If path found, return true
            }
        }

        // Backtrack: unmark the cell and remove from path
        visited[row][col] = false;
        path.remove(path.size() - 1);
        return false; // No path found from this cell
    }
}