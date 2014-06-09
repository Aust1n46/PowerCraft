package larsg310.mods.powercraft.item;

import larsg310.mods.powercraft.lib.Names;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
	public static Item DEBUGGER_TOOL;
	public static Item DUSTS;
	public static Item COMPOUNDS;
	public static Item COMPRESSED_COMPOUNDS;
	public static Item INGOTS;
	public static Item HARDENED_PICKAXE;
	public static Item HARDENED_SWORD;
	public static Item HARDENED_SHOVEL;
	public static Item HARDENED_AXE;
	public static Item HARDENED_HOE;
	
	public static void init()
	{
		DEBUGGER_TOOL = new ItemEnergyMeter();
		DUSTS = new ItemDusts();
		COMPOUNDS = new ItemCompound();
		COMPRESSED_COMPOUNDS = new ItemCompressedCompound();
		INGOTS = new ItemIngots();
		HARDENED_PICKAXE = new ItemHardenedPickaxe();
		HARDENED_SWORD = new ItemHardenedSword();
		HARDENED_SHOVEL = new ItemHardenedShovel();
		HARDENED_AXE = new ItemHardenedAxe();
		HARDENED_HOE = new ItemHardenedHoe();
		
		GameRegistry.registerItem(DEBUGGER_TOOL, Names.ENERGY_METER);
		GameRegistry.registerItem(DUSTS, Names.DUSTS);
		GameRegistry.registerItem(COMPOUNDS, Names.COMPOUNDS);
		GameRegistry.registerItem(COMPRESSED_COMPOUNDS, Names.COMPRESSED_COMPOUNDS);
		GameRegistry.registerItem(INGOTS, Names.INGOTS);
		GameRegistry.registerItem(HARDENED_PICKAXE, Names.HARDENED_PICKAXE);
		GameRegistry.registerItem(HARDENED_SWORD, Names.HARDENED_SWORD);
		GameRegistry.registerItem(HARDENED_SHOVEL, Names.HARDENED_SHOVEL);
		GameRegistry.registerItem(HARDENED_AXE, Names.HARDENED_AXE);
		GameRegistry.registerItem(HARDENED_HOE, Names.HARDENED_HOE);
	}
}
