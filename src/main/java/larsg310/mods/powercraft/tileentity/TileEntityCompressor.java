package larsg310.mods.powercraft.tileentity;

import larsg310.mods.powercraft.block.BlockType;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.recipe.CompressingRecipes;
import larsg310.mods.powercraft.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCompressor extends TileEntity implements IEnergy, IInventory
{
	private EnergyBar energyBar = new EnergyBar(1000);
	private ItemStack[] inventory = new ItemStack[2];
	private int energyUsePerCompress = 16;
	public boolean isCompressing;
	private int compressStatus;
	private int compressTime = 160;
	public int rotation = 3;
	
	public void updateEntity()
	{
		boolean modified = isCompressing;
		this.isCompressing = canCompress();
		if (this.isCompressing)
		{
			this.compressStatus += 1;
		}
		else
		{
			this.compressStatus = 0;
		}
		if (this.compressStatus >= this.compressTime)
		{
			compress();
		}
		if (modified != isCompressing)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	private boolean canCompress()
	{
		if (this.inventory[0] == null)
		{
			return false;
		}
		
		ItemStack itemstack = CompressingRecipes.getCompressResult(this.inventory[0]);
		if (itemstack == null) return false;
		if (!energyBar.canRemoveEnergy(energyUsePerCompress)) return false;
		if (this.inventory[1] == null) return true;
		if (!this.inventory[1].isItemEqual(itemstack)) return false;
		int result = this.inventory[1].stackSize + itemstack.stackSize;
		return (result <= getInventoryStackLimit()) && (result <= itemstack.getMaxStackSize());
	}
	
	private void compress()
	{
		ItemStack itemstack = CompressingRecipes.getCompressResult(this.inventory[0]);
		
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
			
			this.inventory[0].stackSize -= CompressingRecipes.getStackSizeToDecrease(itemstack);
			
			if (this.inventory[0].stackSize <= 0)
			{
				this.inventory[0] = null;
			}
			this.energyBar.removeEnergy(energyUsePerCompress);
		}
		this.compressStatus = 0;
		this.isCompressing = false;
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
	public BlockType getTypeOfBlock()
	{
		return BlockType.MACHINE;
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
		return null;
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
	
	public int getCompressProgressScaled(int scale)
	{
		return this.compressStatus * scale / this.compressTime;
	}
}
