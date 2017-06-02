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
    }

    /**
     * Method to calculate the path
     *  that the robot will follow
     */
    public void calculatePath()
    {
        optimalPath = vision.computeOptimalPathForBranchandBound(this);
    }


    // I code this (Alex). This function will be perfect.
    public void calculateTerrainMap(ArrayList<Obstacle>obstacles)
    {
        for(Obstacle o: obstacles)
        {

        }
    }
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
