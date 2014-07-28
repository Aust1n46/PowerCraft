package larsg310.mods.powercraft.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import larsg310.mods.powercraft.container.ContainerEnergizedGrinder;
import larsg310.mods.powercraft.container.ContainerEnergyGenerator;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.lib.Energy;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.tileentity.TileEntityEnergyGenerator;
import larsg310.mods.powercraft.util.GuiUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiEnergyGenerator extends GuiContainer
{
    public final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiEnergyGenerator.png");
    
    public final EntityPlayer player;
    public final World world;
    public final int x;
    public final int y;
    public final int z;
    public TileEntityEnergyGenerator tileentity;
    public boolean prevExtraInventory;
    
    public GuiEnergyGenerator(EntityPlayer player, World world, int x, int y, int z)
    {
        super(new ContainerEnergyGenerator(player, world, x, y, z));
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.tileentity = (TileEntityEnergyGenerator) world.getTileEntity(x, y, z);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {
        this.mc.getTextureManager().bindTexture(GUI);
        GuiUtil.drawRectangle(guiLeft, guiTop, xSize, ySize, 256, 256, 0, 0);
        int energyBarSize = 48;
        this.drawTexturedModalRect(guiLeft + 80, guiTop + 7 + energyBarSize - ((IEnergy) tileentity).getEnergyBar().getEnergyLevelScaled(energyBarSize), 176, 0, 16, ((IEnergy) tileentity).getEnergyBar().getEnergyLevelScaled(energyBarSize));
        if (this.tileentity.isBurning())
        {
            this.drawTexturedModalRect(guiLeft + 117, guiTop + 63, 176, 50, 14, 14);
        }
        if (prevExtraInventory != tileentity.hasExtraInventory)
        {
            prevExtraInventory = tileentity.hasExtraInventory;
            ((ContainerEnergyGenerator) this.inventorySlots).updateSlots();
        }
        if (tileentity.hasExtraInventory)
        {
            this.drawTexturedModalRect(guiLeft + 97, guiTop + 60, 151, 7, 18, 18);
            this.drawTexturedModalRect(guiLeft + 61, guiTop + 60, 151, 7, 18, 18);
        }
    }
    
    @Override
    public void drawGuiContainerForegroundLayer(int x, int y)
    {
        float scale = 0.8F;
        GL11.glScalef(scale, scale, scale);
        int seconds = tileentity.getBurnTime() / (20 * tileentity.burnTimeRemovedPerTick);
        int minutes = seconds / 60;
        seconds -= minutes * 60;
        fontRendererObj.drawString(minutes + " minutes and", 8, 8, 4210752);
        fontRendererObj.drawString(seconds + (this.tileentity.isBurning() ? 1 : 0) + " seconds left", 8, 16, 4210752);
        GL11.glScalef(1 / scale, 1 / scale, 1 / scale);
        drawEnergyLevel(x, y);
    }
    
    private void drawEnergyLevel(int x, int y)
    {
        int minX = guiLeft + 80;
        int maxX = guiLeft + 95;
        int minY = guiTop + 7;
        int maxY = guiTop + 54;
        EnergyBar energyBar = ((IEnergy) tileentity).getEnergyBar();
        if (x >= minX && x <= maxX && y >= minY && y <= maxY)
        {
            this.drawHoveringText(Arrays.asList(energyBar.getEnergyLevel() + " / " + energyBar.getMaxEnergyLevel() + " " + Energy.WATT.getName()), x - guiLeft - 6, y - guiTop, fontRendererObj);
        }
    }
    
    private void drawBurnTime(int x, int y)
    {
        int minX = guiLeft + 100;
        int maxX = guiLeft + 114;
        int minY = guiTop + 61;
        int maxY = guiTop + 74;
        if (x >= minX && x <= maxX && y >= minY && y <= maxY)
        {
            this.drawHoveringText(Arrays.asList((tileentity.getBurnTime() / 20) + " Burn Seconds Left"), x - guiLeft + 10, y - guiTop, fontRendererObj);
        }
    }
}
