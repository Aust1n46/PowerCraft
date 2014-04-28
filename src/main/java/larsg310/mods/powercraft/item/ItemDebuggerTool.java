package larsg310.mods.powercraft.item;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.api.IEnergy;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemDebuggerTool extends Item
{
	public ItemDebuggerTool()
	{
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
		this.setUnlocalizedName(Names.DEBUGGER_TOOL);
	}
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof IEnergy)
			{
				player.addChatMessage(new ChatComponentText("[" + Reference.MOD_NAME + " - Debugging] Energy level = " + ((IEnergy) tileentity).getPowerBar().getEnergyLevel()));
				return true;
			}
			player.addChatMessage(new ChatComponentText("No TileEntity that implements IEnergy!"));
		}
		return false;
	}
}
