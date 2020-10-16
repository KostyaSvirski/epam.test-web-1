package by.svirski.testweb.dao;

import by.svirski.testweb.dao.exception.DaoException;

public interface SiteDao {
	
	int countUsers() throws DaoException;

}
