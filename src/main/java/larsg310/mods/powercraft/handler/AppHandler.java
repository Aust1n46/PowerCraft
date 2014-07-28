package larsg310.mods.powercraft.handler;

import java.util.HashMap;

import larsg310.mods.powercraft.app.App;
import larsg310.mods.powercraft.app.AppBlockInfo;
import larsg310.mods.powercraft.app.AppRegistry;
import larsg310.mods.powercraft.app.IAppHandler;

public class AppHandler implements IAppHandler
{
    @Override
    public void addAppsToList(HashMap<Integer, App> apps)
    {
        apps.put(AppRegistry.instance.getNextAvailableId(), new AppBlockInfo());
    }
    
    public static void register()
    {
        AppRegistry.instance.registerAppHandler(new AppHandler());
    }
}
