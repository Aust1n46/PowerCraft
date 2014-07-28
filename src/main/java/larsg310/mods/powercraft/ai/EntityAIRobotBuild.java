package larsg310.mods.powercraft.ai;

import larsg310.mods.powercraft.entity.EntityRobot;
import larsg310.mods.powercraft.task.Task;
import larsg310.mods.powercraft.util.BlockCoord;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class EntityAIRobotBuild extends EntityAIBase
{
    public EntityRobot entity;
    
    public EntityAIRobotBuild(EntityRobot entity)
    {
        this.entity = entity;
    }
    
    public void startExecuting()
    {
        if (entity.getTask() != null)
        {
            entity.getTask().startExecuting();
        }
    }
    
    public void resetTask()
    {
        if (entity.getTask() != null)
        {
            entity.getTask().resetTask();
        }
    }
    
    public boolean shouldExecute()
    {
        return entity.getTask() != null && entity.getTask().shouldExecute();
    }
    
    public boolean continueExecuting()
    {
        return entity.getTask() != null && entity.getTask().continueExecuting();
    }
    
    public void updateTask()
    {
        if (entity.getTask() != null)
        {
            entity.getTask().execute(entity);
        }
    }
}
