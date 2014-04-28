package larsg310.mods.powercraft.energy;

import larsg310.mods.powercraft.api.IEnergy;
import larsg310.mods.powercraft.api.PowerBar;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EnergyNet
{
	/**
	 * A utility method for distributing energy to the surrounding
	 * machines/cables.
	 * 
	 * @param world
	 *            Self-explainatory
	 * @param x
	 *            Self-explainatory
	 * @param y
	 *            Self-explainatory
	 * @param z
	 *            Self-explainatory
	 * @param lastDirection
	 *            The direction the energy was set from last time, so it
	 *            prevents back and forth looping of cables. Please use one of
	 *            the six valid directions ({@link ForgeDirection}), or UNKNOWN
	 *            of you don't have a last direction.
	 * @param powerBar
	 *            The PowerBar object to take the enrgy from.
	 */
	public static void distributeEnergyToSurrounding(World world, int x, int y, int z, ForgeDirection lastDirection, PowerBar powerBar)
	{
		int sides = 0;
		boolean sidesCanOutput[] = new boolean[6];
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			if (world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) instanceof IEnergy)
			{
				IEnergy energyTileNextToIt = (IEnergy) world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
				IEnergy thisEnergyTile = (IEnergy) world.getTileEntity(x, y, z);
				ForgeDirection invertedSide = ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[direction.ordinal()]];
				if (thisEnergyTile.canConnect(invertedSide) && energyTileNextToIt.canAddEnergyOnSide(invertedSide))
				{
					sidesCanOutput[direction.ordinal()] = true;
					sides++;
				}
			}
		}
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			if (sidesCanOutput[direction.ordinal()] && direction != lastDirection)
			{
				IEnergy energyTile = (IEnergy) world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
				if (powerBar.getEnergyLevel() - energyTile.getPowerTransferRate() >= 0 && energyTile.getPowerBar().canAddEnergy(energyTile.getPowerTransferRate()))
				{
					energyTile.getPowerBar().addEnergy(energyTile.getPowerTransferRate());
					powerBar.removeEnergy(energyTile.getPowerTransferRate());
					energyTile.setLastReceivedDirection(ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[direction.ordinal()]]);
				}
			}
		}
	}
	
	/**
	 * A utility method for distributing energy to the surrounding
	 * machines/cables.
	 * 
	 * @param world
	 *            Self-explainatory
	 * @param x
	 *            Self-explainatory
	 * @param y
	 *            Self-explainatory
	 * @param z
	 *            Self-explainatory
	 * @param powerBar
	 *            The PowerBar object to take the enrgy from.
	 */
	public static void distributeEnergyToSurrounding(World world, int x, int y, int z, PowerBar powerBar)
	{
		distributeEnergyToSurrounding(world, x, y, z, ForgeDirection.UNKNOWN, powerBar);
	}
}
