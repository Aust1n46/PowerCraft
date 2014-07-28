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
	public int ticksBuilding;
	
	public EntityAIRobotBuild(EntityRobot entity)
	{
		this.entity = entity;
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
		return entity.hasTask();
	}
	
	public boolean continueExecuting()
	{
		return entity.getTask() != null && entity.getTask().blocks.size() > 0;
	}
	
	public void updateTask()
	{
		if (entity.getTask() != null)
		{
			if (entity.getTask().blocks.size() > 0)
			{
				ticksBuilding++;
				if (ticksBuilding > 20)
				{
					ticksBuilding -= 20;
					BlockCoord coord = entity.getTask().blocks.remove(0);
					int x = (int) (entity.posX + coord.getX()), y = (int) (entity.posY + coord.getY()), z = (int) (entity.posZ + coord.getZ());
					if (entity.worldObj.getBlock(x, y, z) != coord.getBlock())
					{
						if (entity.worldObj.getBlock(x, y, z) != Blocks.air)
						{
							entity.worldObj.getBlock(x, y, z).dropBlockAsItemWithChance(entity.worldObj, x, y, z, coord.getMeta(), 1, 0);
							entity.worldObj.getBlock(x, y, z).breakBlock(entity.worldObj, x, y, z, coord.getBlock(), coord.getMeta());
						}
						entity.worldObj.setBlock(x, y, z, coord.getBlock(), coord.getMeta(), 3);
					}
				}
			}
			else
			{
				entity.setTask(null);
			}
		}
	}
}
