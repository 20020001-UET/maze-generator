package generator.function.line;

import generator.function.Function;

public class DoubleLine implements Function {
    private final double a;
    private final double b;
    private final double c;

    public DoubleLine(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int calc(int x, int y) {
        return (int)(a*x + b*y + c);
    }

    public double calcDouble(int x, int y) {
        return a*x + b*y + c;
    }

    public boolean check(int x, int y) {
        return (int)Math.abs(calcDouble(x, y)) == 0;
    }
}
