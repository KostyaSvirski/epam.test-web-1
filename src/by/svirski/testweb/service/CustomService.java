package by.svirski.testweb.service;

import java.util.Map;

import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.service.exception.ServiceException;

public interface CustomService {
	
	boolean execute(Map<TypeOfParameters.UserType, String> parameters) throws ServiceException;
}
