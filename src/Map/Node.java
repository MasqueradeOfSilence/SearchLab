package Map;

import RobotFunctions.RobotUtils;

import java.util.ArrayList;

/**
 * Created by williamjones on 5/15/17.
 * Class designed to represent a node in our traversal graph.
 */
public class Node
{
    private double degree = 0;
    private Coordinate location = new Coordinate(-1, -1);

    private Coordinate topLeft;
    private Coordinate topRight;
    private Coordinate bottomLeft;
    private Coordinate bottomRight;
    /**
     * All the previous nodes up to this point in our current path
     */
    private ArrayList<Node> pathVisited;
    private RobotUtils.TYPE type;

    public Node()
    {

    }

    //<editor-fold desc="Getters/Setters! :D">
    public double getDegree()
    {
        return degree;
    }

    public void setDegree(double degree)
    {
        this.degree = degree;
    }

    public Coordinate getLocation()
    {
        return location;
    }

    public void setLocation(Coordinate location)
    {
        this.location = location;
    }

    public Coordinate getTopLeft()
    {
        return topLeft;
    }

    public void setTopLeft(Coordinate topLeft)
    {
        this.topLeft = topLeft;
    }

    public Coordinate getTopRight()
    {
        return topRight;
    }

    public void setTopRight(Coordinate topRight)
    {
        this.topRight = topRight;
    }

    public Coordinate getBottomLeft()
    {
        return bottomLeft;
    }

    public void setBottomLeft(Coordinate bottomLeft)
    {
        this.bottomLeft = bottomLeft;
    }

    public Coordinate getBottomRight()
    {
        return bottomRight;
    }

    public void setBottomRight(Coordinate bottomRight)
    {
        this.bottomRight = bottomRight;
    }

    public ArrayList<Node> getPathVisited()
    {
        return pathVisited;
    }

    public void setPathVisited(ArrayList<Node> pathVisited)
    {
        this.pathVisited = pathVisited;
    }

    public RobotUtils.TYPE getType()
    {
        return type;
    }

    public void setType(RobotUtils.TYPE type)
    {
        this.type = type;
    }
    //</editor-fold>
}
