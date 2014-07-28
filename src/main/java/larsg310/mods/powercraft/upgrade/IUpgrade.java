package larsg310.mods.powercraft.upgrade;

public interface IUpgrade
{
    public Upgrade getUpgrade(int meta);
    
    public int getUpgradeWorthness(Upgrade upgrade, int meta);
}
