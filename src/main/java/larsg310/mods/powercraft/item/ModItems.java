package larsg310.mods.powercraft.item;

import larsg310.mods.powercraft.lib.Names;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
	public static Item DEBUGGER_TOOL;
	
	public static void init()
	{
		DEBUGGER_TOOL = new ItemDebuggerTool();
		
		GameRegistry.registerItem(DEBUGGER_TOOL, Names.DEBUGGER_TOOL);
	}
}
