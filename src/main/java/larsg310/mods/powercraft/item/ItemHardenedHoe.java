package larsg310.mods.powercraft.item;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tool.ModToolMaterial;
import net.minecraft.item.ItemHoe;

public class ItemHardenedHoe extends ItemHoe
{
    public ItemHardenedHoe()
    {
        super(ModToolMaterial.HARDENED);
        this.setUnlocalizedName(Names.HARDENED_HOE);
        this.setCreativeTab(PowerCraft.CREATIVE_TAB);
    }
}
