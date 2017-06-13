package gameWorld;

public class EndPoint extends WorldEntity
{
    private boolean finish;
    public EndPoint(int row, int col)
    {
        super(row, col, 'E', true);
        finish = false;
    }
    
    public boolean finish(WorldCharacter user)
    {
        if(user.isStandingOn(this) && user.getDisplayCharacter() == 'U')
        {
            finish = true;
        }
        
        return finish;
    }
}
