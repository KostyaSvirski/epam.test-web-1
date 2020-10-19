package by.svirski.testweb.service.impl;

import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.dao.SystemDao;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.service.CustomSystemService;
import by.svirski.testweb.service.exception.ServiceException;

public class SystemServiceImpl implements CustomSystemService{

	public SystemServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int countUsers() throws ServiceException {
		int countUsers = 0;
		DaoFactory factory = DaoFactory.getInstance();
		SystemDao siteDao = factory.getSiteDao(); 
		try {
			countUsers = siteDao.countUsers();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return countUsers;
	}
	
	
}
