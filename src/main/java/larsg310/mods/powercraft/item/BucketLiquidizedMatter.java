package larsg310.mods.powercraft.item;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.block.ModBlocks;
import larsg310.mods.powercraft.lib.Names;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class BucketLiquidizedMatter extends ItemBucket
{
	public BucketLiquidizedMatter()
	{
		super(ModBlocks.LIQUIDIZED_MATTER);
		this.setUnlocalizedName(Names.BUCKET_LIQUIDIZED_MATTER);
		this.setContainerItem(Items.bucket);
		this.setCreativeTab(PowerCraft.CREATIVE_TAB);
	}
}
