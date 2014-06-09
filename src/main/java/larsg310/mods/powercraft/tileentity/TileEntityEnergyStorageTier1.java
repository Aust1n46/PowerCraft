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
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyStorageTier1 extends TileEntity implements IEnergy
{
	private EnergyBar energyBar = new EnergyBar(10000);
	public ForgeDirection outputSide = ForgeDirection.SOUTH;
	
	public void updateEntity()
	{
		EnergyNet.distributeEnergyToSide(worldObj, xCoord, yCoord, zCoord, outputSide, energyBar);
	}
	
	public boolean canAddEnergyOnSide(ForgeDirection direction)
	{
		return direction != outputSide;
	}
	
	public boolean canConnect(ForgeDirection direction)
	{
		return true;
	}
	
	public EnergyBar getEnergyBar()
	{
		return energyBar;
	}
	
	public void setLastReceivedDirection(ForgeDirection direction)
	{
		
	}
	
	@Override
	public int getEnergyTransferRate()
	{
		return 100;
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		energyBar.writeToNBT(tag);
		tag.setInteger("outputSide", outputSide.ordinal());
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		energyBar.readFromNBT(tag);
		outputSide = ForgeDirection.getOrientation(tag.getInteger("outputSide"));
	}
	
	@Override
	public BlockType getTypeOfBlock()
	{
		return BlockType.MACHINE;
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
}
