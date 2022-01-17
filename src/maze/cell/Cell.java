package maze.cell;

public abstract class Cell {
    protected int value;

    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
