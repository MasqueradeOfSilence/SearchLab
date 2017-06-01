import RobotFunctions.Decoder;
import RobotFunctions.Robot;
import TelnetFunctions.Telnet;

import java.io.IOException;

/**
 * Created by williamjones on 5/15/17.
 * Main class.
 */
public class Main
{
    public static void main(String argv[]) throws IOException, InterruptedException
    {
        Robot robot = new Robot();
        System.out.println("Hi. I am a robot \uD83E\uDD16");
        Telnet connection = new Telnet();

        String w = connection.sendWhere();
        Decoder.updateTerrainField(robot, w);
        robot.calculatePath();
        while(true)
        {
            String s = connection.sendWhere();

           if(s.equals("None") || s.equals("") || s.equals("\n"))
           {
               continue;
           }
            Decoder.updateRobot(robot, s);
           // robot will follow what the list says to do

           // if speed 0 0, stop the robot
        }
    }

}
