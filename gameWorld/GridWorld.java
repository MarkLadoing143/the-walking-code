package gameWorld;

/**
 * Represents the Grid World used in the game
 * @author Jordan Kidney
 * @version 1.0
 * Last Modified: March 14, 2014 - Created (By Jordan Kidney)
 */

import userCommunication.*;

public class GridWorld 
{  
    //instance variables
    private WorldEntity[][] grid;
    private boolean printGrid;

    /**
     * Constructor, sets up the blank world
     * @param numRows the number of rows for the world
     * @param numCols the number of columns for the world
     * @throws NumberFormatException if either value is zero or negative
     */
    public GridWorld(int numRows, int numCols) throws NumberFormatException
    {
        if(numRows <= 0 || numCols <= 0)
            throw new NumberFormatException();

        grid = new WorldEntity[numRows][numCols];
        printGrid = false;
    }

    public void setPrintGrid(boolean value) { printGrid = value; }

    /**
     * Determines if the given row and col value are within bounds
     * of the grid world
     * @param row
     * @param col
     * @return true for pass, false for fail
     */
    public boolean inBounds(int row, int col)
    {
        return (row >= 0 && row < grid.length) && (col >= 0 && col < grid[row].length); 
    }

    /**
     * Determines if the given Entity is within bounds
     * @param thing
     * @return
     */
    public boolean inBounds(WorldEntity thing)
    {
        return inBounds(thing.getRow(), thing.getCol());
    }

    public boolean isEmpty(int row, int col)
    {
        boolean locationEmpty = false;
        if(grid[row][col] == null)
        {
            locationEmpty = true;
        }

        return locationEmpty;
    }

    /**
     * Places the entity at the location it is marked to appear at. If any
     * other entity is at the location already it will be returned. 
     * @param thing the entity to place in the world
     * @return null for no past value or the previous entity is returned
     */
    public WorldEntity place(WorldEntity thing)
    {
        WorldEntity oldObjectAtLocation = null;

        if(inBounds(thing))
        {
            oldObjectAtLocation = grid[thing.getRow()][thing.getCol()];
            grid[thing.getRow()][thing.getCol()] = thing;
        }
        return oldObjectAtLocation;
    }

    /**
     * Places the entity at the given location. If any
     * other entity is at the location already it will be returned. 
     * @param row
     * @param col
     * @param thing the entity to place in the world
     * @return null for no past value or the previous entity is returned
     */
    public WorldEntity place(int row, int col,WorldEntity thing)
    {
        WorldEntity oldObjectAtLocation = null;

        if(inBounds(row,col))
        {
            oldObjectAtLocation = grid[row][col];
            grid[row][col] = thing;
        }
        return oldObjectAtLocation;
    }

    public WorldEntity remove(WorldEntity thing)
    {
        WorldEntity oldObjectAtLocation = null;
        int row = thing.getRow();
        int col = thing.getCol();
        if(inBounds(row,col))
        {
            oldObjectAtLocation = grid[row][col];
            grid[row][col] = null;
        }
        return oldObjectAtLocation;
    }

    public WorldEntity remove(int row, int col)
    {
        WorldEntity oldObjectAtLocation = null;
        if(inBounds(row,col))
        {
            oldObjectAtLocation = grid[row][col];
            grid[row][col] = null;
        }
        return oldObjectAtLocation;
    }

    public boolean moveNorth(WorldEntity thing)
    {
        boolean moved = false;
        int newRow = thing.getRow();
        int newCol = thing.getCol();
        if(thing.getDisplayCharacter() == 'F')
        {
            newRow -= 2;
        }
        else
        {
            newRow--;
        }

        if(inBounds(newRow,newCol) && isEmpty(newRow,newCol))
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        else if(inBounds(newRow,newCol) && !isEmpty(newRow,newCol) && grid[newRow][newCol].canMoveOnTo() == true)
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        return moved;
    }

    public boolean moveSouth(WorldEntity thing)
    {
        boolean moved = false;
        int newRow = thing.getRow();
        int newCol = thing.getCol();
        if(thing.getDisplayCharacter() == 'F')
        {
            newRow += 2;
        }
        else
        {
            newRow++;
        }

        if(inBounds(newRow,newCol) && isEmpty(newRow,newCol))
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        else if(inBounds(newRow,newCol) && !isEmpty(newRow,newCol) && grid[newRow][newCol].canMoveOnTo() == true)
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        //else if() finish for SZ and Rat
        //else if() for FZ to move one if path blocked
        return moved;
    }

