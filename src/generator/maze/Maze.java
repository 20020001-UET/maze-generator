package generator.maze;

import generator.maze.cell.Cell;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {

    private final int[] changeX = {0, 1, 0, -1, 1, 1, -1, -1};
    private final int[] changeY = {-1, 0, 1, 0, 1, -1, 1, -1};

    private final Cell[][] maze;
    private ArrayList<ArrayList<Integer>> data;
    private ArrayList<Point> pointIns;
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
        pointIns = new ArrayList<>();
    }

    public Maze(ArrayList<ArrayList<Integer>> data) {
        this.data = data;
        this.height = (data.size() - 1) / 2;
        this.width = (data.get(this.height-1).size() - 1) / 2;
        maze = new Cell[height+1][width+1];
        pointIns = new ArrayList<>();
        for (int x = 0; x <= height; x++) {
            for (int y = 0; y <= width; y++) {
                if (data.get(x*2).get(y*2) == 1) {
                    maze[x][y] = new Cell(x, y);

                    int xData = x*2;
                    int yData = y*2;
                    for (int i = 0; i < 8; i++) {
                        int newX = xData + changeX[i];
                        int newY = yData + changeY[i];
                        if (newX >= 0 && newX < data.size() && newY >= 0 && newY < data.get(0).size()) {
                            if (data.get(newX).get(newY) == 4) {
                                pointIns.add(new Point(x, y));
                                data.get(newX).set(newY, 0);
                                break;
                            }
                        }
                    }
                }

                if (data.get(x*2).get(y*2) == 4) {
                    maze[x][y] = new Cell(x, y);
                    pointIns.add(new Point(x, y));
                    data.get(x*2).set(y*2, 0);
                }
            }
        }

        //System.out.println(pointIns);
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
                    if (this.data != null) {
                        int pathX = x + cell.getX();
                        int pathY = y + cell.getY();
                        if (this.data.get(pathX).get(pathY) != 1)
                            continue;
                    }
                    availableNeighbors.add(getCell(x, y));
                }
            }
        }
        return availableNeighbors;
    }

    public ArrayList<ArrayList<Integer>> export() {
        Random random = new Random();
        ArrayList<ArrayList<Integer>> exportData = new ArrayList<>();

        for (int x = 0; x <= height; x++) {
            exportData.add(new ArrayList<>());
            if (x != height-1) {
                exportData.add(new ArrayList<>());
            }
        }

        for (ArrayList<Integer> value : exportData) {
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
                    exportData.get(x).set(y, this.data.get(x).get(y));
                }
            }
        }

        for (int x = 0; x <= height; x++) {
            for (int y = 0; y <= width; y++) {
                if (getCell(x, y) != null) {
                    exportData.get(x * 2).set(y * 2, 0);
                    ArrayList<Cell> neighbors = getCell(x, y).getConnectCell();

                    if (neighbors.isEmpty()) {
                        continue;
                    }

                    for (Cell neighbor : neighbors) {
                        int pathX = x + neighbor.getX();
                        int pathY = y + neighbor.getY();
                        exportData.get(pathX).set(pathY, 0);
                    }
                }
            }
        }

        for (int x = 0; x <= height*2; x++) {
            for (int y = 0; y <= width*2; y++) {
                if (exportData.get(x).get(y) == 2) {
                    int realX = x - height;
                    int realY = y - width;

                    int up = 0;
                    int down = 0;

                    if (realX == realY || realX == -realY) {
                        if (x < height*2) up = exportData.get(x+1).get(y);
                        if (x > 0) down = exportData.get(x-1).get(y);

                        if (up == down && up == 1) {
                            int rand = Math.abs(random.nextInt()) % 2;
                            if (x < height * 2) exportData.get(x + 1).set(y, rand);
                            if (x > 0) exportData.get(x - 1).set(y, rand);
                        }
                        continue;
                    }

                    boolean check = true;
                    int newData;

                    if (realX == 0) {
                        if (x < height*2) up = exportData.get(x+1).get(y);
                        if (x > 0) down = exportData.get(x-1).get(y);
                        check = false;
                    }

                    if (realY == 0) {
                        if (y < width*2) up = exportData.get(x).get(y+1);
                        if (y > 0) down = exportData.get(x).get(y-1);
                        check = false;
                    }

                    if (check)
                        continue;

                    if (up != down) {
                        newData = 0;
                    }
                    else {
                        if (up == 1) {
                            newData = 0;
                        }
                        else {
                            newData = 1;
                        }
                    }

                    exportData.get(x).set(y, newData);
                }
            }
        }

        for (int x = 0; x <= height*2; x++) {
            for (int time = 0; time < 2; time++) {
                int y = x;
                if (time == 1) {
                    y = -(x - height) + width;
                }
                if (exportData.get(x).get(y) == 2) {
                    boolean chk = true;
                    int cnt = 0;
                    int countCell = 0;
                    for (int i = 0; i < 4; i++) {
                        int newX = x + changeX[i];
                        int newY = y + changeY[i];
                        if (newX >= 0 && newX <= height * 2 && newY >= 0 && newY <= width * 2) {
                            int value = exportData.get(newX).get(newY);
                            if (value == 0 || value == 7) {
                                cnt++;
                            }
                            if (value == -1) {
                                countCell++;
                            }
                            if (value == 5) {
                                countCell++;
                            }
                        }
                        else {
                            countCell++;
                        }
                    }

                    if (cnt >= 2) {
                        chk = false;
                    }

                    if (chk && countCell == 0) {
                        exportData.get(x).set(y, 0);
                    } else {
                        exportData.get(x).set(y, 1);
                    }
                }

                if (exportData.get(x).get(y) == 5) {
                    exportData.get(x).set(y, 1);
                }
            }
        }

        return exportData;
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
        dfsAlgorithm(x, y, maze);
        return maze;
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
        dfsAlgorithm(x, y, maze);
        return maze;
    }

    /**
     * <strong>DFS Algorithms </strong> <br>
     * - Choose a starting cell in the field and add it to the stack. <br>
     * - While there is a cell to be handled in the stack: <br>
     *      1. Pop cell from the top of the stack. <br>
     *      2. Connect and visit all available neighbors from the bottom, left, right, top and unvisited. <br>
     *      3. Randomly select the one to be on the top and Push those neighbors on the stack. <br>
     *
     * @param x is the x coordinate of the starting point
     * @param y is the y coordinate of the starting point
     * @param maze is the base maze
     */
    public static void dfsAlgorithm(int x, int y, Maze maze) {
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
    }

    /**
     * <strong>Binary Tree Algorithms </strong> <br>
     * - For each existing cell in the grid: <br>
     *      1. Get if they exist, north or west neighbors. <br>
     *      2. Toss a coin to connect with one of them. <br>
     * - It is already done! <br>
     *
     * @param maze is the base maze
     */
    public static void binaryTreeAlgorithm(Maze maze) {
        Random random = new Random(System.nanoTime());

        for (int x = 0; x < maze.height; x++) {
            for (int y = 0; y < maze.width; y++) {
                if (maze.getCell(x, y) != null) {
                    ArrayList<Cell> neighbors = new ArrayList<>();

                    if (x > 0) {
                        neighbors.add(maze.getCell(x-1, y));
                    }
                    if (y > 0) {
                        neighbors.add(maze.getCell(x, y-1));
                    }

                    if (neighbors.isEmpty()) {
                        continue;
                    }

                    int coin = Math.abs(random.nextInt()) % (neighbors.size());
                    maze.getCell(x, y).connect(neighbors.get(coin));
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void generate() {
        if (this.data.isEmpty())
            return;

        if (this.pointIns.isEmpty())
            return;

        for (Point pointIn : pointIns) {
            dfsAlgorithm(pointIn.x, pointIn.y, this);
        }
    }
}
