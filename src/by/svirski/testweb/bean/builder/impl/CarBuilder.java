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

public class CarBuilder implements Builder<Car, CarType> {
	
	private static Logger logger = LogManager.getLogger(CarBuilder.class);

	public CarBuilder() {
	}

	@Override
	public Car build(Map<CarType, String> parameters) {
		Car car = new Car();
		try {
			car.setBrand(parameters.get(CarType.BRAND));
			car.setModel(parameters.get(CarType.MODEL));
			car.setCarClass(parameters.get(CarType.CAR_CLASS));
			car.setPower(Integer.parseInt(parameters.get(CarType.POWER)));
			car.setEngine(parameters.get(CarType.ENGINE));
			car.setAcceleration(Float.parseFloat(parameters.get(CarType.ACCELERATION)));
			DriveUnit[] driveUnitTypes = DriveUnit.values();
			boolean flag = false;
			for(DriveUnit driveUnit : driveUnitTypes) {
				if(driveUnit.toString().equalsIgnoreCase(parameters.get(CarType.DRIVE_UNIT))) {
					car.setDriveUnit(driveUnit);
					flag = true;
					break;
				}
			}
			if(!flag) {
				logger.log(Level.INFO, "не найден нужный DriveUnit");
			}
			Fuel[] fuelTypes = Fuel.values();
			flag = false;
			for(Fuel fuel : fuelTypes) {
				if(fuel.toString().equalsIgnoreCase(parameters.get(CarType.FUEL))) {
					car.setFuel(fuel);
					flag = true;
					break;
				}
			}
			if(!flag) {
				logger.log(Level.INFO, "не найден нужный Fuel");
			}
			car.setCost(Long.parseLong(parameters.get(CarType.COST)));
			car.setImage(parameters.get(CarType.IMG));
			car.setBooked(Boolean.parseBoolean(parameters.get(CarType.IS_BOOCKED)));
		} catch (NumberFormatException e) {
			logger.log(Level.INFO, "ошибка при парсинге", e);
		}
		logger.log(Level.DEBUG, "построился объект Car");
		return car;
	}

}
