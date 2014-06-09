package larsg310.mods.powercraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityComputerScreen extends TileEntity
{
	public TileEntityComputerDrive drive;
	public int drivePosX;
	public int drivePosY;
	public int drivePosZ;
	public int rotation = 3;
	public int currentAppId = -1;
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.drivePosX = tag.getInteger("drivePosX");
		this.drivePosY = tag.getInteger("drivePosY");
		this.drivePosZ = tag.getInteger("drivePosZ");
		this.rotation = tag.getInteger("rotation");
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("drivePosX", drivePosX);
		tag.setInteger("drivePosY", drivePosY);
		tag.setInteger("drivePosZ", drivePosZ);
		tag.setInteger("rotation", rotation);
	}
}
