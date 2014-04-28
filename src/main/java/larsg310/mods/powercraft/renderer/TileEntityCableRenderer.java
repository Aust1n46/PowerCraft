package larsg310.mods.powercraft.renderer;

import larsg310.mods.powercraft.api.IEnergy;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.model.ModelCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileEntityCableRenderer extends TileEntitySpecialRenderer
{
	public static final TileEntityCableRenderer instance = new TileEntityCableRenderer();
	ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/model/cable.png");
	
	public void renderTileEntityAt(TileEntity tileentity, double xOffset, double yOffset, double zOffset, float partialTickTime)
	{
		boolean[] sides = new boolean[6];
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			sides[direction.ordinal()] = tileentity.getWorldObj().getTileEntity(tileentity.xCoord + direction.offsetX, tileentity.yCoord + direction.offsetY, tileentity.zCoord + direction.offsetZ) instanceof IEnergy && ((IEnergy) tileentity.getWorldObj().getTileEntity(tileentity.xCoord + direction.offsetX, tileentity.yCoord + direction.offsetY, tileentity.zCoord + direction.offsetZ)).canConnect(direction);
		}
		GL11.glPushMatrix();
		GL11.glTranslatef((float) (xOffset + 0.5F), (float) (yOffset + 1.5F), (float) (zOffset + 0.5F));
		GL11.glRotatef(180, 0, 0, 1);
		this.bindTexture(TEXTURE);
		ModelCable.instance.renderMiddle();
		if (sides[0]) ModelCable.instance.renderDown(true);
		if (sides[1]) ModelCable.instance.renderUp(true);
		if (sides[2]) ModelCable.instance.renderNorth(true);
		if (sides[3]) ModelCable.instance.renderSouth(true);
		if (sides[4]) ModelCable.instance.renderWest(true);
		if (sides[5]) ModelCable.instance.renderEast(true);
		GL11.glPopMatrix();
	}
}
