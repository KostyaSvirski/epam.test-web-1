package by.svirski.testweb.dao.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
	
	public Connection getConnection() {
		Context context;
		Connection connection = null;
		try {
			context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/ConnPool");
			connection = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
}
