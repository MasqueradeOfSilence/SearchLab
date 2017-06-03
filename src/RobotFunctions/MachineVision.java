package RobotFunctions;


import Map.Coordinate;
import Map.Node;
import Map.TerrainMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alex on 5/16/17.
 * Class to represent the vision of the robot.
 */
public class MachineVision
{
    public void addChildren(ArrayList<Node>priotrityque,Coordinate location, Node updating,  TerrainMap terrainMap)
    {
        Node add;
        add=terrainMap.getMyMap()[(int)location.getX()][(int)location.getY()-1];
        if(!add.getPathVisited().contains(add)) {
            add.getPathVisited().addAll(updating.getPathVisited());
            priotrityque.add(add);
        }
        add=terrainMap.getMyMap()[(int)location.getX()][(int)location.getY()+1];
        if(!add.getPathVisited().contains(add)) {
            add.getPathVisited().addAll(updating.getPathVisited());
            priotrityque.add(add);
        }
        add=terrainMap.getMyMap()[(int)location.getX()+1][(int)location.getY()];
        if(!add.getPathVisited().contains(add)) {
            add.getPathVisited().addAll(updating.getPathVisited());
            priotrityque.add(add);
        }
        add=terrainMap.getMyMap()[(int)location.getX()-1][(int)location.getY()];
        if(!add.getPathVisited().contains(add)) {
            add.getPathVisited().addAll(updating.getPathVisited());
            priotrityque.add(add);
        }
        add=terrainMap.getMyMap()[(int)location.getX()-1][(int)location.getY()-1];
        if(!add.getPathVisited().contains(add)) {
            add.getPathVisited().addAll(updating.getPathVisited());
            priotrityque.add(add);
        }
        add=terrainMap.getMyMap()[(int)location.getX()+1][(int)location.getY()+1];
        if(!add.getPathVisited().contains(add)) {
            add.getPathVisited().addAll(updating.getPathVisited());
            priotrityque.add(add);
        }
        add=terrainMap.getMyMap()[(int)location.getX()+1][(int)location.getY()-1];
        if(!add.getPathVisited().contains(add)) {
            add.getPathVisited().addAll(updating.getPathVisited());
            priotrityque.add(add);
        }
        add=terrainMap.getMyMap()[(int)location.getX()-1][(int)location.getY()+1];
        if(!add.getPathVisited().contains(add)) {
            add.getPathVisited().addAll(updating.getPathVisited());
            priotrityque.add(add);
        }
    }
    public Node getHighestPrioirity(ArrayList<Node>prioirtyqueue)
    {
        int largestPath=-1;
        Node returning=null;
        for(Node n:prioirtyqueue)
        {
            if(n.getPathVisited().size()>largestPath)
            {
                returning=n;
                largestPath=n.getPathVisited().size();
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
        while(priorityque.size()>0)
        {
            Node current=getHighestPrioirity(priorityque);
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


        }
        return bfsf.getPathVisited();
    }

    /**
     * Function to determine if we can reach the goal
     *  from our current node's location.
     * @return true if we can, false otherwise.
     */
    private boolean goalReachable(Node current, Node[][] myMap, Node goalNode)
    {
        int x = (int) Math.round(current.getLocation().getX());
        int y = (int) Math.round(current.getLocation().getY());

        // x too low, y fine: have nothing with x - 1
        if (x - 1 < 1 && (y + 1 < RobotUtils.gridDimensionY && y - 1 >= 0))
        {
            if (myMap[x][y].equals(goalNode) || myMap[x + 1][y].equals(goalNode) || myMap[x][y + 1].equals(goalNode)
                    || myMap[x][y - 1].equals(goalNode)
                    || myMap[x + 1][y + 1].equals(goalNode)
                    || myMap[x + 1][y - 1].equals(goalNode))
            {
                System.out.println("I have reached the goal 1");
                return true;
            }
            return false;
        }
        // x fine, y too low: have nothing with y - 1. delete 3 each time.
        else if ((x + 1 < RobotUtils.gridDimensionX && x - 1 >= 0) && y - 1 < 1)
        {
            if (myMap[x][y].equals(goalNode) || myMap[x + 1][y].equals(goalNode) || myMap[x][y + 1].equals(goalNode)
                    || myMap[x - 1][y].equals(goalNode)
                    || myMap[x + 1][y + 1].equals(goalNode)
                    || myMap[x - 1][y + 1].equals(goalNode))
            {
                System.out.println("I have reached the goal 2");
                return true;
            }
            return false;
        }
        // x too high; y fine: have nothing with x + 1.
        else if (x + 1 >= RobotUtils.gridDimensionX && (y + 1 < RobotUtils.gridDimensionY && y - 1 >= 0))
        {
            if (myMap[x][y].equals(goalNode) || myMap[x][y + 1].equals(goalNode)
                    || myMap[x - 1][y].equals(goalNode) || myMap[x][y - 1].equals(goalNode)
                    || myMap[x - 1][y + 1].equals(goalNode)
                    || myMap[x - 1][y - 1].equals(goalNode))
            {
                System.out.println("I have reached the goal 3");
                return true;
            }
            return false;
        }
        // x fine; y too high: get rid of y + 1.
        else if ((x + 1 < RobotUtils.gridDimensionX && x - 1 >= 0) && y >= RobotUtils.gridDimensionY)
        {
            if (myMap[x][y].equals(goalNode) || myMap[x + 1][y].equals(goalNode)
                    || myMap[x - 1][y].equals(goalNode) || myMap[x][y - 1].equals(goalNode)
                    || myMap[x + 1][y - 1].equals(goalNode)
                    || myMap[x - 1][y - 1].equals(goalNode))
            {
                System.out.println("I have reached the goal 4");
                return true;
            }
            return false;
        }
        // x too low, y too low: get rid of x - 1 and y - 1.
        else if (x - 1 < 1 && y - 1 < 1)
        {
            if (myMap[x][y].equals(goalNode) || myMap[x + 1][y].equals(goalNode) || myMap[x][y + 1].equals(goalNode)
                    || myMap[x + 1][y + 1].equals(goalNode))
            {
                System.out.println("I have reached the goal 5");
                return true;
            }
            return false;
        }
        // x too high, y too high: get rid of x + 1 and y + 1.
        else if (x + 1 >= RobotUtils.gridDimensionX && y + 1 >= RobotUtils.gridDimensionY)
        {
            if (myMap[x][y].equals(goalNode)
                    || myMap[x - 1][y].equals(goalNode) || myMap[x][y - 1].equals(goalNode)
                    || myMap[x - 1][y - 1].equals(goalNode))
            {
                System.out.println("I have reached the goal 6");
                return true;
            }
            return false;
        }
        // x too high, y too low: get rid of x + 1 and y - 1
        else if (x + 1 >= RobotUtils.gridDimensionX && y - 1 < 1)
        {
            if (myMap[x][y].equals(goalNode) || myMap[x][y + 1].equals(goalNode)
                    || myMap[x - 1][y].equals(goalNode)
                    || myMap[x - 1][y + 1].equals(goalNode))
            {
                System.out.println("I have reached the goal 7");
                return true;
            }
            return false;
        }
        // x too low, y too high: get rid of x - 1 and y + 1
        else if (x - 1 < 1 && y + 1 >= RobotUtils.gridDimensionY)
        {
            if (myMap[x][y].equals(goalNode) || myMap[x + 1][y].equals(goalNode)
                    || myMap[x][y - 1].equals(goalNode)
                    || myMap[x + 1][y - 1].equals(goalNode))
            {
                System.out.println("I have reached the goal 8");
                return true;
            }
            return false;
        }
        // both are OK
        else
        {
            if (myMap[x][y].equals(goalNode) || myMap[x + 1][y].equals(goalNode) || myMap[x][y + 1].equals(goalNode)
                    || myMap[x - 1][y].equals(goalNode) || myMap[x][y - 1].equals(goalNode)
                    || myMap[x + 1][y + 1].equals(goalNode)
                    || myMap[x + 1][y - 1].equals(goalNode) || myMap[x - 1][y + 1].equals(goalNode)
                    || myMap[x - 1][y - 1].equals(goalNode))
            {
                System.out.println("I have reached the goal 9");
                return true;
            }
        }
        return false;
    }

    /**
     * Collision detection
     * @param res the resultant node
     * @param allNodesInGraph all already existing nodes
     * @return true if it will either collide with a node that's
     *  already in the graph OR if the node is an obstacle; false
     *  otherwise.
     */
    private boolean collision(Node res, ArrayList<Node> allNodesInGraph)
    {
        return allNodesInGraph.contains(res) || res.getType() == RobotUtils.TYPE.OBSTACLE;
    }

    /**
     * Selects a random node in the map.
     * @param myMap the map
     * @return the random node, OR null if it has a collision
     */
    private Node generateRandomNode(Node[][] myMap, ArrayList<Node> allNodesInGraph, Robot robot)
    {
        Random random = new Random();
        // The nextInt parameter is exclusive, and this is what we want.
        int solX = random.nextInt(RobotUtils.gridDimensionX - 1);
        int solY = random.nextInt(RobotUtils.gridDimensionY - 1);
        while (solX == 0)
        {
            solX = random.nextInt(RobotUtils.gridDimensionX - 1);
        }
        while (solY == 0)
        {
            solY = random.nextInt(RobotUtils.gridDimensionY - 1);
        }
        System.out.println("SolX: " + solX);
        System.out.println("SolY: " + solY);
        Node res = myMap[solX][solY];
        // The only way to use .contains is to have an equals method in the node class.
        if (collision(res, allNodesInGraph))
        {
            return null;
        }
        return res;
    }

    /**
     * Expecting that the value angle will not take on a negative value.
     * @param myMap the map
     * @param currentNode the node
     * @param randomNode the qRAND node
     * @return the new node, one immediately adjacent
     */
    private Node chooseNewNode(Node[][] myMap, Node currentNode, Node randomNode)
    {
        // Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI + 180
        // Just use node.getLocation I guess
        // absolute value?
        double angle = Math.atan2(Math.abs(randomNode.getLocation().getY() - currentNode.getLocation().getY()),
                Math.abs(randomNode.getLocation().getX() - currentNode.getLocation().getX()));
        // Boundary checking? TECHNICALLY, the closest one shouldn't be sending it out of bounds.
        System.out.println("Examining node at location " + currentNode.getLocation().toString());
        System.out.println("Random node at location " + randomNode.getLocation().toString());
        if (angle < 0)
        {
            angle += 360; // since atan2 generates only between -180 and 180 degrees
        }
        System.out.println("Angle (normalized): " + angle);
        if (angle < 45 || (angle <= 360 && angle > 315))
        {
            return myMap[(int) Math.round(currentNode.getLocation().getX()) + 1]
                    [(int) Math.round(currentNode.getLocation().getY())];
        }
        else if (angle == 45)
        {
            return myMap[(int) Math.round(currentNode.getLocation().getX()) + 1]
                    [(int) Math.round(currentNode.getLocation().getY()) + 1];
        }
        else if (angle > 45 && angle < 135)
        {
            return myMap[(int) Math.round(currentNode.getLocation().getX())]
                    [(int) Math.round(currentNode.getLocation().getY()) + 1];
        }
        else if (angle == 135)
        {
            return myMap[(int) Math.round(currentNode.getLocation().getX()) - 1]
                    [(int) Math.round(currentNode.getLocation().getY()) + 1];
        }
        else if (angle > 135 && angle < 225)
        {
            return myMap[(int) Math.round(currentNode.getLocation().getX()) - 1]
                    [(int) Math.round(currentNode.getLocation().getY())];
        }
        else if (angle == 225)
        {
            return myMap[(int) Math.round(currentNode.getLocation().getX()) - 1]
                    [(int) Math.round(currentNode.getLocation().getY()) - 1];
        }
        else if (angle > 225 && angle < 315)
        {
            return myMap[(int) Math.round(currentNode.getLocation().getX())]
                    [(int) Math.round(currentNode.getLocation().getY()) - 1];
            // it shouldn't be getting -1 here, because it shouldn't compute an angle that's out of bounds.
        }
        else if (angle == 315)
        {
            return myMap[(int) Math.round(currentNode.getLocation().getX()) + 1]
                    [(int) Math.round(currentNode.getLocation().getY()) - 1];
        }
        return null;
    }

    /**
     * RRT algorithm for finding a path.
     *  Just FYI: the nature of this algorithm means
     *  that it will most likely produce a twisted
     *  path that is definitely not optimal. However,
     *  the primary advantage is the extremely high probability
     *  of SOME path being found.
     *  @author Alex
     *
     * @param r The robot
     * @return An arrayList of nodes containing the optimal path.
     */
    public ArrayList<Node> computePathForRRT(Robot r, Node goalNode)
    {
        ArrayList<Node> solutionPath = new ArrayList<>();
        ArrayList<Node> allNodesInGraph = new ArrayList<>();
        TerrainMap myMap = r.getMap();
        Node startNode = myMap.getMyMap()[(int) Math.round(r.getCurrentLocation().getX())]
                [(int) Math.round(r.getCurrentLocation().getY())];
        allNodesInGraph.add(startNode);
        Node currentNode = startNode;
        while (!goalReachable(currentNode, myMap.getMyMap(), goalNode))
        {
            Node randomNode;
            while ((randomNode = generateRandomNode(myMap.getMyMap(), allNodesInGraph, r)) == null)
            {
                // Just keep generating it until it's not null anymore, so it's not in the forbidden zone
            }
            // THEN DO STEP 4...
            Node newNode = chooseNewNode(myMap.getMyMap(), currentNode, randomNode);
            while (collision(newNode, allNodesInGraph))
            {
                newNode = chooseNewNode(myMap.getMyMap(), currentNode, randomNode);
            }
            // The chooseNewNode should not be called if the goal is reached
            newNode.getPathVisited().addAll(currentNode.getPathVisited());
            newNode.getPathVisited().add(currentNode);
            allNodesInGraph.add(newNode);
            currentNode = newNode;
            System.out.println("The current node is now at " + currentNode.getLocation().toString());
            // The newNode is NOT generated properly.
        }
        // This is the beginning of the end
        Node endNode = currentNode;
        solutionPath = endNode.getPathVisited();
        // This actually isn't quite correct. We need to add the GOAL NODE.
        return solutionPath;
    }
}
