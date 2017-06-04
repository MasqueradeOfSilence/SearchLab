package RobotFunctions;


import Map.Coordinate;
import Map.Node;
import Map.TerrainMap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Alex on 5/16/17.
 * Class to represent the vision of the robot.
 */
public class MachineVision
{

    public boolean doIcontainduplicates(Node check, Node adding)
    {
        for (Node n: adding.getPathVisited())
        {
            if(check.getPathVisited().contains(n)||check.getType()== RobotUtils.TYPE.OBSTACLE)
            {
                return false;
            }
        }
        return true;
    }

    public void addChildren(ArrayList<Node>priotrityque,Coordinate location, Node updating,  TerrainMap terrainMap)
    {
        if(!updating.getPathVisited().contains(updating)) {
            updating.getPathVisited().add(updating);
        }
        Node add;
        if(location.getY()!=0) {
            add = terrainMap.getMyMap()[(int) location.getX()][(int) location.getY() - 1];
           // System.out.println("I add "+add.getLocation().toString());
            if (doIcontainduplicates(add, updating)) {
                add.getPathVisited().addAll(updating.getPathVisited());
                priotrityque.add(add);
            }
        }
        if(location.getY()!=terrainMap.getMyMap()[0].length-1) {
            add = terrainMap.getMyMap()[(int) location.getX()][(int) location.getY() + 1];
           // System.out.println("I add "+add.getLocation().toString());
            if (doIcontainduplicates(add, updating)) {
                add.getPathVisited().addAll(updating.getPathVisited());
                priotrityque.add(add);
            }
        }
        if(location.getX()!=terrainMap.getMyMap().length-1) {
            add = terrainMap.getMyMap()[(int) location.getX() + 1][(int) location.getY()];
            //System.out.println("I add "+add.getLocation().toString());
            if (doIcontainduplicates(add, updating)) {
                add.getPathVisited().addAll(updating.getPathVisited());
                priotrityque.add(add);
            }
        }
        if(location.getX()!=0) {
            add = terrainMap.getMyMap()[(int) location.getX() - 1][(int) location.getY()];
           // System.out.println("I add "+add.getLocation().toString());
            if (doIcontainduplicates(add, updating)) {
                add.getPathVisited().addAll(updating.getPathVisited());
                priotrityque.add(add);
            }
        }
        if(location.getX()!=0&&location.getY()!=0) {
            add = terrainMap.getMyMap()[(int) location.getX() - 1][(int) location.getY() - 1];
            //System.out.println("I add "+add.getLocation().toString());

            if (doIcontainduplicates(add, updating)){
                add.getPathVisited().addAll(updating.getPathVisited());
                priotrityque.add(add);
            }
        }
        if(location.getX()!=terrainMap.getMyMap().length-1&&location.getY()!=terrainMap.getMyMap()[0].length-1) {
            add = terrainMap.getMyMap()[(int) location.getX() + 1][(int) location.getY() + 1];
            //System.out.println("I add "+add.getLocation().toString());

            if (doIcontainduplicates(add, updating)) {
                add.getPathVisited().addAll(updating.getPathVisited());
                priotrityque.add(add);
            }
        }
        if(location.getX()!=terrainMap.getMyMap().length-1&&location.getY()!=0) {
            add = terrainMap.getMyMap()[(int) location.getX() + 1][(int) location.getY() - 1];
           // System.out.println("I add "+add.getLocation().toString());

            if (doIcontainduplicates(add, updating)) {
                add.getPathVisited().addAll(updating.getPathVisited());
                priotrityque.add(add);
            }
        }
        if(location.getX()!=0&&location.getY()!=terrainMap.getMyMap()[0].length-1) {
            add = terrainMap.getMyMap()[(int) location.getX() - 1][(int) location.getY() + 1];
           // System.out.println("I add "+add.getLocation().toString());

            if (doIcontainduplicates(add, updating)) {
                add.getPathVisited().addAll(updating.getPathVisited());
                priotrityque.add(add);
            }
        }
    }
    public Node getHighestPrioirity(ArrayList<Node>prioirtyqueue)
    {
        int smallest=Integer.MAX_VALUE;
        Node returning=null;
        for(Node n:prioirtyqueue)
        {
            if(n.getPathVisited().size()<smallest)
            {
                returning=n;
                smallest=n.getPathVisited().size();

            }
        }
        return returning;
    }


