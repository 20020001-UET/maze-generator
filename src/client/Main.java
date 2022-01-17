package client;

import generator.circle.CircleGenerator;
import maze.Maze;

public class Main {
    public static void main(String[] args) {
        CircleGenerator circleGenerator = new CircleGenerator(50);
        Maze maze = new Maze(200+1, 200+1);
        maze.build(circleGenerator.generate());
        maze.print("D:/Study/Code/Java/maze-generator/data/maze.txt");
    }
}
