package larsg310.mods.powercraft.proxy;

import larsg310.mods.powercraft.block.ModBlocks;
import larsg310.mods.powercraft.entity.EntityRobot;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.lib.RenderIds;
import larsg310.mods.powercraft.render.EntityRobotRenderer;
import larsg310.mods.powercraft.render.ItemCableRenderer;
import larsg310.mods.powercraft.render.ItemComputerRenderer;
import larsg310.mods.powercraft.render.TileEntityCableRenderer;
import larsg310.mods.powercraft.render.TileEntityComputerDriveRenderer;
import larsg310.mods.powercraft.render.TileEntityComputerScreenRenderer;
import larsg310.mods.powercraft.render.TileEntityContructableBlockRenderer;
import larsg310.mods.powercraft.tileentity.TileEntityCable;
import larsg310.mods.powercraft.tileentity.TileEntityComputerDrive;
import larsg310.mods.powercraft.tileentity.TileEntityComputerScreen;
import larsg310.mods.powercraft.tileentity.TileEntityUninsulatedCable;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        ClientRegistry.registerTileEntity(TileEntityCable.class, Reference.MOD_ID + ":" + Names.CABLE, TileEntityCableRenderer.instance);
        ClientRegistry.registerTileEntity(TileEntityUninsulatedCable.class, Reference.MOD_ID + ":" + Names.CABLE + "Uninsulated", TileEntityCableRenderer.instance);
        ClientRegistry.registerTileEntity(TileEntityComputerScreen.class, Reference.MOD_ID + ":" + Names.COMPUTER + "Screen", TileEntityComputerScreenRenderer.instance);
        ClientRegistry.registerTileEntity(TileEntityComputerDrive.class, Reference.MOD_ID + ":" + Names.COMPUTER + "Drive", TileEntityComputerDriveRenderer.instance);
        
        RenderingRegistry.registerBlockHandler(RenderIds.CONSTRUCTABLE_BLOCK, TileEntityContructableBlockRenderer.instance);
        
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CABLE), ItemCableRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.COMPUTER), ItemComputerRenderer.instance);
        
        RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new EntityRobotRenderer());
    }
}
