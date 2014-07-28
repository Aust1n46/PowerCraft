package larsg310.mods.powercraft.block;

import java.util.List;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.energy.IEnergy;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tileentity.TileEntityCable;
import larsg310.mods.powercraft.tileentity.TileEntityUninsulatedCable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
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
        int meta = world.getBlockMetadata(x, y, z);
        boolean[] blockBounds = new boolean[6];
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            blockBounds[direction.ordinal()] = world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) instanceof IEnergy && ((IEnergy) world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ)).canConnect(direction);
        }
        if (meta == 0) this.setBlockBounds(blockBounds[4] ? 0 : 0.375F, blockBounds[0] ? 0 : 0.375F, blockBounds[2] ? 0 : 0.375F, blockBounds[5] ? 1 : 0.625F, blockBounds[1] ? 1 : 0.625F, blockBounds[3] ? 1 : 0.625F);
        if (meta == 1) this.setBlockBounds(blockBounds[4] ? 0 : 0.375F + 0.0625F, blockBounds[0] ? 0 : 0.375F + 0.0625F, blockBounds[2] ? 0 : 0.375F + 0.0625F, blockBounds[5] ? 1 : 0.625F - 0.0625F, blockBounds[1] ? 1 : 0.625F - 0.0625F, blockBounds[3] ? 1 : 0.625F - 0.0625F);
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        boolean[] blockBounds = new boolean[6];
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            blockBounds[direction.ordinal()] = world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) instanceof IEnergy && ((IEnergy) world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ)).canConnect(direction);
        }
        if (meta == 0) return AxisAlignedBB.getBoundingBox(x + (blockBounds[4] ? 0 : 0.375F), y + (blockBounds[0] ? 0 : 0.375F), z + (blockBounds[2] ? 0 : 0.375F), x + (blockBounds[5] ? 1 : 0.625F), y + (blockBounds[1] ? 1 : 0.625F), z + (blockBounds[3] ? 1 : 0.625F));
        if (meta == 1) return AxisAlignedBB.getBoundingBox(x + (blockBounds[4] ? 0 : 0.375F + 0.0625F), y + (blockBounds[0] ? 0 : 0.375F + 0.0625F), z + (blockBounds[2] ? 0 : 0.375F + 0.0625F), x + (blockBounds[5] ? 1 : 0.625F - 0.0625F), y + (blockBounds[1] ? 1 : 0.625F - 0.0625F), z + (blockBounds[3] ? 1 : 0.625F - 0.0625F));
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public TileEntity createTileEntity(World world, int meta)
    {
        switch (meta)
        {
            case 0:
                return new TileEntityCable();
            case 1:
                return new TileEntityUninsulatedCable();
        }
        return null;
    }
    
    public boolean hasTileEntity(int meta)
    {
        return meta <= 1;
    }
    
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return false;
    }
    
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(this, 1, 0));
        list.add(new ItemStack(this, 1, 1));
    }
}
