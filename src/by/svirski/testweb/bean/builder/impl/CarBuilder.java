package by.svirski.testweb.bean.builder.impl;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.bean.DriveUnit;
import by.svirski.testweb.bean.Fuel;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;

/**
 * class represents builder for car bean 
 * 
 * @see Car
 * @see CarType  
 * @see Builder
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class CarBuilder implements Builder<Car, CarType> {

	private static Logger logger = LogManager.getLogger(CarBuilder.class);

	/**
	 * default constructor
	 */
	public CarBuilder() {
	}

	/**
	 * overriden method {@link Builder#build(Map)} of building Car bean 
	 */
	@Override
	public Car build(Map<CarType, String> parameters) {
		Car car = new Car();
		try {
			car.setId(Integer.parseInt(parameters.get(CarType.ID)));
			car.setBrand(parameters.get(CarType.BRAND));
			car.setModel(parameters.get(CarType.MODEL));
			car.setCarClass(parameters.get(CarType.CLASS));
			car.setPower(Integer.parseInt(parameters.get(CarType.POWER)));
			car.setEngine(parameters.get(CarType.ENGINE));
			car.setAcceleration(Float.parseFloat(parameters.get(CarType.ACCELERATION)));
			DriveUnit[] driveUnitTypes = DriveUnit.values();
			boolean flag = false;
			for (DriveUnit driveUnit : driveUnitTypes) {
				if (driveUnit.toString().equalsIgnoreCase(parameters.get(CarType.DRIVE_UNIT))
						|| driveUnit.name().equalsIgnoreCase(parameters.get(CarType.DRIVE_UNIT))) {
					car.setDriveUnit(driveUnit);
					flag = true;
					break;
				}
			}
			if (!flag) {
				logger.log(Level.INFO, "не найден нужный DriveUnit");
			}
			Fuel[] fuelTypes = Fuel.values();
			flag = false;
			for (Fuel fuel : fuelTypes) {
				if (fuel.toString().equalsIgnoreCase(parameters.get(CarType.FUEL))
						|| fuel.name().equalsIgnoreCase(parameters.get(CarType.FUEL))) {
					car.setFuel(fuel);
					flag = true;
					break;
				}
			}
			if (!flag) {
				logger.log(Level.INFO, "не найден нужный Fuel");
			}
			car.setCost(Long.parseLong(parameters.get(CarType.COST)));
			car.setImage(parameters.get(CarType.IMG));
			car.setBooked(Boolean.parseBoolean(parameters.get(CarType.IS_BOOKED)));
			car.setDiscription(parameters.get(CarType.DETAIL));
		} catch (IllegalArgumentException e) {
			logger.log(Level.INFO, "ошибка при парсинге", e);
		}
		logger.log(Level.DEBUG, "построился объект Car");
		return car;
	}

}
