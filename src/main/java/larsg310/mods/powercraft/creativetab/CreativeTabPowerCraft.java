package larsg310.mods.powercraft.creativetab;

import larsg310.mods.powercraft.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

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
