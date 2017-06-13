/**
 * Represents main Controller for the game
 * System
 * @author Jordan Kidney
 * @version 1.0
 * Last Modified: March 14, 2014 - Created (By Jordan Kidney)
 */

import java.io.*;
import java.util.*;
import userCommunication.*;
import userCommunication.Options.*;
import utility.*;
import gameWorld.*;

public class TheWalkingCodeGame implements Command
{
    //constants for user controls 
    //===Modified to match correct compass directions (ie West is Left; East is Right)===
    public static final String       EAST = "d"; //changed a to d
    public static final String NORTH_EAST = "e"; //changed q to e
    public static final String      NORTH = "w";
    public static final String NORTH_WEST = "q"; //changed e to q
    public static final String       WEST = "a"; //changed d to a
    public static final String SOUTH_WEST = "z"; //changed c to z
    public static final String      SOUTH = "s";
    public static final String SOUTH_EAST = "c";//changed z to c
    public static final String LIST_STATE = "list";
    public static final String[] ALL_ACTIONS = 
        { EAST, NORTH_EAST,NORTH, NORTH_WEST, WEST, SOUTH, SOUTH_WEST, SOUTH_EAST, LIST_STATE};

    public static final String[] ALL_ACTIONS_DES = 
        { "Move East","Move North East","Move North","Move North West", 
            "Move West","Move South","Move South West","Move South East", 
            "List current game state"
        };

    //instance variables
    private CmdLineReader commandLineReader;
    private UserInteraction comm;
    private Shell gameShell;
    private boolean endGame;
    private int user_moves_count;
    private WorldCharacter user;
    private EndPoint endPoint;
    private ArrayList<WorldCharacter> enemies;
    private ArrayList<Wall> walls;
    private GridWorld world;

    public TheWalkingCodeGame()
    {
        comm = new UserInteraction();
        endGame = false;
        user_moves_count=0;
    }

    /**
     * Startup method for the game
     * @param args command line arguments passed to the program
     */
    public void run(String[] args) throws FileNotFoundException
    {
        String fileName = null;
        setUp(args);
        BooleanOption debug = (BooleanOption) commandLineReader.getOption("debug");
        StringOption file = (StringOption) commandLineReader.getOption("file");

        comm.setDebug(true); // here for testing set to false to turn off debug messages at the start

        if(file.isSet()) fileName = file.getValue();
        else
            fileName = comm.getInput_String("Enter game file name: ");  

        if( loadGameFile(fileName) )
        {
            gameShell.showStartMessage();

            do
            {
                printWorld();
                Option userAction = gameShell.runSingleCommand();

                if(userAction != null)
                {
                    switch(userAction.getName().toLowerCase())
                    {
                        case "debug": comm.setDebug( debug.getValue() ); 
                        GameRandomNumber.setDebug(debug.getValue() ); 
                        break; // in case the debug value changed in the shell
                        case  "grid": world.setPrintGrid( ((BooleanOption)userAction).getValue()); break;                      
                    }
                }
                else if(gameShell.getEnd() == true) endGame = true;
            }
            while(!endGame);
            comm.println("Exiting game");
        }
        else
        {
            comm.println("Unable to open the game file");
        }
    }

    /**
     * 
     */
    private void printWorld()
    {
        comm.clearBlueJTerminal();
        comm.println("User move count: " + user_moves_count);
        world.print(comm);
    }

    /**
     * sets up all basic objects for the beginning of the game
     * @param args command line arguments passed to the program
     */
    private void setUp(String[] args)
    {
        setUpCommandLineReader();
        setUpShell();
        gameShell.conectCmdLineOptions(commandLineReader);
        commandLineReader.checkCmdLine(args);
    }

    /**
     * sets up all basic information for the command line reader
     */
    private void setUpCommandLineReader()
    {
        commandLineReader = new CmdLineReader();
        commandLineReader.addOption( new BooleanOption("Debug","Control output of debug messages (arg=TRUE/FALSE)") );
        commandLineReader.addOption( new StringOption("file", "control startup file (arg=filename)") ); 
    }

