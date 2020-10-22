package by.svirski.testweb.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import by.svirski.testweb.dao.exception.DaoException;

public interface BeanDao<T, U> {
	
	boolean insert(List<String> paramters, Connection cn, String request) throws DaoException;
	boolean update(Map<U, String> parameters, String request, Connection cn) throws DaoException;
	List<T> select(List<String> parameters, String request, Connection cn) throws DaoException;
	boolean delete(Map<String, String> parameters); 
	
}
