package by.svirski.testweb.service;

import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.bean.Comment;
import by.svirski.testweb.bean.Order;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.CommentType;
import by.svirski.testweb.service.exception.ServiceException;

public interface CustomCarService {

	List<Car> showCars(Map<TypeOfParameters.CarType, String> parametersMap) throws ServiceException;

	boolean rentAuto(Map<TypeOfParameters.OrderType, String> parameters) throws ServiceException;

	List<Order> showOrders(Map<TypeOfParameters.UserType, String> parameters) throws ServiceException;
	
	List<Comment> showComments(Map<CommentType, String> parameters) throws ServiceException;

}
