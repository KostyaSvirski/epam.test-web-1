package by.svirski.testweb.service;

import by.svirski.testweb.service.impl.CarServiceImpl;
import by.svirski.testweb.service.impl.SiteServiceImpl;
import by.svirski.testweb.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	
	private final CustomUserService userService = new UserServiceImpl();
	private final CustomSiteService siteService = new SiteServiceImpl();
	private final CustomCarService carService = new CarServiceImpl();
	
	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public CustomUserService getUserService() {
		return userService;
	}

	public CustomSiteService getSiteService() {
		return siteService;
	}

	public CustomCarService getCarService() {
		return carService;
	}		
	
}
