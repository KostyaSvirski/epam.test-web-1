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

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.builder.impl.UserBuilder;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.BeanDao;
import by.svirski.testweb.dao.exception.DaoException;

public abstract class AbstractUserDAOImpl implements BeanDao<User, UserType> {
	
	private static Logger logger = LogManager.getLogger(AbstractUserDAOImpl.class);
	
	public AbstractUserDAOImpl() {	
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
			try {
				for (int i = 1; i <= parameters.size(); i++) {
					ps.setString(i, parameters.get(i - 1));
				}
			} catch (SQLException e) {
				throw new DaoException("error in setting parameters", e);
			}
			try {
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException("error in sending request", e);
			}
			return true;
		} finally {
			close(ps);
		}
	}


	@Override
	public boolean update(Map<UserType, String> parameters, String request, Connection cn) throws DaoException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setString(1, parameters.get(UserType.NAME));
			ps.setString(2, parameters.get(UserType.SURNAME));
			ps.setString(3, parameters.get(UserType.GENDER));
			ps.setString(4, parameters.get(UserType.PASSPORT_ID));
			ps.setString(5, parameters.get(UserType.PASSPORT_NUMBER));
			ps.setString(6, parameters.get(UserType.DATE_OF_BIRTH));
			ps.setString(7, parameters.get(UserType.PHONE_NUMBER));
			ps.setString(8, parameters.get(UserType.ID));
			int result = ps.executeUpdate();
			return (result == -1) ? false : true;
		} catch (SQLException e) {
			throw new DaoException("error in creating PreparedStatement");		
		} finally {
			close(ps);
		}

	}

	@Override
	public List<User> select(List<String> parameters, String request, Connection cn) throws DaoException {
		List<User> listOfBeans = new ArrayList<User>();
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			for(int i = 1; i <= parameters.size(); i++) {
				ps.setString(i, parameters.get(i-1));
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<TypeOfParameters.UserType, String> parametersMap = new HashMap<TypeOfParameters.UserType, String>();
				parametersMap.put(UserType.ID, Integer.toString(rs.getInt(1)));
				parametersMap.put(UserType.LOGIN, rs.getString(2));
				parametersMap.put(UserType.SURNAME, rs.getString(3));
				parametersMap.put(UserType.NAME, rs.getString(4));
				parametersMap.put(UserType.GENDER, rs.getString(5));
				parametersMap.put(UserType.PASSPORT_ID, rs.getString(6));
				parametersMap.put(UserType.PASSPORT_NUMBER, rs.getString(7));
				parametersMap.put(UserType.DATE_OF_BIRTH, rs.getString(8));
				parametersMap.put(UserType.EMAIL, rs.getString(9));
				parametersMap.put(UserType.PHONE_NUMBER, rs.getString(10));
				parametersMap.put(UserType.ROLE_IN_PROJECT, rs.getString(11));
				parametersMap.put(UserType.IS_BLOCKED, rs.getString(12));
				
				Builder<User, UserType> builder = new UserBuilder();
				User user = builder.build(parametersMap);
				listOfBeans.add(user);
			}
			return listOfBeans;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ошибка в выполнении запроса");
			throw new DaoException("ошибка в выполнении запроса", e);
		} finally {
			close(ps);
		}
		
	}

	@Override
	public boolean delete(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	protected List<String> createListOfMainParameters(Map<TypeOfParameters.UserType, String> parametersMap) {
		List<String> listOfParameters = new ArrayList<String>();
		listOfParameters.add(parametersMap.get(TypeOfParameters.UserType.LOGIN));
		listOfParameters.add(parametersMap.get(TypeOfParameters.UserType.PASSWORD));
		return listOfParameters;
	}
	
	protected List<String> createListOfPersonalParameters(Map<TypeOfParameters.UserType, String> parametersMap) {
		List<String> listOfParameters = new ArrayList<String>() {{
			add(parametersMap.get(TypeOfParameters.UserType.ID));
			add(parametersMap.get(TypeOfParameters.UserType.SURNAME));
			add(parametersMap.get(TypeOfParameters.UserType.NAME));
			add(parametersMap.get(TypeOfParameters.UserType.GENDER));
			add(parametersMap.get(TypeOfParameters.UserType.PASSPORT_ID));
			add(parametersMap.get(TypeOfParameters.UserType.PASSPORT_NUMBER));
			add(parametersMap.get(TypeOfParameters.UserType.DATE_OF_BIRTH));
			add(parametersMap.get(TypeOfParameters.UserType.LOGIN));
			add(parametersMap.get(TypeOfParameters.UserType.PHONE_NUMBER));			
		}};
		return listOfParameters;
	}
	
	protected List<String> createListOfRoleParameters(Map<TypeOfParameters.UserType, String> parametersMap) {
		List<String> listOfParameters = new ArrayList<String>() {{
			add(parametersMap.get(TypeOfParameters.UserType.ID));
			add(parametersMap.get(TypeOfParameters.UserType.ROLE_IN_PROJECT));
		}};
		return listOfParameters;
	}
	
	protected List<String> createListOfStatusParameters(Map<TypeOfParameters.UserType, String> parametersMap) {
		List<String> listOfParameters = new ArrayList<String>() {{
			add(parametersMap.get(TypeOfParameters.UserType.ID));
			add(parametersMap.get(TypeOfParameters.UserType.IS_BLOCKED));
		}};
		return listOfParameters;
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

    public abstract boolean registrateUser(Map<TypeOfParameters.UserType, String> parameters) throws DaoException;
    public abstract User authorizateUser(Map<TypeOfParameters.UserType, String> parameters) throws DaoException;
    public abstract User editUser(Map<TypeOfParameters.UserType, String> parameters) throws DaoException;
	
}
