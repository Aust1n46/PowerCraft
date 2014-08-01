package larsg310.mods.powercraft.energy;

import larsg310.mods.powercraft.block.BlockType;
import net.minecraftforge.common.util.ForgeDirection;

public interface IEnergy
{
    public boolean canAddEnergyOnSide(ForgeDirection direction);
    
    public boolean canConnect(ForgeDirection direction);
    
    public EnergyBar getEnergyBar();
    
    public void setLastReceivedDirection(ForgeDirection direction);
    
    public int getEnergyTransferRate();
    
    public BlockType getTypeOfBlock();
}
