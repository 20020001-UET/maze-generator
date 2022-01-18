package client;

import client.print.Print;
import generator.circle.CircleGenerator;
import generator.maze.Maze;
import generator.multipleCircle.MultipleCircleGenerator;

public class Main {
    public static void main(String[] args) {
        /*
        //CircleGenerator circleGenerator = new CircleGenerator(50);
        MultipleCircleGenerator multipleCircleGenerator = new MultipleCircleGenerator();
        multipleCircleGenerator.addRadii(100);
        multipleCircleGenerator.addRadii(20);
        multipleCircleGenerator.addRadii(4);
        multipleCircleGenerator.addRadii(3);

        Maze maze = new Maze(300+1, 300+1);
        maze.build(multipleCircleGenerator.generate());
        maze.print("D:/Study/Code/Java/maze-generator/data/maze.txt");
         */
        CircleGenerator circleGenerator = new CircleGenerator(25);
        //Maze maze = Maze.dfs(30, 30, 0, 0);
        Maze maze = Maze.dfs(circleGenerator.generate(), 12, 12);
        Print.print(maze.export(), "D:/Study/Code/Java/maze-generator/data/maze.txt");
    }
}
