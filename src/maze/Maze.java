package maze;

import maze.cell.Cell;
import maze.cell.CellFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
    protected ArrayList<ArrayList<Cell>> cells;
    protected int height;
    protected int width;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            cells.set(i, new ArrayList<>(width));
        }
    }

    public void initialize() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells.get(i).set(j, null);
            }
        }
    }

    public void build(ArrayList<ArrayList<Integer>> data) {
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                cells.get(i).set(j, CellFactory.create(data.get(i).get(j)));
            }
        }
    }

    public void print(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:/Study/Code/Java/maze-generator/data/maze.txt"));
            for (int i = 0; i < cells.size(); i++) {
                for (int j = 0; j < cells.get(i).size(); j++) {
                    writer.write(cells.get(i).get(j).getValue());
                    writer.write(' ');
                }
                writer.write('\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO: print path and room
}
