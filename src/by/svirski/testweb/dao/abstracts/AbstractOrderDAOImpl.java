package by.svirski.testweb.dao.abstracts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Order;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.builder.impl.OrderBuilder;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.BeanDao;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.exception.TransactionException;

public abstract class AbstractOrderDAOImpl implements BeanDao<Order, OrderType> {

	private static Logger logger = LogManager.getLogger(AbstractOrderDAOImpl.class);

	@Override
	public boolean insert(List<String> parameters, Connection cn, String request)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(parameters.get(0)));
			ps.setInt(2, Integer.parseInt(parameters.get(1)));
			ps.setString(3, parameters.get(2));
			ps.setString(4, parameters.get(3));
			ps.setString(5, parameters.get(4));
			ps.setLong(6, Long.parseLong(parameters.get(5)));
			ps.setString(7, "обрабатывается");
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
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
			ps.setInt(1, Integer.parseInt(parameters.get(OrderType.CAR_ID)));
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new TransactionException("ошибка в выполнении запроса", e);
		} finally {
			close(ps);
		}
	}
	
	public boolean update(List<String> params, String request, Connection cn)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			for(int i = 1; i <= params.size(); i++) {
				if(i == 2) {
					ps.setInt(i, Integer.parseInt(params.get(i-1)));									
				} else {
					ps.setString(i, params.get(i-1));
				}			
			}
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new TransactionException("ошибка в выполнении запроса", e);
		} finally {
			close(ps);
		}
	}

	@Override
	public List<Order> select(List<String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			if(parameters!= null && !parameters.isEmpty()) {
				for(int i = 1; i <=parameters.size(); i++) {
					ps.setInt(i, Integer.parseInt(parameters.get(i-1)));					
				}
			}
			ResultSet rs = ps.executeQuery();
			Builder<Order, OrderType> builder = new OrderBuilder();
			List<Order> resultList = new ArrayList<Order>();
			while (rs.next()) {
				Map<TypeOfParameters.OrderType, String> parametersMap = new EnumMap<TypeOfParameters.OrderType, String>(
						TypeOfParameters.OrderType.class);
				parametersMap.put(OrderType.ORDER_ID, Integer.toString(rs.getInt(1)));
				parametersMap.put(OrderType.USER_ID, Integer.toString(rs.getInt(2)));
				parametersMap.put(OrderType.CAR_ID, Integer.toString(rs.getInt(3)));
				parametersMap.put(OrderType.USER_SIGNATURE, rs.getString(4));
				parametersMap.put(OrderType.DATE_OF_START, rs.getString(5));
				parametersMap.put(OrderType.DATE_OF_FINISH, rs.getString(6));
				parametersMap.put(OrderType.COST, Long.toString(rs.getLong(7)));
				parametersMap.put(OrderType.CONDITION, rs.getString(8));
				parametersMap.put(OrderType.INFO, rs.getString(9));
				parametersMap.put(OrderType.BRAND, rs.getString(10));
				parametersMap.put(OrderType.MODEL, rs.getString(11));
				Order order = builder.build(parametersMap);
				resultList.add(order);
			}
			return resultList;
		} catch (SQLException e) {
			throw new TransactionException("ошибка в выполнении запроса", e);
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

	public abstract boolean rentAuto(Map<OrderType, String> parameters) throws DaoException;
	public abstract List<Order> showOrdersForCurrentUser(Map<UserType, String> parameters) throws DaoException;
	public abstract boolean releaseRent(Map<OrderType, String> parameters) throws DaoException;
	public abstract List<Order> showAllOrders() throws DaoException;
	public abstract boolean confirmOrder(Map<OrderType, String> parameters) throws DaoException;
	public abstract boolean denyOrder(Map<OrderType, String> parameters) throws DaoException;
} 
