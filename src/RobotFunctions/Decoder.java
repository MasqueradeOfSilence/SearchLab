package RobotFunctions;


/**
 * Created by williamjones on 5/21/17.
 * JSON decoder
 */
public class Decoder
{
    /**
     * From JSON: The information we need to update the map
     */
    public static void updateTerrainField(Robot robot, String json)
    {

        robot.calculateTerrainMap();
    }

    /**
     * Updates robot's location and direction
     */
    public static void updateRobot(Robot robot, String json)
    {

    }
}
