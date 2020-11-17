package by.svirski.testweb.dao.abstracts.realisation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Order;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.abstracts.AbstractOrderDAOImpl;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.exception.TransactionException;
import by.svirski.testweb.dao.pool.ConnectionPool;

public class OrderDAO extends AbstractOrderDAOImpl {

	private static Logger logger = LogManager.getLogger(OrderDAO.class);

	private static final String MAKE_ORDER = "insert into order_list "
			+ "(id_user, id_car, signature, date_of_start, date_of_finish, total_price, order_condition) "
			+ "values (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_BOOK_LIST_RENT = "update book_list set is_booked = true where id_car = ?";
	private static final String RELEASE_ORDER = "update order_list set order_condition = 'завершено' where id_order = ?";
	private static final String CONFIRM_ORDER = "update order_list set order_condition = 'одобрено' where id_order = ?";
	private static final String DENY_ORDER = "update order_list set order_condition = 'отклонено' where id_order = ?";
	private static final String FIND_ID_CAR = "select car.id_car from order_list join car on order_list.id_car = car.id_car"
			+ " where order_list.id_order = ?";
	private static final String UPDATE_BOOK_LIST_RELEASE = "update book_list set is_booked = false where id_car = ?";
	private static final String GET_ORDERS_FOR_CURRENT_USER = "select order_list.id_order, "
			+ "order_list.id_user, order_list.id_car, order_list.signature, "
			+ "order_list.date_of_start, order_list.date_of_finish, order_list.total_price, order_list.order_condition, order_list.info,"
			+ "car.brand, car.model from order_list join car on order_list.id_car = car.id_car where order_list.id_user = ?";
	private static final String GET_ALL_ORDERS = "select order_list.id_order, order_list.id_user, order_list.id_car, order_list.signature, "
			+ "order_list.date_of_start, order_list.date_of_finish, order_list.total_price, order_list.order_condition, "
			+ "order_list.info, car.brand, car.model from order_list join car on order_list.id_car = car.id_car";
	private static final String UPDATE_INFO_ORDER = "update order_list set info = ? where id_order = ?";

