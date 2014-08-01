package larsg310.mods.powercraft.network;

import larsg310.mods.powercraft.lib.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler
{
    public static SimpleNetworkWrapper network;
    
    public static void init()
    {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID + ":PacketChannel");
        
        // network.registerMessage(PacketExtraInventoryUpdate.class,
        // PacketExtraInventoryUpdate.class, 0, Side.SERVER);
    }
}
