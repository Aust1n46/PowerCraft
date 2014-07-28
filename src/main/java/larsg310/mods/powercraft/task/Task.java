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

public abstract class Task
{
    public String path;
    
    protected Task(String path)
    {
        this.path = path;
    }
    
    public abstract void execute(EntityRobot entity);
    
    public void startExecuting()
    {
        
    }
    
    public void resetTask()
    {
        
    }
    
    public abstract boolean shouldExecute();
    
    public boolean continueExecuting()
    {
        return true;
    }
}
