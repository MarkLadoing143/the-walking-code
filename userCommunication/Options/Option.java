package userCommunication.Options;
/**
 * Class to hold all basic information about a single option.
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Nov 7, 2004 - Created (Jordan Kidney)
 */

public abstract class Option
{
	private String OptionName; // name that will appear on cmdline
	private String OptionDescription; // what the option is for

	private boolean RequiredOption; // is it required or not (has to always appear or not)
	private boolean set; // flag to indicate of the option has been set or not

	//------------------------------------------------------
	//protected  fields
	//------------------------------------------------------
	protected int num_args; // the number of argument that should appear after
	                        // the option name (as input to set values )
	                        // this value gets set by the subclasses
	
	
	public Option(String name, String des)
	{
		OptionName = name;
		OptionDescription = des;
		RequiredOption = false;
		set=false;
		num_args = 0;
	}    
	/**
	 * this method should set the values of the option or execute an action
	 * @param args arguments inputed for the command
	 * @return returns null for no errors, otherwise the error message will be returned
	 */
	public abstract String execute(String[] args);
	
	/**
	 * returns string for displaying options value
	 */
	public String toString()
	{
		String s = getName();

		if(isSet()) s += ": is set";
		else s += ": is not set";

		return s;
	}
	
	
	public void setName(String name) { OptionName = new String(name); }
	public String getName() { return OptionName; }

	public void setDescription(String des) { OptionDescription = new String(des); }
	public String getDescription() { return OptionDescription; }

	public void setAsRequired() { RequiredOption= true; }
	public void setaAsNotRequired() { RequiredOption= false; }
	public boolean isRequired() { return RequiredOption; }

	public int getNumArgs() { return num_args; }

	public void markAsSet() { set=true; }
	public void markAsNotSet() { set=false; } 
	public boolean isSet(){ return set;}
}
