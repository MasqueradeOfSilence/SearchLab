package RobotFunctions;

import Map.Node;
import Map.TerrainMap;

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

    public void calculateTerrainMap()
    {
        // update "map" directly
    }
}
