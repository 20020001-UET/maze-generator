package generator.function.thetaFunction;

import generator.function.Function;

import java.util.ArrayList;

public class ThetaFunction implements Function {
    ArrayList<Integer> thetas;

    public ThetaFunction() {
        thetas = new ArrayList<>();
    }

    public void addTheta(int theta) {
        thetas.add(theta);
    }

    public int calc(int x, int y) {
        int pow_x = 1;
        int sum = 0;
        for (int theta : thetas) {
            sum += pow_x*theta;
            pow_x *= x;
        }
        return sum;
    }

    public boolean check(int x, int y) {
        return calc(x, y) == y;
    }
}
