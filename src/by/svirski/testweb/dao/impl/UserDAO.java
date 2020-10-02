package by.svirski.testweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import by.svirski.testweb.dao.connector.ConnectionPool;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;

public class UserDAO extends AbstractDAO {

	private static final String REGISTRATE_USER_MAIN = "insert into users (login, password) values (?, ?)";
	private static final String REGISTRATE_USER_ROLE = "insert into role_in_project (id, role) values (?, ?)";
	private static final String CHECK_IS_EXIST = "select (id) from users where login = ?";
	private static final String REGISTRATE_USER_PERSONAL = "insert into personal (id_user, surname, name, gender, passport_id, passport_number, date_of_birth, email, phone) values"
			+ "	(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String REGISTRATE_STATUS = "insert into status_in_project (id, is_blocked) values (?, ?)";
	private static final String REGISTRATE_ROLE = "insert into role_in_project (id, role) values (?, ?)";
	private static final String SELECT_ID = "select (id) from users where login = ? and password = ?";
	private static final String LOGIN = "login";
	private static final String PASS = "pass";

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	public boolean registrateUser(Map<String, String> parameters) throws DaoException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection cn = null;
		try {
			cn = connectionPool.getConnection();
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		}
		boolean isExist = false;
		try {
			isExist = checkIsExist(cn, parameters);
		} catch (DaoException e) {
			throw new DaoException(e);
		}
		if (isExist) {
			try {
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		return true;
	}

	
	@Override
	public boolean insert(List<String> parameters) {
		
		return super.insert(parameters);
	}

	private boolean checkIsExist(Connection cn, Map<String, String> parameters) throws DaoException {
		try {
			PreparedStatement ps = cn.prepareStatement(CHECK_IS_EXIST);
			ResultSet rs = null;
			try {
				String login = parameters.get(LOGIN);
				ps.setString(1, login);
				rs = ps.executeQuery();
				if (rs.first()) {
					return true;
				}
			} catch (SQLException e) {
				throw new DaoException("error in rs", e);
			} finally {
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			throw new DaoException("can't create preparded statement", e);
		}
		return false;
	}

}
