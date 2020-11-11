package by.svirski.testweb.service;

import by.svirski.testweb.service.impl.AdminServiceImpl;
import by.svirski.testweb.service.impl.CarServiceImpl;
import by.svirski.testweb.service.impl.SystemServiceImpl;
import by.svirski.testweb.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	
	private final CustomUserService userService = new UserServiceImpl();
	private final CustomSystemService siteService = new SystemServiceImpl();
	private final CustomCarService carService = new CarServiceImpl();
	private final CustomAdminService adminService = new AdminServiceImpl();
	
	private ServiceFactory() {
	}

	public CustomAdminService getAdminService() {
		return adminService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public CustomUserService getUserService() {
		return userService;
	}

	public CustomSystemService getSiteService() {
		return siteService;
	}

	public CustomCarService getCarService() {
		return carService;
	}		
	
}
