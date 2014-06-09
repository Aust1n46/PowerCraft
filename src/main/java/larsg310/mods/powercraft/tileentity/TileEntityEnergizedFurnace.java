package larsg310.mods.powercraft.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import larsg310.mods.powercraft.block.BlockType;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.energy.IEnergy;
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

public class TileEntityEnergizedFurnace extends TileEntity implements IEnergy, IInventory
{
	private EnergyBar energyBar = new EnergyBar(1000);
	private ItemStack[] inventory = new ItemStack[2];
	private int energyUsePerSmelt = 16;
	public boolean isSmelting;
	private int smeltStatus;
	private int smeltTime = 160;
	public int rotation = 3;
	
	public void updateEntity()
	{
		boolean modified = isSmelting;
		this.isSmelting = canSmelt();
		if (this.isSmelting)
		{
			this.smeltStatus += 1;
		}
		else
		{
			this.smeltStatus = 0;
		}
		if (this.smeltStatus >= this.smeltTime)
		{
			smelt();
		}
		if(modified != isSmelting)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	private boolean canSmelt()
	{
		if (this.inventory[0] == null)
		{
			return false;
		}
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
		if (itemstack == null) return false;
		if (!energyBar.canRemoveEnergy(energyUsePerSmelt)) return false;
		if (this.inventory[1] == null) return true;
		if (!this.inventory[1].isItemEqual(itemstack)) return false;
		int result = this.inventory[1].stackSize + itemstack.stackSize;
		return (result <= getInventoryStackLimit()) && (result <= itemstack.getMaxStackSize());
	}
	
	private void smelt()
	{
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
		
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
			this.energyBar.removeEnergy(energyUsePerSmelt);
		}
		this.smeltStatus = 0;
		this.isSmelting = false;
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
		return "container.energizedFurnace";
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
	
	public int getCookProgressScaled(int scale)
	{
		return this.smeltStatus * scale / smeltTime;
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
