package by.svirski.testweb.service;

import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.service.exception.ServiceException;

public interface CustomCarService {
	
	List<Car> showCars(Map<TypeOfParameters.CarType, String> parametersMap) throws ServiceException;
}
