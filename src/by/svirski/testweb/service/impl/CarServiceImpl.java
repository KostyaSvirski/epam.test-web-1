package by.svirski.testweb.service.impl;

import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.dao.abstracts.AbstractCarDAOImpl;
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
		if(parametersMap.isEmpty()) {
			try {
				List<Car> carList = carDao.showAllCars();
				return carList;
			} catch (DaoException e) {
				throw new ServiceException("ошибка в дао");
			}			
		} 
		return null;
	}



}
