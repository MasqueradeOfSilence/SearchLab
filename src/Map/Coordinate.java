package Map;

/**
 * Created by williamjones on 5/15/17.
 * Represents a coordinate position on the terrain map.
 */
public class Coordinate
{
    private int x;
    private int y;
    public Coordinate(int x, int y)
    {
        this.x=x;
        this.y=y;
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
}
