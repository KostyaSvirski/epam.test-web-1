package by.svirski.testweb.service;

import by.svirski.testweb.service.impl.AuthorizationService;
import by.svirski.testweb.service.impl.RegistrationService;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	
	private final RegistrationService registrationService = new RegistrationService();
	private final AuthorizationService authorizationService = new AuthorizationService();
	
	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}

	public AuthorizationService getAuthorizationService() {
		return authorizationService;
	}
	
	
	
}
