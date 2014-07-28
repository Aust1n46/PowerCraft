package larsg310.mods.powercraft.tileentity;

import java.util.ArrayList;

import larsg310.mods.powercraft.block.BlockType;
import larsg310.mods.powercraft.damage.DamageSources;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.recipe.CompressingRecipes;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCompressor extends TileEntity implements IEnergy, IInventory
{
    private EnergyBar energyBar = new EnergyBar(10000);
    private ItemStack[] inventory = new ItemStack[6];
    private int energyUsePerCompress = 1600;
    private int defaultEnergyUsePerCompress = 1600;
    public boolean isCompressing;
    private int compressStatus;
    private int compressTime = 160;
    private int defaultCompressTime = 160;
    public int rotation = 3;
    
    public void updateEntity()
    {
        updateUpgrades();
        updateCompressing();
    }
    
    private void updateUpgrades()
    {
        compressTime = defaultCompressTime;
        energyUsePerCompress = defaultEnergyUsePerCompress;
        energyBar.resetMaxEnergyLevel();
        for (int index = 2; index < 6; index++)
        {
            if (inventory[index] != null && inventory[index].getItem() instanceof IUpgrade)
            {
                IUpgrade itemUpgrade = (IUpgrade) inventory[index].getItem();
                this.energyBar.setMaxEnergyLevel(energyBar.getMaxEnergyLevel() + itemUpgrade.getUpgradeWorthness(Upgrade.STORAGE, inventory[index].getItemDamage()) * inventory[index].stackSize);
                this.compressTime -= itemUpgrade.getUpgradeWorthness(Upgrade.SPEED, inventory[index].getItemDamage()) * inventory[index].stackSize;
                this.energyUsePerCompress -= itemUpgrade.getUpgradeWorthness(Upgrade.EFFICIENCY, inventory[index].getItemDamage() * inventory[index].stackSize);
            }
        }
        if (energyBar.getEnergyLevel() > energyBar.getMaxEnergyLevel())
        {
            if (!worldObj.isRemote)
            {
                ArrayList<Entity> entities = (ArrayList<Entity>) worldObj.getEntitiesWithinAABB(EntityPlayer.class, getRenderBoundingBox().expand(3, 3, 3));
                for (Entity entity : entities)
                {
                    entity.attackEntityFrom(DamageSources.ELECTRICITY, (energyBar.getEnergyLevel() - energyBar.getMaxEnergyLevel()) / 100);
                }
            }
            energyBar.setEnergyLevel(energyBar.getMaxEnergyLevel());
        }
    }
    
    private void updateCompressing()
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
        return MachineUtil.canOperate(inventory, energyBar, CompressingRecipes.getCompressResult(inventory[0]), this, energyUsePerCompress, CompressingRecipes.getStackSizeToDecrease(inventory[0]));
    }
    
    private void compress()
    {
        MachineUtil.operate(inventory, energyBar, CompressingRecipes.getCompressResult(inventory[0]), this, compressStatus, isCompressing, energyUsePerCompress, CompressingRecipes.getStackSizeToDecrease(inventory[0]));
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
    
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        energyBar.writeToNBT(tag);
        tag.setInteger("rotation", rotation);
        tag.setInteger("compressStatus", compressStatus);
        NBTUtil.writeItemStackArrayToNBT(inventory, tag);
    }
    
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        energyBar.readFromNBT(tag);
        compressStatus = tag.getInteger("compressStatus");
        NBTUtil.readItemStackArrayFromNBT(inventory, tag);
    }
}
