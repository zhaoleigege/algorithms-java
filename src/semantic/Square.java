package semantic;

/**
 * Created by BUSE on 2016/11/29.
 */
public class Square extends Shape {

    private double r;

    public Square() {
        this(0, 0, 1);
    }

    public Square(int x, int y, double r) {
        super(x, y);
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public int compareTo(Shape o) {
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        return "Square{" +
                "r=" + r +
                '}';
    }

    @Override
    public double area() {
        return 3.14 * r * r;
    }
}
