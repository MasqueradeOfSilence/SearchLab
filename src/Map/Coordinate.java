package Map;

/**
 * Created by williamjones on 5/15/17.
 * Represents a coordinate position on the terrain map.
 */
public class Coordinate
{
    private double x;
    private double y;
    public Coordinate(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
