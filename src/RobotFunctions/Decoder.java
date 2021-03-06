package RobotFunctions;


import Map.Coordinate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

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
        ArrayList<Obstacle>obstacles=new ArrayList<>();
        JSONObject myobject = new JSONObject(json);
        final Iterator<String> keys = myobject.keys();
        while (keys.hasNext())
        {
            final String key = keys.next();
            if (!key.equals("robot") && !key.equals("time"))
            {
                JSONObject jason = (JSONObject) myobject.get(key);
                JSONArray orientation = jason.getJSONArray("orientation");
                double xCoord = orientation.getDouble(0);
                double yCoord = orientation.getDouble(1);
                Coordinate res = new Coordinate((int) xCoord, (int) yCoord);
                JSONArray corners = jason.getJSONArray("corners");
                JSONArray corner1 = corners.getJSONArray(0);
                JSONArray corner2 = corners.getJSONArray(1);
                JSONArray corner3 = corners.getJSONArray(2);
                JSONArray corner4 = corners.getJSONArray(3);
                Coordinate corner1Coordinate = new Coordinate((int) corner1.getDouble(0),
                        (int) corner1.getDouble(1));
                Coordinate corner2Coordinate = new Coordinate((int) corner2.getDouble(0),
                        (int) corner2.getDouble(1));
                Coordinate corner3Coordinate = new Coordinate((int) corner3.getDouble(0),
                        (int) corner3.getDouble(1));
                Coordinate corner4Coordinate = new Coordinate((int) corner4.getDouble(0),
                        (int) corner4.getDouble(1));

                JSONArray center = jason.getJSONArray("center");
                Coordinate centerCoord = new Coordinate((int) center.getDouble(0),
                        (int) center.getDouble(1));
                Obstacle newObstacle = new Obstacle(centerCoord, corner1Coordinate, corner2Coordinate,
                        corner3Coordinate, corner4Coordinate, res,
                        RobotUtils.distance(centerCoord, corner1Coordinate), "obstacle");//obstacle
                newObstacle.setId(Integer.parseInt(key));
                obstacles.add(newObstacle);

            }

        }
        processObstacles(obstacles, robot);
        robot.calculateTerrainMap(obstacles);
        updateRobot(robot,json);

    }
    private static void processObstacles(ArrayList<Obstacle> obstacles, Robot r)
    {
        System.out.println("Please select a GOAL among the following options:");
        for (int j = 0; j < obstacles.size(); j++)
        {
            System.out.println(obstacles.get(j).getId() + " ");
        }
        Scanner scan=new Scanner(System.in);
        int myresult=scan.nextInt();
        for(Obstacle o:obstacles)
        {
            if(o.getId()==myresult)
            {
                o.setType("goal");      //goal
            }
        }
    }

    /**
     * Updates robot's location and direction
     */
    public static void updateRobot(Robot robot, String json)
    {
        try {
            JSONObject jsonstring = new JSONObject(json);
            JSONObject robotstring = jsonstring.getJSONObject("robot");
            JSONArray centerCoordinates = robotstring.getJSONArray("center");
            double x = centerCoordinates.getDouble(0);
            double y = centerCoordinates.getDouble(1);
            Coordinate currentCenter = new Coordinate((int)x, (int) y);
            JSONArray orientationCoordinates = robotstring.getJSONArray("orientation");
            double a = orientationCoordinates.getDouble(0);
            double b = orientationCoordinates.getDouble(1);
            JSONArray corners = robotstring.getJSONArray("corners");
            // Compute front of robot
            JSONArray corner1 = corners.getJSONArray(0);
            JSONArray corner2 = corners.getJSONArray(1);
            JSONArray corner3 = corners.getJSONArray(2);
            JSONArray corner4 = corners.getJSONArray(3);

            Coordinate c1 = new Coordinate(corner1.getDouble(0), corner1.getDouble(1));
            Coordinate c2 = new Coordinate(corner2.getDouble(0), corner2.getDouble(1));
            Coordinate c3 = new Coordinate(corner3.getDouble(0), corner3.getDouble(1));
            Coordinate c4 = new Coordinate(corner4.getDouble(0), corner4.getDouble(1));
            // Not sure which two corners to use, tbh. Experimentation will be OK IF they don't change.
            // nope
            Coordinate frontOfRobot = RobotUtils.frontOfRobot(new Coordinate((int) x, (int) y),
                    new Coordinate(corner2.getDouble(0), corner2.getDouble(1)),
                    new Coordinate(corner3.getDouble(0), corner3.getDouble(1)));

            robot.setOrientation(new Coordinate(a, b));
            //robot.setCurrentLocation(new Coordinate((int) x, (int) y));
            robot.setCurrentLocation(currentCenter);
        }
        catch( Exception e ){
            System.out.println("Robot not found ");
    }
    }



}
