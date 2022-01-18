package generator.maze;

import generator.maze.cell.Cell;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {

    private final int[] changeX = {0, 1, 0, -1};
    private final int[] changeY = {-1, 0, 1, 0};

    private final Cell[][] maze;
    private ArrayList<ArrayList<Integer>> data;
    private final int height;
    private final int width;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        maze = new Cell[height+1][width+1];
        for (int x = 0; x <= height; x++) {
            for (int y = 0; y <= width; y++) {
                maze[x][y] = new Cell(x, y);
            }
        }
    }

    public Maze(ArrayList<ArrayList<Integer>> data) {
        this.data = data;
        this.height = (data.size() - 1) / 2;
        this.width = (data.get(this.height-1).size() - 1) / 2;
        maze = new Cell[height+1][width+1];
        for (int x = 0; x <= height; x++) {
            for (int y = 0; y <= width; y++) {
                if (data.get(x*2).get(y*2) == 1) {
                    maze[x][y] = new Cell(x, y);
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        return maze[x][y];
    }

    private boolean checkPoint(int x, int y) {
        return (x >= 0 && x <= height && y >= 0 && y <= width);
    }

    public ArrayList<Cell> getAvailableNeighbors(Cell cell) {
        ArrayList<Cell> availableNeighbors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int x = cell.getX() + changeX[i];
            int y = cell.getY() + changeY[i];
            if (checkPoint(x, y) && getCell(x, y) != null) {
                if (getCell(x, y).isAvailable()) {
                    availableNeighbors.add(getCell(x, y));
                }
            }
        }
        return availableNeighbors;
    }

    public ArrayList<ArrayList<Integer>> export() {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();

        for (int x = 0; x <= height; x++) {
            data.add(new ArrayList<>());
            if (x != height-1) {
                data.add(new ArrayList<>());
            }
        }

        for (ArrayList<Integer> value : data) {
            for (int y = 0; y <= width; y++) {
                value.add(1);
                if (y != width-1) {
                    value.add(1);
                }
            }
        }

        if (this.data != null) {
            for (int x = 0; x < this.data.size(); x++) {
                for (int y = 0; y < this.data.get(x).size(); y++) {
                    data.get(x).set(y, this.data.get(x).get(y));
                }
            }
        }

        for (int x = 0; x <= height; x++) {
            for (int y = 0; y <= width; y++) {
                if (getCell(x, y) != null) {
                    data.get(x * 2).set(y * 2, 0);
                    ArrayList<Cell> neighbors = getCell(x, y).getConnectCell();

                    if (neighbors == null) {
                        continue;
                    }

                    System.out.println(neighbors.size());

                    for (Cell neighbor : neighbors) {
                        int pathX = x + neighbor.getX();
                        int pathY = y + neighbor.getY();
                        data.get(pathX).set(pathY, 0);
                    }
                }
                else {
                    data.get(x * 2).set(y * 2, -1);
                }
            }
        }

        return data;
    }

    /**
     * Maze generator - Depth First Search (DFS)
     * @param height is the height of the maze
     * @param width is the width of the maze
     * @param x is the x coordinate of the starting point
     * @param y is the y coordinate of the starting point
     * @return a random maze
     */
    public static Maze dfs(int height, int width, int x, int y) {

        Maze maze = new Maze(height, width);

        return dfsAlgorithm(x, y, maze);
    }
    /**
     * Maze generator - Depth First Search (DFS)
     * @param data is the data of the maze
     * @param x is the x coordinate of the starting point
     * @param y is the y coordinate of the starting point
     * @return a random maze
     */
    public static Maze dfs(ArrayList<ArrayList<Integer>> data, int x, int y) {

        Maze maze = new Maze(data);

        return dfsAlgorithm(x, y, maze);
    }

    private static Maze dfsAlgorithm(int x, int y, Maze maze) {
        Random random = new Random(System.nanoTime());
        Stack<Cell> stack = new Stack<>();
        stack.add(maze.getCell(x, y));
        maze.getCell(x, y).setAvailable(false);

        while (!stack.isEmpty()) {
            Cell cell = stack.pop();
            ArrayList<Cell> neighbors = maze.getAvailableNeighbors(cell);

            if (!neighbors.isEmpty()) {
                int rand = Math.abs(random.nextInt()) % neighbors.size();

                int cnt = 0;
                for (Cell neighbor : neighbors) {
                    cell.connect(neighbor);
                    cnt++;
                    if (cnt != rand) {
                        stack.add(neighbor);
                        neighbor.setAvailable(false);
                    }
                }

                stack.add(neighbors.get(rand));
                neighbors.get(rand).setAvailable(false);
            }
        }

        return maze;
    }
}
