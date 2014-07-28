package larsg310.mods.powercraft.lib;

public enum Energy
{
    WATT("Watt", "W");
    
    private String name;
    private String symbol;
    
    private Energy(String name, String symbol)
    {
        this.name = name;
        this.symbol = symbol;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getSymbol()
    {
        return symbol;
    }
    
    public String getPlural()
    {
        return name + "s";
    }
}
