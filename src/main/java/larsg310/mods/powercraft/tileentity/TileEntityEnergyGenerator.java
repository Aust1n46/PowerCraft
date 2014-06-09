package larsg310.mods.powercraft.tileentity;

import larsg310.mods.powercraft.block.BlockType;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.energy.EnergyNet;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.upgrade.IUpgrade;
import larsg310.mods.powercraft.util.InventoryUtil;
import larsg310.mods.powercraft.util.NBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyGenerator extends TileEntity implements IEnergy, IInventory, ISidedInventory
{
	private EnergyBar energyBar = new EnergyBar(10000);
	private ItemStack[] inventory = new ItemStack[1];
	public int burnTime;
	public int currentItemBurnTime;
	public int rotation = 3;
	
	public void updateEntity()
	{
		boolean modified = burnTime > 0;
		if (burnTime > 0)
		{
			burnTime--;
			energyBar.addEnergyWithRemaining(1);
		}
		if (burnTime == 0 && TileEntityFurnace.isItemFuel(inventory[0]))
		{
			currentItemBurnTime = burnTime = TileEntityFurnace.getItemBurnTime(inventory[0]) / 10;
			inventory[0].stackSize--;
			if (inventory[0].stackSize <= 0)
			{
				inventory[0] = null;
			}
		}
		EnergyNet.distributeEnergyToSurrounding(worldObj, xCoord, yCoord, zCoord, energyBar);
		if (modified != burnTime > 0)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	@Override
	public boolean canAddEnergyOnSide(ForgeDirection direction)
	{
		return false;
	}
	
	@Override
	public boolean canConnect(ForgeDirection direction)
	{
		return true;
	}
	
	@Override
	public EnergyBar getEnergyBar()
	{
		return energyBar;
	}
	
	@Override
	public void setLastReceivedDirection(ForgeDirection direction)
	{
		
	}
	
	@Override
	public int getEnergyTransferRate()
	{
		return 100;
	}
	
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}
	
	@Override
	public int getSizeInventory()
	{
		return InventoryUtil.getSizeInventory(inventory);
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return InventoryUtil.getStackInSlot(inventory, slot);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		return InventoryUtil.decrStackSize(inventory, slot, count);
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return InventoryUtil.getStackInSlotOnClosing(inventory, slot);
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		InventoryUtil.setInventorySlotContents(this, inventory, slot, itemstack);
	}
	
	@Override
	public String getInventoryName()
	{
		return "container.powerGenerator";
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public void openInventory()
	{
		
	}
	
	@Override
	public void closeInventory()
	{
		
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		switch (slot)
		{
			case 0:
				return TileEntityFurnace.isItemFuel(itemstack);
			case 1:
				return itemstack.getItem() instanceof IUpgrade;
		}
		return false;
	}
	
	public int getBurnTime()
	{
		return burnTime;
	}
	
	public boolean isBurning()
	{
		return burnTime > 0;
	}
	
	public int getBurnTimeScaled(int scale)
	{
		return burnTime * scale / currentItemBurnTime;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int slot)
	{
		return new int[] {0};
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		return isItemValidForSlot(slot, itemstack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side)
	{
		return false;
	}
	
	@Override
	public BlockType getTypeOfBlock()
	{
		return BlockType.MACHINE;
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		energyBar.writeToNBT(tag);
		tag.setInteger("rotation", rotation);
		NBTUtil.writeItemStackArrayToNBT(inventory, tag);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		energyBar.readFromNBT(tag);
		rotation = tag.getInteger("rotation");
		NBTUtil.readItemStackArrayFromNBT(inventory, tag);
	}
}
