package by.svirski.testweb.service.impl;

import java.util.Map;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.impl.AbstractUserDAO;
import by.svirski.testweb.service.CustomService;
import by.svirski.testweb.service.exception.ServiceException;

public class UserService implements CustomService{

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean registrate(Map<TypeOfParameters.UserType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractUserDAO dao = factory.getUserDao();
		boolean check = false;
		try {
			check = dao.registrateUser(parameters);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return check;
	}

	@Override
	public User authorize(Map<UserType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractUserDAO dao = factory.getUserDao();
		User user = null;
		try {
			user = dao.authorizateUser(parameters);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
		return user;
		
	}

	
}
