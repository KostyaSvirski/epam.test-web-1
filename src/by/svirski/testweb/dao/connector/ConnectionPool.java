package by.svirski.testweb.dao.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import by.svirski.testweb.dao.exception.ConnectionPoolException;

public final class ConnectionPool {

	private static final String DB_PROPERTIES = "database";
	private static final String DB_URL = "url";
	private static final String DB_LOGIN = "user";
	private static final String DB_PASSWORD = "password";
	private static final String DB_AUTORECONNECT = "autoReconnect";
	private static final String DB_CHARACTER_ENCODING = "characterEncoding";
	private static final String DB_SERVER_TIMEZONE = "serverTimezone";
	private static final String DB_USE_UNICODE = "useUnicode";
	private static final String DB_POOLSIZE = "poolsize";

	private static ConnectionPool instance;
	private BlockingDeque<Connection> freeConnectionDeque;
	private Deque<Connection> givenConnections;

	public static synchronized ConnectionPool getInstance() throws ConnectionPoolException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	private ConnectionPool() throws ConnectionPoolException {
		ResourceBundle rb = ResourceBundle.getBundle(DB_PROPERTIES);
		Properties properties = createPropertiesList(rb);
		int capacity = Integer.parseInt(rb.getString(DB_POOLSIZE));
		freeConnectionDeque = new LinkedBlockingDeque<Connection>(capacity);
		givenConnections = new ArrayDeque<Connection>();
		try {
			for (int i = 0; i < capacity; i++) {
				Connection connection = DriverManager.getConnection(properties.getProperty(DB_URL),
						properties.getProperty(DB_LOGIN), properties.getProperty(DB_PASSWORD));
				freeConnectionDeque.offer(connection);
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException(e.getMessage(), e);
		}

	}

	public Connection getConnection() throws ConnectionPoolException { 
		Connection connection = null;
		connection = freeConnectionDeque.remove();
		givenConnections.push(connection);
		return connection;
	}

	public boolean returnConnectionIntoPool(Connection connection) {
		if (connection.equals(givenConnections.getFirst())) {
			givenConnections.remove();
			freeConnectionDeque.offer(connection);
			return true;
		}
		return false;
	}

	private static Properties createPropertiesList(ResourceBundle rb) {
		Properties properties = new Properties();
		properties.put(DB_URL, rb.getString(DB_URL));
		properties.put(DB_LOGIN, rb.getString(DB_LOGIN));
		properties.put(DB_PASSWORD, rb.getString(DB_PASSWORD));
		properties.put(DB_AUTORECONNECT, rb.getString(DB_AUTORECONNECT));
		properties.put(DB_CHARACTER_ENCODING, rb.getString(DB_CHARACTER_ENCODING));
		properties.put(DB_SERVER_TIMEZONE, rb.getString(DB_SERVER_TIMEZONE));
		properties.put(DB_USE_UNICODE, rb.getString(DB_USE_UNICODE));
		return properties;
	}
}
