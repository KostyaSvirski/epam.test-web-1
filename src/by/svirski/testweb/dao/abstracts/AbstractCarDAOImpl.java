package by.svirski.testweb.dao.abstracts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import by.svirski.testweb.dao.exception.TransactionException;

public abstract class AbstractCarDAOImpl implements BeanDao<Car, CarType> {

	private static Logger logger = LogManager.getLogger(AbstractCarDAOImpl.class);

	public AbstractCarDAOImpl() {

	}

	@Override
	public boolean insert(List<String> parameters, Connection cn, String request)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			try {
				ps = cn.prepareStatement(request);
				if (!parameters.isEmpty()) {
					for (int i = 1; i <= parameters.size(); i++) {
						if(i == 9) {
							ps.setLong(i, Long.parseLong(parameters.get(i - 1)));							
						} else {
							ps.setString(i, parameters.get(i - 1));							
						}
					}
				}
			} catch (SQLException e) {
				logger.log(Level.ERROR, "ошибка при создании PreparedStatement");
				throw new TransactionException("ошибка при создании PreparedStatement");
			}
			try {
				ps.executeUpdate();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "ошибка при выполнении запроса");
				throw new TransactionException("ошибка при выполнении запроса");
			}
			return true;

		} finally {
			close(ps);
		}

	}

	@Override
	public boolean update(Map<CarType, String> parameters, String request, Connection cn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Car> select(List<String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			try {
				ps = cn.prepareStatement(request);
				if (!parameters.isEmpty()) {
					for (int i = 1; i <= parameters.size(); i++) {
						try {
							ps.setLong(i, Long.parseLong(parameters.get(i - 1)));
						} catch (NumberFormatException e) {
							ps.setString(i, parameters.get(i - 1));
						}
					}
				}
			} catch (SQLException e) {
				logger.log(Level.ERROR, "ошибка при создании PreparedStatement");
				throw new TransactionException("ошибка при создании PreparedStatement");
			}
			try {
				ResultSet rs = ps.executeQuery();
				List<Car> carList = new ArrayList<Car>();
				Builder<Car, CarType> builder = new CarBuilder();
				while (rs.next()) {
					Map<CarType, String> parametersMap = new HashMap<TypeOfParameters.CarType, String>();
					parametersMap.put(CarType.BRAND, rs.getString(1));
					parametersMap.put(CarType.MODEL, rs.getString(2));
					parametersMap.put(CarType.CLASS, rs.getString(3));
					parametersMap.put(CarType.POWER, rs.getString(4));
					parametersMap.put(CarType.ENGINE, rs.getString(5));
					parametersMap.put(CarType.ACCELERATION, rs.getString(6));
					parametersMap.put(CarType.DRIVE_UNIT, rs.getString(7));
					parametersMap.put(CarType.FUEL, rs.getString(8));
					parametersMap.put(CarType.COST, Long.toString(rs.getLong(9)));
					parametersMap.put(CarType.IMG, rs.getString(10));
					parametersMap.put(CarType.IS_BOOKED, Boolean.toString(rs.getBoolean(11)));
					parametersMap.put(CarType.DETAIL, rs.getString(12));
					parametersMap.put(CarType.ID, Integer.toString(rs.getInt(13)));
					Car car = builder.build(parametersMap);
					carList.add(car);
				}
				return carList;
			} catch (SQLException e) {
				logger.log(Level.ERROR, "ошибка при чтении ResultSet");
				throw new TransactionException("ошибка при чтении ResultSet");
			}
		} finally {
			close(ps);
		}

	}

	@Override
	public boolean delete(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return false;
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

	public abstract List<Car> showCars(Map<CarType, String> parametersMap) throws DaoException;

	public abstract boolean addCar(Map<CarType, String> parametersMap) throws DaoException;

}
