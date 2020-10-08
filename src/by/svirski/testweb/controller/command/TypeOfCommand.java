package by.svirski.testweb.controller.command;

import by.svirski.testweb.controller.command.impl.*;

public enum TypeOfCommand {
	
	AUTHORIZATION_COMMAND(new AuthorizationCommand()),
	REGISTRATION_COMMAND(new RegistrationCommand()),
	LOG_OUT_COMMAND(new LogOutCommand()),
	INCORRECT_COMMAND(new IncorrectCommand());
	
	private ActionCommand command;

	private TypeOfCommand(ActionCommand command) {
		this.command = command;
	}

	public ActionCommand getCommand() {
		return command;
	}
	
}
