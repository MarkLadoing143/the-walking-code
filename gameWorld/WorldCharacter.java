package gameWorld;

public class WorldCharacter extends WorldEntity
{
    //inheriting row, col, displayChar
    private int health;
    private int stumble;
    private String name;
    private int strength;
    private boolean duelWelding;
    private boolean hasWeapon;
    private Weapon weapon1;
    private Weapon weapon2;

    public WorldCharacter(int row, int col, int health, int strength, Weapon weapon) //user
    {
        super(row, col, 'U');
        name = "User";
        this.health = health;
        this.strength = strength;
        hasWeapon = true;
        duelWelding = false;
        if(weapon == null)
        {
            weapon1 = new Weapon("FIST");
        }
        else
        {
            weapon1 = weapon;
        }
        weapon2 = null;
    }

    public WorldCharacter(int row, int col, int health, String name) //rat
    {
        super(row, col, 'R');
        this.health = health;
        this.name = name;
        hasWeapon = false;
        duelWelding = false;
        weapon1 = null;
        weapon2 = null;
    }

    public WorldCharacter(int row, int col, int health, String name, Weapon weapon) //DZ
    {
        super(row, col, 'Z');
        this.health = health;
        this.name = name;
        hasWeapon = true;
        duelWelding = false;
        if(weapon == null)
        {
            weapon1 = new Weapon("FIST");
        }
        else
        {
            weapon1 = weapon;
        }
        weapon2 = null;
    }

    public WorldCharacter(int row, int col, int health, String name, Weapon weapon1, Weapon weapon2) //FZ
    {
        super(row, col, 'F');
        this.health = health;
        this.name = name;
        hasWeapon = true;
        duelWelding = true;
        if(weapon1 == null)
        {
            this.weapon1 = new Weapon("FIST");
        }
        else
        {
            this.weapon1 = weapon1;
        }

        if(weapon2 == null)
        {
            this.weapon2 = new Weapon("FIST");
        }
        else
        {
            this.weapon2 = weapon2;
        }
    }

    public WorldCharacter(int row, int col, int health, String name, int stumble, Weapon weapon) //FSZ
    {
        super(row, col, 'S');
        this.health = health;
        this.name = name;
        this.stumble = stumble;
        if(weapon == null)
        {
            weapon1 = new Weapon("FIST");
        }
        else
        {
            weapon1 = weapon;
        }
    }

    public boolean canAttack()
    {
        return hasWeapon;
    }

    public int attack()
    {
        if(duelWelding == true)
        {
            int attack = weapon1.getDamage() + weapon2.getDamage();
            return attack;
        }
        else
        {
            int attack = weapon1.getDamage();
            return attack;
        }
    }

    public String toString()
    {
        String string = String.format("Row=%2d, Col=%2d, type=%c, name=%s, energy level=%4d", 
                this.getRow(), this.getCol(), this.getDisplayCharacter(), name, health);

        if(weapon1 != null)
        {
            string += ", weapon1=" + weapon1.getName() + " [attackPower=" + weapon1.getDamage() + "]";
        }

        if(weapon2 != null)
        {
            string += ", weapon2=" + weapon2.getName() + " [attackPower=" + weapon2.getDamage() + "]";
        }

        return string;
    }
}
