package by.svirski.testweb.dao;

import by.svirski.testweb.dao.abstracts.realisation.CarDAO;
import by.svirski.testweb.dao.abstracts.realisation.SystemDAOImpl;
import by.svirski.testweb.dao.abstracts.realisation.UserDAO;

public class DaoFactory {

	private final static DaoFactory instance = new DaoFactory();
	
	private final UserDAO userDao = new UserDAO();
	private final SystemDAOImpl siteDao = new SystemDAOImpl();
	private final CarDAO carDao = new CarDAO();
	
	private DaoFactory() {
	}

	public CarDAO getCarDao() {
		return carDao;
	}

	public static DaoFactory getInstance() {
		return instance;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public SystemDAOImpl getSiteDao() {
		return siteDao;
	}
	
	
}
