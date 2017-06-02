package RobotFunctions;

import Map.Coordinate;
import Map.Node;
import Map.TerrainMap;
import TelnetFunctions.Telnet;
import com.sun.xml.internal.bind.v2.TODO;

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
    Coordinate currentlocation;
    Coordinate orientation;

    public Coordinate getCurrentlocation() {
        return currentlocation;
    }

    public void setCurrentlocation(Coordinate currentlocation) {
        this.currentlocation = currentlocation;
    }

    public Coordinate getOrientation() {
        return orientation;
    }

    public void setOrientaion(Coordinate orientaion) {
        this.orientation = orientaion;
    }

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
        optimalPath = vision.computeOptimalPath();
    }



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
}
