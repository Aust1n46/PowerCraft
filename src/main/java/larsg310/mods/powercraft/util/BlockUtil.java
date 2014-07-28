package larsg310.mods.powercraft.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;

public class BlockUtil
{
    public static final Block[] vanillaBlocks = new Block[] {Blocks.stone, Blocks.grass, Blocks.dirt, Blocks.cobblestone, Blocks.planks, Blocks.sapling, Blocks.bedrock, Blocks.water, Blocks.lava, Blocks.sand, Blocks.gravel, Blocks.gold_ore, Blocks.iron_ore, Blocks.coal_ore, Blocks.log, Blocks.leaves, Blocks.sponge, Blocks.glass, Blocks.lapis_ore, Blocks.lapis_block, Blocks.dispenser, Blocks.sandstone, Blocks.noteblock, Blocks.bed, Blocks.golden_rail, Blocks.detector_rail, Blocks.sticky_piston, Blocks.web, Blocks.tallgrass, Blocks.deadbush, Blocks.piston, Blocks.wool, Blocks.red_flower, Blocks.brown_mushroom, Blocks.gold_block, Blocks.iron_block, Blocks.stone_slab, Blocks.brick_block, Blocks.tnt, Blocks.bookshelf, Blocks.mossy_cobblestone, Blocks.obsidian, Blocks.torch, Blocks.fire, Blocks.mob_spawner, Blocks.oak_stairs, Blocks.chest, Blocks.diamond_ore, Blocks.diamond_block, Blocks.crafting_table, Blocks.farmland, Blocks.furnace, Blocks.standing_sign, Blocks.wooden_door, Blocks.ladder, Blocks.rail, Blocks.lever, Blocks.iron_door, Blocks.wooden_pressure_plate, Blocks.redstone_ore, Blocks.redstone_torch, Blocks.snow_layer, Blocks.ice, Blocks.snow, Blocks.cactus, Blocks.clay, Blocks.reeds, Blocks.jukebox, Blocks.fence, Blocks.pumpkin, Blocks.netherrack, Blocks.soul_sand, Blocks.glowstone, Blocks.cake, Blocks.unpowered_repeater, Blocks.trapdoor, Blocks.stonebrick, Blocks.iron_bars, Blocks.glass_pane, Blocks.melon_block, Blocks.vine, Blocks.fence_gate, Blocks.mycelium, Blocks.waterlily, Blocks.nether_brick, Blocks.nether_wart, Blocks.enchanting_table, Blocks.brewing_stand, Blocks.cauldron, Blocks.end_portal, Blocks.end_portal_frame, Blocks.end_stone, Blocks.dragon_egg, Blocks.redstone_lamp, Blocks.wooden_slab, Blocks.cocoa, Blocks.emerald_ore, Blocks.ender_chest, Blocks.tripwire_hook, Blocks.tripwire, Blocks.emerald_block, Blocks.command_block, Blocks.beacon, Blocks.cobblestone_wall, Blocks.flower_pot, Blocks.wooden_button, Blocks.skull, Blocks.anvil, Blocks.trapped_chest, Blocks.light_weighted_pressure_plate, Blocks.unpowered_repeater, Blocks.daylight_detector, Blocks.redstone_block, Blocks.quartz_ore, Blocks.hopper, Blocks.quartz_block, Blocks.activator_rail, Blocks.dropper, Blocks.stained_hardened_clay, Blocks.hay_block, Blocks.carpet, Blocks.hardened_clay, Blocks.coal_block, Blocks.packed_ice, Blocks.stained_glass};
    
    public static int determineMetadataBasedOnPlayerOrientation(EntityLivingBase player)
    {
        int rotation = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (rotation == 0)
        {
            return 2;
        }
        if (rotation == 1)
        {
            return 5;
        }
        if (rotation == 2)
        {
            return 3;
        }
        if (rotation == 3)
        {
            return 4;
        }
        return 3;
    }
}
