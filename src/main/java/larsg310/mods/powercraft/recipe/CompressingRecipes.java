package larsg310.mods.powercraft.recipe;

import larsg310.mods.powercraft.block.ModBlocks;
import larsg310.mods.powercraft.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompressingRecipes
{
	public static ItemStack getCompressResult(ItemStack itemstack)
	{
		if (itemstack != null)
		{
			if (itemstack.isItemEqual(new ItemStack(ModItems.DUSTS, 1, 2)) && stackSizeIsMoreOrEqual(itemstack, 2)) return new ItemStack(ModBlocks.CONNECTED_GLASS, 1, 0);
			if (itemstack.isItemEqual(new ItemStack(ModItems.COMPOUNDS, 1, 0)) && stackSizeIsMoreOrEqual(itemstack, 1)) return new ItemStack(ModItems.COMPRESSED_COMPOUNDS, 1, 0);
			if (itemstack.isItemEqual(new ItemStack(ModItems.DUSTS, 1, 6)) && stackSizeIsMoreOrEqual(itemstack, 1)) return new ItemStack(ModItems.INGOTS, 1, 0);
		}
		return null;
	}
	
	private static boolean stackSizeIsMoreOrEqual(ItemStack itemstack, int stackSize)
	{
		return itemstack.stackSize >= stackSize;
	}
	
	public static int getStackSizeToDecrease(ItemStack itemstack)
	{
		if (itemstack.getItem() == Item.getItemFromBlock(ModBlocks.CONNECTED_GLASS))
		{
			return 2;
		}
		return 1;
	}
}
