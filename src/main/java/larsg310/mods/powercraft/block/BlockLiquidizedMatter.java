package larsg310.mods.powercraft.block;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.fluid.ModFluids;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockLiquidizedMatter extends BlockFluidClassic
{
    protected IIcon stillIcon;
    protected IIcon flowingIcon;
    
    public BlockLiquidizedMatter()
    {
        super(ModFluids.LIQUIDIZED_MATTER, Material.water);
        // this.setCreativeTab(PowerCraft.CREATIVE_TAB);
        this.setBlockName(Names.LIQUIDIZED_MATTER);
    }
    
    public IIcon getIcon(int side, int meta)
    {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }
    
    public void registerBlockIcons(IIconRegister register)
    {
        stillIcon = register.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName() + "Still");
        flowingIcon = register.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName() + "Flowing");
    }
    
    public boolean canDisplace(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
        return super.canDisplace(world, x, y, z);
    }
    
    public boolean displaceIfPossible(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
        return super.displaceIfPossible(world, x, y, z);
    }
}
