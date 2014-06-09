package larsg310.mods.powercraft.renderer;

import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.model.ModelCable;
import larsg310.mods.powercraft.model.ModelUninsulatedCable;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemCableRenderer implements IItemRenderer
{
	public static final ItemCableRenderer instance = new ItemCableRenderer();
	ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/model/cable.png");
	ResourceLocation TEXTURE_UNINSULATED = new ResourceLocation(Reference.MOD_ID, "textures/model/uninsulatedCable.png");
	
	@Override
	public boolean handleRenderType(ItemStack itemstack, ItemRenderType type)
	{
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack itemstack, Object... object)
	{
		int meta = itemstack.getItemDamage();
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 1.5F, 0.5F);
		GL11.glRotatef(180, 0, 0, 1);
		if (meta == 0)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			ModelCable.instance.renderMiddle();
			ModelCable.instance.renderDown(false);
			ModelCable.instance.renderUp(false);
			ModelCable.instance.renderNorth(false);
			ModelCable.instance.renderSouth(false);
			ModelCable.instance.renderWest(false);
			ModelCable.instance.renderEast(false);
		}
		if (meta == 1)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE_UNINSULATED);
			ModelUninsulatedCable.instance.renderMiddle();
			ModelUninsulatedCable.instance.renderDown();
			ModelUninsulatedCable.instance.renderUp();
			ModelUninsulatedCable.instance.renderNorth();
			ModelUninsulatedCable.instance.renderSouth();
			ModelUninsulatedCable.instance.renderWest();
			ModelUninsulatedCable.instance.renderEast();
		}
		GL11.glPopMatrix();
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack itemstack, ItemRendererHelper helper)
	{
		return true;
	}
	
}
