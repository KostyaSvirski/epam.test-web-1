package by.svirski.testweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.BeanIndicator;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.connector.ConnectionPool;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;

public class UserDAO extends AbstractUserDAO {

	private static final String REGISTRATE_USER_MAIN = "INSERT INTO USERS (login, password) VALUES (?, ?)";
	private static final String FIND_ID_USER = "select (id) from users where login = ?";
	private static final String REGISTRATE_USER_PERSONAL = "insert into personal (id_user, surname, name, gender, passport_id, passport_number, date_of_birth, email, phone) values"
			+ "	(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String REGISTRATE_STATUS = "insert into status_in_project (id, is_blocked) values (?, ?)";
	private static final String REGISTRATE_ROLE = "insert into role_in_project (id, role) values (?, ?)";
	private static final String CHECK_REGISTRATION = "select (id) from users where login = ? and password = ?";
	private static final String SELECT_USER = "select * from users join personal on personal.id_user = users.id where login = ? and password = ?";

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User authorizateUser(Map<UserType, String> parameters) throws DaoException {
		User user = null;
		Connection cn = null;
		ConnectionPool connectionPool = null;
		try {
			try {
				connectionPool = ConnectionPool.getInstance();
			} catch (ConnectionPoolException e1) {
				throw new DaoException(e1.getMessage(), e1);
			}
			try {
				cn = connectionPool.getConnection();
			} catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
			String login = parameters.get(TypeOfParameters.UserType.LOGIN);
			String password = parameters.get(TypeOfParameters.UserType.PASSWORD);
			int position = findUserId(cn, login, password);
			if (position != -1) {
				List<String> listOfParametersForRequest = new ArrayList<String>();
				listOfParametersForRequest.add(login);
				listOfParametersForRequest.add(password);
				List<BeanIndicator> foundList = select(listOfParametersForRequest, SELECT_USER, cn);
				if(foundList.size() == 1) {
					user = (User) foundList.get(0);
				} else if (foundList.size() > 1) {
					throw new DaoException("найдено более одного пользователя");
				}
			}
			return user;
		} finally {
			if (cn != null) {
				if (!connectionPool.returnConnectionIntoPool(cn)) {
					throw new DaoException("не закрыт ресурс connection");
				}
			}
		}
	}

	@Override
	public boolean registrateUser(Map<UserType, String> parameters) throws DaoException {
		Connection cn = null;
		ConnectionPool connectionPool = null;
		try {
			try {
				connectionPool = ConnectionPool.getInstance();
			} catch (ConnectionPoolException e1) {
				throw new DaoException(e1.getMessage(), e1);
			}
			try {
				cn = connectionPool.getConnection();
			} catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
			String login = parameters.get(TypeOfParameters.UserType.LOGIN);
			int position = findUserId(cn, login);
			if (position != -1) {
				/*
				 * if (cn != null) { if (!connectionPool.returnConnectionIntoPool(cn)) { throw
				 * new DaoException("не закрыт ресурс connection"); } }
				 */
				return false;
			}
			boolean isMainRegistrate = false;
			
			List<String> listOfParamters = createListOfMainParameters(parameters);
			isMainRegistrate = insert(listOfParamters, cn, REGISTRATE_USER_MAIN);
			position = findUserId(cn, login);
			parameters.put(TypeOfParameters.UserType.ID, Integer.toString(position));
			listOfParamters = createListOfPersonalParameters(parameters);
			boolean isPersonalRegistrated = insert(listOfParamters, cn, REGISTRATE_USER_PERSONAL);
			listOfParamters = createListOfRoleParameters(parameters);
			boolean isRoleRegistated= insert(listOfParamters, cn, REGISTRATE_ROLE);
			listOfParamters = createListOfStatusParameters(parameters);
			boolean isStatusRegistrated= insert(listOfParamters, cn, REGISTRATE_STATUS);
			return (isMainRegistrate && isPersonalRegistrated && isRoleRegistated && isStatusRegistrated);
		} finally {
			if (cn != null) {
				if (!connectionPool.returnConnectionIntoPool(cn)) {
					throw new DaoException("не закрыт ресурс connection");
				}
			}
		}
	}

	private int findUserId(Connection cn, String login, String password) throws DaoException {
		try {
			PreparedStatement ps = cn.prepareStatement(CHECK_REGISTRATION);
			ResultSet rs = null;
			try {
				ps.setString(1, login);
				ps.setString(2, password);
				rs = ps.executeQuery();
				if (!rs.first()) {
					return -1;
				} else {
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				throw new DaoException("error in rs", e);
			} finally {
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			throw new DaoException("can't create prepareded statement", e);
		}
	}


	private int findUserId(Connection cn, String login) throws DaoException {
		try {
			PreparedStatement ps = cn.prepareStatement(FIND_ID_USER);
			ResultSet rs = null;
			try {
				ps.setString(1, login);
				rs = ps.executeQuery();
				if (!rs.first()) {
					return -1;
				} else {
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				throw new DaoException("error in rs", e);
			} finally {
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			throw new DaoException("can't create prepareded statement", e);
		}
	}

}
