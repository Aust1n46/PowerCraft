package larsg310.mods.powercraft.tileentity;

import java.util.ArrayList;

import larsg310.mods.powercraft.block.BlockType;
import larsg310.mods.powercraft.damage.DamageSources;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.energy.EnergyNet;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.item.ModItems;
import larsg310.mods.powercraft.upgrade.IUpgrade;
import larsg310.mods.powercraft.upgrade.Upgrade;
import larsg310.mods.powercraft.util.InventoryUtil;
import larsg310.mods.powercraft.util.NBTUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
    private ItemStack[] inventory = new ItemStack[7];
    public int burnTime;
    public int currentItemBurnTime;
    public int rotation = 3;
    private float modifier = 17.5F;
    private float defaultModifier = 17.5F;
    public boolean hasExtraInventory;
    public int burnTimeRemovedPerTick = 3;
    
    public void updateEntity()
    {
        updateUpgrades();
        updateGenerating();
    }
    
    private void updateUpgrades()
    {
        int inventoryUpgrades = 0;
        modifier = defaultModifier;
        energyBar.resetMaxEnergyLevel();
        for (int index = 1; index < 5; index++)
        {
            if (inventory[index] != null && inventory[index].getItem() instanceof IUpgrade)
            {
                IUpgrade itemUpgrade = (IUpgrade) inventory[index].getItem();
                this.energyBar.setMaxEnergyLevel(energyBar.getMaxEnergyLevel() + itemUpgrade.getUpgradeWorthness(Upgrade.STORAGE, inventory[index].getItemDamage()) * inventory[index].stackSize);
                this.modifier -= itemUpgrade.getUpgradeWorthness(Upgrade.SPEED, inventory[index].getItemDamage()) * inventory[index].stackSize;
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
            }
            energyBar.setEnergyLevel(energyBar.getMaxEnergyLevel());
        }
    }
    
    private void updateGenerating()
    {
        boolean modified = burnTime > 0;
        if (burnTime > 0)
        {
            if (burnTime > burnTimeRemovedPerTick)
            {
                burnTime -= burnTimeRemovedPerTick;
                energyBar.addEnergyWithRemaining(burnTimeRemovedPerTick);
            }
            else
            {
                energyBar.addEnergyWithRemaining(burnTime);
                burnTime = 0;
            }
        }
        else if (burnTime > 0)
        {
            energyBar.addEnergyWithRemaining(burnTime);
            burnTime = 0;
        }
        if (burnTime == 0 && (TileEntityFurnace.isItemFuel(inventory[0]) || TileEntityFurnace.isItemFuel(inventory[5]) || TileEntityFurnace.isItemFuel(inventory[6])))
        {
            currentItemBurnTime = burnTime += (int) (TileEntityFurnace.getItemBurnTime(inventory[0]) * modifier) + (int) (TileEntityFurnace.getItemBurnTime(inventory[5]) * modifier) + (int) (TileEntityFurnace.getItemBurnTime(inventory[6]) * modifier);
            if (inventory[0] != null)
            {
                inventory[0].stackSize--;
                if (inventory[0].stackSize <= 0)
                {
                    inventory[0] = null;
                }
            }
            if (inventory[5] != null)
            {
                inventory[5].stackSize--;
                if (inventory[5].stackSize <= 0)
                {
                    inventory[5] = null;
                }
            }
            if (inventory[6] != null)
            {
                inventory[6].stackSize--;
                if (inventory[6].stackSize <= 0)
                {
                    inventory[6] = null;
                }
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
        tag.setInteger("burnTime", burnTime);
        tag.setInteger("currentItemBurnTime", currentItemBurnTime);
        NBTUtil.writeItemStackArrayToNBT(inventory, tag);
    }
    
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        energyBar.readFromNBT(tag);
        rotation = tag.getInteger("rotation");
        burnTime = tag.getInteger("burnTime");
        currentItemBurnTime = tag.getInteger("currentItemBurnTime");
        NBTUtil.readItemStackArrayFromNBT(inventory, tag);
    }
}
