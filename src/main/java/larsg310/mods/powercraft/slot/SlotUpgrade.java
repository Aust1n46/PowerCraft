package larsg310.mods.powercraft.slot;

import larsg310.mods.powercraft.upgrade.IUpgrade;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SlotUpgrade extends Slot
{
    public SlotUpgrade(TileEntity tileentity, int id, int x, int y)
    {
        super((IInventory) tileentity, id, x, y);
    }
    
    public boolean isItemValid(ItemStack itemstack)
    {
        return itemstack.getItem() instanceof IUpgrade;
    }
}
