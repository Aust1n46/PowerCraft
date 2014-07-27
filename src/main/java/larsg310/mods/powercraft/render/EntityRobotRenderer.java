package larsg310.mods.powercraft.render;

import larsg310.mods.powercraft.lib.Reference;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class EntityRobotRenderer extends RenderLiving
{
	public ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/entity/robot.png");
	
	public EntityRobotRenderer()
	{
		super(new ModelBiped(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
//	public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
//    {
//		
//    }
}
