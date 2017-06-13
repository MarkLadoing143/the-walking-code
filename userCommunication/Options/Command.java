package userCommunication.Options;
/**
 *basic interface that follows a command pattern design for an action attached to an option
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Nov 7, 2004 - Created (Jordan Kidney)
 */
public interface Command 
{
	/**
	 * Executes and action 
	 * @param opt the option this object is attached to 
	 */
     public void execute(Option opt);
}
