package larsg310.mods.powercraft.tileentity;

import larsg310.mods.powercraft.api.IEnergy;
import larsg310.mods.powercraft.api.PowerBar;
import larsg310.mods.powercraft.energy.EnergyNet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCreativeEnergyStorage extends TileEntity implements IEnergy
{
	private PowerBar powerBar = new PowerBar(10000, true);
	
	public void updateEntity()
	{
		EnergyNet.distributeEnergyToSurrounding(worldObj, xCoord, yCoord, zCoord, powerBar);
		powerBar.setEnergyLevel(powerBar.getMaxEnergyLevel());
	}
	
	@Override
	public boolean canAddEnergyOnSide(ForgeDirection direction)
	{
		return false;
	}
	
	@Override
	public boolean canConnect(ForgeDirection direction)
	{
		return direction == ForgeDirection.NORTH;
	}
	
	@Override
	public void setLastReceivedDirection(ForgeDirection direction)
	{
		
	}
	
	@Override
	public PowerBar getPowerBar()
	{
		return powerBar;
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		powerBar.writeToNBT(tag);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		powerBar.readFromNBT(tag);
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

	@Override
	public int getPowerTransferRate()
	{
		return 100;
	}
}
