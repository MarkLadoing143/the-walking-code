package userCommunication.Options;
/**
 * String command option - allows another command to be executed when this option is called
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: March 2014, 2014 - Created (Jordan Kidney)
 */
public class StringExecCmdOption extends Option
{
	private String value;
    private Command attachedComand = null;
	//------------------------------------------------------
	public StringExecCmdOption(String name, String des, Command attach)
	{
		super(name,des);
		value="Blank";
		num_args = 0;
		attachedComand = attach;
	}
	//------------------------------------------------------
	public String execute(String[] args)
	{
		if(attachedComand != null) attachedComand.execute(this);
		return null;
	}
	//------------------------------------------------------
	public String  toString()
	{
		String s = getName();

		if(isSet())s += ": " + value;
		else s += ": is not set";

		return s;
	}
	//------------------------------------------------------
	public String getValue() { return value; } 

}
