package larsg310.mods.powercraft.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemDusts extends Item
{
	private String[] dusts = new String[] {"gold", "iron", "glass", "obsidian", "coal", "coalObsidian", "hardened"};
	private IIcon[] dustIcons = new IIcon[dusts.length];
	
	public ItemDusts()
	{
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
		this.setUnlocalizedName(Names.DUSTS);
		this.setHasSubtypes(true);
	}
	
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int meta = 0; meta < dusts.length; meta++)
		{
			list.add(new ItemStack(this, 1, meta));
		}
	}
	
	public IIcon getIconIndex(ItemStack itemstack)
	{
		return dustIcons[itemstack.getItemDamage()];
	}
	
	public void registerIcons(IIconRegister iconRegister)
	{
		for (int i = 0; i < dusts.length; i++)
		{
			dustIcons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName() + "." + i);
		}
	}
	
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return getUnlocalizedName() + "." + itemstack.getItemDamage();
	}
	
	public boolean isFull3D()
	{
		return true;
	}
	
	public IIcon getIconFromDamage(int meta)
	{
		return dustIcons[meta];
	}
}
