package larsg310.mods.powercraft.render;

import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.model.ModelComputerDrive;
import larsg310.mods.powercraft.model.ModelComputerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemComputerRenderer implements IItemRenderer
{
	public static final ItemComputerRenderer instance = new ItemComputerRenderer();
	ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/model/computer.png");
	ResourceLocation TEXTURE_DRIVE = new ResourceLocation(Reference.MOD_ID, "textures/model/drive.png");
	
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
		GL11.glRotatef(type == ItemRenderType.INVENTORY ? 90 : -90, 0, 1, 0);
		if (meta == 0)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			ModelComputerScreen.instance.render(null, 0, 0, 0, 0, 0, 0.0625F);
		}
		if (meta == 1)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE_DRIVE);
			ModelComputerDrive.instance.render(null, 0, 0, 0, 0, 0, 0.0625F);
		}
		GL11.glPopMatrix();
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack itemstack, ItemRendererHelper helper)
	{
		return true;
	}
}
