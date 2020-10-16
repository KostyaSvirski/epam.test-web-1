package by.svirski.testweb.service;

import java.util.List;

import by.svirski.testweb.bean.Car;

public interface CustomCarService {
	
	List<Car> showCars(String parameter);
}
