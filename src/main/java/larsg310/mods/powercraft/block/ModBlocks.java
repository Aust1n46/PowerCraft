package larsg310.mods.powercraft.block;

import larsg310.mods.powercraft.lib.Names;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
    public static Block CABLE;
    public static Block ENERGY_STORAGE;
    public static Block POWER_GENERATOR;
    public static Block MACHINES;
    public static Block CONNECTED_GLASS;
    public static Block COMPUTER;
    public static Block METAL_BLOCKS;
    public static Block LIQUIDIZED_MATTER;
    public static Block CONSTRUCTABLE_BLOCK;
    
    public static void init()
    {
        CABLE = new BlockCable();
        ENERGY_STORAGE = new BlockEnergyStorage();
        POWER_GENERATOR = new BlockEnergyGenerator();
        MACHINES = new BlockMachines();
        CONNECTED_GLASS = new BlockConnectedGlass();
        COMPUTER = new BlockComputer();
        METAL_BLOCKS = new BlockMetalBlocks();
        LIQUIDIZED_MATTER = new BlockLiquidizedMatter();
        CONSTRUCTABLE_BLOCK = new BlockConstructableBlock();
        
        GameRegistry.registerBlock(CABLE, ItemBlockStandardMetadata.class, Names.CABLE);
        GameRegistry.registerBlock(ENERGY_STORAGE, ItemBlockStandardMetadata.class, Names.ENERGY_STORAGE);
        GameRegistry.registerBlock(POWER_GENERATOR, ItemBlockStandardMetadata.class, Names.ENERGY_GENERATOR);
        GameRegistry.registerBlock(MACHINES, ItemBlockStandardMetadata.class, Names.MACHINES);
        GameRegistry.registerBlock(CONNECTED_GLASS, ItemBlockStandardMetadata.class, Names.CONNECTED_GLASS);
        GameRegistry.registerBlock(COMPUTER, ItemBlockStandardMetadata.class, Names.COMPUTER);
        GameRegistry.registerBlock(METAL_BLOCKS, ItemBlockStandardMetadata.class, Names.METAL_BLOCKS);
        GameRegistry.registerBlock(LIQUIDIZED_MATTER, Names.LIQUIDIZED_MATTER);
        GameRegistry.registerBlock(CONSTRUCTABLE_BLOCK, Names.CONSTRUCTABLE_BLOCK);
    }
}
