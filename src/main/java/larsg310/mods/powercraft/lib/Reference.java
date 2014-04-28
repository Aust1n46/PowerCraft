package larsg310.mods.powercraft.lib;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Reference
{
	public static final String MOD_ID = "powercraft";
	public static final String MOD_AUTHOR = "Larsg310";
	public static final String MOD_NAME = "PowerCraft";
	public static final String MOD_VERSION = "See mcmod.info";
	public static final String CLIENT_PROXY = "larsg310.mods.powercraft.proxy.ClientProxy";
	public static final String COMMON_PROXY = "larsg310.mods.powercraft.proxy.CommonProxy";
	
	public static File CONFIG_FILE(FMLPreInitializationEvent event)
	{
		return new File(event.getModConfigurationDirectory() + File.separator + Reference.MOD_AUTHOR + File.separator + Reference.MOD_NAME + ".cfg");
	}
}
