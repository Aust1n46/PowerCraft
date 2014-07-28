package larsg310.mods.powercraft.gui;

import java.util.Arrays;
import java.util.List;

import larsg310.mods.powercraft.container.ContainerComputerScreen;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.tileentity.TileEntityComputerScreen;
import larsg310.mods.powercraft.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiComputerScreen extends GuiContainer
{
    ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiComputer.png");
    public ResourceLocation APP_HOVER = new ResourceLocation(Reference.MOD_ID, "textures/app/appHover.png");
    public final EntityPlayer player;
    public final World world;
    public final int x;
    public final int y;
    public final int z;
    public TileEntityComputerScreen tileentity;
    public RenderItem itemRenderer = new RenderItem();
    
    public GuiComputerScreen(EntityPlayer player, World world, int x, int y, int z)
    {
        super(new ContainerComputerScreen(player, world, x, y, z));
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.tileentity = (TileEntityComputerScreen) world.getTileEntity(x, y, z);
        this.xSize = 494;
        this.ySize = 302;
    }
    
    public void drawGuiContainerBackgroundLayer(float f1, int x, int y)
    {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(GUI);
        GuiUtil.drawRectangle(guiLeft, guiTop - 6, xSize, ySize, 512, 512, 0, 0);
        if (tileentity.drive == null)
        {
            fontRendererObj.drawString("No drive found! Please install a drive!", guiLeft + 11, guiTop + 5, 0xFFFFFF);
        }
        else
        {
            if (tileentity.currentAppId < 0)
            {
                for (int index = 0; index < tileentity.drive.apps.size(); index++)
                {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(tileentity.drive.apps.get(index).getAppTexture());
                    GuiUtil.drawRectangle(guiLeft + 11, guiTop + 5, 32, 32, 32, 32, 0, 0);
                }
            }
        }
    }
    
    public void drawGuiContainerForegroundLayer(int x, int y)
    {
        GL11.glColor4f(1, 1, 1, 1);
        if (tileentity.drive != null)
        {
            if (tileentity.currentAppId < 0)
            {
                for (int appIndex = 0; appIndex < tileentity.drive.apps.size(); appIndex++)
                {
                    int minX = guiLeft + 11 + 40 * appIndex;
                    int maxX = guiLeft + 42 + 40 * appIndex;
                    int minY = guiTop + 5 + 40 * (appIndex / 12);
                    int maxY = guiTop + 36 + 40 * (appIndex / 12);
                    
                    if (x >= minX && x <= maxX && y >= minY && y <= maxY)
                    {
                        Minecraft.getMinecraft().getTextureManager().bindTexture(APP_HOVER);
                        GuiUtil.drawRectangle(minX - guiLeft - 1, minY - 26, 34, 34, 34, 34, 0, 0);
                        this.drawHoveringText(Arrays.asList(tileentity.drive.apps.get(appIndex).getAppName()), x - guiLeft + 10, y - guiTop, fontRendererObj);
                    }
                }
            }
            else
            {
                tileentity.drive.apps.get(tileentity.currentAppId).drawGui(this, x, y);
            }
        }
    }
    
    protected void mouseClicked(int x, int y, int mouseButton)
    {
        if (tileentity.drive == null)
        {
            super.mouseClicked(x, y, mouseButton);
            return;
        }
        if (tileentity.currentAppId < 0)
        {
            tileentity.currentAppId = getAppId(x, y);
        }
        if (tileentity.currentAppId >= 0)
        {
            tileentity.drive.apps.get(tileentity.currentAppId).mouseClicked(this, x, y, mouseButton);
        }
        super.mouseClicked(x, y, mouseButton);
    }
    
    private int getAppId(int x, int y)
    {
        for (int appIndex = 0; appIndex < tileentity.drive.apps.size(); appIndex++)
        {
            if (x >= guiLeft + 11 + 40 * appIndex && x <= guiLeft + 42 + 40 * appIndex && y >= guiTop + 5 + 40 * (appIndex / 12) && y <= guiTop + 36 + 40 * (appIndex / 12))
            {
                return appIndex;
            }
        }
        return -1;
    }
    
    protected void keyTyped(char character, int keyId)
    {
        if (tileentity.drive == null)
        {
            super.keyTyped(character, keyId);
            return;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {
            if (tileentity.currentAppId == -1)
            {
                Minecraft.getMinecraft().thePlayer.closeScreen();
            }
            else
            {
                tileentity.currentAppId = -1;
            }
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_E))
        {
            Minecraft.getMinecraft().thePlayer.closeScreen();
        }
        else
        {
            super.keyTyped(character, keyId);
        }
    }
    
    public int getGuiLeft()
    {
        return guiLeft;
    }
    
    public int getGuiTop()
    {
        return guiTop;
    }
    
    public void drawHoveringText(List text, int x, int y, FontRenderer fontRendererObj)
    {
        super.drawHoveringText(text, x, y, fontRendererObj);
    }
}
