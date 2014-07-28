package larsg310.mods.powercraft.block;

import java.util.List;

import larsg310.mods.powercraft.lib.Energy;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemBlockStandardMetadata extends ItemBlock
{
    public ItemBlockStandardMetadata(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }
    
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return getUnlocalizedName() + "." + itemstack.getItemDamage();
    }
    
    public int getMetadata(int meta)
    {
        return meta;
    }
    
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean advanced)
    {
        if (itemstack.getItem() == Item.getItemFromBlock(ModBlocks.CABLE))
        {
            int meta = itemstack.getItemDamage();
            if (meta == 0)
            {
                list.add("Energy loss: 0 " + Energy.WATT.getSymbol() + "/m");
            }
            if (meta == 1)
            {
                list.add("Energy loss: 1 " + Energy.WATT.getSymbol() + "/m");
            }
        }
        if (itemstack.getItem() == Item.getItemFromBlock(ModBlocks.COMPUTER) && itemstack.getItemDamage() == 0)
        {
            if (itemstack.stackTagCompound != null && itemstack.stackTagCompound.hasKey("drivePosX"))
            {
                list.add("X: " + itemstack.stackTagCompound.getInteger("drivePosX"));
                list.add("Y: " + itemstack.stackTagCompound.getInteger("drivePosY"));
                list.add("Z: " + itemstack.stackTagCompound.getInteger("drivePosZ"));
            }
        }
    }
    
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (itemstack.getItem() == Item.getItemFromBlock(ModBlocks.COMPUTER) && itemstack.getItemDamage() == 0)
        {
            if (player.isSneaking())
            {
                if (world.getBlock(x, y, z) == ModBlocks.COMPUTER && world.getBlockMetadata(x, y, z) == 1)
                {
                    itemstack.stackTagCompound = new NBTTagCompound();
                    itemstack.stackTagCompound.setInteger("drivePosX", x);
                    itemstack.stackTagCompound.setInteger("drivePosY", y);
                    itemstack.stackTagCompound.setInteger("drivePosZ", z);
                    return true;
                }
            }
        }
        return super.onItemUse(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
}
