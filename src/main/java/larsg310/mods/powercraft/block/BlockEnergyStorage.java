package larsg310.mods.powercraft.block;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tileentity.TileEntityCable;
import larsg310.mods.powercraft.tileentity.TileEntityEnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyStorage extends Block
{
	public BlockEnergyStorage()
	{
		super(Material.iron);
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
		this.setBlockName(Names.ENERGY_STORAGE);
	}
	
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntityEnergyStorage();
	}
	
	public boolean hasTileEntity(int meta)
	{
		return true;
	}
}
