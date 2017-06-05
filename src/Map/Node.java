package Map;

import RobotFunctions.RobotUtils;

import java.util.ArrayList;

import static RobotFunctions.RobotUtils.TYPE.REGULAR;

/**
 * Created by williamjones on 5/15/17.
 * Class designed to represent a node in our traversal graph.
 */
public class Node
{
    private double degree = -1;
    private Coordinate location = new Coordinate(-1, -1);

    // these would be in pixels, not node coordinates
    private Coordinate topLeft;
    private Coordinate topRight;
    private Coordinate bottomLeft;
    private Coordinate bottomRight;

    private boolean partOfPath = false;
    /**
     * All the previous nodes up to this point in our current path
     */
    private ArrayList<Node> pathVisited;

    private RobotUtils.TYPE type;

    public Node()
    {
        type = REGULAR;
        pathVisited = new ArrayList<>();
    }



    public void printmylist()
    {

        for(Node n:pathVisited)
        {
            System.out.println(n.toString());
        }
    }
    //<editor-fold desc="Equals/HashCode - Do NOT delete">
    /**
     * The equals method is necessary for calling arrayList.contains().
     *  Please do NOT delete this!
     * @param o the object we are comparing to
     * @return true if equal; false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (Double.compare(node.degree, degree) != 0) return false;
        if (location != null ? !location.equals(node.location) : node.location != null) return false;
        if (topLeft != null ? !topLeft.equals(node.topLeft) : node.topLeft != null) return false;
        if (topRight != null ? !topRight.equals(node.topRight) : node.topRight != null) return false;
        if (bottomLeft != null ? !bottomLeft.equals(node.bottomLeft) : node.bottomLeft != null) return false;
        if (bottomRight != null ? !bottomRight.equals(node.bottomRight) : node.bottomRight != null) return false;
        if (pathVisited != null ? !pathVisited.equals(node.pathVisited) : node.pathVisited != null) return false;
        return type == node.type;
    }

    /**
     * Intellij just wanted to generate this, so I said sure son
     * @return a hash code
     */
    @Override
    public int hashCode()
    {
        int result;
        long temp;
        temp = Double.doubleToLongBits(degree);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (topLeft != null ? topLeft.hashCode() : 0);
        result = 31 * result + (topRight != null ? topRight.hashCode() : 0);
        result = 31 * result + (bottomLeft != null ? bottomLeft.hashCode() : 0);
        result = 31 * result + (bottomRight != null ? bottomRight.hashCode() : 0);
        result = 31 * result + (pathVisited != null ? pathVisited.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Node{" +
                "degree=" + degree +
                ", location=" + location +
                ", topLeft=" + topLeft +
                ", topRight=" + topRight +
                ", bottomLeft=" + bottomLeft +
                ", bottomRight=" + bottomRight +
                ", type=" + type +
                '}';
    }

    //</editor-fold>

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
        if (pathVisited == null)
        {
            pathVisited = new ArrayList<>();
        }
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

    public boolean isPartOfPath()
    {
        return partOfPath;
    }

    public void setPartOfPath(boolean partOfPath)
    {
        this.partOfPath = partOfPath;
    }

    //</editor-fold>
}

