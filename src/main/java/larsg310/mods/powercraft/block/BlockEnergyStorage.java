package larsg310.mods.powercraft.block;

import java.util.List;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.tileentity.TileEntityCreativeEnergyStorage;
import larsg310.mods.powercraft.tileentity.TileEntityEnergyStorageTier1;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockEnergyStorage extends Block
{
    public IIcon output;
    public IIcon base;
    
    public BlockEnergyStorage()
    {
        super(Material.iron);
        this.setCreativeTab(PowerCraft.CREATIVE_TAB);
        this.setBlockName(Names.ENERGY_STORAGE);
    }
    
    public TileEntity createTileEntity(World world, int meta)
    {
        switch (meta)
        {
            case 0:
                return new TileEntityCreativeEnergyStorage();
            case 1:
                return new TileEntityEnergyStorageTier1();
        }
        return null;
    }
    
    public boolean hasTileEntity(int meta)
    {
        return meta <= 1;
    }
    
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }
    
    public int damageDropped(int meta)
    {
        return meta;
    }
    
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return new ItemStack(this, 1, meta);
    }
    
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        int meta = world.getBlockMetadata(x, y, z);
        switch (meta)
        {
            case 0:
                return output;
            case 1:
                TileEntityEnergyStorageTier1 tileentity = (TileEntityEnergyStorageTier1) world.getTileEntity(x, y, z);
                if (side == tileentity.outputSide.ordinal()) return output;
        }
        return base;
    }
    
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        output = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.machineOutput");
        base = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.machineBase");
    }
    
    public IIcon getIcon(int side, int meta)
    {
        switch (meta)
        {
            case 0:
                return output;
            case 1:
                if (side == 3) return output;
        }
        return base;
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 1)
        {
            TileEntityEnergyStorageTier1 tileentity = (TileEntityEnergyStorageTier1) world.getTileEntity(x, y, z);
            int rotation = BlockPistonBase.determineOrientation(world, x, y, z, player);
            tileentity.outputSide = ForgeDirection.VALID_DIRECTIONS[rotation];
        }
    }
}
