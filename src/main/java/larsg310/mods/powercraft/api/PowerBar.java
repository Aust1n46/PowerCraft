package larsg310.mods.powercraft.api;

public class PowerBar
{
	private int maxEnergyLevel = 0;
	private int energyLevel = 0;
	
	public PowerBar(int maxEnergyLevel)
	{
		this.maxEnergyLevel = maxEnergyLevel;
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

}
