package larsg310.mods.powercraft.render;

import larsg310.mods.powercraft.lib.RenderIds;
import larsg310.mods.powercraft.tileentity.TileEntityConstructableBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class TileEntityContructableBlockRenderer implements ISimpleBlockRenderingHandler
{
	public static final TileEntityContructableBlockRenderer instance = new TileEntityContructableBlockRenderer();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int xCoord, int yCoord, int zCoord, Block block, int modelId, RenderBlocks renderer)
	{
		TileEntityConstructableBlock tileentity = (TileEntityConstructableBlock) world.getTileEntity(xCoord, yCoord, zCoord);
		Tessellator tessellator = Tessellator.instance;
		for (int x = 0; x < 16; x++)
		{
			for (int y = 0; y < 16; y++)
			{
				for (int z = 0; z < 16; z++)
				{
					if (tileentity.blocks[x][y][z] != null)
					{
						for (int side = 0; side < 6; side++)
						{
							ForgeDirection direction = ForgeDirection.getOrientation(side);
							switch (direction)
							{
								case NORTH:
//									tessellator.startDrawingQuads();
									Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/blocks/" + block.getBlockTextureFromSide(side).getIconName() + ".png"));
									tessellator.addVertexWithUV(x * 0.0625F, y * 0.0625F, z * 0.0625F, 0, 0);
									tessellator.addVertexWithUV((x + 1) * 0.0625F, y * 0.0625F, z * 0.0625F, 1, 0);
									tessellator.addVertexWithUV((x + 1) * 0.0625F, (y + 1) * 0.0625F, z * 0.0625F, 1, 1);
									tessellator.addVertexWithUV(x * 0.0625F, (y + 1) * 0.0625F, z * 0.0625F, 0, 1);
//									tessellator.draw();
								default:
									break;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return false;
	}
	
	@Override
	public int getRenderId()
	{
		return RenderIds.CONSTRUCTABLE_BLOCK;
	}
}
