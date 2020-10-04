package by.svirski.testweb.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.svirski.testweb.bean.BeanIndicator;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.dao.Dao;
import by.svirski.testweb.dao.exception.DaoException;

public abstract class AbstractDAO implements Dao<BeanIndicator> {
	
	public AbstractDAO() {	
	}

	
	@Override
	public boolean insert(List<String> paramters, Connection cn, String request) throws DaoException {
		// TODO Auto-generated method stub
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
	
	protected List<String> createListOfMainParameters(Map<TypeOfParameters.UserType, String> parametersMap) {
		List<String> listOfParameters = new ArrayList<String>();
		listOfParameters.add(parametersMap.get(TypeOfParameters.UserType.LOGIN));
		String hashPassword = Integer.toString(parametersMap.get(TypeOfParameters.UserType.PASSWORD).hashCode());
		listOfParameters.add(hashPassword);
		return listOfParameters;
	}
	
	protected List<String> createListOfPersonalParameters(Map<TypeOfParameters.UserType, String> parametersMap) {
		List<String> listOfParameters = new ArrayList<String>() {{
			add(parametersMap.get(TypeOfParameters.UserType.ID));
			add(parametersMap.get(TypeOfParameters.UserType.SURNAME));
			add(parametersMap.get(TypeOfParameters.UserType.NAME));
			add(parametersMap.get(TypeOfParameters.UserType.GENDER));
			add(parametersMap.get(TypeOfParameters.UserType.PASSPORT_ID));
			add(parametersMap.get(TypeOfParameters.UserType.PASSPORT_NUMBER));
			add(parametersMap.get(TypeOfParameters.UserType.DATE_OF_BIRTH));
			add(parametersMap.get(TypeOfParameters.UserType.EMAIL));
			add(parametersMap.get(TypeOfParameters.UserType.PHONE_NUMBER));			
		}};
		return listOfParameters;
	}
	
	protected List<String> createListOfRoleParameters(Map<TypeOfParameters.UserType, String> parametersMap) {
		List<String> listOfParameters = new ArrayList<String>() {{
			add(parametersMap.get(TypeOfParameters.UserType.ID));
			add(parametersMap.get(TypeOfParameters.UserType.ROLE_IN_PROJECT));
		}};
		return listOfParameters;
	}
	
	protected List<String> createListOfStatusParameters(Map<TypeOfParameters.UserType, String> parametersMap) {
		List<String> listOfParameters = new ArrayList<String>() {{
			add(parametersMap.get(TypeOfParameters.UserType.ID));
			add(parametersMap.get(TypeOfParameters.UserType.IS_BLOCKED));
		}};
		return listOfParameters;
	}
	
	public abstract boolean registrateUser(Map<TypeOfParameters.UserType, String> parameters) throws DaoException;
	public abstract User authorizateUser(Map<TypeOfParameters.UserType, String> parameters) throws DaoException;

	
}
