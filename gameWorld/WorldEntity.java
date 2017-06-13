package gameWorld;
/**
 * Represents root of inheritance tree 
 * @author Jordan Kidney
 * @version 1.0
 * Last Modified: March 14, 2014 - Created (By Jordan Kidney)
 */

public class WorldEntity 
{
    private int row; // row location of the object in the grid world
    private int col; // column location of the object in the world
    private boolean canMoveOnTo; // used to indicate if another object is allowed to
    // stand on, or be located at the same location
    // as the current object
    // false = no, true = yes
    private char displayCharacter;  // The character used to display the object

    /**
     * default constructor
     * @param row the row location of the object
     * @param col the column location of the object
     * @param displayCharacter the character used to display the object
     */
    public WorldEntity(int row, int col, char displayCharacter)
    {
        this(row, col, displayCharacter, false);	  
    }

    /**
     * Constructor
     * @param row the row location of the object
     * @param col the column location of the object
     * @param canMoveOnTo if the object can be moved onto or not
     */
    public WorldEntity(int row, int col, char displayCharacter, boolean canMoveOnTo)
    {
        setLocation(row,col);
        this.canMoveOnTo = canMoveOnTo; 
        this.displayCharacter = displayCharacter;
    }

    /**
     * used to indicate if another Entity is allowed to
     * stand on, or be located at the same location
     * as the current entity
     * @return true = yes, false = no
     */
    public boolean canMoveOnTo() { return canMoveOnTo; }

    public int getRow() { return row; }

    public void setRow(int value) { row = value; }

    public int getCol() { return col; }

    public void setCol(int value) { col = value; }

    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    /**
     * Determines if the current entity is occupying the same location as the given entity
     * @param entity
     * @return true of occupy same location, false otherwise
     */
    public boolean isStandingOn(WorldEntity entity)
    {
        return (row==entity.row) && (col==entity.col);
    }

    public char getDisplayCharacter() { return displayCharacter; }

    public void setDisplayCharacter(char displayCharacter) 
    { this.displayCharacter = displayCharacter; }

    @Override
    public String toString() 
    {
        return String.format("row=%2d, col=%2d, type="+displayCharacter,row,col);
    }
}
