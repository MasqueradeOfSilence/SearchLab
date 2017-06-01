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
    /**
     * All the previous nodes up to this point in our current path
     */
    private ArrayList<Node> pathVisited;
    private RobotUtils.TYPE type;

    public Node()
    {

    }
}
