package userCommunication.Options;
/**
 * String option
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Nov 7, 2004 - Created (Jordan Kidney)
 */


public class StringOption extends Option
{
	private String value;

	//------------------------------------------------------
	public StringOption(String name, String des)
	{
		super(name,des);
		value="Blank";
		num_args = 1;
	}
	//------------------------------------------------------
	public String execute(String[] args)
	{
		value=new String(args[0]);
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
