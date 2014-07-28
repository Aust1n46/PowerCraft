package larsg310.mods.powercraft.block;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.GuiIds;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.tileentity.TileEntityEnergizedFurnace;
import larsg310.mods.powercraft.tileentity.TileEntityEnergizedGrinder;
import larsg310.mods.powercraft.tileentity.TileEntityEnergyGenerator;
import larsg310.mods.powercraft.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEnergyGenerator extends Block
{
    private IIcon[] icons = new IIcon[3];
    
    public BlockEnergyGenerator()
    {
        super(Material.iron);
        this.setCreativeTab(PowerCraft.CREATIVE_TAB);
        this.setBlockName(Names.ENERGY_GENERATOR);
    }
    
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityEnergyGenerator();
    }
    
    public boolean hasTileEntity(int meta)
    {
        return true;
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            player.openGui(PowerCraft.instance, GuiIds.POWER_GENERATOR, world, x, y, z);
        }
        return true;
    }
    
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons[0] = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.machineBase");
        icons[1] = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.energyGeneratorFrontOff");
        icons[2] = iconRegister.registerIcon(Reference.MOD_ID + ":" + "tile.energyGeneratorFrontOn");
    }
    
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0 && side == 3)
        {
            return icons[1];
        }
        return icons[0];
    }
    
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 0)
        {
            TileEntityEnergyGenerator tileentity = (TileEntityEnergyGenerator) world.getTileEntity(x, y, z);
            if (side == tileentity.rotation)
            {
                if (tileentity.burnTime > 0)
                {
                    return icons[2];
                }
                else
                {
                    return icons[1];
                }
            }
        }
        return icons[0];
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 0)
        {
            TileEntityEnergyGenerator tileentity = (TileEntityEnergyGenerator) world.getTileEntity(x, y, z);
            tileentity.rotation = BlockUtil.determineMetadataBasedOnPlayerOrientation(player);
        }
    }
}
