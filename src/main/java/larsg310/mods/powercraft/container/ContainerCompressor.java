package larsg310.mods.powercraft.container;

import larsg310.mods.powercraft.slot.SlotOutput;
import larsg310.mods.powercraft.slot.SlotUpgrade;
import larsg310.mods.powercraft.tileentity.TileEntityCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerCompressor extends Container
{
    public final EntityPlayer player;
    public final World world;
    public final int x;
    public final int y;
    public final int z;
    public TileEntityCompressor tileentity;
    
    public ContainerCompressor(EntityPlayer player, World world, int x, int y, int z)
    {
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.tileentity = (TileEntityCompressor) world.getTileEntity(x, y, z);
        
        this.addSlotToContainer(new Slot(tileentity, 0, 56, 35));
        this.addSlotToContainer(new SlotOutput(player, tileentity, 1, 116, 35));
        this.addSlotToContainer(new SlotUpgrade(tileentity, 2, 152, 8));
        this.addSlotToContainer(new SlotUpgrade(tileentity, 3, 152, 26));
        this.addSlotToContainer(new SlotUpgrade(tileentity, 4, 152, 44));
        this.addSlotToContainer(new SlotUpgrade(tileentity, 5, 152, 62));
        
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        
        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }
    
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
    
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        return null;
    }
}