    /**
     * Sets up all basic information and commands used for the shell interface
     */
    private void setUpShell()
    {
        gameShell = new Shell(comm);

        gameShell.addOption( new BooleanOption("grid", "turn the gird on and off ( eg grid true ")  );

        for(int index=0; index < ALL_ACTIONS.length; index++)
        {
            gameShell.addOption(new StringExecCmdOption(ALL_ACTIONS[index], ALL_ACTIONS_DES[index], this) );
        }
    }

    /**
     * Called by the shell when a user specific command is executed by the shell
     * @param opt the command that was executed by the user
     */
    public void execute(Option opt) 
    {
        String command = opt.getName().toLowerCase();
        comm.println_debug("Command: " + command);

        //execute the users requested action
        switch(command)
        {
            case       EAST: 
            case NORTH_EAST: 
            case      NORTH: 
            case NORTH_WEST: 
            case       WEST:
            case SOUTH_WEST: 
            case      SOUTH:
            case SOUTH_EAST: 
            runUserMoveAction(command); 

            break;
            case LIST_STATE: listGameState(); break;
        }

    }

    /**
     * Called to execute a move action from the user
     * @param direction
     */
    private void runUserMoveAction(String direction) //FIX: character's row/col being updated; not on the board though
    {
        // TODO: COMPLETE THIS METHOD AND REMOVE THIS COMMENT 
        switch(direction)
        {
            case       EAST: 
            comm.println("Move East");
            boolean moved = world.moveEast(user);
            if(moved == false)
            {
                comm.println("===UNABLE TO MOVE===");
            }
            else
            {
                endGame = endPoint.finish(user);
                if(endGame == true)
                {
                    comm.println("===WINNER! END POINT HAS BEEN REACHED===");
                }
            }
            moveEnemies();
            break;

            case NORTH_EAST: 
            comm.println("Move North East");
            moved = world.moveNE(user);
            if(moved == false)
            {
                comm.println("===UNABLE TO MOVE===");
            }
            else
            {
                endGame = endPoint.finish(user);
                if(endGame == true)
                {
                    comm.println("===WINNER! END POINT HAS BEEN REACHED===");
                }
            }
            moveEnemies();
            break;

            case      NORTH: 
            comm.println("Move North");
            moved = world.moveNorth(user);
            if(moved == false)
            {
                comm.println("===UNABLE TO MOVE===");
            }
            else
            {
                endGame = endPoint.finish(user);
                if(endGame == true)
                {
                    comm.println("===WINNER! END POINT HAS BEEN REACHED===");
                }
            }
            moveEnemies();
            break;

            case NORTH_WEST: 
            comm.println("Move North West");
            moved = world.moveNW(user);
            if(moved == false)
            {
                comm.println("===UNABLE TO MOVE===");
            }
            else
            {
                endGame = endPoint.finish(user);
                if(endGame == true)
                {
                    comm.println("===WINNER! END POINT HAS BEEN REACHED===");
                }
            }
            moveEnemies();
            break;

            case       WEST: 
            comm.println("Move West");
            moved = world.moveWest(user);
            if(moved == false)
            {
                comm.println("===UNABLE TO MOVE===");
            }
            else
            {
                endGame = endPoint.finish(user);
                if(endGame == true)
                {
                    comm.println("===WINNER! END POINT HAS BEEN REACHED===");
                }
            }
            moveEnemies();
            break;

            case SOUTH_WEST: 
            comm.println("Move South West");
            moved = world.moveSW(user);
            if(moved == false)
            {
                comm.println("===UNABLE TO MOVE===");
            }
            else
            {
                endGame = endPoint.finish(user);
                if(endGame == true)
                {
                    comm.println("===WINNER! END POINT HAS BEEN REACHED===");
                }
            }
            moveEnemies();
            break;

            case SOUTH_EAST: 
            comm.println("Move South East");
            moved = world.moveSE(user);
            if(moved == false)
            {
                comm.println("===UNABLE TO MOVE===");
            }
            else
            {
                endGame = endPoint.finish(user);
                if(endGame == true)
                {
                    comm.println("===WINNER! END POINT HAS BEEN REACHED===");
                }
            }
            moveEnemies();
            break;

            case      SOUTH: 
            comm.println("Move South");
            moved = world.moveSouth(user);
            if(moved == false)
            {
                comm.println("===UNABLE TO MOVE===");
            }
            else
            {
                endGame = endPoint.finish(user);
                if(endGame == true)
                {
                    comm.println("===WINNER! END POINT HAS BEEN REACHED===");
                }
            }
            moveEnemies();
            break;
        }

        user_moves_count++;
    }

