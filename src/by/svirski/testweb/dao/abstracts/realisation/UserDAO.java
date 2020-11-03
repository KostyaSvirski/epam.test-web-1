package by.svirski.testweb.dao.abstracts.realisation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.abstracts.AbstractUserDAOImpl;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.pool.ConnectionPool;

public class UserDAO extends AbstractUserDAOImpl {

	private final Logger LOGGER = LogManager.getLogger(UserDAO.class);

	private static final String REGISTRATE_USER_MAIN = "INSERT INTO USERS (login, password) VALUES (?, ?)";
	private static final String FIND_ID_USER = "select (id) from users where login = ?";
	private static final String REGISTRATE_USER_PERSONAL = "insert into personal (id_user, surname, name, gender, passport_id, passport_number, date_of_birth, email, phone) values"
			+ "	(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String REGISTRATE_STATUS = "insert into status_in_project (id, is_blocked) values (?, ?)";
	private static final String REGISTRATE_ROLE = "insert into role_in_project (id, role) values (?, ?)";
	private static final String CHECK_REGISTRATION = "select (id) from users where login = ? and password = ?";
	private static final String SELECT_USER = "select * from users join personal on personal.id_user = users.id where login = ? and password = ?";
	private static final String SELECT_USER_BY_ID = "select * from users join personal on personal.id_user = users.id where users.id=?";

	private static final String UPDATE_USER = "update personal join users on personal.id_user = users.id set personal.name=?,"
			+ " personal.surname=?, personal.gender=?, personal.passport_id=?, "
			+ "personal.passport_number=?, personal.date_of_birth=?, personal.phone=? where users.id=?";

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User authorizateUser(Map<UserType, String> parameters) throws DaoException {
		User user = null;
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
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
				List<User> foundList = select(listOfParametersForRequest, SELECT_USER, cn);
				if (foundList.size() == 1) {
					user = (User) foundList.get(0);
					user.setId(position);
					LOGGER.log(Level.DEBUG, "пользователь найден");
				} else if (foundList.size() > 1 || foundList.isEmpty()) {
					LOGGER.log(Level.ERROR, "найдено более одного или ни одного пользователя");
					throw new DaoException("найдено более одного или ни одного пользователя");
				}
			}
			return user;
		} finally {
			close(cn);
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
				return false;
			}
			boolean isMainRegistrate = false;
			List<String> listOfParamters = createListOfMainParameters(parameters);
			isMainRegistrate = insert(listOfParamters, cn, REGISTRATE_USER_MAIN);
			LOGGER.log(Level.DEBUG, "зарегистрирована главная информация");
			position = findUserId(cn, login);
			parameters.put(TypeOfParameters.UserType.ID, Integer.toString(position));
			listOfParamters = createListOfPersonalParameters(parameters);
			boolean isPersonalRegistrated = insert(listOfParamters, cn, REGISTRATE_USER_PERSONAL);
			LOGGER.log(Level.DEBUG, "зарегистрирована персональная информация");
			listOfParamters = createListOfRoleParameters(parameters);
			boolean isRoleRegistated = insert(listOfParamters, cn, REGISTRATE_ROLE);
			LOGGER.log(Level.DEBUG, "зарегистрирована роль");
			listOfParamters = createListOfStatusParameters(parameters);
			boolean isStatusRegistrated = insert(listOfParamters, cn, REGISTRATE_STATUS);
			LOGGER.log(Level.DEBUG, "зарегистрирован статус");
			return (isMainRegistrate && isPersonalRegistrated && isRoleRegistated && isStatusRegistrated);
		} finally {
			close(cn);
		}
	}

	@Override
	public User editUser(Map<UserType, String> parameters) throws DaoException {
		User user = null;
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				cn.setAutoCommit(false);
			} catch (ConnectionPoolException | SQLException e) {
				throw new DaoException(e);
			}
			try {
				boolean resultUpdate = update(parameters, UPDATE_USER, cn);
				List<String> parametersList = new ArrayList<String>();
				parametersList.add(parameters.get(UserType.ID));
				if (resultUpdate) {
					List<User> foundList = select(parametersList, SELECT_USER_BY_ID, cn);
					if (foundList.size() == 1) {
						user = foundList.get(0);
						LOGGER.log(Level.DEBUG, "пользователь найден");
						cn.commit();
					} else if (foundList.size() > 1) {
						cn.rollback();
						LOGGER.log(Level.ERROR, "найдено более одного пользователя");
						throw new DaoException("найдено более одного пользователя");
					} else if (foundList.isEmpty()) {
						cn.rollback();
						LOGGER.log(Level.ERROR, "найдено ни одного пользователя");
						throw new DaoException("найдено ни одного пользователя");
					}
				} else {
					cn.rollback();
					LOGGER.log(Level.ERROR, "пользователь не может быть изменен");
					throw new DaoException("пользователь не может быть изменен");
				}
			} catch (SQLException e) {
				LOGGER.log(Level.ERROR, "ошибка коммита");
				throw new DaoException("ошибка коммита");
			}
			return user;
		} finally {
			close(cn);
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
					LOGGER.log(Level.INFO, "Пользователь по логину и паролю не найден");
					return -1;
				} else {
					LOGGER.log(Level.DEBUG, "Пользователь по логину и паролю найден");
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				throw new DaoException("error in rs", e);
			} finally {
				close(ps);
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
					LOGGER.log(Level.DEBUG, "Пользователь по логину не найден");
					return -1;
				} else {
					LOGGER.log(Level.INFO, "Пользователь по логину найден");
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				throw new DaoException("error in rs", e);
			} finally {
				close(ps);
			}
		} catch (SQLException e) {
			throw new DaoException("can't create prepareded statement", e);
		}
	}

}
