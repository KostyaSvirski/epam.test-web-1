package by.svirski.testweb.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import by.svirski.testweb.dao.exception.DaoException;

public interface BeanDao<T> {
	
	boolean insert(List<String> paramters, Connection cn, String request) throws DaoException;
	boolean update(Map<String, String> parameters);
	List<T> select(List<String> parameters, String request, Connection cn) throws DaoException;
	boolean delete(Map<String, String> parameters); 
	List<T> selectAll(String request, Connection cn) throws DaoException;
	
}
