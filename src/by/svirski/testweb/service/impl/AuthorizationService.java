package by.svirski.testweb.service.impl;

import java.util.Map;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.service.CustomService;
import by.svirski.testweb.service.exception.ServiceException;

public class AuthorizationService implements CustomService {

	public AuthorizationService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Map<UserType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		
		return false;
	}

}
