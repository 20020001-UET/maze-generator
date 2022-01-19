package generator.point.pointOutGenerator;

import generator.function.Function;
import generator.point.PointGenerator;

import java.awt.*;
import java.util.ArrayList;

public class PointOutGenerator implements PointGenerator {
    protected int value;
    protected int radius;
    protected int baseRadius;
    protected ArrayList<Function> functions;

    public PointOutGenerator(int baseRadius, int radius, int value) {
        this.baseRadius = baseRadius;
        this.radius = radius;
        this.value = value;
        functions = new ArrayList<>();
    }

    public void addFunction(Function function) {
        functions.add(function);
    }

    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();
        for (int x = 0; x <= radius*2; x++) {
            for (int y = 0; y <= radius*2; y++) {
                double distance = Math.sqrt((x - radius)*(x - radius) + (y - radius)*(y - radius));
                if ((int)distance == radius) {
                    boolean check = false;
                    for (Function function : functions) {
                        if (function.check(x - radius, y - radius)) {
                            check = true;
                            break;
                        }
                    }

                    if (!check) {
                        points.add(new Point(x - radius + baseRadius, y - radius + baseRadius));
                    }
                }
            }
        }
        return points;
    }

    public int getValue() {
        return value;
    }
}
