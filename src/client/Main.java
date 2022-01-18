package client;

import client.print.Print;
import generator.circle.CircleGenerator;
import generator.donut.DonutGenerator;
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

        DonutGenerator donutGenerator = new DonutGenerator();
        donutGenerator.addRadii(100, 1);
        donutGenerator.addRadii(20, 0);
        donutGenerator.addRadii(4, 2);
        donutGenerator.addRadii(3, 3);

        Maze maze = Maze.dfs(donutGenerator.generate(), 41, 41);
        Print.print(maze.export(), "D:/Study/Code/Java/maze-generator/data/maze.txt");
    }
}
