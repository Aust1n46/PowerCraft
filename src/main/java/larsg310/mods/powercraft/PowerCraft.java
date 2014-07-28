package larsg310.mods.powercraft;

import larsg310.mods.powercraft.block.ModBlocks;
import larsg310.mods.powercraft.creativetab.CreativeTabPowerCraft;
import larsg310.mods.powercraft.entity.Entities;
import larsg310.mods.powercraft.fluid.ModFluids;
import larsg310.mods.powercraft.handler.AppHandler;
import larsg310.mods.powercraft.handler.BucketHandler;
import larsg310.mods.powercraft.handler.GuiHandler;
import larsg310.mods.powercraft.handler.ModEventHandler;
import larsg310.mods.powercraft.item.ModItems;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.proxy.CommonProxy;
import larsg310.mods.powercraft.recipe.CraftingRecipes;
import larsg310.mods.powercraft.recipe.SmeltingRecipes;
import larsg310.mods.powercraft.task.Task;
import larsg310.mods.powercraft.task.TaskType;
import larsg310.mods.powercraft.util.BlockCoord;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME)
public class PowerCraft
{
    @Instance(Reference.MOD_ID)
    public static PowerCraft instance;
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
    public static CommonProxy proxy;
    
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabPowerCraft(CreativeTabs.getNextID(), Reference.MOD_ID);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModFluids.init();
        ModBlocks.init();
        ModItems.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerRenderers();
        proxy.registerTileEntitys();
        Entities.register();
        GuiHandler.register();
        ModEventHandler.register();
        AppHandler.register();
        BucketHandler.register();
        CraftingRecipes.register();
        SmeltingRecipes.register();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        Task task = new Task(TaskType.BUILD_HOUSE);
        for (int y = 0; y < 5; y++)
        {
            task.blocks.add(new BlockCoord(0, y, 0, Blocks.air));
            task.blocks.add(new BlockCoord(1, y, 0, Blocks.air));
            task.blocks.add(new BlockCoord(2, y, 0, Blocks.air));
            task.blocks.add(new BlockCoord(3, y, 0, Blocks.air));
            task.blocks.add(new BlockCoord(4, y, 0, Blocks.air));
            task.blocks.add(new BlockCoord(5, y, 0, Blocks.air));
            task.blocks.add(new BlockCoord(6, y, 0, Blocks.air));
            task.blocks.add(new BlockCoord(7, y, 0, Blocks.air));
            task.blocks.add(new BlockCoord(8, y, 0, Blocks.air));
            
            task.blocks.add(new BlockCoord(0, y, 9, Blocks.air));
            task.blocks.add(new BlockCoord(1, y, 9, Blocks.air));
            task.blocks.add(new BlockCoord(2, y, 9, Blocks.air));
            task.blocks.add(new BlockCoord(3, y, 9, Blocks.air));
            task.blocks.add(new BlockCoord(4, y, 9, Blocks.air));
            task.blocks.add(new BlockCoord(5, y, 9, Blocks.air));
            task.blocks.add(new BlockCoord(6, y, 9, Blocks.air));
            task.blocks.add(new BlockCoord(7, y, 9, Blocks.air));
            task.blocks.add(new BlockCoord(8, y, 9, Blocks.air));
            
            task.blocks.add(new BlockCoord(0, y, 1, Blocks.air));
            task.blocks.add(new BlockCoord(0, y, 2, Blocks.air));
            task.blocks.add(new BlockCoord(0, y, 3, Blocks.air));
            task.blocks.add(new BlockCoord(0, y, 4, Blocks.air));
            task.blocks.add(new BlockCoord(0, y, 5, Blocks.air));
            task.blocks.add(new BlockCoord(0, y, 6, Blocks.air));
            task.blocks.add(new BlockCoord(0, y, 7, Blocks.air));
            
            task.blocks.add(new BlockCoord(8, y, 1, Blocks.air));
            task.blocks.add(new BlockCoord(8, y, 2, Blocks.air));
            task.blocks.add(new BlockCoord(8, y, 3, Blocks.air));
            task.blocks.add(new BlockCoord(8, y, 4, Blocks.air));
            task.blocks.add(new BlockCoord(8, y, 5, Blocks.air));
            task.blocks.add(new BlockCoord(8, y, 6, Blocks.air));
            task.blocks.add(new BlockCoord(8, y, 7, Blocks.air));
        }
        task.save();
    }
}
