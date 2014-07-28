package larsg310.mods.powercraft.util;

public class WorldCoord
{
    public int x;
    public int y;
    public int z;
    
    public WorldCoord(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public boolean equals(Object obj)
    {
        WorldCoord coords = (WorldCoord) obj;
        return x == coords.x && y == coords.y && z == coords.z;
    }
}
