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
import by.svirski.testweb.util.creator.FactoryForCreators;
import by.svirski.testweb.util.creator.RequestCreator;

public class CarDAO extends AbstractCarDAOImpl {

	private static Logger logger = LogManager.getLogger(CarDAO.class);

	private static final String ABSTRACT_CARS_SHOW = "select brand, model, class, power, "
			+ "engine, acceleration, drive_unit, fuel, cost, img, is_booked, car.id_car from car "
			+ "left join book_list on book_list.id_car = car.id_car";
	
	public CarDAO() {
	}

	@Override
	public List<Car> showCars(Map<CarType, String> parametersMap) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				logger.log(Level.DEBUG, "соеденение получено");
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			List<String> parametersForRequest = createParametersList(parametersMap);
			FactoryForCreators factory = FactoryForCreators.getInstance();
			RequestCreator<CarType> creator = factory.getCreatorForCarShow();
			String currentRequest = creator.createRequest(ABSTRACT_CARS_SHOW, parametersMap);
			List<Car> listOfBeans = select(parametersForRequest, currentRequest, cn);
			logger.log(Level.DEBUG, "получен список Car");
			return listOfBeans;
		} finally {
			close(cn);
		}
	}

	private List<String> createParametersList(Map<CarType, String> parametersMap) {
		List<String> parametersList = new ArrayList<String>();
		for (Entry<CarType, String> entry : parametersMap.entrySet()) {
			if(entry.getKey().equals(CarType.COST)) {
				String[] values = entry.getValue().split("-");
				for(String value : values) {
					parametersList.add(value);
				}
			} else {
				parametersList.add(entry.getValue());				
			}
			
		}
		return parametersList;
	}

}
