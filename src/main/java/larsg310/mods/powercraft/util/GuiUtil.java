package larsg310.mods.powercraft.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;

public class GuiUtil
{
	/**
	 * Draws a rectangle without using the 'power of 2' rule.
	 * 
	 * @param x
	 *            The x coordinate on the screen
	 * @param y
	 *            The y coordinate on the screen
	 * @param width
	 *            The width of the rectangle
	 * @param height
	 *            The height of the rectangle
	 * @param imageWidth
	 *            The width of the image the rectangle is drawn from
	 * @param imageHeight
	 *            The height of the image the rectangle is drawn from
	 * @param scale
	 *            The scale the image should be drawn
	 * @param pixelsLeft
	 *            The amount of pixels from the left for the rectangle to be
	 *            drawn
	 * @param pixelsTop
	 *            The amount of pixels from the top for the rectangle to be
	 *            drawn
	 */
	public static void drawRectangle(double x, double y, double width, double height, int imageWidth, int imageHeight, float scale, double pixelsLeft, double pixelsTop)
	{
		GL11.glScalef(scale, scale, scale);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, 0, pixelsLeft / imageWidth, height / imageHeight);
		tessellator.addVertexWithUV(x + width, y + height, 0, width / imageWidth, height / imageHeight);
		tessellator.addVertexWithUV(x + width, y + 0, 0, width / imageWidth, pixelsTop / imageHeight);
		tessellator.addVertexWithUV(x + 0, y + 0, 0, pixelsLeft / imageWidth, pixelsTop / imageHeight);
		tessellator.draw();
		GL11.glScalef(1/scale, 1/scale, 1/scale);
	}
}
