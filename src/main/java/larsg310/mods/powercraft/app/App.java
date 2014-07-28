package larsg310.mods.powercraft.app;

import larsg310.mods.powercraft.gui.GuiComputerScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public abstract class App
{
    public abstract ResourceLocation getAppTexture();
    
    public abstract String getAppName();
    
    public abstract void drawGui(GuiComputerScreen gui, int x, int y);
    
    public void writeToNBT(NBTTagCompound tag)
    {
        
    }
    
    public void readFromNBT(NBTTagCompound tag)
    {
        
    }
    
    public void mouseClicked(GuiComputerScreen gui, int x, int y, int mouseButton)
    {
        
    }
    
    public void keyTyped(char character, int keyId)
    {
        
    }
}
