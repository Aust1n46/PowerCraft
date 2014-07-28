package larsg310.mods.powercraft.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import larsg310.mods.powercraft.entity.EntityRobot;
import larsg310.mods.powercraft.util.BlockCoord;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;

public class TaskBuildDirewolf20House extends Task
{
    public List<BlockCoord> blocks = new ArrayList<BlockCoord>();
    public int ticksBuilding;
    public int blocksPlaced;
    
    public TaskBuildDirewolf20House()
    {
        super("buildDirewolf20House.task");
    }
    
    @Override
    public void execute(EntityRobot entity)
    {
        if (blocks.size() > 0 && !(blocksPlaced >= blocks.size()))
        {
            ticksBuilding++;
            if (ticksBuilding > 20)
            {
                ticksBuilding -= 20;
                BlockCoord coord = blocks.get(blocksPlaced);
                int x = (int) (entity.posX + coord.getX());
                int y = (int) (entity.posY + coord.getY());
                int z = (int) (entity.posZ + coord.getZ());
                while (entity.worldObj.getBlock(x, y, z) == coord.getBlock())
                {
                    blocksPlaced++;
                    if (blocksPlaced >= blocks.size())
                    {
                        break;
                    }
                    coord = blocks.get(blocksPlaced);
                    x = (int) (entity.posX + coord.getX());
                    y = (int) (entity.posY + coord.getY());
                    z = (int) (entity.posZ + coord.getZ());
                }
                
                if (!(blocksPlaced >= blocks.size()))
                {
                    if (entity.worldObj.getBlock(x, y, z) != Blocks.air)
                    {
                        entity.worldObj.getBlock(x, y, z).dropBlockAsItemWithChance(entity.worldObj, x, y, z, coord.getMeta(), 1, 0);
                        entity.worldObj.getBlock(x, y, z).breakBlock(entity.worldObj, x, y, z, coord.getBlock(), coord.getMeta());
                    }
                    entity.worldObj.setBlock(x, y, z, coord.getBlock(), coord.getMeta(), 3);
                }
                blocksPlaced++;
            }
        }
        else
        {
            entity.setTask(null);
        }
    }
    
    public void buildTask()
    {
        String basePath = Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
        String path = basePath.substring(0, basePath.length() - 1) + "tasks\\" + this.path;
        try
        {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            blocks = (ArrayList<BlockCoord>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void save()
    {
        String basePath = Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
        String path = basePath.substring(0, basePath.length() - 1) + "tasks\\" + this.path;
        File file = new File(path);
        try
        {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(blocks);
            out.close();
            fileOut.close();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void startExecuting()
    {
        this.ticksBuilding = 0;
    }
    
    public void resetTask()
    {
        this.ticksBuilding = 0;
    }
    
    public boolean shouldExecute()
    {
        return blocks.size() > 0;
    }
    
    public boolean continueExecuting()
    {
        return blocks.size() > 0;
    }
}
