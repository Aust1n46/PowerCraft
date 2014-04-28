package larsg310.mods.powercraft.api;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public interface IEnergy
{
	public boolean canAddEnergyOnSide(ForgeDirection direction);
	
	public boolean canConnect(ForgeDirection direction);
	
	public PowerBar getPowerBar();
	
	public void setLastReceivedDirection(ForgeDirection direction);
	
	public int getPowerTransferRate();
}
