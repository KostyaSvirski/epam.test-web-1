package by.svirski.testweb.dao.connector;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import by.svirski.testweb.dao.exception.ConnectionPoolException;

public final class ConnectionPool {
	private static ConnectionPool instance;
	
	private ConnectionPool() {
	}
	
	public static synchronized ConnectionPool getInstance() {
		if(instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
	
	public Connection getConnection() throws ConnectionPoolException { //TODO 2:06 03.10.2020 Не берутся соеденения
		Context context;
		Connection connection = null;
		try {
			context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/ConnPool");
			connection = ds.getConnection();
		} catch (NamingException e) {
			throw new ConnectionPoolException("can't find directory", e);
		} catch (SQLException e) {
			throw new ConnectionPoolException("can't take connection", e);
		}
		return connection;
		
	}
}
