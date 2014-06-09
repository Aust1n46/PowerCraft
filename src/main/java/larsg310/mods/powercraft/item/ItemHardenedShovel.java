package larsg310.mods.powercraft.item;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tool.ModToolMaterial;
import net.minecraft.item.ItemSpade;

public class ItemHardenedShovel extends ItemSpade
{
	public ItemHardenedShovel()
	{
		super(ModToolMaterial.HARDENED);
		this.setUnlocalizedName(Names.HARDENED_SHOVEL);
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
	}
}
