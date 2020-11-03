package by.svirski.testweb.dao.abstracts.realisation;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.dao.abstracts.AbstractCarDAOImpl;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.pool.ConnectionPool;

public class CarDAO extends AbstractCarDAOImpl {

	private static Logger logger = LogManager.getLogger(CarDAO.class);

	private static final String SQL_REQUEST_ALL_CARS_SHOW = 
			"select brand, model, class, power, engine, acceleration, drive_unit, fuel, cost, img, is_booked, car.id_car from car "
			+ "left join book_list on book_list.id_car = car.id_car";
	private static final String SQL_REQUEST_CLASS_CARS_SHOW = 
			"select brand, model, class, power, engine, acceleration, drive_unit, fuel, cost, img, is_booked, car.id_car from car "
			+ "left join book_list on book_list.id_car = car.id_car where class=?";
	private static final String SQL_REQUEST_BRAND_CARS_SHOW = 
			"select brand, model, class, power, engine, acceleration, drive_unit, fuel, cost, img, is_booked, car.id_car from car "
			+ "left join book_list on book_list.id_car = car.id_car where brand=?";
	
	public CarDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Car> showAllCars() throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				logger.log(Level.DEBUG, "соеденение получено");
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			List<Car> listOfBeans = select(null, SQL_REQUEST_ALL_CARS_SHOW, cn);
			logger.log(Level.DEBUG, "получен список Car");
			return listOfBeans;			
		} finally {
			close(cn);
		}
	}

	@Override
	public List<Car> showBrandOrClassCars(Map<CarType, String> parametersMap) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				logger.log(Level.DEBUG, "соеденение получено");
				
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			String request = chooseRequest(parametersMap);
			List<String> parametersList = new ArrayList<String>();
			if(request!= null) {
				for(Entry<CarType, String> entry : parametersMap.entrySet()) {
					parametersList.add(entry.getValue());
				}
			}			
			List<Car> listOfBeans = select(parametersList, request, cn);
			return listOfBeans;
		} finally {
			close(cn);
		}
	}

	private String chooseRequest(Map<CarType, String> parametersMap) {
		CarType[] types = CarType.values();
		for(CarType type : types) {
			if(parametersMap.containsKey(type)) {
				if(type.name().equals(CarType.BRAND.name())) {
					return SQL_REQUEST_BRAND_CARS_SHOW;
				} 
				if(type.name().equals(CarType.CAR_CLASS.name())) {
					return SQL_REQUEST_CLASS_CARS_SHOW;
				}
			}
		}
		return null;
	}
	
	

}
