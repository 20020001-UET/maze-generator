package generator.circle;

import generator.Generator;

import java.util.ArrayList;

public class CircleGenerator implements Generator {
    private int centerX = 0;
    private int centerY = 0;
    protected int radius;

    public CircleGenerator(int radius) {
        this.radius = radius;
        centerX = radius;
        centerY = radius;
    }

    @Override
    public ArrayList<ArrayList<Integer>> generate() {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        for (int x = 0; x <= radius*2; x++) {
            data.add(new ArrayList<>());
            for (int y = 0; y <= radius*2; y++) {
                double distance = Math.sqrt((x - centerX)*(x - centerX) + (y - centerY)*(y-centerY));
                if (distance < radius) {
                    data.get(x).add(1);
                }
                else {
                    data.get(x).add(-1);
                }
            }
        }
        return data;
    }
}
