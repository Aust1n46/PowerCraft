package larsg310.mods.powercraft.recipe;

import larsg310.mods.powercraft.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GrindingRecipes
{
	public static ItemStack getGrindingResult(ItemStack itemstack)
	{
		if (itemstack != null)
		{
			if (isInOreDictionary("oreGold", itemstack) && stackSizeIsMoreOrEqual(itemstack, 1)) return new ItemStack(ModItems.DUSTS, 2, 0);
			if (isInOreDictionary("oreIron", itemstack) && stackSizeIsMoreOrEqual(itemstack, 1)) return new ItemStack(ModItems.DUSTS, 2, 1);
			if (itemstack.isItemEqual(new ItemStack(Blocks.glass)) && stackSizeIsMoreOrEqual(itemstack, 1)) return new ItemStack(ModItems.DUSTS, 1, 2);
			if (itemstack.isItemEqual(new ItemStack(Blocks.obsidian)) && stackSizeIsMoreOrEqual(itemstack, 1)) return new ItemStack(ModItems.DUSTS, 1, 3);
			if (itemstack.isItemEqual(new ItemStack(Items.coal)) && stackSizeIsMoreOrEqual(itemstack, 1)) return new ItemStack(ModItems.DUSTS, 1, 4);
			if (itemstack.isItemEqual(new ItemStack(ModItems.COMPRESSED_COMPOUNDS, 1, 0)) && stackSizeIsMoreOrEqual(itemstack, 1)) return new ItemStack(ModItems.DUSTS, 1, 5);
			if (isInOreDictionary("oreCopper", "dustCopper", itemstack) && stackSizeIsMoreOrEqual(itemstack, 1)) return getDoubledOutputFromOreDictionary("dustCopper", 2);
			if (isInOreDictionary("oreTin", "dustTin", itemstack) && stackSizeIsMoreOrEqual(itemstack, 1)) return getDoubledOutputFromOreDictionary("dustTin", 2);
			if (isInOreDictionary("oreNickel", "dustNickel", itemstack) && stackSizeIsMoreOrEqual(itemstack, 1)) return getDoubledOutputFromOreDictionary("dustNickel", 2);
			if (isInOreDictionary("oreSilver", "dustSilver", itemstack) && stackSizeIsMoreOrEqual(itemstack, 1)) return getDoubledOutputFromOreDictionary("dustSilver", 2);
			if (isInOreDictionary("oreLead", "dustLead", itemstack) && stackSizeIsMoreOrEqual(itemstack, 1)) return getDoubledOutputFromOreDictionary("dustLead", 2);
		}
		return null;
	}
	
	private static boolean stackSizeIsMoreOrEqual(ItemStack itemstack, int stackSize)
	{
		return itemstack.stackSize >= stackSize;
	}
	
	public static ItemStack getDoubledOutputFromOreDictionary(String name, int stackSize)
	{
		ItemStack itemstack = OreDictionary.getOres(name).get(0);
		itemstack.stackSize = stackSize;
		return itemstack;
	}
	
	public static boolean isInOreDictionary(String oreName, String dustName, ItemStack itemstack)
	{
		for (ItemStack itemstack1 : OreDictionary.getOres(oreName))
		{
			if (OreDictionary.itemMatches(itemstack, itemstack1, false))
			{
				return !OreDictionary.getOres(dustName).isEmpty();
			}
		}
		return false;
	}
	
	public static boolean isInOreDictionary(String oreName, ItemStack itemstack)
	{
		for (ItemStack itemstack1 : OreDictionary.getOres(oreName))
		{
			if (OreDictionary.itemMatches(itemstack, itemstack1, false))
			{
				return true;
			}
		}
		return false;
	}
}
