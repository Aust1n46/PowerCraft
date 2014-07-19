package larsg310.mods.powercraft.item;

import java.util.List;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.upgrade.IUpgrade;
import larsg310.mods.powercraft.upgrade.Upgrade;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemUpgrades extends Item implements IUpgrade
{
	private String[] upgradeNames = new String[] {"storage", "efficiency", "speed", "inventory"};
	private int[] upgradeValues = new int[] {250, 2, 20};
	private Upgrade[] upgrades = new Upgrade[] {Upgrade.STORAGE, Upgrade.EFFICIENCY, Upgrade.SPEED, Upgrade.INVENTORY};
	private IIcon[] upgradeIcons = new IIcon[upgradeNames.length];
	
	public ItemUpgrades()
	{
		this.setUnlocalizedName(Names.UPGRADES);
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
		this.setHasSubtypes(true);
	}
	
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int meta = 0; meta < upgradeNames.length; meta++)
		{
			list.add(new ItemStack(this, 1, meta));
		}
	}
	
	public IIcon getIconIndex(ItemStack itemstack)
	{
		return upgradeIcons[itemstack.getItemDamage()];
	}
	
	public IIcon getIconFromDamage(int meta)
	{
		return upgradeIcons[meta];
	}
	
	public void registerIcons(IIconRegister iconRegister)
	{
		for (int i = 0; i < upgradeNames.length; i++)
		{
			upgradeIcons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName() + "." + i);
		}
	}
	
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return getUnlocalizedName() + "." + itemstack.getItemDamage();
	}
	
	public Upgrade getUpgrade(int meta)
	{
		return upgrades[meta];
	}
	
	@Override
	public int getUpgradeWorthness(Upgrade upgrade, int meta)
	{
		if(upgrade.ordinal() == meta)
		{
			return upgradeValues[meta];
		}
		return 0;
	}
}
