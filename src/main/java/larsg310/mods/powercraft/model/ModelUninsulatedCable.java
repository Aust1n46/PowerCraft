package larsg310.mods.powercraft.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelUninsulatedCable extends ModelBase
{
    public static ModelUninsulatedCable instance = new ModelUninsulatedCable();
    
    ModelRenderer Middle;
    ModelRenderer West;
    ModelRenderer East;
    ModelRenderer North;
    ModelRenderer South;
    ModelRenderer Down;
    ModelRenderer Up;
    
    public ModelUninsulatedCable()
    {
        textureWidth = 32;
        textureHeight = 32;
        
        Middle = new ModelRenderer(this, 0, 0);
        Middle.addBox(0F, 0F, 0F, 2, 2, 2);
        Middle.setRotationPoint(-1F, 15F, -1F);
        Middle.setTextureSize(32, 32);
        Middle.mirror = true;
        setRotation(Middle, 0F, 0F, 0F);
        West = new ModelRenderer(this, 0, 0);
        West.addBox(0F, 0F, 0F, 7, 2, 2);
        West.setRotationPoint(1F, 15F, -1F);
        West.setTextureSize(32, 32);
        West.mirror = true;
        setRotation(West, 0F, 0F, 0F);
        East = new ModelRenderer(this, 0, 0);
        East.addBox(0F, 0F, 0F, 7, 2, 2);
        East.setRotationPoint(-8F, 15F, -1F);
        East.setTextureSize(32, 32);
        East.mirror = true;
        setRotation(East, 0F, 0F, 0F);
        North = new ModelRenderer(this, 0, 0);
        North.addBox(0F, 0F, 0F, 2, 2, 7);
        North.setRotationPoint(-1F, 15F, -8F);
        North.setTextureSize(32, 32);
        North.mirror = true;
        setRotation(North, 0F, 0F, 0F);
        South = new ModelRenderer(this, 0, 0);
        South.addBox(0F, 0F, 0F, 2, 2, 7);
        South.setRotationPoint(-1F, 15F, 1F);
        South.setTextureSize(32, 32);
        South.mirror = true;
        setRotation(South, 0F, 0F, 0F);
        Down = new ModelRenderer(this, 0, 0);
        Down.addBox(0F, 0F, 0F, 2, 7, 2);
        Down.setRotationPoint(-1F, 17F, -1F);
        Down.setTextureSize(32, 32);
        Down.mirror = true;
        setRotation(Down, 0F, 0F, 0F);
        Up = new ModelRenderer(this, 0, 0);
        Up.addBox(0F, 0F, 0F, 2, 7, 2);
        Up.setRotationPoint(-1F, 8F, -1F);
        Up.setTextureSize(32, 32);
        Up.mirror = true;
        setRotation(Up, 0F, 0F, 0F);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void renderMiddle()
    {
        Middle.render(0.0625F);
    }
    
    public void renderNorth()
    {
        North.render(0.0625F);
    }
    
    public void renderSouth()
    {
        South.render(0.0625F);
    }
    
    public void renderEast()
    {
        East.render(0.0625F);
    }
    
    public void renderWest()
    {
        West.render(0.0625F);
    }
    
    public void renderUp()
    {
        Up.render(0.0625F);
    }
    
    public void renderDown()
    {
        Down.render(0.0625F);
    }
}
