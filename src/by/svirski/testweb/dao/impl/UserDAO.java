package by.svirski.testweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.connector.ConnectionPool;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;

public class UserDAO extends AbstractDAO {

	private static final String REGISTRATE_USER_MAIN = "insert into users (login, password) values (?, ?)";
	private static final String FIND_ID_USER = "select (id) from users where login = ?";
	private static final String REGISTRATE_USER_PERSONAL = "insert into personal (id_user, surname, name, gender, passport_id, passport_number, date_of_birth, email, phone) values"
			+ "	(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String REGISTRATE_STATUS = "insert into status_in_project (id, is_blocked) values (?, ?)";
	private static final String REGISTRATE_ROLE = "insert into role_in_project (id, role) values (?, ?)";

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean registrateUser(Map<UserType, String> parameters) throws DaoException {
		Connection cn = null;
		try {			
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			try {
				cn = connectionPool.getConnection();
			} catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
			int position = -1;
			try {
				position = findUserId(cn, parameters);
			} catch (DaoException e) {
				throw new DaoException(e);
			}
			if (position != -1) {
				try {
					if (cn != null) {
						cn.close();
					}
				} catch (SQLException e) {
					throw new DaoException("не закрыт ресурс connection", e);
				}
				return false;
			}
			boolean isMainRegistrate = false;
			List<String> listOfParamters = createListOfMainParameters(parameters);
			isMainRegistrate = insert(listOfParamters, cn, REGISTRATE_USER_MAIN);
			position = findUserId(cn, parameters);
			parameters.put(TypeOfParameters.UserType.ID, Integer.toString(position));
			listOfParamters = createListOfPersonalParameters(parameters);
			isMainRegistrate = insert(listOfParamters, cn, REGISTRATE_USER_PERSONAL); // TODO 1:21 03.10.2020 problems with casting 
			listOfParamters = createListOfRoleParameters(parameters);
			isMainRegistrate = insert(listOfParamters, cn, REGISTRATE_ROLE);
			listOfParamters = createListOfStatusParameters(parameters);
			isMainRegistrate = insert(listOfParamters, cn, REGISTRATE_STATUS);
			return isMainRegistrate;
		} finally {
			if(cn!=null) {
				try {
					cn.close();
				} catch (SQLException e) {
					throw new DaoException("не закрыт ресурс connection", e);
				}
			}
		}
	}

	@Override
	public boolean insert(List<String> parameters, Connection cn, String request) throws DaoException {
		PreparedStatement ps = null;
		try {
			try {
				ps = cn.prepareStatement(request);
			} catch (SQLException e) {
				throw new DaoException("error in create prepared statement", e);
			}
			int parameterIndex = 0;
			for(String parameter : parameters) {
				try {
					ps.setString(parameterIndex++, parameter);
				} catch (SQLException e) {
					throw new DaoException("error in setting parameters", e);
				}
			}
			try {
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException("error in sending request", e);
			}
			return true;			
		}
		finally {
			if(ps!= null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private int findUserId(Connection cn, Map<TypeOfParameters.UserType, String> parameters) throws DaoException {
		try {
			PreparedStatement ps = cn.prepareStatement(FIND_ID_USER);
			ResultSet rs = null;
			try {
				String login = parameters.get(TypeOfParameters.UserType.LOGIN);
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
