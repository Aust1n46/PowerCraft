package larsg310.mods.powercraft.slot;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;

public class SlotOutput extends Slot
{
	public EntityPlayer thePlayer;
	
	public SlotOutput(EntityPlayer player,IInventory inventory, int id, int x, int y)
	{
		super(inventory, id, x, y);
	}
	
	public boolean isItemValid(ItemStack itemstack)
	{
		return false;
	}

    public void onPickupFromSlot(EntityPlayer player, ItemStack itemstack)
    {
        this.onCrafting(itemstack);
        super.onPickupFromSlot(player, itemstack);
    }

    protected void onCrafting(ItemStack itemstack, int count)
    {
        this.onCrafting(itemstack);
    }
}
