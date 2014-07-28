package larsg310.mods.powercraft.container;

import larsg310.mods.powercraft.tileentity.TileEntityComputerScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerComputerScreen extends Container
{
    public final EntityPlayer player;
    public final World world;
    public final int x;
    public final int y;
    public final int z;
    public TileEntityComputerScreen tileentity;
    
    public ContainerComputerScreen(EntityPlayer player, World world, int x, int y, int z)
    {
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.tileentity = (TileEntityComputerScreen) world.getTileEntity(x, y, z);
        
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 167 + j * 18, 209 + i * 18));
            }
        }
        
        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 167 + i * 18, 267));
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
