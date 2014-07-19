package larsg310.mods.powercraft.container;

import larsg310.mods.powercraft.slot.SlotOutput;
import larsg310.mods.powercraft.slot.SlotUpgrade;
import larsg310.mods.powercraft.tileentity.TileEntityEnergizedFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerEnergizedFurnace extends Container
{
	public final EntityPlayer player;
	public final World world;
	public final int x;
	public final int y;
	public final int z;
	public TileEntityEnergizedFurnace tileentity;
	
	public ContainerEnergizedFurnace(EntityPlayer player, World world, int x, int y, int z)
	{
		this.player = player;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.tileentity = (TileEntityEnergizedFurnace) world.getTileEntity(x, y, z);
		
		updateSlots();
	}
	
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
	{
		return null;
	}
	
	public void updateSlots()
	{
		this.inventorySlots.clear();
		this.addSlotToContainer(new Slot(tileentity, 0, 56 + (tileentity.hasExtraInventory ? 60 : 0), 35));
		this.addSlotToContainer(new SlotOutput(player, tileentity, 1, 116 + (tileentity.hasExtraInventory ? 60 : 0), 35));
		this.addSlotToContainer(new SlotUpgrade(tileentity, 2, 152 + (tileentity.hasExtraInventory ? 60 : 0), 8));
		this.addSlotToContainer(new SlotUpgrade(tileentity, 3, 152 + (tileentity.hasExtraInventory ? 60 : 0), 26));
		this.addSlotToContainer(new SlotUpgrade(tileentity, 4, 152 + (tileentity.hasExtraInventory ? 60 : 0), 44));
		this.addSlotToContainer(new SlotUpgrade(tileentity, 5, 152 + (tileentity.hasExtraInventory ? 60 : 0), 62));
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				this.addSlotToContainer(new Slot(tileentity, 6 + i + j * 3, tileentity.hasExtraInventory ? -51 + 18 * i + 60 : -1000, 9 + 18 * j));
			}
		}
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18 + (tileentity.hasExtraInventory ? 60 : 0), 84 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18 + (tileentity.hasExtraInventory ? 60 : 0), 142));
		}
	}
}
