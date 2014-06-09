package larsg310.mods.powercraft.tileentity;

import larsg310.mods.powercraft.block.BlockType;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.recipe.GrindingRecipes;
import larsg310.mods.powercraft.util.InventoryUtil;
import larsg310.mods.powercraft.util.NBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergizedGrinder extends TileEntity implements IEnergy, IInventory
{
	private ItemStack[] inventory = new ItemStack[2];
	private EnergyBar energyBar = new EnergyBar(1000);
	
	private int energyUsePerGrind = 16;
	public boolean isGrinding;
	private int grindStatus;
	private int grindTime = 160;
	public int rotation = 3;
	
	public void updateEntity()
	{
		boolean modified = isGrinding;
		this.isGrinding = canGrind();
		if (this.isGrinding)
		{
			this.grindStatus += 1;
		}
		else
		{
			this.grindStatus = 0;
		}
		if (this.grindStatus >= this.grindTime)
		{
			grind();
		}
		if (modified != isGrinding)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	private boolean canGrind()
	{
		if (this.inventory[0] == null)
		{
			return false;
		}
		
		ItemStack itemstack = GrindingRecipes.getGrindingResult(this.inventory[0]);
		if (itemstack == null) return false;
		if (!energyBar.canRemoveEnergy(energyUsePerGrind)) return false;
		if (this.inventory[1] == null) return true;
		if (!this.inventory[1].isItemEqual(itemstack)) return false;
		int result = this.inventory[1].stackSize + itemstack.stackSize;
		return (result <= getInventoryStackLimit()) && (result <= itemstack.getMaxStackSize());
	}
	
	private void grind()
	{
		ItemStack itemstack = GrindingRecipes.getGrindingResult(this.inventory[0]);
		
		if (itemstack != null)
		{
			if (this.inventory[1] == null)
			{
				this.inventory[1] = itemstack.copy();
			}
			else if (this.inventory[1].isItemEqual(itemstack))
			{
				this.inventory[1].stackSize += itemstack.stackSize;
			}
			
			this.inventory[0].stackSize -= 1;
			
			if (this.inventory[0].stackSize <= 0)
			{
				this.inventory[0] = null;
			}
			this.energyBar.removeEnergy(energyUsePerGrind);
		}
		this.grindStatus = 0;
		this.isGrinding = false;
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
		return "container.energizedGrinder";
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
		return true;
	}
	
	@Override
	public boolean canAddEnergyOnSide(ForgeDirection direction)
	{
		return true;
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
	
	public int getGrindProgressScaled(int scale)
	{
		return grindStatus * scale / grindTime;
	}
	
	@Override
	public BlockType getTypeOfBlock()
	{
		return BlockType.MACHINE;
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
