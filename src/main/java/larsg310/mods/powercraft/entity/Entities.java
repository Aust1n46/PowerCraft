package larsg310.mods.powercraft.entity;

import larsg310.mods.powercraft.PowerCraft;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entities
{
	public static void register()
	{
		EntityRegistry.registerModEntity(EntityRobot.class, "entity.robot", 0, PowerCraft.instance, 80, 3, false);
	}
}
