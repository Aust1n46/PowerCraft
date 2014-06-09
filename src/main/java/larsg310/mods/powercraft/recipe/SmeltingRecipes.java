package larsg310.mods.powercraft.recipe;

import larsg310.mods.powercraft.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class SmeltingRecipes
{
	public static void register()
	{
		FurnaceRecipes.smelting().func_151396_a(Items.gold_ingot, new ItemStack(ModItems.DUSTS, 1, 0), 0);
		FurnaceRecipes.smelting().func_151396_a(Items.iron_ingot, new ItemStack(ModItems.DUSTS, 1, 1), 0);
	}
}
