package larsg310.mods.powercraft.item;

import java.util.List;

import larsg310.mods.powercraft.PowerCraft;
import larsg310.mods.powercraft.lib.Names;
import larsg310.mods.powercraft.lib.Reference;
import larsg310.mods.powercraft.task.TaskBuild;
import larsg310.mods.powercraft.util.BlockCoord;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class ItemTask extends Item
{
    public String[] tasks = new String[] {"build"};
    
    public ItemTask()
    {
        this.setCreativeTab(PowerCraft.CREATIVE_TAB);
        this.setUnlocalizedName(Names.TASK);
        this.setHasSubtypes(true);
    }
    
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int meta = 0; meta < tasks.length; meta++)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }
    
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName());
    }
    
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return getUnlocalizedName() + "." + itemstack.getItemDamage();
    }
    
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            switch (stack.getItemDamage())
            {
                case 0:
                    if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
                    {
                        stack.setTagCompound(null);
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
                    {
                        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
                        {
                            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("firstX") && stack.getTagCompound().hasKey("secondX"))
                            {
                                int firstX = stack.getTagCompound().getInteger("firstX");
                                int firstY = stack.getTagCompound().getInteger("firstY");
                                int firstZ = stack.getTagCompound().getInteger("firstZ");
                                int secondX = stack.getTagCompound().getInteger("secondX");
                                int secondY = stack.getTagCompound().getInteger("secondY");
                                int secondZ = stack.getTagCompound().getInteger("secondZ");
                                int lowestX = firstX < secondX ? firstX : secondX;
                                int lowestY = firstY < secondY ? firstY : secondY;
                                int lowestZ = firstZ < secondZ ? firstZ : secondZ;
                                int highestX = lowestX == firstX ? secondX : firstX;
                                int highestY = lowestY == firstY ? secondY : firstY;
                                int highestZ = lowestZ == firstZ ? secondZ : firstZ;
                                int width = highestX - lowestX;
                                int height = highestY - lowestY;
                                int depth = highestZ - lowestZ;
                                TaskBuild task = new TaskBuild(stack.getDisplayName() + ".task");
                                for (int y = 0; y < height+1; y++)
                                {
                                    for (int z = 0; z < depth+1; z++)
                                    {
                                        for (int x = 0; x < width+1; x++)
                                        {
                                            task.blocks.add(new BlockCoord(x, y, z, world.getBlock(lowestX + x, lowestY + y, lowestZ + z), world.getBlockMetadata(lowestX + x, lowestY + y, lowestZ + z)));
                                        }
                                    }
                                }
                                task.save();
                            }
                        }
                        else
                        {
                            if (!stack.hasTagCompound())
                            {
                                NBTTagCompound tag = new NBTTagCompound();
                                tag.setInteger("firstX", MathHelper.floor_double(player.posX));
                                tag.setInteger("firstY", MathHelper.floor_double(player.posY));
                                tag.setInteger("firstZ", MathHelper.floor_double(player.posZ));
                                stack.setTagCompound(tag);
                            }
                            else
                            {
                                if (stack.getTagCompound().hasKey("firstX"))
                                {
                                    stack.getTagCompound().setInteger("secondX", MathHelper.floor_double(player.posX));
                                    stack.getTagCompound().setInteger("secondY", MathHelper.floor_double(player.posY));
                                    stack.getTagCompound().setInteger("secondZ", MathHelper.floor_double(player.posZ));
                                }
                                else
                                {
                                    stack.getTagCompound().setInteger("firstX", (int) player.posX);
                                    stack.getTagCompound().setInteger("firstY", (int) player.posY);
                                    stack.getTagCompound().setInteger("firstZ", (int) player.posZ);
                                }
                            }
                        }
                    }
            }
        }
        return stack;
    }
}
