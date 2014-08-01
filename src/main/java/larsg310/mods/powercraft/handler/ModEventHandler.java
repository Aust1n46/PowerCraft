package larsg310.mods.powercraft.handler;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandler
{
    @SubscribeEvent
    public void drawBlockBounds(DrawBlockHighlightEvent event)
    {
        // World world = event.player.worldObj;
        // int x = event.target.blockX;
        // int y = event.target.blockY;
        // int z = event.target.blockZ;
        // Block block = world.getBlock(x, y, z);
        // if (block == ModBlocks.CABLE)
        // {
        // event.setCanceled(true);
        // GL11.glEnable(GL11.GL_BLEND);
        // OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        // if (event.currentItem != null && event.currentItem.getItem() ==
        // ModItems.DEBUGGER_TOOL)
        // {
        // GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        // }
        // else
        // {
        // GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
        // }
        // GL11.glLineWidth(2.0F);
        // GL11.glDisable(GL11.GL_TEXTURE_2D);
        // GL11.glDepthMask(false);
        // double f1 = 0.002D;
        // block.setBlockBoundsBasedOnState(world, x, y, z);
        // double x1 = event.player.lastTickPosX + (event.player.posX -
        // event.player.lastTickPosX) * (double) event.partialTicks;
        // double y1 = event.player.lastTickPosY + (event.player.posY -
        // event.player.lastTickPosY) * (double) event.partialTicks;
        // double z1 = event.player.lastTickPosZ + (event.player.posZ -
        // event.player.lastTickPosZ) * (double) event.partialTicks;
        // boolean[] sides = new boolean[6];
        // int nexts = 0;
        // for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        // {
        // if (world.getTileEntity(x + direction.offsetX, y + direction.offsetY,
        // z + direction.offsetZ) instanceof IEnergy && ((IEnergy)
        // world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z +
        // direction.offsetZ)).canConnect(direction))
        // {
        // sides[direction.ordinal()] = true;
        // nexts++;
        // }
        // }
        // AxisAlignedBB blockBounds =
        // block.getSelectedBoundingBoxFromPool(world, x, y, z).expand(f1, f1,
        // f1).getOffsetBoundingBox(-x1, -y1, -z1);
        // Tessellator tessellator = Tessellator.instance;
        // tessellator.startDrawing(GL11.GL_LINES);
        // if (!sides[EAST.ordinal()] && !sides[WEST.ordinal()] &&
        // !sides[NORTH.ordinal()] && !sides[SOUTH.ordinal()] &&
        // !sides[UP.ordinal()] && !sides[DOWN.ordinal()])
        // {
        // addCubicVertices(blockBounds);
        // }
        // if (nexts == 1)
        // {
        // addCubicVertices(blockBounds);
        // }
        // if (nexts == 2 && ((sides[EAST.ordinal()] && sides[WEST.ordinal()])
        // || (sides[NORTH.ordinal()] && sides[SOUTH.ordinal()]) ||
        // (sides[DOWN.ordinal()] && sides[UP.ordinal()])))
        // {
        // addCubicVertices(blockBounds);
        // }
        // if (nexts == 2 && (sides[WEST.ordinal()] && sides[NORTH.ordinal()]))
        // {
        // AxisAlignedBB tempBounds =
        // getValue(AxisAlignedBB.getAABBPool().getAABB(0.375F, 0.375F, 0.375F,
        // 0.625F, 0.625F, 0.625F), x1, y1, z1);
        // tessellator.addVertex(tempBounds.minX, tempBounds.minY,
        // tempBounds.minY);
        // tempBounds = getValue(AxisAlignedBB.getAABBPool().getAABB(0F, 0F, 0F,
        // 0F, 0F, 0), x1, y1, z1);
        // tessellator.addVertex(tempBounds.maxX, tempBounds.minY,
        // tempBounds.minY);
        //
        // }
        // tessellator.draw();
        // GL11.glDepthMask(true);
        // GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glDisable(GL11.GL_BLEND);
        // }
    }
    
    public static void register()
    {
        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
        FMLCommonHandler.instance().bus().register(new ModEventHandler());
    }
    
    private AxisAlignedBB getValue(AxisAlignedBB bounds, double x1, double y1, double z1)
    {
        double f1 = 0.002D;
        return bounds.expand(f1, f1, f1).getOffsetBoundingBox(-x1, -y1, -z1);
    }
    
    private void addCubicVertices(AxisAlignedBB bounds)
    {
        Tessellator tessellator = Tessellator.instance;
        
        tessellator.addVertex(bounds.minX, bounds.minY, bounds.minZ);
        tessellator.addVertex(bounds.minX, bounds.maxY, bounds.minZ);
        
        tessellator.addVertex(bounds.minX, bounds.minY, bounds.maxZ);
        tessellator.addVertex(bounds.minX, bounds.maxY, bounds.maxZ);
        
        tessellator.addVertex(bounds.maxX, bounds.minY, bounds.minZ);
        tessellator.addVertex(bounds.maxX, bounds.maxY, bounds.minZ);
        
        tessellator.addVertex(bounds.maxX, bounds.minY, bounds.maxZ);
        tessellator.addVertex(bounds.maxX, bounds.maxY, bounds.maxZ);
        
        tessellator.addVertex(bounds.minX, bounds.maxY, bounds.minZ);
        tessellator.addVertex(bounds.minX, bounds.maxY, bounds.maxZ);
        
        tessellator.addVertex(bounds.maxX, bounds.maxY, bounds.minZ);
        tessellator.addVertex(bounds.maxX, bounds.maxY, bounds.maxZ);
        
        tessellator.addVertex(bounds.minX, bounds.minY, bounds.minZ);
        tessellator.addVertex(bounds.minX, bounds.minY, bounds.maxZ);
        
        tessellator.addVertex(bounds.maxX, bounds.minY, bounds.minZ);
        tessellator.addVertex(bounds.maxX, bounds.minY, bounds.maxZ);
        
        tessellator.addVertex(bounds.minX, bounds.maxY, bounds.minZ);
        tessellator.addVertex(bounds.maxX, bounds.maxY, bounds.minZ);
        
        tessellator.addVertex(bounds.minX, bounds.maxY, bounds.maxZ);
        tessellator.addVertex(bounds.maxX, bounds.maxY, bounds.maxZ);
        
        tessellator.addVertex(bounds.minX, bounds.minY, bounds.minZ);
        tessellator.addVertex(bounds.maxX, bounds.minY, bounds.minZ);
        
        tessellator.addVertex(bounds.minX, bounds.minY, bounds.maxZ);
        tessellator.addVertex(bounds.maxX, bounds.minY, bounds.maxZ);
        
    }
}
