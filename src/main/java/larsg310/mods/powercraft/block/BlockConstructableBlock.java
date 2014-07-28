package larsg310.mods.powercraft.block;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.RenderIds;
import larsg310.mods.powercraft.tileentity.TileEntityConstructableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockConstructableBlock extends Block
{
    public BlockConstructableBlock()
    {
        super(Material.iron);
        this.setBlockName(Names.CONSTRUCTABLE_BLOCK);
        this.setCreativeTab(PowerCraft.CREATIVE_TAB);
    }
    
    public boolean hasTileEntity(int meta)
    {
        return true;
    }
    
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityConstructableBlock();
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public int getRenderType()
    {
        return RenderIds.CONSTRUCTABLE_BLOCK;
    }
}
