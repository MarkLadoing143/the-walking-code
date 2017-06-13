import java.io.*;
/**
 * Startup Main for the game
 * @author Jordan Kidney
 * @version 1.0
 * Last Modified: March 14, 2014 - Created (By Jordan Kidney)
 */
public class TheWalkingCode 
{
	public static void main(String[] args) throws FileNotFoundException
	{
       TheWalkingCodeGame game = new TheWalkingCodeGame();
       game.run(args);
	}
}
