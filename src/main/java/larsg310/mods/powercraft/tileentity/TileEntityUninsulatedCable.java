package larsg310.mods.powercraft.tileentity;

import larsg310.mods.powercraft.block.BlockType;
import larsg310.mods.powercraft.energy.EnergyBar;
import larsg310.mods.powercraft.energy.EnergyNet;
import larsg310.mods.powercraft.energy.IEnergy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityUninsulatedCable extends TileEntity implements IEnergy
{
	private ForgeDirection lastReceivedDirection = ForgeDirection.UNKNOWN;
	private EnergyBar energyBar = new EnergyBar(100);
	
	public void updateEntity()
	{
		EnergyNet.distributeEnergyToSurroundingWithLoss(worldObj, xCoord, yCoord, zCoord, lastReceivedDirection, energyBar,1);
	}
	
	@Override
	public boolean canAddEnergyOnSide(ForgeDirection direction)
	{
		return true;
	}
	
	@Override
	public boolean canConnect(ForgeDirection direction)
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return Double.MAX_VALUE;
	}
	
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
	}
	
	public EnergyBar getEnergyBar()
	{
		return energyBar;
	}
	
	@Override
	public void setLastReceivedDirection(ForgeDirection direction)
	{
		this.lastReceivedDirection = direction;
	}
	
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		energyBar.writeToNBT(tag);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		energyBar.readFromNBT(tag);
	}
	
	@Override
	public int getEnergyTransferRate()
	{
		return 100;
	}
	
	@Override
	public BlockType getTypeOfBlock()
	{
		return BlockType.CABLE;
	}
}