    public ArrayList<Node> computeOptimalPathForBranchandBound(Robot r)//robot does need a starting location.
    {
        Coordinate c=RobotUtils.convertFromPixeltoNode(r.getCurrentLocation());
        ArrayList<Node>priorityque=new ArrayList<>();
        priorityque.add(r.getMap().getMyMap()[(int)c.getX()][(int)c.getY()]);  //block back tracking
        Node bfsf=null;
       System.out.println(r.getMap().toString());
        while(priorityque.size()>0)
        {
            Node current=getHighestPrioirity(priorityque);
            System.out.println(current.getPathVisited().size());
            if(current.getType()== RobotUtils.TYPE.OBSTACLE)
            {
                priorityque.remove(current);
                continue;
            }
            if(current.getType()== RobotUtils.TYPE.GOAL)
            {
                if(bfsf==null)
                {
                    bfsf=current;

                }
                else
                {
                    if(bfsf.getPathVisited().size()>current.getPathVisited().size())
                    {
                        bfsf=current;

                    }
                }
                priorityque.remove(current);
                continue;
            }
            if(bfsf!=null)
            {
                if(current.getPathVisited().size()>bfsf.getPathVisited().size())
                {
                    priorityque.remove(current);
                    continue;
                }
            }
            addChildren(priorityque, current.getLocation(),current, r.getMap());
            priorityque.remove(current);

        }
        return bfsf.getPathVisited();
    }

    /**
     * Ascertains if the tile we are checking is out of bounds.
     */
    private boolean outOfBounds(int xFactor, int yFactor)
    {
        return xFactor < 0 || xFactor >= RobotUtils.gridDimensionX
                || yFactor < 0 || yFactor >= RobotUtils.gridDimensionY;
    }

    /**
     * If we are close enough to the goal to stop.
     * @param currentTile where we are currently
     * @param myMap the 2D array representing the map
     * @return true if we can reach the goal in one step or less;
     *  false otherwise.
     *
     * @author Alex
     */
    private Node goalReachedOrReachable(Node currentTile, Node[][] myMap)
    {
        if (currentTile.getType() == RobotUtils.TYPE.GOAL)
        {
            return currentTile;
        }
        int x = (int) Math.round(currentTile.getLocation().getX());
        int y = (int) Math.round(currentTile.getLocation().getY());

        if (!outOfBounds(x - 1, y + 1)
                && myMap[x - 1][y + 1].getType() == RobotUtils.TYPE.GOAL)
        {
            return myMap[x - 1][y + 1];
        }
        if (!outOfBounds(x, y + 1) && myMap[x][y + 1].getType() == RobotUtils.TYPE.GOAL)
        {
            return myMap[x][y + 1];
        }
        if (!outOfBounds(x + 1, y + 1) && myMap[x + 1][y + 1].getType() == RobotUtils.TYPE.GOAL)
        {
            return myMap[x + 1][y + 1];
        }
        if (!outOfBounds(x - 1, y) && myMap[x - 1][y].getType() == RobotUtils.TYPE.GOAL)
        {
            return myMap[x - 1][y];
        }
        if (!outOfBounds(x + 1, y) && myMap[x + 1][y].getType() == RobotUtils.TYPE.GOAL)
        {
            return myMap[x + 1][y];
        }
        if (!outOfBounds(x - 1, y - 1) && myMap[x - 1][y - 1].getType() == RobotUtils.TYPE.GOAL)
        {
            return myMap[x - 1][y - 1];
        }
        if (!outOfBounds(x, y - 1) && myMap[x][y - 1].getType() == RobotUtils.TYPE.GOAL)
        {
            return myMap[x][y - 1];
        }
        if (!outOfBounds(x + 1, y - 1) && myMap[x + 1][y - 1].getType() == RobotUtils.TYPE.GOAL)
        {
            return myMap[x + 1][y - 1];
        }
        return null;
    }

    private boolean collidesWithObstacle(Node current)
    {
        return current.getType() == RobotUtils.TYPE.OBSTACLE;
    }

    /**
     * Generates random node in the graph.
     */
    private Node generateRandomPoint(Node[][] myMap, ArrayList<Node> searchGraph)
    {
        Random rand1 = new Random();
        Random rand2 = new Random();
        boolean stop = false;
        Node toReturn = null;

        while (!stop)
        {
            int x = rand1.nextInt(RobotUtils.gridDimensionX);
            int y = rand2.nextInt(RobotUtils.gridDimensionY);
            Node temp = myMap[x][y];
            if (!collidesWithObstacle(temp) && !searchGraph.contains(temp))
            {
                toReturn = temp;
                stop = true;
            }
        }
        return toReturn;
    }

