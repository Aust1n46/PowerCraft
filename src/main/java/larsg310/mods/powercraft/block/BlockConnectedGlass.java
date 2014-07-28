package larsg310.mods.powercraft.block;

import java.util.List;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.textures.ConnectedTextures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockConnectedGlass extends Block
{
    public IIcon[][] textures = new IIcon[2][47];
    public String[] names = new String[] {"connectedGlass", "woodGlass"};
    
    public BlockConnectedGlass()
    {
        super(Material.glass);
        this.setCreativeTab(PowerCraft.CREATIVE_TAB);
        this.setStepSound(soundTypeGlass);
        this.setBlockName(Names.CONNECTED_GLASS);
    }
    
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < textures.length; i++)
        {
            for (int j = 0; j < textures[i].length; j++)
            {
                textures[i][j] = iconRegister.registerIcon(Reference.MOD_ID + ":" + names[i] + "/" + names[i] + "_" + (j + 1));
            }
        }
    }
    
    public IIcon getIcon(int side, int meta)
    {
        return textures[meta][0];
    }
    
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        return ConnectedTextures.getConnectedIconWithMeta(world, x, y, z, side, textures, this);
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        int meta = 0;
        
        if (side == 0)
        {
            meta = world.getBlockMetadata(x, y + 1, z);
        }
        if (side == 1)
        {
            meta = world.getBlockMetadata(x, y - 1, z);
        }
        if (side == 2)
        {
            meta = world.getBlockMetadata(x, y, z + 1);
        }
        if (side == 3)
        {
            meta = world.getBlockMetadata(x, y, z - 1);
        }
        if (side == 4)
        {
            meta = world.getBlockMetadata(x + 1, y, z);
        }
        if (side == 5)
        {
            meta = world.getBlockMetadata(x - 1, y, z);
        }
        return !(world.getBlock(x, y, z) == this && world.getBlockMetadata(x, y, z) == meta);
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public void getSubBlocks(Item item, CreativeTabs tab, List itemList)
    {
        itemList.add(new ItemStack(this, 1, 0));
        itemList.add(new ItemStack(this, 1, 1));
    }
}
