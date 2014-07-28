package larsg310.mods.powercraft.handler;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.container.ContainerCompressor;
import larsg310.mods.powercraft.container.ContainerComputerScreen;
import larsg310.mods.powercraft.container.ContainerEnergizedFurnace;
import larsg310.mods.powercraft.container.ContainerEnergizedGrinder;
import larsg310.mods.powercraft.container.ContainerEnergyGenerator;
import larsg310.mods.powercraft.gui.GuiCompressor;
import larsg310.mods.powercraft.gui.GuiComputerScreen;
import larsg310.mods.powercraft.gui.GuiEnergizedFurnace;
import larsg310.mods.powercraft.gui.GuiEnergizedGrinder;
import larsg310.mods.powercraft.gui.GuiEnergyGenerator;
import larsg310.mods.powercraft.lib.GuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GuiIds.POWER_GENERATOR) return new ContainerEnergyGenerator(player, world, x, y, z);
        if (ID == GuiIds.ENERGIZED_FURNACE) return new ContainerEnergizedFurnace(player, world, x, y, z);
        if (ID == GuiIds.ENERGIZED_GRINDER) return new ContainerEnergizedGrinder(player, world, x, y, z);
        if (ID == GuiIds.COMPRESSOR) return new ContainerCompressor(player, world, x, y, z);
        if (ID == GuiIds.COMPUTER_SCREEN) return new ContainerComputerScreen(player, world, x, y, z);
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GuiIds.POWER_GENERATOR) return new GuiEnergyGenerator(player, world, x, y, z);
        if (ID == GuiIds.ENERGIZED_FURNACE) return new GuiEnergizedFurnace(player, world, x, y, z);
        if (ID == GuiIds.ENERGIZED_GRINDER) return new GuiEnergizedGrinder(player, world, x, y, z);
        if (ID == GuiIds.COMPRESSOR) return new GuiCompressor(player, world, x, y, z);
        if (ID == GuiIds.COMPUTER_SCREEN) return new GuiComputerScreen(player, world, x, y, z);
        return null;
    }
    
    public static void register()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(PowerCraft.instance, new GuiHandler());
    }
}
