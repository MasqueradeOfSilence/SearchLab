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
        Robot r = new Robot();
        System.out.println("Hi. I am a robot \uD83E\uDD16");
        Telnet connection = new Telnet();

        while(true)
        {
            String s=connection.sendWhere();

           if(s.equals("None") || s.equals("") || s.equals("\n"))
           {
               continue;
           }
        }
    }

}
