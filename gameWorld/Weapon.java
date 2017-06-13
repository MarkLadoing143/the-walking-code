package gameWorld;

public class Weapon
{
    private String name;
    private int power;
    private int weight;
    private int attackDamage;

    public Weapon(String name)
    {
        this.name = name;
        power = 1;
        attackDamage = power;
        weight = 0;
    }
    
    public Weapon(String name, int power)
    {
        this.name = name;
        this.power = power;
        switch(name)
        {
            case "THESHOTGUN":
            attackDamage = 666 * power;
            break;
            case "CROWBAR":
            attackDamage = power;
            break;
            case "CHAINSAW":
            attackDamage = 2 * power;
            break;
        }
        weight = 0;
    }
    
    public Weapon(String name, int power, int weight)
    {
        this.name = name;
        this.power = power;
        this.weight = weight;
        attackDamage = power * weight;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getPower()
    {
        return power;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public int getDamage()
    {
        return attackDamage;
    }
}
