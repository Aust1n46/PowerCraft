package larsg310.mods.powercraft.proxy;

import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.tileentity.TileEntityCompressor;
import larsg310.mods.powercraft.tileentity.TileEntityComputerDrive;
import larsg310.mods.powercraft.tileentity.TileEntityComputerScreen;
import larsg310.mods.powercraft.tileentity.TileEntityCreativeEnergyStorage;
import larsg310.mods.powercraft.tileentity.TileEntityEnergizedFurnace;
import larsg310.mods.powercraft.tileentity.TileEntityEnergizedGrinder;
import larsg310.mods.powercraft.tileentity.TileEntityEnergyGenerator;
import larsg310.mods.powercraft.tileentity.TileEntityEnergyStorageTier1;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void registerRenderers()
	{
		
	}
	
	public void registerTileEntitys()
	{
		GameRegistry.registerTileEntity(TileEntityCreativeEnergyStorage.class, Reference.MOD_ID + ":EnergyStorageCreative");
		GameRegistry.registerTileEntity(TileEntityEnergyStorageTier1.class, Reference.MOD_ID + ":EnergyStorageTier1");
		GameRegistry.registerTileEntity(TileEntityEnergyGenerator.class, Reference.MOD_ID + ":EnergyGenerator");
		GameRegistry.registerTileEntity(TileEntityEnergizedFurnace.class, Reference.MOD_ID + ":EnergizedFurnace");
		GameRegistry.registerTileEntity(TileEntityEnergizedGrinder.class, Reference.MOD_ID + ":EnergizedGrinder");
		GameRegistry.registerTileEntity(TileEntityCompressor.class, Reference.MOD_ID + ":Compressor");
		GameRegistry.registerTileEntity(TileEntityComputerScreen.class, Reference.MOD_ID + ":ComputerScreen");
		GameRegistry.registerTileEntity(TileEntityComputerDrive.class, Reference.MOD_ID + ":ComputerDrive");
	}
}
