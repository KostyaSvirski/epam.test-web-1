package by.svirski.testweb.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.BeanIndicator;
import by.svirski.testweb.dao.Dao;
import by.svirski.testweb.dao.connector.ConnectionPool;
import by.svirski.testweb.dao.exception.DaoException;

public class AbstractDAO implements Dao<BeanIndicator> {
	
	
	public AbstractDAO() {
		
	}

	@Override
	public boolean insert(Map<String, String> paramters) {
		
		return false;
	}

	@Override
	public boolean update(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BeanIndicator> select(List<String> paramters) {
		// TODO Auto-generated method stub
		return null;
	}

	public Connection getCn() {
		return cn;
	}
	
}
