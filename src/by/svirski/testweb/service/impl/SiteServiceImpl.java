package by.svirski.testweb.service.impl;

import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.dao.SiteDao;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.service.CustomSiteService;
import by.svirski.testweb.service.exception.ServiceException;

public class SiteServiceImpl implements CustomSiteService{

	public SiteServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int countUsers() throws ServiceException {
		int countUsers = 0;
		DaoFactory factory = DaoFactory.getInstance();
		SiteDao siteDao = factory.getSiteDao(); 
		try {
			countUsers = siteDao.countUsers();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return countUsers;
	}
	
	
}
