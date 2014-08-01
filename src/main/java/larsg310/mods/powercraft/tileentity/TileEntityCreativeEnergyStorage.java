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

public class TileEntityCreativeEnergyStorage extends TileEntity implements IEnergy
{
    private EnergyBar energyBar = new EnergyBar(50000000, true);
    
    public void updateEntity()
    {
        EnergyNet.distributeEnergyToSurrounding(worldObj, xCoord, yCoord, zCoord, energyBar);
        energyBar.setEnergyLevel(energyBar.getMaxEnergyLevel());
    }
    
    @Override
    public boolean canAddEnergyOnSide(ForgeDirection direction)
    {
        return false;
    }
    
    @Override
    public boolean canConnect(ForgeDirection direction)
    {
        return true;
    }
    
    @Override
    public void setLastReceivedDirection(ForgeDirection direction)
    {
        
    }
    
    @Override
    public EnergyBar getEnergyBar()
    {
        return energyBar;
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
    public int getEnergyTransferRate()
    {
        return energyBar.getMaxEnergyLevel();
    }
    
    @Override
    public BlockType getTypeOfBlock()
    {
        return BlockType.MACHINE;
    }
}
