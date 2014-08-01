package larsg310.mods.powercraft.block;

import java.util.List;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.GuiIds;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.tileentity.TileEntityCompressor;
import larsg310.mods.powercraft.tileentity.TileEntityEnergizedFurnace;
import larsg310.mods.powercraft.tileentity.TileEntityEnergizedGrinder;
import larsg310.mods.powercraft.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachines extends Block
{
    private IIcon[] icons = new IIcon[5];
    
    public BlockMachines()
    {
        super(Material.iron);
        this.setCreativeTab(PowerCraft.CREATIVE_TAB);
        this.setBlockName(Names.MACHINES);
    }
    
    public TileEntity createTileEntity(World world, int meta)
    {
        switch (meta)
        {
            case 0:
                return new TileEntityEnergizedFurnace();
            case 1:
                return new TileEntityEnergizedGrinder();
            case 2:
                return new TileEntityCompressor();
        }
        return null;
    }
    
    public boolean hasTileEntity(int meta)
    {
        return meta == 0 || meta == 1 || meta == 2;
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            int meta = world.getBlockMetadata(x, y, z);
            if (meta == 0)
            {
                player.openGui(PowerCraft.instance, GuiIds.ENERGIZED_FURNACE, world, x, y, z);
                return true;
            }
            if (meta == 1)
            {
                player.openGui(PowerCraft.instance, GuiIds.ENERGIZED_GRINDER, world, x, y, z);
                return true;
            }
            if (meta == 2)
            {
                player.openGui(PowerCraft.instance, GuiIds.COMPRESSOR, world, x, y, z);
                return true;
            }
        }
        return true;
    }
    
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
        list.add(new ItemStack(item, 1, 2));
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
    
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons[0] = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.machineBase");
        icons[1] = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.energizedFurnaceFrontOff");
        icons[2] = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.energizedFurnaceFrontOn");
        icons[3] = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.energizedGrinderFrontOff");
        icons[4] = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.energizedGrinderFrontOn");
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 0)
        {
            TileEntityEnergizedFurnace tileentity = (TileEntityEnergizedFurnace) world.getTileEntity(x, y, z);
            tileentity.rotation = BlockUtil.determineMetadataBasedOnPlayerOrientation(player);
        }
        if (meta == 1)
        {
            TileEntityEnergizedGrinder tileentity = (TileEntityEnergizedGrinder) world.getTileEntity(x, y, z);
            tileentity.rotation = BlockUtil.determineMetadataBasedOnPlayerOrientation(player);
        }
        if (meta == 2)
        {
            TileEntityCompressor tileentity = (TileEntityCompressor) world.getTileEntity(x, y, z);
            tileentity.rotation = BlockUtil.determineMetadataBasedOnPlayerOrientation(player);
        }
    }
    
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 0)
        {
            TileEntityEnergizedFurnace tileentity = (TileEntityEnergizedFurnace) world.getTileEntity(x, y, z);
            if (side == tileentity.rotation)
            {
                if (tileentity.isSmelting)
                {
                    return icons[2];
                }
                else
                {
                    return icons[1];
                }
            }
        }
        if (meta == 1)
        {
            TileEntityEnergizedGrinder tileentity = (TileEntityEnergizedGrinder) world.getTileEntity(x, y, z);
            if (side == tileentity.rotation)
            {
                if (tileentity.isGrinding)
                {
                    return icons[4];
                }
                else
                {
                    return icons[3];
                }
            }
        }
        return icons[0];
    }
    
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0 && side == 3)
        {
            return icons[1];
        }
        if (meta == 1 && side == 3)
        {
            return icons[3];
        }
        return icons[0];
    }
}
