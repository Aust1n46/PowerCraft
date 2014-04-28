package larsg310.mods.powercraft.proxy;

import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.tileentity.TileEntityEnergyStorage;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void registerRenderers()
	{
		
	}
	public void registerTileEntitys()
	{
		GameRegistry.registerTileEntity(TileEntityEnergyStorage.class, Names.ENERGY_STORAGE);
	}
}
