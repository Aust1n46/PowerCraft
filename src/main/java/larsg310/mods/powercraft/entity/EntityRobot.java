package larsg310.mods.powercraft.entity;

import larsg310.mods.powercraft.ai.EntityAIRobotBuild;
import larsg310.mods.powercraft.task.Task;
import larsg310.mods.powercraft.task.TaskType;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityRobot extends EntityCreature
{
	private Task task;
	
	public EntityRobot(World world)
	{
		super(world);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIRobotBuild(this));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
	}
	
	protected boolean isAIEnabled()
	{
		return true;
	}
	
	public boolean hasTask()
	{
		return task != null;
	}
	
	public void addTask(Task task)
	{
		this.task = task;
	}
	
	public Task getTask()
	{
		return task;
	}
	
	public void setTask(Task task)
	{
		this.task = task;
	}
	
	public boolean interact(EntityPlayer player)
	{
		Task task = new Task(TaskType.BUILD_HOUSE);
		task.buildTask();
		this.setTask(task);
		return true;
	}
}
