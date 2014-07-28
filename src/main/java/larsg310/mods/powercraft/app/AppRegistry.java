package larsg310.mods.powercraft.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppRegistry
{
    public static final AppRegistry instance = new AppRegistry();
    
    private AppRegistry()
    {
        
    }
    
    private HashMap<Integer, App> apps = new HashMap<Integer, App>();
    private List<IAppHandler> appHandlers = new ArrayList<IAppHandler>();
    
    public HashMap<Integer, App> getApps()
    {
        apps.clear();
        for (int index = 0; index < appHandlers.size(); index++)
        {
            appHandlers.get(index).addAppsToList(apps);
        }
        return apps;
    }
    
    public void registerAppHandler(IAppHandler handler)
    {
        appHandlers.add(handler);
    }
    
    public int getNextAvailableId()
    {
        return apps.size();
    }
}
