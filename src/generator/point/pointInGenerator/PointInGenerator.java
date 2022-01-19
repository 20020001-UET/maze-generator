package generator.point.pointInGenerator;

import generator.function.Function;
import generator.point.PointGenerator;

import java.awt.*;
import java.util.ArrayList;

public class PointInGenerator implements PointGenerator {
    protected int value;
    protected int radius;
    protected int baseRadius;
    protected ArrayList<Function> functions;
    protected ArrayList<Boolean> checkPoint;

    public PointInGenerator(int baseRadius, int radius, int value) {
        this.baseRadius = baseRadius;
        this.radius = radius;
        this.value = value;
        functions = new ArrayList<>();
        checkPoint = new ArrayList<>();
    }

    public void addFunction(Function function) {
        functions.add(function);
        checkPoint.add(true);
    }

    public void init() {
        for (int i = 0; i < checkPoint.size(); i++) {
            checkPoint.set(i, true);
        }
    }

    public ArrayList<Point> getPoints() {
        init();
        ArrayList<Point> points = new ArrayList<>();
        for (int x = 0; x <= radius*2; x++) {
            for (int y = 0; y <= radius*2; y++) {
                double distance = Math.sqrt((x - radius)*(x - radius) + (y - radius)*(y - radius));
                if ((int)distance == radius) {
                    boolean check = false;
                    for (int i = 0; i < functions.size(); i++) {
                        if (functions.get(i).check(x - radius, y - radius)) {
                            check = checkPoint.get(i);
                            //check = true;
                            if (check) {
                                checkPoint.set(i, false);
                            }
                            break;
                        }
                    }

                    if (check) {
                        points.add(new Point(x - radius + baseRadius, y - radius + baseRadius));
                        points.add(new Point(-(x - radius) + baseRadius, y - radius + baseRadius));
                        points.add(new Point(x - radius + baseRadius, -(y - radius) + baseRadius));
                        points.add(new Point(-(x - radius) + baseRadius, -(y - radius) + baseRadius));
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
