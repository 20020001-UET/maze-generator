package generator.pizzaGenerator;

import generator.Generator;
import generator.function.Function;

import java.util.*;

public class PizzaGenerator implements Generator {
    private int centerX = 0;
    private int centerY = 0;
    protected Set<Integer> radius;
    protected Map<Integer, Integer> valueOfRadius;
    protected Map<Integer, Boolean> isBorder;
    protected ArrayList<Function> functions;
    protected int maxRadius = -1;

    public PizzaGenerator() {
        radius = new TreeSet<>();
        valueOfRadius = new TreeMap<>();
        isBorder = new TreeMap<>();
        functions = new ArrayList<>();
    }

    public void addRadii(int radii, int value, boolean border) {
        radius.add(radii);
        valueOfRadius.put(radii, value);
        isBorder.put(radii, border);
    }

    public void addFunction(Function function) {
        functions.add(function);
    }

    public int find(int target) {
        for (int radii : radius) {
            if (target <= radii) {
                return valueOfRadius.get(radii);
            }
        }
        return -1;
    }

    public boolean checkBorder(int x, int y, int distance) {
        boolean check = false;
        for (Function function : functions) {
            if (function.check(x - centerX, y - centerY)) {
                check = true;
                break;
            }
        }

        if (check) {
            for (int radii : radius) {
                if (distance <= radii) {
                    return isBorder.get(radii);
                }
            }
            return false;
        }
        return false;
    }

    public void update() {
        for (int radii : radius) {
            maxRadius = centerX = centerY = radii;
        }
    }

    @Override
    public ArrayList<ArrayList<Integer>> generate() {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        update();
        for (int x = 0; x <= maxRadius*2; x++) {
            data.add(new ArrayList<>());
            for (int y = 0; y <= maxRadius*2; y++) {
                double distance = Math.sqrt((x - centerX)*(x - centerX) + (y - centerY)*(y-centerY));
                int value = find((int)distance);
                data.get(x).add(value);
                if (checkBorder(x, y, (int)distance)) {
                    data.get(x).set(y, 2);
                }
            }
        }
        return data;
    }
}
