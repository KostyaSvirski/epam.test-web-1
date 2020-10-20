package by.svirski.testweb.dao.abstracts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.builder.impl.CarBuilder;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.dao.BeanDao;
import by.svirski.testweb.dao.exception.DaoException;

public abstract class AbstractCarDAOImpl implements BeanDao<Car> {

	private static Logger logger = LogManager.getLogger(AbstractCarDAOImpl.class);

	public AbstractCarDAOImpl() {

	}

	@Override
	public boolean insert(List<String> paramters, Connection cn, String request) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Car> select(List<String> parameters, String request, Connection cn) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Car> selectAll(String request, Connection cn) throws DaoException {
		PreparedStatement ps = null;
		try {
			try {
				ps = cn.prepareStatement(request);
			} catch (SQLException e) {
				logger.log(Level.ERROR, "ошибка при создании PreparedStatement");
				throw new DaoException("ошибка при создании PreparedStatement");
			}
			try {
				ResultSet rs = ps.executeQuery();
				List<Car> carList = new ArrayList<Car>();
				Builder<Car, CarType> builder = new CarBuilder();
				//Car carPrev = null;
				while (rs.next()) {
					Map<CarType, String> parametersMap = new HashMap<TypeOfParameters.CarType, String>();
					parametersMap.put(CarType.BRAND, rs.getString(1));
					parametersMap.put(CarType.MODEL, rs.getString(2));
					parametersMap.put(CarType.CAR_CLASS, rs.getString(3));
					parametersMap.put(CarType.POWER, rs.getString(4));
					parametersMap.put(CarType.ENGINE, rs.getString(5));
					parametersMap.put(CarType.ACCELERATION, rs.getString(6));
					parametersMap.put(CarType.DRIVE_UNIT, rs.getString(7));
					parametersMap.put(CarType.FUEL, rs.getString(8));
					parametersMap.put(CarType.COST, Long.toString(rs.getLong(9)));
					parametersMap.put(CarType.IMG, rs.getString(10));
					parametersMap.put(CarType.IS_BOOCKED, Boolean.toString(rs.getBoolean(11)));
					Car car = builder.build(parametersMap);
					//carPrev = car;
					carList.add(car);
				}
				return carList;
			} catch (SQLException e) {
				logger.log(Level.ERROR, "ошибка при чтении ResultSet");
				throw new DaoException("ошибка при чтении ResultSet");
			}
		} finally {
			close(ps);
		}
	}

	protected void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
				logger.log(Level.DEBUG, "Statement был закрыт");
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Statement не был закрыт");
			}
		}
	}

	protected void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				logger.log(Level.DEBUG, "Connection был закрыт");
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Connection не был закрыт");
			}
		}
	}

	public abstract List<Car> showAllCars() throws DaoException;
}
