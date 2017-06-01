package Map;

import RobotFunctions.RobotUtils;

/**
 * Created by williamjones on 5/15/17.
 * Terrain Map: Map of the surrounding terrain.
 */
public class TerrainMap
{
    /**
     * Squares which need to be converted
     *  FROM pixel coordinates.
     */
    private Node[][] myMap;

    public TerrainMap()
    {
        // 1920 x 1080 pixels; there is no buffer
        myMap = new Node[RobotUtils.gridDimensionX][RobotUtils.gridDimensionY];
    }
}