    /**
     * Computes the next node (closest one to the one with the smallest distance)
     */
    private Node getNextNodeInGraph(NodeDistance theSmallest, Node[][] myMap, Node randomPoint)
    {
        Node smallest = theSmallest.getNode();
        ArrayList<Node> contestants = new ArrayList<>();
        int x = (int) Math.round(smallest.getLocation().getX());
        int y = (int) Math.round(smallest.getLocation().getY());

        if (!outOfBounds(x - 1, y + 1) && !collidesWithObstacle(myMap[x - 1][y + 1]))
        {
            contestants.add(myMap[x - 1][y + 1]);
        }
        if (!outOfBounds(x, y + 1) && !collidesWithObstacle(myMap[x][y + 1]))
        {
            contestants.add(myMap[x][y + 1]);
        }
        if (!outOfBounds(x + 1, y + 1) && !collidesWithObstacle(myMap[x + 1][y + 1]))
        {
            contestants.add(myMap[x + 1][y + 1]);
        }
        if (!outOfBounds(x - 1, y) && !collidesWithObstacle(myMap[x - 1][y]))
        {
            contestants.add(myMap[x - 1][y]);
        }
        if (!outOfBounds(x + 1, y) && !collidesWithObstacle(myMap[x + 1][y]))
        {
            contestants.add(myMap[x + 1][y]);
        }
        if (!outOfBounds(x - 1, y - 1) && !collidesWithObstacle(myMap[x - 1][y - 1]))
        {
            contestants.add(myMap[x - 1][y - 1]);
        }
        if (!outOfBounds(x, y - 1) && !collidesWithObstacle(myMap[x][y - 1]))
        {
            contestants.add(myMap[x][y - 1]);
        }
        if (!outOfBounds(x + 1, y - 1) && !collidesWithObstacle(myMap[x + 1][y - 1]))
        {
            contestants.add(myMap[x + 1][y - 1]);
        }
        if (contestants.size() == 0)
        {
            return null;
        }

        ArrayList<NodeDistance> nodeDistances = new ArrayList<>();
        for (Node thisOne : contestants)
        {
            NodeDistance nodeDistance = new NodeDistance(thisOne, randomPoint);
            nodeDistances.add(nodeDistance);
        }

        // Get whatever node distance is closest to the random one
        NodeDistance theTiniest = nodeDistances.get(0);
        for (int i = 1; i < nodeDistances.size(); i++)
        {
            NodeDistance cur = nodeDistances.get(i);
            if (cur.getDistance() < theTiniest.getDistance())
            {
                theTiniest = cur;
            }
        }

        return theTiniest.getNode();
    }

    /**
     * Does the searchGraph have a goal in it?
     * @param searchGraph
     * @return
     */
    private Node searchGraphHasGoal(ArrayList<Node> searchGraph)
    {
        for (int i = 0; i < searchGraph.size(); i++)
        {
            if (searchGraph.get(i).getType() == RobotUtils.TYPE.GOAL)
            {
                return searchGraph.get(i);
            }
        }
        return null;
    }

    /**
     * RRT algorithm for finding a path.
     * Non-optimal, but it is guaranteed to find a path.
     *  @author Alex
     *
     * @param r The robot
     * @return An arrayList of nodes containing the optimal path.
     */
    public ArrayList<Node> computePathForRRT(Robot r) // delete that pls
    {
        // The nodes we have visited so far
        ArrayList<Node> searchGraph = new ArrayList<>();
        TerrainMap myMap = r.getMap();

        // Start at the robot's current location
        Node current = myMap.getMyMap()[(int) Math.round(r.getCurrentLocation().getX())]
                [(int) Math.round(r.getCurrentLocation().getY())];
        searchGraph.add(current);

        Node goal = null;

        while ((goal = goalReachedOrReachable(current, myMap.getMyMap())) == null)
        {
            // Generate the random point in the terrain map
            Node random = generateRandomPoint(myMap.getMyMap(), searchGraph);
            // Find the node in your graph that is closest to the random point
            ArrayList<NodeDistance> nodeDistances = new ArrayList<>();
            for (Node thisOne : searchGraph)
            {
                NodeDistance nodeDistance = new NodeDistance(thisOne, random);
                nodeDistances.add(nodeDistance);
            }

            // Gets whichever node has the shortest distance from the random one
            NodeDistance theSmallest = nodeDistances.get(0);
            for (int i = 1; i < nodeDistances.size(); i++)
            {
                NodeDistance cur = nodeDistances.get(i);
                if (cur.getDistance() < theSmallest.getDistance())
                {
                    theSmallest = cur;
                }
            }

            // For theSmallest, get the adjacent node that is closest to the random point.
            // No-go if the node is on top of an obstacle, or if it's out of bounds.
            Node nextInGraph = getNextNodeInGraph(theSmallest, myMap.getMyMap(), random);
            if (nextInGraph == null)
            {
                continue;
            }
            // There might be a pointer issue here. But I *think* it should be okay.
            nextInGraph.getPathVisited().add(current);
            nextInGraph.getPathVisited().addAll((new ArrayList<>(current.getPathVisited())));
            current.setPathVisited(null);
            searchGraph.add(nextInGraph);
            current = nextInGraph;
        }

        Node end = searchGraphHasGoal(searchGraph);
        if (end == null)
        {
            goal.getPathVisited().add(current);
            goal.getPathVisited().addAll(new ArrayList<>(current.getPathVisited()));
            searchGraph.add(goal);
            current.setPathVisited(null);
        }
        else // Then we got the goal from the function, I believe
        {
            goal = end;
            // A bit sketchy on this second line here
            goal.setPathVisited(new ArrayList<>(current.getPathVisited()));
        }

        return goal.getPathVisited();
    }
}
