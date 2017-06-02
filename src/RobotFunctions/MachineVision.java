package RobotFunctions;


import Map.Coordinate;
import Map.Node;
import Map.TerrainMap;

import java.util.ArrayList;

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
    public ArrayList<Node> computeOptimalPathForROT(Robot r)
    {
        return new ArrayList<>();
    }
}
