package larsg310.mods.powercraft.renderer;

import larsg310.mods.powercraft.block.BlockType;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.model.ModelCable;
import larsg310.mods.powercraft.model.ModelUninsulatedCable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileEntityCableRenderer extends TileEntitySpecialRenderer
{
	public static final TileEntityCableRenderer instance = new TileEntityCableRenderer();
	ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/model/cable.png");
	ResourceLocation TEXTURE_UNINSULATED = new ResourceLocation(Reference.MOD_ID, "textures/model/uninsulatedCable.png");
	
	public void renderTileEntityAt(TileEntity tileentity, double xOffset, double yOffset, double zOffset, float partialTickTime)
	{
		int meta = tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
		boolean[][] sides = new boolean[2][6];
		boolean[] cables = new boolean[6];
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			sides[meta][direction.ordinal()] = tileentity.getWorldObj().getTileEntity(tileentity.xCoord + direction.offsetX, tileentity.yCoord + direction.offsetY, tileentity.zCoord + direction.offsetZ) instanceof IEnergy && ((IEnergy) tileentity.getWorldObj().getTileEntity(tileentity.xCoord + direction.offsetX, tileentity.yCoord + direction.offsetY, tileentity.zCoord + direction.offsetZ)).canConnect(direction);
			cables[direction.ordinal()] = tileentity.getWorldObj().getTileEntity(tileentity.xCoord + direction.offsetX, tileentity.yCoord + direction.offsetY, tileentity.zCoord + direction.offsetZ) instanceof IEnergy && !(((IEnergy) tileentity.getWorldObj().getTileEntity(tileentity.xCoord + direction.offsetX, tileentity.yCoord + direction.offsetY, tileentity.zCoord + direction.offsetZ)).getTypeOfBlock() == BlockType.CABLE);
		}
		GL11.glPushMatrix();
		GL11.glTranslatef((float) (xOffset + 0.5F), (float) (yOffset + 1.5F), (float) (zOffset + 0.5F));
		GL11.glRotatef(180, 0, 0, 1);
		if (meta == 0)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			ModelCable.instance.renderMiddle();
			if (sides[0][0]) ModelCable.instance.renderDown(cables[0]);
			if (sides[0][1]) ModelCable.instance.renderUp(cables[1]);
			if (sides[0][2]) ModelCable.instance.renderNorth(cables[2]);
			if (sides[0][3]) ModelCable.instance.renderSouth(cables[3]);
			if (sides[0][4]) ModelCable.instance.renderWest(cables[4]);
			if (sides[0][5]) ModelCable.instance.renderEast(cables[5]);
		}
		if (meta == 1)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE_UNINSULATED);
			ModelUninsulatedCable.instance.renderMiddle();
			if (sides[1][0]) ModelUninsulatedCable.instance.renderDown();
			if (sides[1][1]) ModelUninsulatedCable.instance.renderUp();
			if (sides[1][2]) ModelUninsulatedCable.instance.renderNorth();
			if (sides[1][3]) ModelUninsulatedCable.instance.renderSouth();
			if (sides[1][4]) ModelUninsulatedCable.instance.renderWest();
			if (sides[1][5]) ModelUninsulatedCable.instance.renderEast();
		}
		GL11.glPopMatrix();
	}
}
