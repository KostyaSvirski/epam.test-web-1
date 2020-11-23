package by.svirski.testweb.dao.abstracts.realisation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import by.svirski.testweb.dao.exception.TransactionException;
import by.svirski.testweb.dao.pool.ConnectionPool;
import by.svirski.testweb.util.creator.FactoryForCreators;
import by.svirski.testweb.util.creator.RequestCreator;

public class CarDAO extends AbstractCarDAOImpl {

	private static Logger logger = LogManager.getLogger(CarDAO.class);

	private static final String ABSTRACT_CARS_SHOW = "select brand, model, class, power, "
			+ "engine, acceleration, drive_unit, fuel, cost, img, is_booked, detail, car.id_car from car "
			+ "left join book_list on book_list.id_car = car.id_car";
	private static final String ADD_NEW_CAR = "insert into car (brand, model, class, power, "
			+ "engine, acceleration, drive_unit, fuel, cost, img, detail) values "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_IN_BOOK_LIST = "insert into book_list (id_car, is_booked) values (?, ?)";
	private static final String FIND_ID_NEW_CAR = "select id_car from car where brand = ? and model = ?";

	public CarDAO() {
	}

	@Override
	public boolean addCar(Map<CarType, String> parametersMap) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				cn.setAutoCommit(false);
				logger.log(Level.DEBUG, "соеденение получено");
			} catch (ConnectionPoolException | SQLException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			try {
				List<String> parametersList = createListOfParameters(parametersMap);
				boolean flag = false;
				try {
					flag = super.insert(parametersList, cn, ADD_NEW_CAR);
					List<String> secList = new ArrayList<String>();
					secList.add(parametersMap.get(CarType.BRAND));
					secList.add(parametersMap.get(CarType.MODEL));
					List<Car> beanList = select(secList, FIND_ID_NEW_CAR, cn);
					if (beanList.size() == 1) {
						int id = beanList.get(0).getId();
						parametersMap.put(CarType.ID, Integer.toString(id));
						List<String> parametersListForBookList = createListForBookList(parametersMap);
						flag = insert(parametersListForBookList, cn, ADD_IN_BOOK_LIST);
						cn.commit();
						logger.log(Level.DEBUG, "добавление завершено");
						return flag;
					} else {
						cn.rollback();
						logger.log(Level.ERROR, "не корректный запрос");
						throw new DaoException("не корректный запрос");
					}
				} catch (TransactionException e) {
					cn.rollback();
					logger.log(Level.ERROR, e.getMessage());
					throw new DaoException(e);
				}
			} catch (SQLException e) {
				logger.log(Level.ERROR, "ошибка коммита");
				throw new DaoException("ошибка коммита");
			}
		} finally {
			close(cn);
		}
	}

	@Override
	public List<Car> select(List<String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		List<Car> carList = new ArrayList<Car>();
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			for (int i = 1; i <= parameters.size(); i++) {
				ps.setString(i, parameters.get(i-1));
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Car car = new Car(rs.getInt(1));
				carList.add(car);
			}
			return carList;
		} catch (SQLException e) {
			throw new TransactionException("ошибка при создании/выполнении PreparedStatement");
		}
	}

	private List<String> createListForBookList(Map<CarType, String> parametersMap) {
		List<String> parametersList = new ArrayList<String>();
		parametersList.add(parametersMap.get(CarType.ID));
		parametersList.add(Boolean.toString(false));
		return parametersList;
	}

	private List<String> createListOfParameters(Map<CarType, String> parametersMap) {
		List<String> parametersList = new ArrayList<String>();
		parametersList.add(parametersMap.get(CarType.BRAND));
		parametersList.add(parametersMap.get(CarType.MODEL));
		parametersList.add(parametersMap.get(CarType.CLASS));
		parametersList.add(parametersMap.get(CarType.POWER));
		parametersList.add(parametersMap.get(CarType.ENGINE));
		parametersList.add(parametersMap.get(CarType.ACCELERATION));
		parametersList.add(parametersMap.get(CarType.DRIVE_UNIT));
		parametersList.add(parametersMap.get(CarType.FUEL));
		parametersList.add(parametersMap.get(CarType.COST));
		parametersList.add(parametersMap.get(CarType.IMG));
		parametersList.add(parametersMap.get(CarType.DETAIL));
		return parametersList;
	}

	@Override
	public boolean insert(List<String> parameters, Connection cn, String request)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(parameters.get(0)));
			ps.setBoolean(2, Boolean.parseBoolean(parameters.get(1)));
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new TransactionException("ошибка при создании/выполнении PreparedStatement");
		}
		return true;
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
			List<Car> listOfBeans = null;
			try {
				listOfBeans = super.select(parametersForRequest, currentRequest, cn);
			} catch (TransactionException e) {
				throw new DaoException(e);
			}
			logger.log(Level.DEBUG, "получен список Car");
			return listOfBeans;
		} finally {
			close(cn);
		}
	}

	private List<String> createParametersList(Map<CarType, String> parametersMap) {
		List<String> parametersList = new ArrayList<String>();
		for (Entry<CarType, String> entry : parametersMap.entrySet()) {
			if (entry.getKey().equals(CarType.COST)) {
				String[] values = entry.getValue().split("-");
				for (String value : values) {
					parametersList.add(value);
				}
			} else {
				parametersList.add(entry.getValue());
			}

		}
		return parametersList;
	}

}