    private void moveEnemies()
    {
        GameRandomNumber grn = GameRandomNumber.getInstance();
        grn.setDebug(true);
        for(int index = 0; index < enemies.size(); index++)
        {
            int moveNum = grn.next();
            switch(moveNum)
            {
                case 0:
                case 1:
                world.moveNorth(enemies.get(index));
                break;
                case 2:
                world.moveEast(enemies.get(index));
                break;
                case 3:
                world.moveSouth(enemies.get(index));
                break;
                case 4:
                world.moveWest(enemies.get(index));
                break;
                case 5:
                world.moveNE(enemies.get(index));
                break;
                case 6:
                world.moveNW(enemies.get(index));
                break;
                case 7:
                world.moveSE(enemies.get(index));
                break;
                case 8:
                case 9:
                world.moveSW(enemies.get(index));
                break;
            }
        }
    }

    /**
     * lists out the current state of all game objects
     */
    private void listGameState()
    {
        comm.println_debug("Executing listGameState() ....");
        System.out.println(endPoint);
        System.out.println(user);
        for(int index = 0; index < walls.size(); index++)
        {
            System.out.println(walls.get(index));
        }

        for(int index = 0; index < enemies.size(); index++)
        {
            System.out.println(enemies.get(index));
        }
        comm.pause();
    }

    /**
     * loads in all data from the game file
     * @param fileName the file to load data from
     * @return true if file loaded properly, false otherwise
     */
    private boolean loadGameFile(String fileName)
    {      
        String[] brokenNames = fileName.split("\\.");
        File file = new File("input/" + brokenNames[0] + ".txt");
        try
        {
            GameFileReader fileReader = new GameFileReader(fileName);
            if(fileReader.fileError() == true)
            {
                throw new IllegalArgumentException();
            }
            world = new GridWorld(fileReader.worldRow(), fileReader.worldCol());

            user = fileReader.getUser();
            world.place(user);

            endPoint = fileReader.getEnd();
            world.place(endPoint);

            if(fileReader.getEnemies().size() > 0)
            {
                enemies = fileReader.getEnemies();
            }
            else
            {
                enemies = new ArrayList<WorldCharacter>();
            }

            if(fileReader.getWalls().size() > 0)
            {
                walls = fileReader.getWalls();
            }
            else
            {
                walls = new ArrayList<Wall>();
            }

            for(int index = 0; index < enemies.size(); index++)
            {
                WorldEntity oldObject = world.place( enemies.get(index) );
                if(oldObject != null)
                {
                    throw new ObjectOverlapException();
                }
            }

            for(int index = 0; index < walls.size(); index++)
            {
                WorldEntity oldObject = world.place( walls.get(index) );
                if(oldObject != null)
                {
                    throw new ObjectOverlapException();
                }
            }

            world.setPrintGrid(false);
            return true;
        }
        catch(FileNotFoundException error)
        {
            System.out.println("===FILE ERROR: FILE NOT FOUND===");
            System.out.println("PLEASE DOUBLE-CHECK THAT ENTERED FILENAME IS CORRECT===");
            return false;
        }
        catch(IllegalArgumentException error)
        {
            System.out.println("===FILE ERROR: MISSING INFORMATION / ILL-FORMATTING===");
            System.out.println("===PLEASE DOUBLE-CHECK FOR INCORRECT AND/OR MISSING INFORMATION IN FILE===");
            return false;
        }
        catch(ObjectOverlapException error)
        {
            System.out.println("===FILE ERROR: MORE THAN ONE OBJECT PLACED IN SAME SPOT===");
            System.out.println("===PLEASE DOUBLE-CHECK ALL CHARACTER POSITIONINGS ARE DIFFERENT IN FILE===");
            return false;
        }
    }
}
