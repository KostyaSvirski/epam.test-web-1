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
	CHANGE_LOCALE(new ChangeLocaleCommand()),
	RENT_COMMAND(new RentCommand()),
	SHOW_RENT_LIST_COMMAND(new ShowRentListCommand()),
	RELEASE_ORDER_COMMAND(new ReleaseOrderUserCommand()),
	SHOW_USERS_RENT_LIST_COMMAND(new ShowUsersRentListCommand()),
	SHOW_USERS_COMMAND(new ShowUsersCommand()),
	ADD_CARS_COMMAND(new AddCarCommand()),
	BLOCK_UNBLOCK_COMMAND(new BlockOrUnblockCommand()),
	MAKE_ADMIN_COMMAND(new MakeAdminCommand()),
	SHOW_THIS_USER_COMMAND(new ShowThisUserCommand()),
	CONFIRM_USER_ORDER_COMMAND(new ConfirmUserOrderCommand()),
	DENY_USER_ORDER_COMMAND(new DenyUserOrderCommand()),
	RELEASE_ORDER_ADMIN_COMMAND(new ReleaseOrderAdminCommand()),
	SHOW_DETAIL_CAR_COMMAND(new ShowDetailCarCommand()),
	SHOW_PENALTY_COMMAND(new ShowPenaltyCommand()),
	CLOSE_PENALTY_COMMAND(new ClosePenaltyCommand());
	
	private ActionCommand command;

	private TypeOfCommand(ActionCommand command) {
		this.command = command;
	}

	public ActionCommand getCommand() {
		return command;
	}
	
}
