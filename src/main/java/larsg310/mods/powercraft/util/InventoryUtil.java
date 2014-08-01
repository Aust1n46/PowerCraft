package larsg310.mods.powercraft.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryUtil
{
    public static void setInventorySlotContents(IInventory inventoryClass, ItemStack[] inventory, int slot, ItemStack itemstack)
    {
        inventory[slot] = itemstack;
        
        if (itemstack != null && itemstack.stackSize > inventoryClass.getInventoryStackLimit())
        {
            itemstack.stackSize = inventoryClass.getInventoryStackLimit();
        }
    }
    
    public static ItemStack getStackInSlotOnClosing(ItemStack[] inventory, int slot)
    {
        if (inventory[slot] != null)
        {
            ItemStack itemstack = inventory[slot];
            inventory[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }
    
    public static ItemStack decrStackSize(ItemStack[] inventory, int slot, int count)
    {
        if (inventory[slot] != null)
        {
            ItemStack itemstack;
            
            if (inventory[slot].stackSize <= count)
            {
                itemstack = inventory[slot];
                inventory[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = inventory[slot].splitStack(count);
                
                if (inventory[slot].stackSize == 0)
                {
                    inventory[slot] = null;
                }
                
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }
    
    public static ItemStack getStackInSlot(ItemStack[] inventory, int slot)
    {
        return inventory[slot];
    }
    
    public static int getSizeInventory(ItemStack[] inventory)
    {
        return inventory.length;
    }
}
