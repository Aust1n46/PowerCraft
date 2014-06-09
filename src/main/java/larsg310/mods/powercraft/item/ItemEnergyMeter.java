package larsg310.mods.powercraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemEnergyMeter extends Item
{
	public ItemEnergyMeter()
	{
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
		this.setUnlocalizedName(Names.ENERGY_METER);
	}
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof IEnergy)
			{
				player.addChatMessage(new ChatComponentText("[" + Reference.MOD_NAME + "] Energy level = " + ((IEnergy) tileentity).getEnergyBar().getEnergyLevel()));
			}
			return true;
		}
		return false;
	}
	
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName());
	}
	
	public boolean isFull3D()
	{
		return true;
	}
}
