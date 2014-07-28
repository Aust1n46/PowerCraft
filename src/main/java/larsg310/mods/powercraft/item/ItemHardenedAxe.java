package larsg310.mods.powercraft.item;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tool.ModToolMaterial;
import net.minecraft.item.ItemAxe;

public class ItemHardenedAxe extends ItemAxe
{
    public ItemHardenedAxe()
    {
        super(ModToolMaterial.HARDENED);
        this.setUnlocalizedName(Names.HARDENED_AXE);
        this.setCreativeTab(PowerCraft.CREATIVE_TAB);
    }
}
