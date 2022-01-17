package maze;

import maze.cell.Cell;
import maze.cell.CellFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
    protected Cell[][] cells;
    protected int height;
    protected int width;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
    }

    public void initialize() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = null;
            }
        }
    }

    public void build(ArrayList<ArrayList<Integer>> data) {
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                cells[i][j] = CellFactory.create(data.get(i).get(j));
            }
        }
    }

    public void print(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (cells[i][j] != null) {
                        writer.write(""+cells[i][j].getValue());
                    }
                    else {
                        writer.write(" ");
                    }
                    writer.write(" ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO: print path and room
}
