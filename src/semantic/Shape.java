package semantic;

/**
 * Created by BUSE on 2016/11/29.
 */
public class Shape implements Comparable<Shape> {

    private int x;
    private int y;

    public Shape() {
        this(0, 0);
    }

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double area() {
        return 0.0;
    }

    @Override
    public int compareTo(Shape o) {
        if (this.area() > o.area())
            return 1;
        else if (this.area() == o.area())
            return 0;
        else
            return -1;
    }
}
