package by.svirski.testweb.service;

import java.util.Map;

import by.svirski.testweb.bean.Penalty;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.bean.type.TypeOfParameters.PenaltyType;
import by.svirski.testweb.service.exception.InvalidParameterException;
import by.svirski.testweb.service.exception.ServiceException;

public interface CustomUserService {
	
	boolean registrate(Map<TypeOfParameters.UserType, String> parameters) throws ServiceException, InvalidParameterException;
	User authorize(Map<TypeOfParameters.UserType, String> parameters) throws ServiceException;
	User editUser(Map<TypeOfParameters.UserType, String> parameters) throws ServiceException, InvalidParameterException;
	boolean releaseRent(Map<OrderType, String> parameters) throws ServiceException;
	Penalty showPenalty(Map<PenaltyType, String> parameters) throws ServiceException; 
	boolean closePenalty(Map<PenaltyType, String> parameters) throws ServiceException; 
}
