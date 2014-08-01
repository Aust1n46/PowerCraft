package larsg310.mods.powercraft.textures;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class ConnectedTextures
{
    public static IIcon getConnectedIcon(IBlockAccess world, int x, int y, int z, int side, IIcon[] textures, Block block)
    {
        boolean[] bitMatrix = new boolean[8];
        
        if (side == 0)
        {
            bitMatrix[0] = world.getBlock(x - 1, y, z - 1) == block && !(world.getBlock(x - 1, y - 1, z - 1) == block);
            bitMatrix[1] = world.getBlock(x, y, z - 1) == block && !(world.getBlock(x, y - 1, z - 1) == block);
            bitMatrix[2] = world.getBlock(x + 1, y, z - 1) == block && !(world.getBlock(x + 1, y - 1, z - 1) == block);
            bitMatrix[3] = world.getBlock(x - 1, y, z) == block && !(world.getBlock(x - 1, y - 1, z) == block);
            bitMatrix[4] = world.getBlock(x + 1, y, z) == block && !(world.getBlock(x + 1, y - 1, z) == block);
            bitMatrix[5] = world.getBlock(x - 1, y, z + 1) == block && !(world.getBlock(x - 1, y - 1, z + 1) == block);
            bitMatrix[6] = world.getBlock(x, y, z + 1) == block && !(world.getBlock(x, y - 1, z + 1) == block);
            bitMatrix[7] = world.getBlock(x + 1, y, z + 1) == block && !(world.getBlock(x + 1, y - 1, z + 1) == block);
        }
        if (side == 1)
        {
            bitMatrix[0] = world.getBlock(x - 1, y, z - 1) == block && !(world.getBlock(x - 1, y + 1, z - 1) == block);
            bitMatrix[1] = world.getBlock(x, y, z - 1) == block && !(world.getBlock(x, y + 1, z - 1) == block);
            bitMatrix[2] = world.getBlock(x + 1, y, z - 1) == block && !(world.getBlock(x + 1, y + 1, z - 1) == block);
            bitMatrix[3] = world.getBlock(x - 1, y, z) == block && !(world.getBlock(x - 1, y + 1, z) == block);
            bitMatrix[4] = world.getBlock(x + 1, y, z) == block && !(world.getBlock(x + 1, y + 1, z) == block);
            bitMatrix[5] = world.getBlock(x - 1, y, z + 1) == block && !(world.getBlock(x - 1, y + 1, z + 1) == block);
            bitMatrix[6] = world.getBlock(x, y, z + 1) == block && !(world.getBlock(x, y + 1, z + 1) == block);
            bitMatrix[7] = world.getBlock(x + 1, y, z + 1) == block && !(world.getBlock(x + 1, y + 1, z + 1) == block);
        }
        if (side == 5)
        {
            bitMatrix[0] = world.getBlock(x - 1, y + 1, z) == block && !(world.getBlock(x - 1, y + 1, z - 1) == block);
            bitMatrix[1] = world.getBlock(x, y + 1, z) == block && !(world.getBlock(x, y + 1, z - 1) == block);
            bitMatrix[2] = world.getBlock(x + 1, y + 1, z) == block && !(world.getBlock(x + 1, y + 1, z - 1) == block);
            bitMatrix[3] = world.getBlock(x - 1, y, z) == block && !(world.getBlock(x - 1, y, z - 1) == block);
            bitMatrix[4] = world.getBlock(x + 1, y, z) == block && !(world.getBlock(x + 1, y, z - 1) == block);
            bitMatrix[5] = world.getBlock(x - 1, y - 1, z) == block && !(world.getBlock(x - 1, y - 1, z - 1) == block);
            bitMatrix[6] = world.getBlock(x, y - 1, z) == block && !(world.getBlock(x, y - 1, z - 1) == block);
            bitMatrix[7] = world.getBlock(x + 1, y - 1, z) == block && !(world.getBlock(x + 1, y - 1, z - 1) == block);
        }
        if (side == 3)
        {
            bitMatrix[0] = world.getBlock(x - 1, y + 1, z) == block && !(world.getBlock(x - 1, y + 1, z + 1) == block);
            bitMatrix[1] = world.getBlock(x, y + 1, z) == block && !(world.getBlock(x, y + 1, z + 1) == block);
            bitMatrix[2] = world.getBlock(x + 1, y + 1, z) == block && !(world.getBlock(x + 1, y + 1, z + 1) == block);
            bitMatrix[3] = world.getBlock(x - 1, y, z) == block && !(world.getBlock(x - 1, y, z + 1) == block);
            bitMatrix[4] = world.getBlock(x + 1, y, z) == block && !(world.getBlock(x + 1, y, z + 1) == block);
            bitMatrix[5] = world.getBlock(x - 1, y - 1, z) == block && !(world.getBlock(x - 1, y - 1, z + 1) == block);
            bitMatrix[6] = world.getBlock(x, y - 1, z) == block && !(world.getBlock(x, y - 1, z + 1) == block);
            bitMatrix[7] = world.getBlock(x + 1, y - 1, z) == block && !(world.getBlock(x + 1, y - 1, z + 1) == block);
        }
        if (side == 4)
        {
            bitMatrix[0] = world.getBlock(x, y + 1, z - 1) == block && !(world.getBlock(x - 1, y + 1, z - 1) == block);
            bitMatrix[1] = world.getBlock(x, y + 1, z) == block && !(world.getBlock(x - 1, y + 1, z) == block);
            bitMatrix[2] = world.getBlock(x, y + 1, z + 1) == block && !(world.getBlock(x - 1, y + 1, z + 1) == block);
            bitMatrix[3] = world.getBlock(x, y, z - 1) == block && !(world.getBlock(x - 1, y, z - 1) == block);
            bitMatrix[4] = world.getBlock(x, y, z + 1) == block && !(world.getBlock(x - 1, y, z + 1) == block);
            bitMatrix[5] = world.getBlock(x, y - 1, z - 1) == block && !(world.getBlock(x - 1, y - 1, z - 1) == block);
            bitMatrix[6] = world.getBlock(x, y - 1, z) == block && !(world.getBlock(x - 1, y - 1, z) == block);
            bitMatrix[7] = world.getBlock(x, y - 1, z + 1) == block && !(world.getBlock(x - 1, y - 1, z + 1) == block);
        }
        if (side == 2)
        {
            bitMatrix[0] = world.getBlock(x, y + 1, z - 1) == block && !(world.getBlock(x + 1, y + 1, z - 1) == block);
            bitMatrix[1] = world.getBlock(x, y + 1, z) == block && !(world.getBlock(x + 1, y + 1, z) == block);
            bitMatrix[2] = world.getBlock(x, y + 1, z + 1) == block && !(world.getBlock(x + 1, y + 1, z + 1) == block);
            bitMatrix[3] = world.getBlock(x, y, z - 1) == block && !(world.getBlock(x + 1, y, z - 1) == block);
            bitMatrix[4] = world.getBlock(x, y, z + 1) == block && !(world.getBlock(x + 1, y, z + 1) == block);
            bitMatrix[5] = world.getBlock(x, y - 1, z - 1) == block && !(world.getBlock(x + 1, y - 1, z - 1) == block);
            bitMatrix[6] = world.getBlock(x, y - 1, z) == block && !(world.getBlock(x + 1, y - 1, z) == block);
            bitMatrix[7] = world.getBlock(x, y - 1, z + 1) == block && !(world.getBlock(x + 1, y - 1, z + 1) == block);
        }
        
        int[] textureRefByID = {0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 16, 16, 20, 20, 16, 16, 28, 28, 21, 21, 46, 42, 21, 21, 43, 38, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 16, 16, 20, 20, 16, 16, 28, 28, 25, 25, 45, 37, 25, 25, 40, 32, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 7, 7, 24, 24, 7, 7, 10, 10, 29, 29, 44, 41, 29, 29, 39, 33, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 7, 7, 24, 24, 7, 7, 10, 10, 8, 8, 36, 35, 8, 8, 34, 11};
        
        int idBuilder = 0;
        for (int i = 0; i < bitMatrix.length; i++)
        {
            idBuilder += (bitMatrix[i] ? (i == 0 ? 1 : (i == 1 ? 2 : (i == 2 ? 4 : (i == 3 ? 8 : (i == 4 ? 16 : (i == 5 ? 32 : (i == 6 ? 64 : (i == 7 ? 128 : 0)))))))) : 0);
        }
        
        return idBuilder > textureRefByID.length - 1 || idBuilder < 0 ? textures[0] : textures[textureRefByID[idBuilder]];
    }
    
    public static IIcon getConnectedIconWithMeta(IBlockAccess world, int x, int y, int z, int side, IIcon[][] textures, Block block)
    {
        int meta = world.getBlockMetadata(x, y, z);
        boolean[] bitMatrix = new boolean[8];
        
        if (side == 0)
        {
            bitMatrix[0] = world.getBlock(x - 1, y, z - 1) == block && !(world.getBlock(x - 1, y - 1, z - 1) == block) && world.getBlockMetadata(x - 1, y, z - 1) == meta;
            bitMatrix[1] = world.getBlock(x, y, z - 1) == block && !(world.getBlock(x, y - 1, z - 1) == block) && world.getBlockMetadata(x, y, z - 1) == meta;
            bitMatrix[2] = world.getBlock(x + 1, y, z - 1) == block && !(world.getBlock(x + 1, y - 1, z - 1) == block) && world.getBlockMetadata(x + 1, y, z - 1) == meta;
            bitMatrix[3] = world.getBlock(x - 1, y, z) == block && !(world.getBlock(x - 1, y - 1, z) == block) && world.getBlockMetadata(x - 1, y, z) == meta;
            bitMatrix[4] = world.getBlock(x + 1, y, z) == block && !(world.getBlock(x + 1, y - 1, z) == block) && world.getBlockMetadata(x + 1, y, z) == meta;
            bitMatrix[5] = world.getBlock(x - 1, y, z + 1) == block && !(world.getBlock(x - 1, y - 1, z + 1) == block) && world.getBlockMetadata(x - 1, y, z + 1) == meta;
            bitMatrix[6] = world.getBlock(x, y, z + 1) == block && !(world.getBlock(x, y - 1, z + 1) == block) && world.getBlockMetadata(x, y, z + 1) == meta;
            bitMatrix[7] = world.getBlock(x + 1, y, z + 1) == block && !(world.getBlock(x + 1, y - 1, z + 1) == block) && world.getBlockMetadata(x + 1, y, z + 1) == meta;
        }
        if (side == 1)
        {
            bitMatrix[0] = world.getBlock(x - 1, y, z - 1) == block && !(world.getBlock(x - 1, y + 1, z - 1) == block) && world.getBlockMetadata(x - 1, y, z - 1) == meta;
            bitMatrix[1] = world.getBlock(x, y, z - 1) == block && !(world.getBlock(x, y + 1, z - 1) == block) && world.getBlockMetadata(x, y, z - 1) == meta;
            bitMatrix[2] = world.getBlock(x + 1, y, z - 1) == block && !(world.getBlock(x + 1, y + 1, z - 1) == block) && world.getBlockMetadata(x + 1, y, z - 1) == meta;
            bitMatrix[3] = world.getBlock(x - 1, y, z) == block && !(world.getBlock(x - 1, y + 1, z) == block) && world.getBlockMetadata(x - 1, y, z) == meta;
            bitMatrix[4] = world.getBlock(x + 1, y, z) == block && !(world.getBlock(x + 1, y + 1, z) == block) && world.getBlockMetadata(x + 1, y, z) == meta;
            bitMatrix[5] = world.getBlock(x - 1, y, z + 1) == block && !(world.getBlock(x - 1, y + 1, z + 1) == block) && world.getBlockMetadata(x - 1, y, z + 1) == meta;
            bitMatrix[6] = world.getBlock(x, y, z + 1) == block && !(world.getBlock(x, y + 1, z + 1) == block) && world.getBlockMetadata(x, y, z + 1) == meta;
            bitMatrix[7] = world.getBlock(x + 1, y, z + 1) == block && !(world.getBlock(x + 1, y + 1, z + 1) == block) && world.getBlockMetadata(x + 1, y, z + 1) == meta;
        }
        if (side == 2)
        {
            bitMatrix[0] = world.getBlock(x - 1, y + 1, z) == block && !(world.getBlock(x - 1, y + 1, z - 1) == block) && world.getBlockMetadata(x - 1, y + 1, z) == meta;
            bitMatrix[1] = world.getBlock(x, y + 1, z) == block && !(world.getBlock(x, y + 1, z - 1) == block) && world.getBlockMetadata(x, y + 1, z) == meta;
            bitMatrix[2] = world.getBlock(x + 1, y + 1, z) == block && !(world.getBlock(x + 1, y + 1, z - 1) == block) && world.getBlockMetadata(x + 1, y + 1, z) == meta;
            bitMatrix[3] = world.getBlock(x - 1, y, z) == block && !(world.getBlock(x - 1, y, z - 1) == block) && world.getBlockMetadata(x - 1, y, z) == meta;
            bitMatrix[4] = world.getBlock(x + 1, y, z) == block && !(world.getBlock(x + 1, y, z - 1) == block) && world.getBlockMetadata(x + 1, y, z) == meta;
            bitMatrix[5] = world.getBlock(x - 1, y - 1, z) == block && !(world.getBlock(x - 1, y - 1, z - 1) == block) && world.getBlockMetadata(x - 1, y - 1, z) == meta;
            bitMatrix[6] = world.getBlock(x, y - 1, z) == block && !(world.getBlock(x, y - 1, z - 1) == block) && world.getBlockMetadata(x, y - 1, z) == meta;
            bitMatrix[7] = world.getBlock(x + 1, y - 1, z) == block && !(world.getBlock(x + 1, y - 1, z - 1) == block) && world.getBlockMetadata(x + 1, y - 1, z) == meta;
        }
        if (side == 3)
        {
            bitMatrix[0] = world.getBlock(x - 1, y + 1, z) == block && !(world.getBlock(x - 1, y + 1, z + 1) == block) && world.getBlockMetadata(x - 1, y + 1, z) == meta;
            bitMatrix[1] = world.getBlock(x, y + 1, z) == block && !(world.getBlock(x, y + 1, z + 1) == block) && world.getBlockMetadata(x, y + 1, z) == meta;
            bitMatrix[2] = world.getBlock(x + 1, y + 1, z) == block && !(world.getBlock(x + 1, y + 1, z + 1) == block) && world.getBlockMetadata(x + 1, y + 1, z) == meta;
            bitMatrix[3] = world.getBlock(x - 1, y, z) == block && !(world.getBlock(x - 1, y, z + 1) == block) && world.getBlockMetadata(x - 1, y, z) == meta;
            bitMatrix[4] = world.getBlock(x + 1, y, z) == block && !(world.getBlock(x + 1, y, z + 1) == block) && world.getBlockMetadata(x + 1, y, z) == meta;
            bitMatrix[5] = world.getBlock(x - 1, y - 1, z) == block && !(world.getBlock(x - 1, y - 1, z + 1) == block) && world.getBlockMetadata(x - 1, y - 1, z) == meta;
            bitMatrix[6] = world.getBlock(x, y - 1, z) == block && !(world.getBlock(x, y - 1, z + 1) == block) && world.getBlockMetadata(x, y - 1, z) == meta;
            bitMatrix[7] = world.getBlock(x + 1, y - 1, z) == block && !(world.getBlock(x + 1, y - 1, z + 1) == block) && world.getBlockMetadata(x + 1, y - 1, z) == meta;
        }
        if (side == 4)
        {
            bitMatrix[0] = world.getBlock(x, y + 1, z - 1) == block && !(world.getBlock(x - 1, y + 1, z - 1) == block) && world.getBlockMetadata(x, y + 1, z - 1) == meta;
            bitMatrix[1] = world.getBlock(x, y + 1, z) == block && !(world.getBlock(x - 1, y + 1, z) == block) && world.getBlockMetadata(x, y + 1, z) == meta;
            bitMatrix[2] = world.getBlock(x, y + 1, z + 1) == block && !(world.getBlock(x - 1, y + 1, z + 1) == block) && world.getBlockMetadata(x, y + 1, z + 1) == meta;
            bitMatrix[3] = world.getBlock(x, y, z - 1) == block && !(world.getBlock(x - 1, y, z - 1) == block) && world.getBlockMetadata(x, y, z - 1) == meta;
            bitMatrix[4] = world.getBlock(x, y, z + 1) == block && !(world.getBlock(x - 1, y, z + 1) == block) && world.getBlockMetadata(x, y, z + 1) == meta;
            bitMatrix[5] = world.getBlock(x, y - 1, z - 1) == block && !(world.getBlock(x - 1, y - 1, z - 1) == block) && world.getBlockMetadata(x, y - 1, z - 1) == meta;
            bitMatrix[6] = world.getBlock(x, y - 1, z) == block && !(world.getBlock(x - 1, y - 1, z) == block) && world.getBlockMetadata(x, y - 1, z) == meta;
            bitMatrix[7] = world.getBlock(x, y - 1, z + 1) == block && !(world.getBlock(x - 1, y - 1, z + 1) == block) && world.getBlockMetadata(x, y - 1, z + 1) == meta;
        }
        if (side == 5)
        {
            bitMatrix[0] = world.getBlock(x, y + 1, z - 1) == block && !(world.getBlock(x + 1, y + 1, z - 1) == block) && world.getBlockMetadata(x, y + 1, z - 1) == meta;
            bitMatrix[1] = world.getBlock(x, y + 1, z) == block && !(world.getBlock(x + 1, y + 1, z) == block) && world.getBlockMetadata(x, y + 1, z) == meta;
            bitMatrix[2] = world.getBlock(x, y + 1, z + 1) == block && !(world.getBlock(x + 1, y + 1, z + 1) == block) && world.getBlockMetadata(x, y + 1, z + 1) == meta;
            bitMatrix[3] = world.getBlock(x, y, z - 1) == block && !(world.getBlock(x + 1, y, z - 1) == block) && world.getBlockMetadata(x, y, z - 1) == meta;
            bitMatrix[4] = world.getBlock(x, y, z + 1) == block && !(world.getBlock(x + 1, y, z + 1) == block) && world.getBlockMetadata(x, y, z + 1) == meta;
            bitMatrix[5] = world.getBlock(x, y - 1, z - 1) == block && !(world.getBlock(x + 1, y - 1, z - 1) == block) && world.getBlockMetadata(x, y - 1, z - 1) == meta;
            bitMatrix[6] = world.getBlock(x, y - 1, z) == block && !(world.getBlock(x + 1, y - 1, z) == block) && world.getBlockMetadata(x, y - 1, z) == meta;
            bitMatrix[7] = world.getBlock(x, y - 1, z + 1) == block && !(world.getBlock(x + 1, y - 1, z + 1) == block) && world.getBlockMetadata(x, y - 1, z + 1) == meta;
        }
        
        int[] textureRefByID = {0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 16, 16, 20, 20, 16, 16, 28, 28, 21, 21, 46, 42, 21, 21, 43, 38, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 16, 16, 20, 20, 16, 16, 28, 28, 25, 25, 45, 37, 25, 25, 40, 32, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 7, 7, 24, 24, 7, 7, 10, 10, 29, 29, 44, 41, 29, 29, 39, 33, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 7, 7, 24, 24, 7, 7, 10, 10, 8, 8, 36, 35, 8, 8, 34, 11};
        int idBuilder = 0;
        
        for (int i = 0; i < bitMatrix.length; i++)
        {
            idBuilder += (bitMatrix[i] ? (i == 0 ? 1 : (i == 1 ? 2 : (i == 2 ? 4 : (i == 3 ? 8 : (i == 4 ? 16 : (i == 5 ? 32 : (i == 6 ? 64 : (i == 7 ? 128 : 0)))))))) : 0);
        }
        
        return idBuilder > textureRefByID.length - 1 || idBuilder < 0 ? textures[meta][0] : textures[meta][textureRefByID[idBuilder]];
    }
}
