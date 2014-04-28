package larsg310.mods.powercraft.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCable extends ModelBase
{
	public static ModelCable instance = new ModelCable();
	
	ModelRenderer Middle;
	ModelRenderer WestClosing;
	ModelRenderer EastClosing;
	ModelRenderer East;
	ModelRenderer West;
	ModelRenderer North;
	ModelRenderer NorthClosing;
	ModelRenderer South;
	ModelRenderer SouthClosing;
	ModelRenderer Up;
	ModelRenderer UpClosing;
	ModelRenderer Down;
	ModelRenderer DownClosing;
	
	public ModelCable()
	{
		textureWidth = 32;
		textureHeight = 32;
		
		Middle = new ModelRenderer(this, 0, 0);
		Middle.addBox(0F, 0F, 0F, 4, 4, 4);
		Middle.setRotationPoint(-2F, 14F, -2F);
		Middle.setTextureSize(32, 32);
		Middle.mirror = true;
		setRotation(Middle, 0F, 0F, 0F);
		WestClosing = new ModelRenderer(this, 0, 0);
		WestClosing.addBox(0F, 0F, 0F, 1, 6, 6);
		WestClosing.setRotationPoint(7F, 13F, -3F);
		WestClosing.setTextureSize(32, 32);
		WestClosing.mirror = true;
		setRotation(WestClosing, 0F, 0F, 0F);
		EastClosing = new ModelRenderer(this, 0, 0);
		EastClosing.addBox(0F, 0F, 0F, 1, 6, 6);
		EastClosing.setRotationPoint(-8F, 13F, -3F);
		EastClosing.setTextureSize(32, 32);
		EastClosing.mirror = true;
		setRotation(EastClosing, 0F, 0F, 0F);
		East = new ModelRenderer(this, 0, 0);
		East.addBox(0F, 0F, 0F, 6, 4, 4);
		East.setRotationPoint(-8F, 14F, -2F);
		East.setTextureSize(32, 32);
		East.mirror = true;
		setRotation(East, 0F, 0F, 0F);
		West = new ModelRenderer(this, 0, 0);
		West.addBox(0F, 0F, 0F, 6, 4, 4);
		West.setRotationPoint(2F, 14F, -2F);
		West.setTextureSize(32, 32);
		West.mirror = true;
		setRotation(West, 0F, 0F, 0F);
		North = new ModelRenderer(this, 0, 0);
		North.addBox(0F, 0F, 0F, 4, 4, 6);
		North.setRotationPoint(-2F, 14F, -8F);
		North.setTextureSize(32, 32);
		North.mirror = true;
		setRotation(North, 0F, 0F, 0F);
		NorthClosing = new ModelRenderer(this, 0, 0);
		NorthClosing.addBox(0F, 0F, 0F, 6, 6, 1);
		NorthClosing.setRotationPoint(-3F, 13F, -8F);
		NorthClosing.setTextureSize(32, 32);
		NorthClosing.mirror = true;
		setRotation(NorthClosing, 0F, 0F, 0F);
		South = new ModelRenderer(this, 0, 0);
		South.addBox(0F, 0F, 0F, 4, 4, 6);
		South.setRotationPoint(-2F, 14F, 2F);
		South.setTextureSize(32, 32);
		South.mirror = true;
		setRotation(South, 0F, 0F, 0F);
		SouthClosing = new ModelRenderer(this, 0, 0);
		SouthClosing.addBox(0F, 0F, 0F, 6, 6, 1);
		SouthClosing.setRotationPoint(-3F, 13F, 7F);
		SouthClosing.setTextureSize(32, 32);
		SouthClosing.mirror = true;
		setRotation(SouthClosing, 0F, 0F, 0F);
		Up = new ModelRenderer(this, 0, 0);
		Up.addBox(0F, 0F, 0F, 4, 6, 4);
		Up.setRotationPoint(-2F, 8F, -2F);
		Up.setTextureSize(32, 32);
		Up.mirror = true;
		setRotation(Up, 0F, 0F, 0F);
		UpClosing = new ModelRenderer(this, 0, 0);
		UpClosing.addBox(0F, 0F, 0F, 6, 1, 6);
		UpClosing.setRotationPoint(-3F, 8F, -3F);
		UpClosing.setTextureSize(32, 32);
		UpClosing.mirror = true;
		setRotation(UpClosing, 0F, 0F, 0F);
		Down = new ModelRenderer(this, 0, 0);
		Down.addBox(0F, 0F, 0F, 4, 6, 4);
		Down.setRotationPoint(-2F, 18F, -2F);
		Down.setTextureSize(32, 32);
		Down.mirror = true;
		setRotation(Down, 0F, 0F, 0F);
		DownClosing = new ModelRenderer(this, 0, 0);
		DownClosing.addBox(0F, 0F, 0F, 6, 1, 6);
		DownClosing.setRotationPoint(-3F, 23F, -3F);
		DownClosing.setTextureSize(32, 32);
		DownClosing.mirror = true;
		setRotation(DownClosing, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Middle.render(f5);
		WestClosing.render(f5);
		EastClosing.render(f5);
		East.render(f5);
		West.render(f5);
		North.render(f5);
		NorthClosing.render(f5);
		South.render(f5);
		SouthClosing.render(f5);
		Up.render(f5);
		UpClosing.render(f5);
		Down.render(f5);
		DownClosing.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
	public void renderNorth(boolean closing)
	{
		this.North.render(0.0625F);
		if (closing) this.NorthClosing.render(0.0625F);
	}
	
	public void renderSouth(boolean closing)
	{
		this.South.render(0.0625F);
		if (closing) this.SouthClosing.render(0.0625F);
	}
	
	public void renderWest(boolean closing)
	{
		this.West.render(0.0625F);
		if (closing) this.WestClosing.render(0.0625F);
	}
	
	public void renderEast(boolean closing)
	{
		this.East.render(0.0625F);
		if (closing) this.EastClosing.render(0.0625F);
	}
	
	public void renderUp(boolean closing)
	{
		this.Up.render(0.0625F);
		if (closing) this.UpClosing.render(0.0625F);
	}
	
	public void renderDown(boolean closing)
	{
		this.Down.render(0.0625F);
		if (closing) this.DownClosing.render(0.0625F);
	}
	
	public void renderMiddle()
	{
		this.Middle.render(0.0625F);
	}
}
