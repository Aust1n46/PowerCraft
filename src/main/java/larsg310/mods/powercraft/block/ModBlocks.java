package larsg310.mods.powercraft.block;

import larsg310.mods.powercraft.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
	public static Block CABLE;
	public static Block ENERGY_STORAGE;
	public static Block POWER_GENERATOR;
	public static Block MACHINES;
	public static Block CONNECTED_GLASS;
	public static Block COMPUTER;
	
	public static void init()
	{
		CABLE = new BlockCable();
		ENERGY_STORAGE = new BlockEnergyStorage();
		POWER_GENERATOR = new BlockEnergyGenerator();
		MACHINES = new BlockMachines();
		CONNECTED_GLASS = new BlockConnectedGlass();
		COMPUTER = new BlockComputer();
		
		GameRegistry.registerBlock(CABLE, ItemBlockStandardMetadata.class, Names.CABLE);
		GameRegistry.registerBlock(ENERGY_STORAGE, ItemBlockStandardMetadata.class, Names.ENERGY_STORAGE);
		GameRegistry.registerBlock(POWER_GENERATOR, ItemBlockStandardMetadata.class, Names.ENERGY_GENERATOR);
		GameRegistry.registerBlock(MACHINES, ItemBlockStandardMetadata.class, Names.MACHINES);
		GameRegistry.registerBlock(CONNECTED_GLASS, ItemBlockStandardMetadata.class, Names.CONNECTED_GLASS);
		GameRegistry.registerBlock(COMPUTER, ItemBlockStandardMetadata.class, Names.COMPUTER);
	}
}
