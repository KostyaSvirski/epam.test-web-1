package by.svirski.testweb.service;

import java.util.Map;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.service.exception.ServiceException;

public interface CustomUserService {
	
	boolean registrate(Map<TypeOfParameters.UserType, String> parameters) throws ServiceException;
	User authorize(Map<TypeOfParameters.UserType, String> parameters) throws ServiceException;
}
