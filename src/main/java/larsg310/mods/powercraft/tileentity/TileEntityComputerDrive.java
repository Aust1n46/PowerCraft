package larsg310.mods.powercraft.tileentity;

import java.util.HashMap;

import larsg310.mods.powercraft.app.App;
import larsg310.mods.powercraft.app.AppRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityComputerDrive extends TileEntity
{
	public TileEntityComputerScreen screen;
	public int screenPosX;
	public int screenPosY;
	public int screenPosZ;
	public int rotation = 3;
	public HashMap<Integer, App> apps = AppRegistry.instance.getApps();
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.screenPosX = tag.getInteger("screenPosX");
		this.screenPosY = tag.getInteger("screenPosY");
		this.screenPosZ = tag.getInteger("screenPosZ");
		this.rotation = tag.getInteger("rotation");
		this.screen = (TileEntityComputerScreen) worldObj.getTileEntity(screenPosX, screenPosY, screenPosZ);
		System.out.println(screen);
		for (int index = 0; index < apps.size(); index++)
		{
			apps.get(index).readFromNBT(tag);
		}
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);;
		tag.setInteger("screenPosX", screenPosX);
		tag.setInteger("screenPosY", screenPosY);
		tag.setInteger("screenPosZ", screenPosZ);
		tag.setInteger("rotation", rotation);
		for (int index = 0; index < apps.size(); index++)
		{
			apps.get(index).writeToNBT(tag);
		}
	}
	
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
	}
	
	public void onPacketData(NetworkManager manager, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}
}
