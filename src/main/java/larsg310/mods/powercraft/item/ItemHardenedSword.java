package larsg310.mods.powercraft.item;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tool.ModToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemHardenedSword extends ItemSword
{
	public ItemHardenedSword()
	{
		super(ModToolMaterial.HARDENED);
		this.setUnlocalizedName(Names.HARDENED_SWORD);
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
	}
}
