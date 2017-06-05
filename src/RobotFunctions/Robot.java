package RobotFunctions;

import Map.Coordinate;
import Map.Node;
import Map.TerrainMap;
import TelnetFunctions.Telnet;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by williamjones on 5/15/17.
 * Robot: Class designed to encapsulate our physical robot.
 * 🤖
 */
public class Robot
{
    private ArrayList<Node> optimalPath;
    private MachineVision vision;
    private TerrainMap map;
    private Coordinate currentLocation;
    private Coordinate orientation;

    public Robot()
    {
        optimalPath = new ArrayList<>();
        map = new TerrainMap();
        vision=new MachineVision();
    }

    /**
     * Method to calculate the path
     *  that the robot will follow
     */
    public void calculatePath()
    {
        optimalPath = vision.computeOptimalPathForBranchandBound(this);
        for (int i=0; i<optimalPath.size(); i++)
        {
            if(i!=optimalPath.size()-1) {
                optimalPath.get(i).setDegree(RobotUtils.CalculateAngle(optimalPath.get(i), optimalPath.get(i + 1)));
            }
            else
            {
                optimalPath.get(i).setDegree(  optimalPath.get(i-1).getDegree());
            }
            System.out.println(optimalPath.get(i).toString());
        }
       // System.exit(0);
    }
    public  RobotUtils.TYPE GetMeWhereIAm()
    {
        Coordinate location=RobotUtils.convertFromPixeltoNode(currentLocation);
        return map.getMyMap()[(int)location.getX()][(int)location.getY()].getType();
    }


    /**
     * @author Alex
     *
     * Determines whether or not x and y are inside of an obstacle.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param currentObstacle The obstacle we are determining.
     * @return true if inside; false otherwise.
     */
    private boolean areCoordinatesInsideOfObstacle(int x, int y, Obstacle currentObstacle)
    {
        Coordinate center = currentObstacle.getCenter();
        Coordinate lowerRight = currentObstacle.getCorner1();
        Coordinate upperRight = currentObstacle.getCorner2();
        Coordinate upperLeft = currentObstacle.getCorner3();
        Coordinate lowerLeft = currentObstacle.getCorner4();

        Coordinate nodeCenter = RobotUtils.convertFromPixeltoNode(center);
        Coordinate nodeLowerRight = RobotUtils.convertFromPixeltoNode(lowerRight);
        Coordinate nodeUpperRight = RobotUtils.convertFromPixeltoNode(upperRight);
        Coordinate nodeUpperLeft = RobotUtils.convertFromPixeltoNode(upperLeft);
        Coordinate nodeLowerLeft = RobotUtils.convertFromPixeltoNode(lowerLeft);

        boolean isInside =
                (x == Math.round(nodeCenter.getX()) && y == Math.round(nodeCenter.getY()))
                || (x == Math.round(nodeLowerRight.getX()) && y == Math.round(nodeLowerRight.getY()))
                || (x == Math.round(nodeUpperRight.getX()) && y == Math.round(nodeUpperRight.getY()))
                || (x == Math.round(nodeUpperLeft.getX()) && y == Math.round(nodeUpperLeft.getY()))
                || (x == Math.round(nodeLowerLeft.getX()) && y == Math.round(nodeLowerLeft.getY()));

        if(isInside)
        {
            System.out.println("THIS WORKS");
        }
        return isInside;
    }

    /**
     * Initial setup of the terrain map as per the steps
     *  outlined in the specs.
     *  @author Alex
     *
     * @param obstacles Each obstacle that is in our terrain map.
     *                  Assumption: Goal has already been taken care of and assigned.
     */
    public void calculateTerrainMap(ArrayList<Obstacle> obstacles)
    {
        System.out.println("the size of the obstacles is "+obstacles.size());
        // Any nodes that are inside of the obstacle's corners become obstacle type
        for(Obstacle currentObstacle : obstacles)
        {
            System.out.println(currentObstacle.toString());
            for (int p = 0; p < map.getMyMap().length; p++)
            {
                for (int q = 0; q < map.getMyMap()[p].length; q++)
                {
                    Node current = map.getMyMap()[p][q];
                    if (current == null)
                    {
                        System.out.println("current is null");
                    }
                    current.setLocation(new Coordinate(p, q));

                    if (areCoordinatesInsideOfObstacle(p, q, currentObstacle))
                         //   && current.getType() != RobotUtils.TYPE.GOAL)
                    {
                        if(currentObstacle.getType().equals("goal"))
                        {
                            current.setType(RobotUtils.TYPE.GOAL);
                        }
                        else {
                            current.setType(RobotUtils.TYPE.OBSTACLE);
                        }
                    }
                    // Otherwise, it stays with either goal or regular.
                }
            }
        }
    }
    // Though nothing I bleed for is more tormenting....
    // She made my stormy sky beautiful.

