package larsg310.mods.powercraft.container;

import larsg310.mods.powercraft.tileentity.TileEntityEnergyGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerEnergyGenerator extends Container
{
	public final EntityPlayer player;
	public final World world;
	public final int x;
	public final int y;
	public final int z;
	public TileEntityEnergyGenerator tileentity;
	
	public ContainerEnergyGenerator(EntityPlayer player, World world, int x, int y, int z)
	{
		this.player = player;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.tileentity = (TileEntityEnergyGenerator) world.getTileEntity(x, y, z);
		
		this.addSlotToContainer(new Slot(tileentity, 0, 176 / 2 - 8, 61));
		
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 83 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 141));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		return null;
	}
}
