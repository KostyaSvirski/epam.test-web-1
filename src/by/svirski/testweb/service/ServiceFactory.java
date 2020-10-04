package by.svirski.testweb.service;

import by.svirski.testweb.service.impl.UserService;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	
	private final UserService userService = new UserService();
	
	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}	
	
}