    public void rotateMe(Telnet t) throws IOException, InterruptedException
    {
        // Figure out the angles
        double currentAngle = RobotUtils.robotCurrentAngle(this.orientation);
        currentAngle = Math.toDegrees(currentAngle);
        // Normalize
        if (currentAngle < 0)
        {
            currentAngle += 360;
        }
        Coordinate c=RobotUtils.convertFromPixeltoNode(currentLocation);
        if (map.getMyMap()[(int)c.getX()][(int)c.getY()].getType()== RobotUtils.TYPE.GOAL)
        {
            t.sendSpeed(0, 0);
            System.exit(0);

        }
        double angleIneedtoBecome = map.getMyMap()[(int)c.getX()][(int)c.getY()].getDegree();
        if(angleIneedtoBecome==-1)
        {
            return;
        }
        double upperbound=angleIneedtoBecome+RobotUtils.marginoferror%360;

        double lowerbound=angleIneedtoBecome-RobotUtils.marginoferror;
        if(lowerbound<0)
        {
            lowerbound+=360;
        }
            if (currentAngle > upperbound || currentAngle < lowerbound) {
                t.sendSpeed(0, 0);
                while (currentAngle > upperbound || currentAngle < lowerbound) {
                    System.out.println("my angle is this " + currentAngle);
                    System.out.println("my angle I should be is this " + angleIneedtoBecome);
                    System.out.println("upper bound " + upperbound + " lowerbound " + lowerbound);
                    double normalizedAngle = currentAngle - angleIneedtoBecome;
                    if (normalizedAngle < 0) {
                        normalizedAngle += 360;
                    }
                    System.out.println("my normalized angle is this " + normalizedAngle);
                    if (normalizedAngle < 180) {
                        t.sendSpeed(-2, 2);
                    } else {
                        t.sendSpeed(2, -2);
                    }
                    String responseFromServer = t.sendWhere();
                    if (responseFromServer.equals("None") || responseFromServer.equals("") ||
                            responseFromServer.equals("\n")) {
                        continue;
                    }
                    Decoder.updateRobot(this, responseFromServer);
                    currentAngle = Math.toDegrees(RobotUtils.robotCurrentAngle(orientation));

                    if (currentAngle < 0) {
                        currentAngle += 360;
                    }

                }
            }




        System.out.println("I ESCAPE THE WHILE LOOP");

    }

        /*TODO  We need to calculate the degree that the robot gets from the terrain map.  Will be different now also need
        to recalculate  how we did our rotation as the formula was incorrect.
        */


    //<editor-fold desc="Getters/Setters. Do not delete this editor fold.">
    public TerrainMap getMap()
    {
        return map;
    }

    public void setMap(TerrainMap map)
    {
        this.map = map;
    }

    public Coordinate getCurrentLocation()
    {
        return currentLocation;
    }

    public void setCurrentLocation(Coordinate currentLocation)
    {
        this.currentLocation = currentLocation;
        Coordinate c = RobotUtils.convertFromPixeltoNode(currentLocation);
        //System.out.println("the robot is now located at "+c.toString());
        if(RobotUtils.startoff) {

            map.getMyMap()[(int)c.getX()][(int)c.getY()].setType(RobotUtils.TYPE.ROBOTSTARTLOCATION);
            RobotUtils.startoff=false;
        }

    }

    public Coordinate getOrientation()
    {
        return orientation;
    }

    public void setOrientation(Coordinate orientation)
    {
        this.orientation = orientation;
    }
    //</editor-fold>
}
