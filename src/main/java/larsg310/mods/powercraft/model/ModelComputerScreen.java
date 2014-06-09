package larsg310.mods.powercraft.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelComputerScreen extends ModelBase
{
	public static final ModelComputerScreen instance = new ModelComputerScreen();
	
	ModelRenderer Stand1;
	ModelRenderer Stand2;
	ModelRenderer Stand3;
	ModelRenderer Screen;
	
	public ModelComputerScreen()
	{
		textureWidth = 48;
		textureHeight = 24;
		
		Stand1 = new ModelRenderer(this, 0, 0);
		Stand1.addBox(0F, 0F, 0F, 12, 1, 4);
		Stand1.setRotationPoint(-6F, 23F, -2F);
		Stand1.setTextureSize(48, 24);
		Stand1.mirror = true;
		setRotation(Stand1, 0F, 0F, 0F);
		Stand2 = new ModelRenderer(this, 0, 0);
		Stand2.addBox(0F, 0F, 0F, 8, 1, 4);
		Stand2.setRotationPoint(-4F, 22F, -2F);
		Stand2.setTextureSize(48, 24);
		Stand2.mirror = true;
		setRotation(Stand2, 0F, 0F, 0F);
		Stand3 = new ModelRenderer(this, 0, 0);
		Stand3.addBox(0F, 0F, 0F, 4, 2, 2);
		Stand3.setRotationPoint(-2F, 20F, -1F);
		Stand3.setTextureSize(48, 24);
		Stand3.mirror = true;
		setRotation(Stand3, 0F, 0F, 0F);
		Screen = new ModelRenderer(this, 0, 5);
		Screen.addBox(0F, 0F, 0F, 16, 12, 2);
		Screen.setRotationPoint(-8F, 8F, -1F);
		Screen.setTextureSize(48, 24);
		Screen.mirror = true;
		setRotation(Screen, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		Stand1.render(f5);
		Stand2.render(f5);
		Stand3.render(f5);
		Screen.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
}
