package TestCase;

import Map.Node;
import Map.TerrainMap;
import RobotFunctions.Robot;
import RobotFunctions.RobotUtils;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Alex on 6/1/17.
 * Junit class for setting up the terrain map.
 */
public class TestTerrainMapSetup extends TestCase
{
    /**
     * There is no peace
     * There is only destruction
     */
    private Robot annihilateTheWorld;

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        annihilateTheWorld = new Robot();
    }

    @After
    public void tearDown() throws Exception
    {
        super.tearDown();
        annihilateTheWorld = null;
    }

    /**
     * This basically will test the toString method
     *  and that everything is printed out properly.
     * @throws Exception if any of the asserts fail
     */
    @Test
    public void testHardcodedMap() throws Exception
    {
        System.out.println("Hardcoded map test case...\n");
        TerrainMap myMap = annihilateTheWorld.getMap();
        Node[][] map = myMap.getMyMap();

        map[0][0].setType(RobotUtils.TYPE.OBSTACLE);
        map[10][10].setType(RobotUtils.TYPE.OBSTACLE);
        map[31][18].setType(RobotUtils.TYPE.OBSTACLE);
        map[RobotUtils.gridDimensionX - 1][RobotUtils.gridDimensionY - 1].setType(RobotUtils.TYPE.GOAL);

        myMap.print();
        assertTrue(myMap.toString().contains("  .   "));
        assertTrue(myMap.toString().contains("_____"));
        assertTrue(myMap.toString().contains("_GOAL"));
    }

}
