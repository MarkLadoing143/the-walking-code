package userCommunication.Options;
/**
 * Boolean option
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Nov 7, 2004 - Created (Jordan Kidney)
 */
public class BooleanOption extends Option
{
	private boolean value;
	
	public BooleanOption(String name, String des)
	{
		super(name,des);
		value = false;
		num_args = 1;
	}
	
	public String execute(String[] args)
	{
		if(args[0].toUpperCase().compareTo("TRUE") == 0) value = true;
		else value=false;
		return null;
	}
	
	public  String  toString()
	{
		String s = getName();

		if(isSet())
		{
			if(value == true) s += ": true";
			else s += ": false";
		}
		else s += ": is not set";

		return s;
	}
	
	public boolean getValue() { return value; } 
	
}
