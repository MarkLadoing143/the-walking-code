/**
 * Example to show how to use the random number generator for this game
 * @author Jordan Kidney
 * @version 1.0
 * Last Modified: March 14, 2014 - Created (By Jordan Kidney)
 */

import utility.*;

public class RandomGen_Example_RMEOVE_BEFORE_SUBMIT
{
    public static void main(String args[])
    {
        GameRandomNumber rng = GameRandomNumber.getInstance();

        for(int count=0; count < 5; count++)
            System.out.println(""+count+": "+rng.next());

    }
}
