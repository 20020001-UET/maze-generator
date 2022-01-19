package generator.function.line;

import generator.function.Function;

public class Line implements Function {
    private final int a;
    private final int b;
    private final int c;

    public Line(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int calc(int x, int y) {
        return a*x + b*y + c;
    }

    public boolean check(int x, int y) {
        return calc(x, y) == 0;
    }
}
