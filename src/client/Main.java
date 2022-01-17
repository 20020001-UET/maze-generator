package client;

import generator.circle.CircleGenerator;
import generator.multipleCircle.MultipleCircleGenerator;
import maze.Maze;

public class Main {
    public static void main(String[] args) {
        //CircleGenerator circleGenerator = new CircleGenerator(50);
        MultipleCircleGenerator multipleCircleGenerator = new MultipleCircleGenerator();
        multipleCircleGenerator.addRadii(100);
        multipleCircleGenerator.addRadii(20);
        multipleCircleGenerator.addRadii(4);
        multipleCircleGenerator.addRadii(3);

        Maze maze = new Maze(300+1, 300+1);
        maze.build(multipleCircleGenerator.generate());
        maze.print("D:/Study/Code/Java/maze-generator/data/maze.txt");
    }
}
