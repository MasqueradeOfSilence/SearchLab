package RobotFunctions;

import Map.Node;

/**
 * Created by Alex on 6/3/17.
 * Data package encapsulating the distance
 *  between a Node and the randomly generated node.
 */
public class NodeDistance
{
    private Node node;
    private double distance;

    public NodeDistance(Node node, Node random)
    {
        this.node = node;
        distance = RobotUtils.distance(node.getLocation(), random.getLocation());
    }

    //<editor-fold desc="Getters/Setters">
    public Node getNode()
    {
        return node;
    }

    public void setNode(Node node)
    {
        this.node = node;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }
    //</editor-fold>
}
