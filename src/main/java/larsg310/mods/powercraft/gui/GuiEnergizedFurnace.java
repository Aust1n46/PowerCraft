package larsg310.mods.powercraft.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import larsg310.mods.powercraft.container.ContainerEnergizedFurnace;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.tileentity.TileEntityEnergizedFurnace;
import larsg310.mods.powercraft.util.GuiUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiEnergizedFurnace extends GuiContainer
{
	private static final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiEnergizedFurnace.png");
	
	public final EntityPlayer player;
	public final World world;
	public final int x;
	public final int y;
	public final int z;
	public TileEntityEnergizedFurnace tileentity;
	
	public GuiEnergizedFurnace(EntityPlayer player, World world, int x, int y, int z)
	{
		super(new ContainerEnergizedFurnace(player, world, x, y, z));
		this.player = player;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.tileentity = (TileEntityEnergizedFurnace) world.getTileEntity(x, y, z);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		this.mc.getTextureManager().bindTexture(GUI);
		GuiUtil.drawRectangle(guiLeft, guiTop, xSize, ySize, 256, 256, 1, 0, 0);
		int energyBarSize = 48;
		this.drawTexturedModalRect(guiLeft + 8, guiTop + 8 + energyBarSize - ((IEnergy) tileentity).getEnergyBar().getEnergyLevelScaled(energyBarSize), 176, 31, 16, ((IEnergy) tileentity).getEnergyBar().getEnergyLevelScaled(energyBarSize));
		int middleX = (this.width - this.xSize) / 2;
		int middleY = (this.height - this.ySize) / 2;
		
		if (this.tileentity.isSmelting)
		{
			this.drawTexturedModalRect(middleX + 56, middleY + 36 + 18, 176, 0, 14, 14);
		}
		
		int scale = this.tileentity.getCookProgressScaled(24);
		this.drawTexturedModalRect(middleX + 79, middleY + 34, 176, 14, scale + 1, 16);
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int x, int y)
	{
		drawEnergyLevel(x, y);
	}
	
	private void drawEnergyLevel(int x, int y)
	{
		int minX = guiLeft + 8;
		int maxX = guiLeft + 8 + 15;
		int minY = guiTop + 8;
		int maxY = guiTop + 8 + 47;
		EnergyBar energyBar = ((IEnergy) tileentity).getEnergyBar();
		if (x >= minX && x <= maxX && y >= minY && y <= maxY)
		{
			this.drawHoveringText(Arrays.asList(energyBar.getEnergyLevel() + " / " + energyBar.getMaxEnergyLevel() + " Joule"), x - guiLeft - 6, y - guiTop, fontRendererObj);
		}
	}
}
