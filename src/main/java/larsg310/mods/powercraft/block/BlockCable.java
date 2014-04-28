package larsg310.mods.powercraft.block;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.api.IEnergy;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tileentity.TileEntityCable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCable extends Block
{
	public BlockCable()
	{
		super(Material.iron);
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
		this.setBlockName(Names.CABLE);
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		boolean[] blockBounds = new boolean[6];
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			blockBounds[direction.ordinal()] = world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) instanceof IEnergy && ((IEnergy) world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ)).canConnect(direction);
		}
		this.setBlockBounds(blockBounds[4] ? 0 : 0.375F, blockBounds[0] ? 0 : 0.375F, blockBounds[2] ? 0 : 0.375F, blockBounds[5] ? 1 : 0.625F, blockBounds[1] ? 1 : 0.625F, blockBounds[3] ? 1 : 0.625F);
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.375F, 0.375F, 0, 0.625F, 0.625F, 1);
	}
	
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntityCable();
	}
	
	public boolean hasTileEntity(int meta)
	{
		return true;
	}
	
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		return false;
	}
}
