package larsg310.mods.powercraft.app;

import java.util.Arrays;

import larsg310.mods.powercraft.gui.GuiComputerScreen;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.util.BlockUtil;
import larsg310.mods.powercraft.util.GuiUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class AppBlockInfo extends App
{
    ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/app/appBlockInfo.png");
    ResourceLocation SIDE_BAR = new ResourceLocation(Reference.MOD_ID, "textures/app/sideBar.png");
    public int appsPerRow = 5;
    public int scrolled = 0;
    public int selectedBlock = -1;
    public Block currentBlock;
    
    public ResourceLocation getAppTexture()
    {
        return TEXTURE;
    }
    
    public String getAppName()
    {
        return "Block Info";
    }
    
    @Override
    public void drawGui(GuiComputerScreen gui, int x, int y)
    {
        GL11.glColor3f(1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(SIDE_BAR);
        GuiUtil.drawRectangle(281, -1, 6, 203, 6, 203, 0, 0);
        int maxScrolled = BlockUtil.vanillaBlocks.length / appsPerRow - 5;
        for (int column = 0; column < 5; column++)
        {
            for (int row = 0; row < appsPerRow; row++)
            {
                Block block = BlockUtil.vanillaBlocks[row + (column + scrolled) * appsPerRow];
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/blocks/" + block.getBlockTextureFromSide(3).getIconName() + ".png"));
                if (block == Blocks.fire) Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/blocks/fire.png"));
                if (block == Blocks.oak_stairs) Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/blocks/stairsWood.png"));
                if (block == Blocks.chest) Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/blocks/chest.png"));
                if (block == Blocks.tallgrass) Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/blocks/tallgrass.png"));
                if (block == Blocks.farmland) Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/blocks/farmland_dry.png"));
                if (block == Blocks.fence) Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/blocks/fence.png"));
                if (block == Blocks.leaves)
                {
                    float red = (float) (ColorizerFoliage.getFoliageColor(0.5D, 1) >> 16 & 255) / 255F;
                    float green = (float) (ColorizerFoliage.getFoliageColor(0.5D, 1) >> 8 & 255) / 255F;
                    float blue = (float) (ColorizerFoliage.getFoliageColor(0.5D, 1) & 255) / 255F;
                    GL11.glColor3f(red, green, blue);
                }
                if (block == Blocks.tallgrass)
                {
                    float red = (float) (ColorizerGrass.getGrassColor(0.5D, 1) >> 16 & 255) / 255F;
                    float green = (float) (ColorizerGrass.getGrassColor(0.5D, 1) >> 8 & 255) / 255F;
                    float blue = (float) (ColorizerGrass.getGrassColor(0.5D, 1) & 255) / 255F;
                    GL11.glColor3f(red, green, blue);
                }
                GuiUtil.drawRectangle(291 + 40 * row, 5 + 40 * column, 32, 32, 32, 32, 0, 0);
                GL11.glColor3f(1, 1, 1);
                if (currentBlock != null)
                {
                    String description = StatCollector.translateToLocal("info.block." + currentBlock.getUnlocalizedName().substring(5));
                    String[] words = description.split(" ");
                    int counted = 0;
                    int lines = 0;
                    for (int i = 0; i < words.length; i++)
                    {
                        gui.drawString(Minecraft.getMinecraft().fontRenderer, words[i] + " ", 11 + counted, 5 + lines * 9, 0xFFFFFF);
                        if (counted + Minecraft.getMinecraft().fontRenderer.getStringWidth(words[i] + " ") > 230)
                        {
                            counted = 0;
                            lines++;
                        }
                        else
                        {
                            counted += Minecraft.getMinecraft().fontRenderer.getStringWidth(words[i] + " ");
                        }
                    }
                }
                GL11.glEnable(GL11.GL_LIGHTING);
            }
        }
        for (int column = 0; column < 5; column++)
        {
            for (int row = 0; row < appsPerRow; row++)
            {
                Block block = BlockUtil.vanillaBlocks[row + (column + scrolled) * appsPerRow];
                
                String prefix = "";
                if (block == Blocks.dirt || block == Blocks.sand) prefix = ".default";
                if (block == Blocks.sapling) prefix = ".oak";
                if (block == Blocks.red_flower) prefix = ".poppy";
                if (block == Blocks.stone_slab) prefix = ".stone";
                int minX = gui.getGuiLeft() + 291 + 40 * row;
                int maxX = gui.getGuiLeft() + 322 + 40 * row;
                int minY = gui.getGuiTop() + 5 + 40 * column;
                int maxY = gui.getGuiTop() + 36 + 40 * column;
                
                if (x >= minX && x <= maxX && y >= minY && y <= maxY)
                {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(gui.APP_HOVER);
                    GuiUtil.drawRectangle(minX - gui.getGuiLeft() - 1, minY - 26, 34, 34, 34, 34, 0, 0);
                    gui.drawHoveringText(Arrays.asList(StatCollector.translateToLocal(block.getUnlocalizedName() + prefix + ".name")), x - gui.getGuiLeft() + 10, y - gui.getGuiTop(), Minecraft.getMinecraft().fontRenderer);
                }
            }
        }
        int dWheel = Mouse.getDWheel();
        if (dWheel < 0)
        {
            scrolled++;
        }
        else if (dWheel > 0)
        {
            scrolled--;
        }
        if (scrolled < 0)
        {
            scrolled = 0;
        }
        if (scrolled > maxScrolled)
        {
            scrolled = maxScrolled;
        }
    }
    
    public void mouseClicked(GuiComputerScreen gui, int x, int y, int mouseButton)
    {
        for (int column = 0; column < 5; column++)
        {
            for (int row = 0; row < appsPerRow; row++)
            {
                Block block = BlockUtil.vanillaBlocks[row + (column + scrolled) * appsPerRow];
                
                int minX = gui.getGuiLeft() + 291 + 40 * row;
                int maxX = gui.getGuiLeft() + 322 + 40 * row;
                int minY = gui.getGuiTop() + 5 + 40 * column;
                int maxY = gui.getGuiTop() + 36 + 40 * column;
                
                if (x >= minX && x <= maxX && y >= minY && y <= maxY)
                {
                    this.currentBlock = block;
                }
            }
        }
    }
}
