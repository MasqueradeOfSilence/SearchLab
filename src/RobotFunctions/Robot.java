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
        for (Node n:optimalPath)
        {
            System.out.println(n.toString());
        }
        System.exit(0);
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

        boolean isInside = (x == nodeCenter.getX() && y == nodeCenter.getY())
                || (x == nodeLowerRight.getX() && y == nodeLowerRight.getY())
                || (x == nodeUpperRight.getX() && y == nodeUpperRight.getY())
                || (x == nodeUpperLeft.getX() && y == nodeUpperLeft.getY())
                || (x == nodeLowerLeft.getX() && y == nodeLowerLeft.getY());

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
        // Any nodes that are inside of the obstacle's corners become obstacle type
        for(Obstacle currentObstacle : obstacles)
        {
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

                    if (areCoordinatesInsideOfObstacle(p, q, currentObstacle)
                            && current.getType() != RobotUtils.TYPE.GOAL)
                    {
                        current.setType(RobotUtils.TYPE.OBSTACLE);
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
        // Robot needs to face the vector angle

        double degreefromMap = -1;

        /*TODO  We need to calculate the degree that the robot gets from the terrain map.  Will be different now also need
        to recalculate  how we did our rotation as the formula was incorrect.
        */
    }

    //<editor-fold desc="Getters/Setters">
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
