package larsg310.mods.powercraft.task;

import larsg310.mods.powercraft.lib.Reference;
import net.minecraft.util.ResourceLocation;

public class TaskType
{
	public static final TaskType BUILD_HOUSE = new TaskType("buildHouse.task");
	
	public String path;
	
	/**
	 * @param path
	 *            The location to the task file. Pre-fixed with {minecraft_dir}\tasks\.
	 */
	public TaskType(String path)
	{
		this.path = path;
	}
}
