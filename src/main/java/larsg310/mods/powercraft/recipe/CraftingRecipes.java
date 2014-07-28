package larsg310.mods.powercraft.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import larsg310.mods.powercraft.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class CraftingRecipes
{
    public static void register()
    {
        CraftingManager.getInstance().addRecipe(new ItemStack(ModItems.COMPOUNDS, 2, 0), "XZX", "ZXZ", "XZX", 'X', new ItemStack(ModItems.DUSTS, 1, 4), 'Z', new ItemStack(ModItems.DUSTS, 1, 3));
        
        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ModItems.DUSTS, 1, 6), new ItemStack(ModItems.DUSTS, 1, 1), new ItemStack(ModItems.DUSTS, 1, 5));
    }
}
