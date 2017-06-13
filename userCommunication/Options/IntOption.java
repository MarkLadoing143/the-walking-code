package userCommunication.Options;
/**
 * Integer option
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Nov 7, 2004 - Created (Jordan Kidney)
 */

public class IntOption extends Option
{
	private int value;
	//------------------------------------------------------
	public IntOption(String name, String des)
	{
		super(name,des);
		value=0;
		num_args = 1;
	}
	//------------------------------------------------------
	public IntOption(String name, String des,int val)
	{
		super(name,des);
		value=val;
		num_args = 1;
	}
	//------------------------------------------------------
	public String execute(String[] args)
	{
		String ret = null;
		try
		{
			value = Integer.parseInt(args[0]);
		}
		catch(Exception e) { ret = "input value was not an integer"; }	

		return ret;
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
	public int getValue() { return value; } 

}
