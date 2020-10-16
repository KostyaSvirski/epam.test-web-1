package by.svirski.testweb.dao;

import by.svirski.testweb.dao.impl.SiteDAOImpl;
import by.svirski.testweb.dao.impl.UserDAO;

public class DaoFactory {

	private final static DaoFactory instance = new DaoFactory();
	
	private final UserDAO userDao = new UserDAO();
	private final SiteDAOImpl siteDao = new SiteDAOImpl();
	
	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		return instance;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public SiteDAOImpl getSiteDao() {
		return siteDao;
	}
	
	
}
