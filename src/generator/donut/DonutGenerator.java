package generator.donut;

import generator.Generator;

import java.util.*;

public class DonutGenerator implements Generator {
    private int centerX = 0;
    private int centerY = 0;
    protected Set<Integer> radius;
    protected Map<Integer, Integer> valueOfRadius;
    protected int maxRadius = -1;

    public DonutGenerator() {
        radius = new TreeSet<>();
        valueOfRadius = new TreeMap<>();
    }

    public void addRadii(int radii, int value) {
        radius.add(radii);
        valueOfRadius.put(radii, value);
    }

    public int find(int target) {
        for (int radii : radius) {
            if (target <= radii) {
                return valueOfRadius.get(radii);
            }
        }
        return -1;
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
            }
        }
        return data;
    }
}