    public boolean moveEast(WorldEntity thing)
    {
        boolean moved = false;
        int newRow = thing.getRow();
        int newCol = thing.getCol();
        if(thing.getDisplayCharacter() == 'F')
        {
            newCol += 2;
        }
        else
        {
            newCol++;
        }

        if(inBounds(newRow,newCol) && isEmpty(newRow,newCol))
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        else if(inBounds(newRow,newCol) && !isEmpty(newRow,newCol) && grid[newRow][newCol].canMoveOnTo() == true)
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        return moved;
    }

    public boolean moveWest(WorldEntity thing)
    {
        boolean moved = false;
        int newRow = thing.getRow();
        int newCol = thing.getCol();
        if(thing.getDisplayCharacter() == 'F')
        {
            newCol -= 2;
        }
        else
        {
            newCol--;
        }

        if(inBounds(newRow,newCol) && isEmpty(newRow,newCol))
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        else if(inBounds(newRow,newCol) && !isEmpty(newRow,newCol) && grid[newRow][newCol].canMoveOnTo() == true)
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        return moved;
    }

    public boolean moveNE(WorldEntity thing)
    {
        boolean moved = false;
        int newRow = thing.getRow();
        int newCol = thing.getCol();
        if(thing.getDisplayCharacter() == 'F')
        {
            newRow -= 2;
            newCol += 2;
        }
        else
        {
            newRow--;
            newCol++;
        }

        if(inBounds(newRow,newCol) && isEmpty(newRow,newCol))
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        else if(inBounds(newRow,newCol) && !isEmpty(newRow,newCol) && grid[newRow][newCol].canMoveOnTo() == true)
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        return moved;
    }

    public boolean moveNW(WorldEntity thing)
    {
        boolean moved = false;
        int newRow = thing.getRow();
        int newCol = thing.getCol();
        if(thing.getDisplayCharacter() == 'F')
        {
            newRow -= 2;
            newCol -= 2;
        }
        else
        {
            newRow--;
            newCol--;
        }

        if(inBounds(newRow,newCol) && isEmpty(newRow,newCol))
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        else if(inBounds(newRow,newCol) && !isEmpty(newRow,newCol) && grid[newRow][newCol].canMoveOnTo() == true)
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        return moved;
    }

    public boolean moveSE(WorldEntity thing)
    {
        boolean moved = false;
        int newRow = thing.getRow();
        int newCol = thing.getCol();
        if(thing.getDisplayCharacter() == 'F')
        {
            newRow += 2;
            newCol += 2;
        }
        else
        {
            newRow++;
            newCol++;
        }

        if(inBounds(newRow,newCol) && isEmpty(newRow,newCol))
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        else if(inBounds(newRow,newCol) && !isEmpty(newRow,newCol) && grid[newRow][newCol].canMoveOnTo() == true)
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        return moved;
    }

    public boolean moveSW(WorldEntity thing)
    {
        boolean moved = false;
        int newRow = thing.getRow();
        int newCol = thing.getCol();
        if(thing.getDisplayCharacter() == 'F')
        {
            newRow += 2;
            newCol -= 2;
        }
        else
        {
            newRow++;
            newCol--;
        }

        if(inBounds(newRow,newCol) && isEmpty(newRow,newCol))
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        else if(inBounds(newRow,newCol) && !isEmpty(newRow,newCol) && grid[newRow][newCol].canMoveOnTo() == true)
        {
            grid[thing.getRow()][thing.getCol()] = null;
            grid[newRow][newCol] = thing;
            thing.setLocation(newRow,newCol);
            moved = true;
        }
        return moved;
    }

    /**
     * Prints the current state of the world out to the console 
     */
    public void print(UserInteraction comm)
    {
        String line = "";  
        String hBar = "-";
        String vBar = "|";
        String out = " ";
        for(int count=0; count < grid[0].length; count++)
        {
            line += hBar;
            if(printGrid) line += hBar;
        }

        comm.println(" "+line);
        for(int row=0; row < grid.length; row++)
        {
            comm.print(vBar);
            for(int col=0; col < grid[row].length; col++)
            {
                out = " ";

                if(grid[row][col] != null)
                    out = ""+grid[row][col].getDisplayCharacter();

                comm.print(""+out);

                if(printGrid && col !=  grid[row].length-1 )
                    comm.print(vBar); 
            }

            comm.println(vBar);

            if(printGrid && row !=  grid.length-1)
                comm.println(" "+line);
        }

        comm.println(" "+line);
    }

}
