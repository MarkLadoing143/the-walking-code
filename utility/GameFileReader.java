package utility;

import java.io.*;
import java.util.*;
import gameWorld.*;
public class GameFileReader
{
    private Scanner fileReader;
    private File file;
    private boolean fileError;

    private int worldRowSize;
    private int worldColSize;
    private int endPointRow;
    private int endPointCol;

    private WorldCharacter user;
    private EndPoint end;
    private ArrayList<Wall> walls;
    private ArrayList<WorldCharacter> enemies;

    public GameFileReader(String fileName) throws FileNotFoundException
    {
        String[] brokenNames = fileName.split("\\.");
        file = new File("input/" + brokenNames[0] + ".txt");

        try
        {
            fileReader = new Scanner(file);
            //add code around here to remove all whitespace in file on April 4
            //place all worlds to upper case in file (string.touppercase)

            String[] worldSize = fileReader.nextLine().split(",");
            worldRowSize = Integer.parseInt(worldSize[0]);
            worldColSize = Integer.parseInt(worldSize[1]);

            String[] endPoint = fileReader.nextLine().split(",");
            endPointRow = Integer.parseInt(endPoint[0]);
            endPointCol = Integer.parseInt(endPoint[1]);
            end = new EndPoint(endPointRow, endPointCol);

            String[] userInfo = fileReader.nextLine().split(",");
            int userRow = Integer.parseInt(userInfo[0]);
            int userCol = Integer.parseInt(userInfo[1]);
            int userHealth = Integer.parseInt(userInfo[2]);
            int userStrength = Integer.parseInt(userInfo[3]);

            Weapon userWeapon = null;
            if(!fileReader.hasNextInt())
            {
                userWeapon = buildWeapon();
            }
            user = new WorldCharacter(userRow, userCol, userHealth, userStrength, userWeapon);

            walls = new ArrayList<Wall>();
            enemies = new ArrayList<WorldCharacter>();
            while (fileReader.hasNextLine())
            {
                String[] objectData = fileReader.nextLine().split(",");
                int objectRow = Integer.parseInt(objectData[0]);
                int objectCol = Integer.parseInt(objectData[1]);
                String objectType = objectData[2];

                switch(objectType)
                {
                    case "WALL":
                    walls.add(new Wall(objectRow, objectCol));
                    break;

                    case "R":                    
                    String name = objectData[3];
                    int health = Integer.parseInt(objectData[4]);
                    enemies.add(new WorldCharacter(objectRow, objectCol, health, name));
                    break;

                    case "DZ":
                    name = objectData[3];
                    health = Integer.parseInt(objectData[4]);
                    Weapon weapon = null;
                    if(!fileReader.hasNextInt())
                    {
                        weapon = buildWeapon();
                    }
                    enemies.add(new WorldCharacter(objectRow, objectCol, health, name, weapon));
                    break;

                    case "FZ":
                    name = objectData[3];
                    health = Integer.parseInt(objectData[4]);
                    int numWeapons = Integer.parseInt(objectData[5]);
                    Weapon weapon1 = null;
                    Weapon weapon2 = null;
                    if(!fileReader.hasNextInt() && numWeapons == 1)
                    {
                        weapon1 = buildWeapon();
                    }   
                    else if(!fileReader.hasNextInt() && numWeapons == 2)
                    {
                        weapon1 = buildWeapon();
                        weapon2 = buildWeapon();
                    }
                    enemies.add(new WorldCharacter(objectRow, objectCol, health, name, weapon1, weapon2));
                    break;

                    case "FSZ":
                    name = objectData[3];
                    health = Integer.parseInt(objectData[4]);
                    weapon = null;
                    if(!fileReader.hasNextInt())
                    {
                        weapon = buildWeapon();
                    }
                    int stumble = Integer.parseInt(objectData[5]);
                    enemies.add(new WorldCharacter(objectRow, objectCol, health, name, stumble, weapon));
                    break;
                }
            }

            fileError = false;
        }
        catch(IllegalArgumentException error)
        {
            fileError = true;
        }
        catch(IndexOutOfBoundsException error)
        {
            fileError = true;
        }
    }

    private Weapon buildWeapon()
    {   
        String[] weaponData = fileReader.nextLine().split(",");
        String weaponName = weaponData[0];

        Weapon weapon = null;
        switch(weaponName)
        {
            case "FIST":
            weapon = new Weapon(weaponName);
            break;
            case "BASEBALLBAT":
            int weaponPower = Integer.parseInt(weaponData[1]);
            int weaponWeight = Integer.parseInt(weaponData[2]);
            weapon = new Weapon(weaponName, weaponPower, weaponWeight);
            break;
            default:
            weaponPower = Integer.parseInt(weaponData[1]);
            weapon = new Weapon(weaponName, weaponPower);
            break;
        }

        return weapon;
    }

    public boolean fileError()
    {
        return fileError;
    }

    public void listData()
    {
        System.out.println(end);
        System.out.println(user);
        for(int index = 0; index < enemies.size(); index++)
        {
            System.out.println(enemies.get(index));
        }     

        for(int index = 0; index < walls.size(); index++)
        {
            System.out.println(walls.get(index));
        }
    }

    public WorldCharacter getUser()
    {
        return user;
    }
    
    public EndPoint getEnd()
    {
        return end;
    }

    public ArrayList<WorldCharacter> getEnemies()
    {
        return enemies;
    }

    public ArrayList<Wall> getWalls()
    {
        return walls;
    }

    public int worldRow()
    {
        return worldRowSize;
    }

    public int worldCol()
    {
        return worldColSize;
    }
}
