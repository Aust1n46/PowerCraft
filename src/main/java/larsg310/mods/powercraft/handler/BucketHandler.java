package larsg310.mods.powercraft.handler;

import java.util.HashMap;
import java.util.Map;

import larsg310.mods.powercraft.block.ModBlocks;
import larsg310.mods.powercraft.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BucketHandler
{
    public static BucketHandler INSTANCE = new BucketHandler();
    public Map<Block, Item> buckets = new HashMap<Block, Item>();
    
    private BucketHandler()
    {
    }
    
    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event)
    {
        ItemStack result = fillCustomBucket(event.world, event.target);
        
        if (result == null) return;
        
        event.result = result;
        event.setResult(Result.ALLOW);
    }
    
    private ItemStack fillCustomBucket(World world, MovingObjectPosition pos)
    {
        
        Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
        
        Item bucket = buckets.get(block);
        if (bucket != null && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0)
        {
            world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
            return new ItemStack(bucket);
        }
        else
        {
            return null;
        }
        
    }
    
    public static void register()
    {
        BucketHandler.INSTANCE.buckets.put(ModBlocks.LIQUIDIZED_MATTER, ModItems.LIQUIDIZED_MATTER_BUCKET);
        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
    }
}
