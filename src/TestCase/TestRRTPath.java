package TestCase;

import Map.Coordinate;
import Map.Node;
import Map.TerrainMap;
import RobotFunctions.MachineVision;
import RobotFunctions.Obstacle;
import RobotFunctions.Robot;
import RobotFunctions.RobotUtils;
import junit.framework.TestCase; // This import has to be here manually, or it screws it up because the package name is same
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alex on 6/2/17.
 * Simple RRT test to make sure it doesn't crash or anything
 */
public class TestRRTPath extends TestCase
{
    /**
     * But why is her name Aunt Fanny?
     * We couldn't call her Aunt Booty.
     */
    private Robot auntBooty;

    private MachineVision vision;

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        auntBooty = new Robot();
        vision = new MachineVision();
    }

    @After
    public void tearDown() throws Exception
    {
        super.tearDown();
        auntBooty = null;
        vision = null;
    }

    public void testBasic() throws Exception
    {
        TerrainMap terrainMap = new TerrainMap();
        for (int i = 0; i < terrainMap.getMyMap().length; i++)
        {
            for (int j = 0; j < terrainMap.getMyMap()[i].length; j++)
            {
                if (i > 33 && j < 24)
                {
                    terrainMap.getMyMap()[i][j].setType(RobotUtils.TYPE.GOAL);
                }
            }
        }
        auntBooty.setMap(terrainMap);
        assertNotNull(auntBooty.getMap());
        assertNotNull(auntBooty.getMap().getMyMap());
        assertTrue(auntBooty.getMap().equals(terrainMap));
        assertTrue(Arrays.deepEquals(auntBooty.getMap().getMyMap(), terrainMap.getMyMap()));

        assertNotNull(terrainMap.getMyMap()[0][0]);
        assertNotNull(auntBooty.getMap().getMyMap()[0][0]);

        auntBooty.setCurrentLocation(new Coordinate(0, 19));
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(new Coordinate(609, 320.75), new Coordinate(553, 327),
                new Coordinate(601, 267), new Coordinate(666, 314), new Coordinate(616, 375),
                new Coordinate(-0.8, -0.6), RobotUtils.distance(new Coordinate(609, 320.75),
                new Coordinate(553, 327)), "obstacle"));

        auntBooty.calculateTerrainMap(obstacles);
        ArrayList<Node> result = vision.computePathForRRT(auntBooty);
        assertTrue(result.size() > 0);
        System.out.println("The size is: " + result.size());
    }
}
