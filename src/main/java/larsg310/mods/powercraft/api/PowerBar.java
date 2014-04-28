package larsg310.mods.powercraft.api;

import net.minecraft.nbt.NBTTagCompound;

public class PowerBar
{
	private int maxEnergyLevel = 0;
	private int energyLevel = 0;
	
	public PowerBar(int maxEnergyLevel, boolean shouldStartOfWithMaxEnergy)
	{
		this.maxEnergyLevel = maxEnergyLevel;
		if (shouldStartOfWithMaxEnergy) this.energyLevel = maxEnergyLevel;
	}
	
	public PowerBar(int maxEnergyLevel)
	{
		this(maxEnergyLevel, false);
	}
	
	public void addEnergy(int amount)
	{
		energyLevel += amount;
	}
	
	public void removeEnergy(int amount)
	{
		energyLevel -= amount;
	}
	
	public boolean canAddEnergy(int amount)
	{
		return (energyLevel + amount) <= maxEnergyLevel;
	}
	
	public boolean canRemoveEnergy(int amount)
	{
		return (energyLevel - amount) >= 0;
	}
	
	public int getMaxEnergyLevel()
	{
		return maxEnergyLevel;
	}
	
	public void setEnergyLevel(int amount)
	{
		energyLevel = amount;
	}
	
	public int getEnergyLevel()
	{
		return energyLevel;
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setInteger("energyLevel", energyLevel);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		this.energyLevel = tag.getInteger("energyLevel");
	}
}
