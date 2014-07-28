package larsg310.mods.powercraft.fluid;

import larsg310.mods.powercraft.lib.Names;
import net.minecraftforge.fluids.Fluid;

public class FluidLiquidizedMatter extends Fluid
{
    public FluidLiquidizedMatter()
    {
        super(Names.LIQUIDIZED_MATTER);
        this.setViscosity(4000);
        this.setTemperature(1273);
        this.setUnlocalizedName(Names.LIQUIDIZED_MATTER);
    }
}
