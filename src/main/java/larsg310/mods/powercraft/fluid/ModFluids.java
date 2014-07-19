package larsg310.mods.powercraft.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids
{
	public static Fluid LIQUIDIZED_MATTER;
	
	public static void init()
	{
		LIQUIDIZED_MATTER = new FluidLiquidizedMatter();
		
		FluidRegistry.registerFluid(LIQUIDIZED_MATTER);
	}
}
