package larsg310.mods.powercraft.tileentity;

import larsg310.mods.powercraft.api.IEnergy;
import larsg310.mods.powercraft.api.PowerBar;
import larsg310.mods.powercraft.energy.EnergyNet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyStorage extends TileEntity implements IEnergy
{
	private PowerBar powerBar = new PowerBar(10000);
	
	public void updateEntity()
	{
		EnergyNet.distributeEnergyToSurrounding(worldObj, xCoord, yCoord, zCoord, ForgeDirection.UNKNOWN, powerBar);
//		powerBar.setEnergyLevel(powerBar.getMaxEnergyLevel());
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
}
