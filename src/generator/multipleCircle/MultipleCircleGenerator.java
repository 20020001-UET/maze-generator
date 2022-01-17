package generator.multipleCircle;

import generator.Generator;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MultipleCircleGenerator implements Generator {
    private int centerX = 0;
    private int centerY = 0;
    protected Set<Integer> radius;
    protected int maxRadius = -1;

    public MultipleCircleGenerator() {
        radius = new TreeSet<>();
    }

    public void addRadii(int radii) {
        radius.add(radii);
    }

    public int find(int target) {
        int cnt = 0;
        for (int radii : radius) {
            if (target <= radii) {
                return cnt;
            }
            cnt++;
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
                if (value == -1) {
                    data.get(x).add(value);
                }
                else {
                    data.get(x).add(value % 2);
                }
            }
        }
        return data;
    }
}
