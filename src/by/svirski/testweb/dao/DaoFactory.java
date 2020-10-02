package by.svirski.testweb.dao;

import by.svirski.testweb.dao.impl.UserDAO;

public class DaoFactory {

	private final static DaoFactory instance = new DaoFactory();
	
	private final UserDAO userDao = new UserDAO();
	
	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		return instance;
	}

	public UserDAO getUserDao() {
		return userDao;
	}
	
	
}
