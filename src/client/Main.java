package client;

import client.print.Print;
import generator.function.line.DoubleLine;
import generator.function.line.Line;
import generator.maze.Maze;
import generator.mazeData.MazeData;
import generator.point.pointInGenerator.PointInGenerator;
import generator.point.pointOutGenerator.PointOutGenerator;

import java.awt.*;

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

        int maxRadii = 87;

        Line horizontal = new Line(0, 1, 0);
        Line vertical = new Line(1, 0, 0);
        Line mainDiagonal = new Line(1, 1, 0);
        Line subDiagonal = new Line(1, -1, 0);

        MazeData mazeData = new MazeData();

        mazeData.addRadii(maxRadii, 6, false);
        mazeData.addRadii(maxRadii-1, 5, true);
        mazeData.addRadii(maxRadii-6, 2, true);
        mazeData.addRadii(maxRadii-7, 1, true);
        mazeData.addRadii(20, 7, true);
        mazeData.addRadii(4, 2, true);
        mazeData.addRadii(3, 3, false);

        mazeData.addFunction(horizontal);
        mazeData.addFunction(vertical);
        //mazeData.addFunction(mainDiagonal);
        //mazeData.addFunction(subDiagonal);

        PointInGenerator wayIn = new PointInGenerator(maxRadii,maxRadii-6, 4);
        PointOutGenerator wayOut = new PointOutGenerator(maxRadii, 4, 0);

        DoubleLine horizontalMainDiagonal = new DoubleLine(0.414213562373, 1, 0);
        DoubleLine verticalMainDiagonal = new DoubleLine(2.41421356237, 1, 0);

        wayIn.addFunction(horizontalMainDiagonal);
        wayIn.addFunction(verticalMainDiagonal);

        wayOut.addFunction(horizontal);
        wayOut.addFunction(vertical);
        wayOut.addFunction(mainDiagonal);
        wayOut.addFunction(subDiagonal);

        mazeData.addPointIn(wayIn);
        mazeData.addPointIn(wayOut);

        Maze maze = new Maze(mazeData.generate());
        maze.generate();
        Print.print(maze.export(), "D:/Study/Code/Java/maze-generator/data/maze.txt");
        //Print.print(mazeData.generate(), "D:/Study/Code/Java/maze-generator/data/maze.txt");
    }
}
