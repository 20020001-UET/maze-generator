package generator.maze.cell;

import java.util.ArrayList;

public class Cell {
    private final int x;
    private final int y;
    boolean available;
    private final ArrayList<Cell> connectCell;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        available = true;
        connectCell = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void connect(Cell other) {
        connectCell.add(other);
    }

    public ArrayList<Cell> getConnectCell() {
        return connectCell;
    }
}
