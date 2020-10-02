package by.svirski.testweb.service.impl;

import java.util.Map;

import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.impl.AbstractDAO;
import by.svirski.testweb.service.CustomService;
import by.svirski.testweb.service.exception.ServiceException;

public class RegistrationService implements CustomService{

	public RegistrationService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Map<TypeOfParameters.UserType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractDAO dao = factory.getUserDao();
		boolean check = false;
		try {
			check = dao.registrateUser(parameters);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return check;
	}

	
}
