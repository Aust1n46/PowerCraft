package larsg310.mods.powercraft.creativetab;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import larsg310.mods.powercraft.block.ModBlocks;
import larsg310.mods.powercraft.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabPowerCraft extends CreativeTabs
{
	public CreativeTabPowerCraft(int id, String name)
	{
		super(id, name);
	}
	
	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(ModBlocks.CABLE);
	}
}
