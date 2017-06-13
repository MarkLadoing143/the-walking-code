package userCommunication.Options;
/**
 * String List option
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Nov 7, 2004 - Created (Jordan Kidney)
 */

import java.util.*;

public class StrListOption extends Option
{
	private Vector<String> value;
	
	//------------------------------------------------------
	public StrListOption(String name, String des,int size)
	{
		super(name,des);

		if(size <= 0) size = 1;
		num_args = size;
		value = new Vector<String>();
	}
	//------------------------------------------------------
	public String execute(String[] args)
	{
		for(int i=0; i< num_args; i++)
			value.addElement( new String(args[i]) );

		return null;
	}
	//------------------------------------------------------
	public  String  toString()
	{
		String s = getName();

		if(isSet())
		{
			s += ": " + value;
		}
		else s += ": is not set";

		return s;
	}
	//------------------------------------------------------
	public void markAsNotSet() 
	{
		value.removeAllElements();
		super.markAsNotSet(); 
	} 
	//------------------------------------------------------
	public Vector getValue() { return value; } 
	
}
