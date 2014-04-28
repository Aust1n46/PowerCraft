package larsg310.mods.powercraft.energy;

import larsg310.mods.powercraft.api.IEnergy;
import larsg310.mods.powercraft.api.PowerBar;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EnergyNet
{
	public static void distributeEnergyToSurrounding(World world, int x, int y, int z, ForgeDirection lastDirection, PowerBar powerBar)
	{
		int sides = 0;
		boolean sidesCanOutput[] = new boolean[6];
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			if (world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) instanceof IEnergy && ((IEnergy) world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ)).canAddEnergyOnSide(ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[direction.ordinal()]]))
			{
				sidesCanOutput[direction.ordinal()] = true;
				sides++;
			}
		}
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			if (sidesCanOutput[direction.ordinal()] && direction != lastDirection)
			{
				if(((IEnergy)world.getTileEntity(x+direction.offsetX, y+direction.offsetY, z+direction.offsetZ)).getPowerBar().canAddEnergy(powerBar.getEnergyLevel() / sides))
				{
					((IEnergy)world.getTileEntity(x+direction.offsetX, y+direction.offsetY, z+direction.offsetZ)).getPowerBar().addEnergy(powerBar.getEnergyLevel() / sides);
					powerBar.removeEnergy(powerBar.getEnergyLevel() / sides);
				}
			}
		}
	}
}
