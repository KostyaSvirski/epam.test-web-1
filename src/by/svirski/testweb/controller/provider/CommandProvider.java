package by.svirski.testweb.controller.provider;

import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.controller.command.TypeOfCommand;
import by.svirski.testweb.controller.command.impl.IncorrectCommand;

public class CommandProvider {

	public CommandProvider() {}
	
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
