package RobotFunctions;

import Map.Coordinate;
import Map.Node;

/**
 * Created by williamjones on 5/16/17.
 * Utility class for physics methods.
 */
public class RobotUtils
{
    public static int sizeoftiles=50;
    public static int gridDimensionX = 1960/sizeoftiles;
    public static int gridDimensionY = 1020/sizeoftiles;
    public static int marginoferror=20;

    public static double distance(Coordinate point1, Coordinate point2)
    {
        double first=point1.getX()-point2.getX();
        double second=point1.getY()-point2.getY();
        return Math.sqrt((first*first)+(second*second));
    }
    public static boolean startoff=true;
    public enum TYPE
    {
        GOAL, OBSTACLE, REGULAR,ROBOTSTARTLOCATION
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
    public static double CalculateAngle(Node calc, Node next)
    {
        double xdiff=calc.getLocation().getX()-next.getLocation().getX();
        double ydiff=calc.getLocation().getY()-next.getLocation().getY();
        if(xdiff==1&&ydiff==0)
        {
            return 180;
        }
        if(xdiff==1&&ydiff==1)
        {
            return 125;
        }
        if(xdiff==0&&ydiff==1)
        {
            return 270;
        }
        if(xdiff==-1&&ydiff==1)
        {
            return 315;
        }
        if(xdiff==-1&&ydiff==0)
        {
            return 0;
        }
        if(xdiff==-1&&ydiff==-1)
        {
            return 45;
        }
        if(xdiff==0&&ydiff==-1)
        {
            return 90;
        }
        if(xdiff==1&&ydiff==-1)
        {
            return 135;
        }
        System.out.print("YOU'VE DONE GOOFED");
        return -1;
    }
}