	@Override
	public boolean rentAuto(Map<OrderType, String> parameters) throws DaoException {
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
			List<String> listOfParametersForCreateOrder = createListForOrder(parameters);
			try {
				boolean resultOfCreating = false;
				resultOfCreating = insert(listOfParametersForCreateOrder, cn, MAKE_ORDER);
				boolean resultOfUpdating = false;
				resultOfUpdating = super.update(parameters, UPDATE_BOOK_LIST_RENT, cn);
				cn.commit();
				return resultOfCreating && resultOfUpdating;
			} catch (TransactionException | SQLException e) {
				logger.log(Level.ERROR, "ошибка в процессе создания заявки на аренду");
				try {
					cn.rollback();
				} catch (SQLException e1) {
					logger.log(Level.ERROR, "ошибка rollback");
					throw new DaoException("ошибка rollback");
				}
			}
			return false;
		} finally {
			close(cn);
		}
	}

	@Override
	public List<Order> showOrdersForCurrentUser(Map<UserType, String> parameters) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			List<Order> orders = new ArrayList<Order>();
			List<String> parametersForSelect = new ArrayList<String>();
			parametersForSelect.add(parameters.get(UserType.ID));
			try {
				orders = super.select(parametersForSelect, GET_ORDERS_FOR_CURRENT_USER, cn);
				return orders;
			} catch (TransactionException e) {
				logger.log(Level.ERROR, "ошибка в запросе");
				throw new DaoException(e);
			}
		} finally {
			close(cn);
		}
	}

	@Override
	public List<Order> showAllOrders() throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			List<Order> listOfOrders = null;
			try {
				listOfOrders = super.select(null, GET_ALL_ORDERS, cn);
				return listOfOrders;
			} catch (TransactionException e) {
				throw new DaoException(e);
			}
		} finally {
			close(cn);
		}
	}

	@Override
	public boolean confirmOrder(Map<OrderType, String> parameters) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			boolean flag = false;
			try {
				flag = update(parameters, CONFIRM_ORDER, cn);
			} catch (TransactionException e) {
				throw new DaoException(e);
			}
			return flag;
		} finally {
			close(cn);
		}
	}

	@Override
	public boolean denyOrder(Map<OrderType, String> parameters) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				cn.setAutoCommit(false);
			} catch (ConnectionPoolException | SQLException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			boolean flag = false;
			flag = releaseRent(parameters, cn);
			logger.log(Level.DEBUG, "завершена аренда авто");
			if (flag) {
				List<String> listOfParams = new ArrayList<String>();
				listOfParams.add(parameters.get(OrderType.INFO));
				listOfParams.add(parameters.get(OrderType.ORDER_ID));
				try {
					boolean result = update(listOfParams, UPDATE_INFO_ORDER, cn);
					logger.log(Level.DEBUG, "обновлено оповещение пользователя");
					cn.commit();
					return result;
				} catch (TransactionException e) {
					cn.rollback();
					throw new DaoException(e);
				}
			}
		} catch (SQLException e) {
			throw new DaoException("ошибка коммита/отката");
		} finally {
			close(cn);
		}

		return false;
	}

	@Override
	public boolean releaseRent(Map<OrderType, String> parameters) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				cn.setAutoCommit(false);
			} catch (ConnectionPoolException | SQLException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			boolean resultOfRelease = false;
			try {
				resultOfRelease = update(parameters, RELEASE_ORDER, cn);
				List<String> parametersForFindId = new ArrayList<String>();
				parametersForFindId.add(parameters.get(OrderType.ORDER_ID));
				List<Order> orderList = select(parametersForFindId, FIND_ID_CAR, cn);
				if (orderList.size() == 1) {
					Order order = orderList.get(0);
					int idOrder = order.getId();
					parameters.put(OrderType.CAR_ID, Integer.toString(idOrder));
					resultOfRelease = super.update(parameters, UPDATE_BOOK_LIST_RELEASE, cn);
					cn.commit();
					return resultOfRelease;
				} else {
					cn.rollback();
					logger.log(Level.ERROR, "коллизия в нахождении order");
					throw new DaoException("ошибка в запросе");
				}
			} catch (TransactionException e) {
				logger.log(Level.ERROR, "ошибка в процессе завершении аренды");
				cn.rollback();
				throw new DaoException(e);
			}
		} catch (SQLException e) {
			throw new DaoException("ошибка коммита/отката");
		} finally {
			close(cn);
		}
	}

	public boolean releaseRent(Map<OrderType, String> parameters, Connection cn) throws DaoException {
		boolean resultOfRelease = false;
		try {
			resultOfRelease = update(parameters, DENY_ORDER, cn);
			List<String> parametersForFindId = new ArrayList<String>();
			parametersForFindId.add(parameters.get(OrderType.ORDER_ID));
			List<Order> orderList = select(parametersForFindId, FIND_ID_CAR, cn);
			if (orderList.size() == 1) {
				Order order = orderList.get(0);
				int idOrder = order.getId();
				parameters.put(OrderType.CAR_ID, Integer.toString(idOrder));
				resultOfRelease = super.update(parameters, UPDATE_BOOK_LIST_RELEASE, cn);
				cn.commit();
				return resultOfRelease;
			} else {
				cn.rollback();
				logger.log(Level.ERROR, "коллизия в нахождении order");
				throw new DaoException("ошибка в запросе");
			}
		} catch (TransactionException | SQLException e) {
			logger.log(Level.ERROR, "ошибка в процессе завершении аренды");
			try {
				cn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, "ошибка rollback");
				throw new DaoException("ошибка rollback");
			}
		}
		return false;
	}

	@Override
	public List<Order> select(List<String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(parameters.get(0)));
			ResultSet rs = ps.executeQuery();
			List<Order> resultList = new ArrayList<Order>();
			while (rs.next()) {
				Order order = new Order(rs.getInt(1));
				resultList.add(order);
			}
			return resultList;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ошибка в выполнении запроса select");
			throw new TransactionException("ошибка в выполнении запроса", e);
		} finally {
			close(ps);
		}

	}

	@Override
	public boolean update(Map<OrderType, String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(parameters.get(OrderType.ORDER_ID)));
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ошибка в выполнении запроса update");
			throw new TransactionException("ошибка в выполнении запроса", e);
		} finally {
			close(ps);
		}
	}

	private List<String> createListForOrder(Map<OrderType, String> parameters) {
		List<String> listOfParameters = new ArrayList<String>();
		listOfParameters.add(parameters.get(OrderType.USER_ID));
		listOfParameters.add(parameters.get(OrderType.CAR_ID));
		listOfParameters.add(parameters.get(OrderType.USER_SIGNATURE));
		listOfParameters.add(parameters.get(OrderType.DATE_OF_START));
		listOfParameters.add(parameters.get(OrderType.DATE_OF_FINISH));
		listOfParameters.add(parameters.get(OrderType.COST));
		return listOfParameters;
	}
}
