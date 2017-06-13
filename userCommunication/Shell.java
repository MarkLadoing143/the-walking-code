package userCommunication;
/**
 * runs a shell like command interface
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified:   Nov 7, 2004 - Created (Jordan Kidney)
 *               March 14, 2014 - Updated to allow for single command run
 */
import java.util.*;


import userCommunication.Options.*;

public class Shell {
	private Hashtable<String,Option> Options; 
	private String prompt;
	private UserInteraction comm;
	private boolean END = false;

	public Shell()
	{
		this( new UserInteraction() );
	}
	
	public Shell(UserInteraction comm)
	{
		Options = new Hashtable<String,Option>();
		prompt = ">";
		this.comm = comm;
	}
	/**
	 * sets/adds a shell command option to the list
	 * @param option the option to add
	 */
	public void addOption(Option option)
	{
		if(option != null)
		{			
			Options.put(option.getName().toUpperCase(),
					option);
		}
	}
	/**
	 * Connects shell with command line options
	 * @param r the command line reader to connect to
	 */
	public void conectCmdLineOptions(CmdLineReader r)
	{
		Enumeration<Option> e = r.getOptions();

		while(e.hasMoreElements())
			addOption(e.nextElement() );

	}
	/**
	 * returns a reference to the specified option
	 * @param option
	 * @return null if option not found, otherwise a reference to the option is returned
	 */
	public Option getOption(String option)
	{
		return (Option) Options.get(option.toUpperCase());
	}
	/**
	 * displays start information for the shell
	 */
	public void showStartMessage()
	{
		comm.println("Type 'exit' or 'quit' to exit the shell");
		comm.println("Type 'help' to see a list of commands you can run");
	}
	
	public Option runSingleCommand()
	{
		String cmd = "";
		String[] cmd_parts;
		Option command =  null;
		cmd_parts = getInput();
		if(cmd_parts != null && cmd_parts.length > 0)
		{
			cmd = cmd_parts[0];
			cmd = cmd.toUpperCase();

			switch(cmd)
			{
			  case "HELP":  help(); break;
			  case "DV": displayValues(); break;
			  case "EXIT":
			  case "QUIT": END = true; break;
			  default: command = execCmd(cmd,cmd_parts); break;
			}
		}
		
		return command;
	}
	
	/**
	 * Main method for running the shell command interface
	 */
	public void runFullShell()
	{
		END = false;

		showStartMessage();
		
		do
		{

			runSingleCommand();
		}
		while(!END);
	}
	/**
	 * executes the command based upon the String array
	 * @param cmd command
	 * @param cmd_parts the input for the command
	 */
	private Option execCmd(String cmd, String[] cmd_parts)
	{
		// get the object to execute
		Option o = getOption(cmd);

		if(o == null)
			comm.println("Command not found: '" + cmd +"'");
		else
		{
			// setup args to pass to the Option
			if( o.getNumArgs() + 1 == cmd_parts.length)
			{
				String[] option_args = new String[o.getNumArgs()];
				System.arraycopy(cmd_parts,1,option_args,0, o.getNumArgs()); 

				String result = o.execute(option_args);
				if(result != null)
				{
					comm.println("ERROR: " + result); 
				}
				else
					o.markAsSet();

			}
			else
				comm.println("ERROR: wrong number of parameters: " 
						+ o.getName() +" required " + o.getNumArgs()
						+ " input parameters");

		}
		
		return o;
	}
	/**
	 * Gets the input line from the user and breaks it up based upon spaces
	 * @return the input broken apart
	 */
	private String[] getInput()
	{
		String input = comm.getInput_String(prompt);
		return input.split(" ");
	}
	/**
	 * Displays help information for the commands
	 */
	private void help()
	{
		Enumeration<Option> e = Options.elements();

		comm.println("Commands:");
		while(e.hasMoreElements())
		{
			Option o = e.nextElement();

			comm.println(String.format("%5s %s", o.getName(),o.getDescription()));
		}

		comm.println(String.format("%5s %s","DV","display option values"));
		comm.println(String.format("%5s %s","Help","display help information"));
		comm.println(String.format("%5s %s","Exit/Quit","exit program"));
	}
	/**
	 * Displays the values for each option if they have any
	 */
	private void  displayValues()
	{
		Enumeration<Option> e = Options.elements();

		comm.println("Option Values:");
		while(e.hasMoreElements())
			comm.println("\t" + e.nextElement() );
	}
	//------------------------------------------------------
	public void setPrompt(String s) { prompt = new String(s); }

	public void shutDown() { END = true; }
	public boolean getEnd() { return END; }
}
