package larsg310.mods.powercraft.tileentity;

import java.util.ArrayList;

import larsg310.mods.powercraft.block.BlockType;
import larsg310.mods.powercraft.damage.DamageSources;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.item.ModItems;
import larsg310.mods.powercraft.recipe.CompressingRecipes;
import larsg310.mods.powercraft.recipe.GrindingRecipes;
import larsg310.mods.powercraft.upgrade.IUpgrade;
import larsg310.mods.powercraft.upgrade.Upgrade;
import larsg310.mods.powercraft.util.InventoryUtil;
import larsg310.mods.powercraft.util.MachineUtil;
import larsg310.mods.powercraft.util.NBTUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergizedGrinder extends TileEntity implements IEnergy, IInventory
{
	private ItemStack[] inventory = new ItemStack[18];
	private EnergyBar energyBar = new EnergyBar(10000);
	
	private int energyUsePerGrind = 1600;
	private int energyUsePerGrindDefault = 1600;
	public boolean isGrinding;
	private int grindStatus;
	private int grindTime = 160;
	private int defaultGrindTime = 160;
	public int rotation = 3;
	public boolean hasExtraInventory;
	
	public void updateEntity()
	{
		updateUpgrades();
		updateGrinding();
	}
	
	private void updateUpgrades()
	{
		int inventoryUpgrades = 0;
		grindTime = defaultGrindTime;
		energyUsePerGrind = energyUsePerGrindDefault;
		energyBar.resetMaxEnergyLevel();
		for (int index = 2; index < 6; index++)
		{
			if (inventory[index] != null && inventory[index].getItem() instanceof IUpgrade)
			{
				IUpgrade itemUpgrade = (IUpgrade) inventory[index].getItem();
				this.energyBar.setMaxEnergyLevel(energyBar.getMaxEnergyLevel() + itemUpgrade.getUpgradeWorthness(Upgrade.STORAGE, inventory[index].getItemDamage()) * inventory[index].stackSize);
				this.grindTime -= itemUpgrade.getUpgradeWorthness(Upgrade.SPEED, inventory[index].getItemDamage()) * inventory[index].stackSize;
				this.energyUsePerGrind -= itemUpgrade.getUpgradeWorthness(Upgrade.EFFICIENCY, inventory[index].getItemDamage() * inventory[index].stackSize);
				if (inventory[index].isItemEqual(new ItemStack(ModItems.UPGRADES, 1, 3)))
				{
					inventoryUpgrades++;
				}
			}
		}
		hasExtraInventory = inventoryUpgrades > 0;
		if (energyBar.getEnergyLevel() > energyBar.getMaxEnergyLevel())
		{
			if (!worldObj.isRemote)
			{
				ArrayList<Entity> entities = (ArrayList<Entity>) worldObj.getEntitiesWithinAABB(EntityPlayer.class, getRenderBoundingBox().expand(3, 3, 3));
				for (Entity entity : entities)
				{
					entity.attackEntityFrom(DamageSources.ELECTRICITY, (energyBar.getEnergyLevel() - energyBar.getMaxEnergyLevel()) / 100);
				}
				energyBar.setEnergyLevel(energyBar.getMaxEnergyLevel());
			}
		}
	}
	
	private void updateGrinding()
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
		return MachineUtil.canOperate(inventory, energyBar, GrindingRecipes.getGrindingResult(inventory[0]), this, energyUsePerGrind, 1);
	}
	
	private void grind()
	{
		MachineUtil.operate(inventory, energyBar, GrindingRecipes.getGrindingResult(inventory[0]), this, grindStatus, isGrinding, energyUsePerGrind, 1);
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
		tag.setInteger("grindStatus", grindStatus);
		NBTUtil.writeItemStackArrayToNBT(inventory, tag);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		energyBar.readFromNBT(tag);
		rotation = tag.getInteger("rotation");
		grindStatus = tag.getInteger("grindStatus");
		NBTUtil.readItemStackArrayFromNBT(inventory, tag);
	}
}
