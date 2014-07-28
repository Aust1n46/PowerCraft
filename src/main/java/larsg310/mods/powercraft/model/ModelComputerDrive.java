package larsg310.mods.powercraft.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelComputerDrive extends ModelBase
{
    public static final ModelComputerDrive instance = new ModelComputerDrive();
    ModelRenderer Drive;
    
    public ModelComputerDrive()
    {
        textureWidth = 64;
        textureHeight = 32;
        
        Drive = new ModelRenderer(this, 0, 0);
        Drive.addBox(0F, 0F, 0F, 8, 16, 16);
        Drive.setRotationPoint(-4F, 8F, -8F);
        Drive.setTextureSize(64, 32);
        Drive.mirror = true;
        setRotation(Drive, 0F, 0F, 0F);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        Drive.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
}
