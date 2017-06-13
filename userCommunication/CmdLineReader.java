package userCommunication;
/**
 * Does basic check of command line parameters for a program based upon the provided options
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: March 1, 2007 - Created (Jordan Kidney)
 */

import java.util.*;
import userCommunication.Options.*;

public class CmdLineReader
{
	private Hashtable<String,Option> CmdLineOptions; 
	private Vector<Option> RequiredCmdLineOptions; 
	private boolean displayOnError;

	public CmdLineReader()
	{
		CmdLineOptions = new Hashtable<String,Option>();
		RequiredCmdLineOptions = new Vector<Option>();
		displayOnError = true;
	}
	/**
	 * checks the command line parameters based upon the CmdLineOptions
	 * If their is an error in the list of cmdline the options will be printed to the
	 * screen (if displayOnError flag is set to true)
	 * @param args the command line arguments
	 * @return true for proper pass, false for incorrect cmdline options. 
	 * 
	 */
	public boolean checkCmdLine(String[] args)
	{
		boolean errorflag = false;
		String[] option_args = null;
		int args_count = 0;

		markAllNotSet();

		while(args_count < args.length)
		{
			// get option
			String name = 	args[args_count].replaceAll("-",""); 
			Option o = getOption(name);

			// do error checking
			if(o == null) { errorflag = true; break; }

			args_count++;

			if( args_count + o.getNumArgs() > args.length)
			{ 
				if(displayOnError)
					System.out.println("Error: incorect number of args for option: " 
							+ o.getName());

				errorflag = true; 
				break; 
			}

			//set op input to the option
			option_args = new String[o.getNumArgs()];
			System.arraycopy(args,args_count,option_args,0, o.getNumArgs()); 

			args_count += o.getNumArgs();

			//set the option
			if(o.execute(option_args) != null)
			{ errorflag = true; break; }
			else
				o.markAsSet();

		}


		//now check that all required options have been set
		if(!errorflag)
			for(int i = 0; i < RequiredCmdLineOptions.size(); i++)
			{
				Option o = (Option) RequiredCmdLineOptions.elementAt(i);
				if(!o.isSet())  
				{ 
					errorflag = true;
					if(displayOnError)
						System.out.println("Error: missing required option: " 
								+ o.getName());
				}
			}

		if(errorflag && displayOnError) 
			displayDescriptionInformation();

		return !errorflag; 
	}
	/**
	 * sets/adds a cmd line option to the list note the name of each option must be Unique.
	 * @param option the option to add to the list
	 */
	public void addOption(Option option)
	{
		if(option != null)
		{	
			if(option.isRequired())
				RequiredCmdLineOptions.addElement(option);

			CmdLineOptions.put(option.getName().toUpperCase(),
					option);
		}
	}
	/**
	 * returns a reference to the specified  option
	 * @param option the option to return
	 * @return null if option not found, otherwise a reference to the option is returned
	 */
	public Option getOption(String option)
	{
		return (Option) CmdLineOptions.get(option.toUpperCase());
	}
	//-------------------------------------------------------
	public Enumeration<Option> getOptions() { return CmdLineOptions.elements(); }
	
	/**
	 * Marks all options as not set
	 */
	public void markAllNotSet()
	{
		Enumeration<Option> e = CmdLineOptions.elements();

		while(e.hasMoreElements())
		{
			Option o = (Option) e.nextElement();
			o.markAsNotSet();
		}
	}
	/**
	 * displays all option values to the screen
	 */
	public void displayOptionValues()
	{
		Enumeration<Option> e = CmdLineOptions.elements();

		System.out.println("Option Values:");
		while(e.hasMoreElements())
			System.out.println("\t" + (Option) e.nextElement() );
	}

	/**
	 * displays description information for each option to the screen 
	 */
	public void displayDescriptionInformation()
	{
		Enumeration<Option> e = CmdLineOptions.elements();
		String req = " ";

		System.out.println("Command Line Options: ( * = required option )");
		while(e.hasMoreElements())
		{
			Option o = (Option) e.nextElement();

			if(o.isRequired()) req ="* -";
			else req = "  -";
			System.out.println("\t " + req
					+ o.getName() 
					+" <#args="+o.getNumArgs()+"> = "
					+o.getDescription()
					);
		}
	}
	
	
	public void setDisplayOnError(boolean flag) { displayOnError=flag; }

	

}
