package larsg310.mods.powercraft.render;

import org.lwjgl.opengl.GL11;

import larsg310.mods.powercraft.lib.Reference;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
    
    public void preRenderCallback(EntityLivingBase entity, float f)
    {
        GL11.glScalef(0.9375F, 0.9375F, 0.9375F);
    }
}
