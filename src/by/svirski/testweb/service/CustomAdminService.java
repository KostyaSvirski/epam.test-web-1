package by.svirski.testweb.service;

import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.Order;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.service.exception.ServiceException;

public interface CustomAdminService {

	List<User> showAllUsers() throws ServiceException;

	List<Order> showAllOrders() throws ServiceException;

	boolean addCar(Map<CarType, String> parametersMap) throws ServiceException;
	
	boolean blockOrUnblockUser(Map<UserType, String> parametersMap) throws ServiceException;
}
