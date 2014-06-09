package larsg310.mods.powercraft.item;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tool.ModToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class ItemHardenedPickaxe extends ItemPickaxe
{
	public ItemHardenedPickaxe()
	{
		super(ModToolMaterial.HARDENED);
		this.setUnlocalizedName(Names.HARDENED_PICKAXE);
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
	}
}
