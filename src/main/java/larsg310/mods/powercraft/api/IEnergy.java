package larsg310.mods.powercraft.api;

import net.minecraftforge.common.util.ForgeDirection;

public interface IEnergy
{
	public boolean canAddEnergyOnSide(ForgeDirection direction);
	
	public boolean canConnect(ForgeDirection direction);
	
	public PowerBar getPowerBar();
	
	public void setLastReceivedDirection(ForgeDirection direction);
}
