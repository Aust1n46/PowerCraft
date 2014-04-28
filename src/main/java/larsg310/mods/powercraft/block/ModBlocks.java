package larsg310.mods.powercraft.block;

import larsg310.mods.powercraft.lib.Names;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
	public static Block CABLE;
	public static Block ENERGY_STORAGE;
	
	public static void init()
	{
		CABLE = new BlockCable();
		ENERGY_STORAGE = new BlockEnergyStorage();
		
		GameRegistry.registerBlock(CABLE, Names.CABLE);
		GameRegistry.registerBlock(ENERGY_STORAGE, Names.ENERGY_STORAGE);
	}
}
