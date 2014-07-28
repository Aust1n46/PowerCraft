package larsg310.mods.powercraft.item;

import java.util.List;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemIngots extends Item
{
    private String[] ingots = new String[] {"refined", "coalObsidian"};
    private IIcon[] ingotIcons = new IIcon[ingots.length];
    
    public ItemIngots()
    {
        this.setCreativeTab(PowerCraft.CREATIVE_TAB);
        this.setUnlocalizedName(Names.INGOTS);
        this.setHasSubtypes(true);
    }
    
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int meta = 0; meta < ingots.length; meta++)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }
    
    public IIcon getIconIndex(ItemStack itemstack)
    {
        return ingotIcons[itemstack.getItemDamage()];
    }
    
    public void registerIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < ingots.length; i++)
        {
            ingotIcons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName() + "." + i);
        }
    }
    
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return getUnlocalizedName() + "." + itemstack.getItemDamage();
    }
    
    public IIcon getIconFromDamage(int meta)
    {
        return ingotIcons[meta];
    }
}
