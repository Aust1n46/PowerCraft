package larsg310.mods.powercraft.proxy;

import larsg310.mods.powercraft.block.ModBlocks;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.renderer.ItemCableRenderer;
import larsg310.mods.powercraft.renderer.TileEntityCableRenderer;
import larsg310.mods.powercraft.tileentity.TileEntityCable;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		ClientRegistry.registerTileEntity(TileEntityCable.class, Names.CABLE, TileEntityCableRenderer.instance);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CABLE), ItemCableRenderer.instance);
	}
}
