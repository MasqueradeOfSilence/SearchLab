import RobotFunctions.Decoder;
import RobotFunctions.Robot;
import RobotFunctions.RobotUtils;
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
           robot.rotateMe(connection);
           // for my function, update robot based on path. But how to test that?

           /*
           RobotUtils.TYPE mytype=robot.GetMeWhereIAm();
           if(mytype==RobotUtils.TYPE.GOAL)
            {
                connection.sendSpeed(0,0);
                break;
            }
            else if (mytype==RobotUtils.TYPE.REGULAR||mytype==RobotUtils.TYPE.ROBOTSTARTLOCATION){
               connection.sendSpeed(3, 3);
           }
           else
           {
               System.out.println("OH NO IN AN OBSTACLE!");
           }
           */
           // robot will follow what the list says to do

           // if speed 0 0, stop the robot
        }
    }

}
