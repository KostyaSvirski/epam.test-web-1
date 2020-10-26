package by.svirski.testweb.controller.command;

import by.svirski.testweb.controller.command.impl.*;

public enum TypeOfCommand {
	
	AUTHORIZATION_COMMAND(new AuthorizationCommand()),
	REGISTRATION_COMMAND(new RegistrationCommand()),
	LOG_OUT_COMMAND(new LogOutCommand()),
	INCORRECT_COMMAND(new IncorrectCommand()),
	COUNT_USERS_COMMAND(new CountUsersCommand()),
	SHOW_CARS_COMMAND(new CarShowCommand()),
	EDIT_INFO_COMMAND(new EditInfoCommand()),
	CHANGE_LOCALE(new ChangeLocale());
	
	private ActionCommand command;

	private TypeOfCommand(ActionCommand command) {
		this.command = command;
	}

	public ActionCommand getCommand() {
		return command;
	}
	
}
