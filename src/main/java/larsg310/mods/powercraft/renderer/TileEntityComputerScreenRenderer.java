package larsg310.mods.powercraft.renderer;

import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.model.ModelComputerScreen;
import larsg310.mods.powercraft.tileentity.TileEntityComputerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileEntityComputerScreenRenderer extends TileEntitySpecialRenderer
{
	public static final TileEntityComputerScreenRenderer instance = new TileEntityComputerScreenRenderer();
	ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/model/computer.png");
	
	public void renderTileEntityAt(TileEntity tileentity, double xOffset, double yOffset, double zOffset, float partialTickTime)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) (xOffset + 0.5F), (float) (yOffset + 1.5F), (float) (zOffset + 0.5F));
		GL11.glRotatef(180, 0, 0, 1);
		rotateModel(tileentity);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		ModelComputerScreen.instance.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GL11.glPopMatrix();
	}
	
	private void rotateModel(TileEntity tile)
	{
		TileEntityComputerScreen tileentity = (TileEntityComputerScreen) tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord);
		ForgeDirection rotation = ForgeDirection.getOrientation(tileentity.rotation);
		if (rotation == ForgeDirection.SOUTH) GL11.glRotatef(180, 0, 1, 0);
		if (rotation == ForgeDirection.NORTH) GL11.glRotatef(0, 0, 1, 0);
		if (rotation == ForgeDirection.EAST) GL11.glRotatef(90, 0, 1, 0);
		if (rotation == ForgeDirection.WEST) GL11.glRotatef(-90, 0, 1, 0);
	}
}
