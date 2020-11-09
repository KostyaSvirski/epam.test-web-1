package by.svirski.testweb.service.impl;

import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.bean.Order;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.dao.abstracts.AbstractCarDAOImpl;
import by.svirski.testweb.dao.abstracts.AbstractOrderDAOImpl;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.service.CustomCarService;
import by.svirski.testweb.service.exception.ServiceException;

public class CarServiceImpl implements CustomCarService {

	public CarServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Car> showCars(Map<CarType, String> parametersMap) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractCarDAOImpl carDao = factory.getCarDao();
		try {
			List<Car> carList = null;
			carList = carDao.showCars(parametersMap);
			return carList;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public boolean rentAuto(Map<OrderType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractOrderDAOImpl dao = factory.getOrderDao();
		boolean resultOfRent = false;
		try {
			resultOfRent = dao.rentAuto(parameters);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return resultOfRent;
	}

	@Override
	public List<Order> showOrders(Map<UserType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractOrderDAOImpl dao = factory.getOrderDao();
		List<Order> orders = null;
		try {
			orders = dao.showOrders(parameters);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return orders;
	}

	@Override
	public boolean releaseRent(Map<OrderType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractOrderDAOImpl dao = factory.getOrderDao();
		boolean result = false;
		try {
			result = dao.releaseRent(parameters);
			if(result) {
				return result;
			} else {
				throw new ServiceException("ошибка в дао");
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	

}
