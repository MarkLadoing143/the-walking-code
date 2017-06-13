package userCommunication.Options;
/**
 * Float option
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Nov 7, 2004 - Created (Jordan Kidney)
 */

public class FloatOption extends Option
{
	private double value;
	//------------------------------------------------------
	public FloatOption(String name, String des)
	{
		super(name,des);
		value=0.0;
		num_args = 1;
	}
	//------------------------------------------------------
	public String execute(String[] args)
	{
		String ret = null;
		try
		{
			value = Double.parseDouble(args[0]);
		}
		catch(Exception e) { ret = "input value was not a double"; }	

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
	public double getValue() { return value; } 
	
}
