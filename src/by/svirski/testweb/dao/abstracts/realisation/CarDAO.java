package by.svirski.testweb.dao.abstracts.realisation;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.dao.abstracts.AbstractCarDAOImpl;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.pool.ConnectionPool;

public class CarDAO extends AbstractCarDAOImpl {

	private static Logger logger = LogManager.getLogger(CarDAO.class);

	private static final String SQL_REQUEST_ALL_CARS_SHOW = 
			"select brand, model, class, power, engine, acceleration, drive_unit, fuel, cost, img, is_booked from car"
			+ "join book_list on book_list.id_car";

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
			List<Car> listOfBeans = selectAll(SQL_REQUEST_ALL_CARS_SHOW, cn);
			logger.log(Level.DEBUG, "получен список Car");
			return listOfBeans;			
		} finally {
			close(cn);
		}
	}

}
