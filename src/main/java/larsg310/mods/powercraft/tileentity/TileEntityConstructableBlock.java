package larsg310.mods.powercraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityConstructableBlock extends TileEntity
{
	public Block[][][] blocks = new Block[16][16][16];
	
	public void updateEntity()
	{
		blocks[0][0][0] = Blocks.stone;
	}
}
