package larsg310.mods.powercraft.block;

import java.util.List;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockMetalBlocks extends Block
{
	public IIcon[] icons = new IIcon[2];
	
	public BlockMetalBlocks()
	{
		super(Material.iron);
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
		this.setBlockName(Names.METAL_BLOCKS);
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
	
	public IIcon getIcon(int side, int meta)
	{
		return icons[meta];
	}
}
