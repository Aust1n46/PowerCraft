package larsg310.mods.powercraft.task;

import larsg310.mods.powercraft.entity.EntityRobot;

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
