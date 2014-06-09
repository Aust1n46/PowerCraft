package larsg310.mods.powercraft.block;

import java.util.List;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.GuiIds;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tileentity.TileEntityComputerDrive;
import larsg310.mods.powercraft.tileentity.TileEntityComputerScreen;
import larsg310.mods.powercraft.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockComputer extends Block
{
	public BlockComputer()
	{
		super(Material.iron);
		this.setBlockName(Names.COMPUTER);
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
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
		if (meta == 0) return new TileEntityComputerScreen();
		if (meta == 1) return new TileEntityComputerDrive();
		return null;
	}
	
	public boolean hasTileEntity(int meta)
	{
		return true;
	}
	
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		return false;
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0)
		{
			TileEntityComputerScreen tileentity = (TileEntityComputerScreen) world.getTileEntity(x, y, z);
			tileentity.rotation = BlockUtil.determineMetadataBasedOnPlayerOrientation(player);
			if (itemstack.stackTagCompound != null && itemstack.stackTagCompound.hasKey("drivePosX") && world.getTileEntity(itemstack.stackTagCompound.getInteger("drivePosX"), itemstack.stackTagCompound.getInteger("drivePosY"), itemstack.stackTagCompound.getInteger("drivePosZ")) instanceof TileEntityComputerDrive)
			{
				tileentity.drive = (TileEntityComputerDrive) world.getTileEntity(itemstack.stackTagCompound.getInteger("drivePosX"), itemstack.stackTagCompound.getInteger("drivePosY"), itemstack.stackTagCompound.getInteger("drivePosZ"));
				tileentity.drive.screen = tileentity;
			}
		}
		if (meta == 1)
		{
			TileEntityComputerDrive tileentity = (TileEntityComputerDrive) world.getTileEntity(x, y, z);
			tileentity.rotation = BlockUtil.determineMetadataBasedOnPlayerOrientation(player);
		}
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		float pixel = 0.0625F;
		if (meta == 0)
		{
			TileEntityComputerScreen tileentity = (TileEntityComputerScreen) world.getTileEntity(x, y, z);
			if (tileentity.rotation == 4 || tileentity.rotation == 5)
			{
				minZ = 0;
				maxZ = 1;
				minX = pixel * 6;
				maxX = pixel * 10;
			}
			if (tileentity.rotation == 2 || tileentity.rotation == 3)
			{
				minZ = pixel * 6;
				maxZ = pixel * 10;
				minX = 0;
				maxX = 1;
			}
		}
		if (meta == 1)
		{
			TileEntityComputerDrive tileentity = (TileEntityComputerDrive) world.getTileEntity(x, y, z);
			if (tileentity.rotation == 2 || tileentity.rotation == 3)
			{
				minZ = 0;
				maxZ = 1;
				minX = pixel * 4;
				maxX = pixel * 12;
			}
			if (tileentity.rotation == 4 || tileentity.rotation == 5)
			{
				minZ = pixel * 4;
				maxZ = pixel * 12;
				minX = 0;
				maxX = 1;
			}
		}
	}
	
	public void getSubBlocks(Item item, CreativeTabs tab, List itemList)
	{
		itemList.add(new ItemStack(this, 1, 0));
		itemList.add(new ItemStack(this, 1, 1));
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0)
		{
			TileEntityComputerScreen screen = (TileEntityComputerScreen) world.getTileEntity(x, y, z);
			if (screen.drive == null)
			{
				screen.drive = (TileEntityComputerDrive) world.getTileEntity(screen.drivePosX, screen.drivePosY, screen.drivePosZ);
			}
			player.openGui(PowerCraft.instance, GuiIds.COMPUTER_SCREEN, world, x, y, z);
			return true;
		}
		return false;
	}
	
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 1)
		{
			TileEntityComputerDrive tileentity = (TileEntityComputerDrive) world.getTileEntity(x, y, z);
			if (tileentity.screen != null)
			{
				tileentity.screen.drive = null;
				tileentity.screen = null;
			}
		}
		return super.removedByPlayer(world, player, x, y, z);
	}
}
