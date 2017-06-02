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

    /**
     * Guarding against those pesky null pointer exceptions.
     */
    private void initMap()
    {
        for (int i = 0; i < myMap.length; i++)
        {
            for (int j = 0; j < myMap[i].length; j++)
            {
                myMap[i][j] = new Node();
            }
        }
    }

    public TerrainMap()
    {
        // 1920 x 1080 pixels; there is no buffer
        myMap = new Node[RobotUtils.gridDimensionX][RobotUtils.gridDimensionY];
        initMap();
    }

    /**
     * Simple print method utilizing the toString.
     */
    public void print()
    {
        System.out.println(toString());
    }

    /**
     * toString method.
     * @return terrainMap in a string representation.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("TerrainMap toString(): \n\n");
        sb.append("Key: \n");
        sb.append("\t  .   = Regular node (Will become part of graph)\n");
        sb.append("\t_____ = Obstacle node\n");
        sb.append("\t_GOAL = Goal node\n");
        sb.append("\tERROR = Something got jacked up. Possibly something is null.\n\n");
        for (Node[] aMyMap : myMap)
        {
            for (int j = 0; j < aMyMap.length; j++)
            {
                Node current = aMyMap[j];
                switch (current.getType())
                {
                    case REGULAR:
                        sb.append("  .   ");
                        break;
                    case OBSTACLE:
                        sb.append("_____ ");
                        break;
                    case GOAL:
                        sb.append("_GOAL ");
                        break;
                    default:
                        sb.append("ERROR ");
                        break;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    //<editor-fold desc="Getters/Setters!">
    public Node[][] getMyMap()
    {
        return myMap;
    }

    public void setMyMap(Node[][] myMap)
    {
        this.myMap = myMap;
    }
    //</editor-fold>
}
