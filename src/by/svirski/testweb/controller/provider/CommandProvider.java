package by.svirski.testweb.controller.provider;

import by.svirski.testweb.controller.MainController;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.controller.command.TypeOfCommand;
import by.svirski.testweb.controller.command.impl.IncorrectCommand;

/**
 * class represents provider for necessary command
 * 
 * @author Kostya Svirski
 * @version 1.0
 *
 */
public class CommandProvider {

	/**
	 * default constructor
	 */
	public CommandProvider() {}
	
	/**
	 * method for define command from request by the existing commands in {@link TypeOfCommand}
	 * 
	 * @param commandToFind - name of command which came to controller {@link MainController}
	 * @return command which should be processed or incorrect command 
	 */
	public ActionCommand defineCommand(String commandToFind) {
		ActionCommand command = new IncorrectCommand();
		TypeOfCommand[] typesOfCommands = TypeOfCommand.values();
		for(TypeOfCommand commandtoCheck : typesOfCommands) {
			if(commandtoCheck.name().equalsIgnoreCase(commandToFind)) {
				return commandtoCheck.getCommand();
			}
		}
		return command;
	}

}
