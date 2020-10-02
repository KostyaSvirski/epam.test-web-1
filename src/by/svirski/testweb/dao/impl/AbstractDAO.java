package by.svirski.testweb.dao.impl;

import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.BeanIndicator;
import by.svirski.testweb.dao.Dao;

public class AbstractDAO implements Dao<BeanIndicator> {
	
	public AbstractDAO() {	
	}

	@Override
	public boolean insert(List<String> parameters) {
		
		return false;
	}

	@Override
	public boolean update(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BeanIndicator> select(List<String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	
}
