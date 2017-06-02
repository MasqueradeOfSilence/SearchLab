package RobotFunctions;

import Map.Coordinate;

/**
 * Created by williamjones on 5/16/17.
 * Utility class for physics methods.
 */
public class RobotUtils
{
    public static int sizeoftiles=50;
    public static int gridDimensionX = 1960/sizeoftiles;
    public static int gridDimensionY = 1020/sizeoftiles;

    public static double distance(Coordinate point1, Coordinate point2)
    {
        double first=point1.getX()-point2.getX();
        double second=point1.getY()-point2.getY();
        return Math.sqrt((first*first)+(second*second));
    }
    public enum TYPE
    {
        GOAL, OBSTACLE, REGULAR
    }
    /**
     * Sign: Returns the sign, either + or -, of a number.
     * If it is zero, we will still be multiplying it
     *  by negative infinity. So keep it as 1.
     */
    public static int sign(double param)
    {
        if (param < 0)
        {
            return -1;
        }
        return 1;
    }
    public static double robotCurrentAngle(Coordinate orientation)
    {
        double x = orientation.getX();
        double y = orientation.getY();
        return Math.acos(x) * sign(y);
    }
    public static Coordinate convertFromPixeltoNode(Coordinate c)
    {
        return new Coordinate(c.getX()/sizeoftiles, c.getY()/sizeoftiles);
    }
}
