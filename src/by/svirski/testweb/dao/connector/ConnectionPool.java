package by.svirski.testweb.dao.connector;

import java.sql.Connection;
import java.sql.Driver;
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
	private BlockingDeque<ProxyConnection> freeConnections;
	private Deque<ProxyConnection> givenConnections;
	private int poolSize;

	public static synchronized ConnectionPool getInstance() throws ConnectionPoolException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	private ConnectionPool() throws ConnectionPoolException {
		ResourceBundle rb = ResourceBundle.getBundle(DB_PROPERTIES);
		Properties properties = createPropertiesList(rb);
		poolSize = Integer.parseInt(rb.getString(DB_POOLSIZE));
		freeConnections = new LinkedBlockingDeque<ProxyConnection>(poolSize);
		givenConnections = new ArrayDeque<ProxyConnection>();
		try {
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(properties.getProperty(DB_URL),
						properties.getProperty(DB_LOGIN), properties.getProperty(DB_PASSWORD));
				freeConnections.offer(new ProxyConnection(connection));
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException(e.getMessage(), e);
		}

	}

	public ProxyConnection getConnection() throws ConnectionPoolException { 
		ProxyConnection connection = null;
		connection = freeConnections.remove();
		givenConnections.push(connection);
		return connection;
	}
	
	public void destroyPool() throws ConnectionPoolException {
        try {
            for (int i = 0; i < poolSize; i++) {
                ProxyConnection proxyConnection = freeConnections.take();
                proxyConnection.reallyClose();
            }
            //LOGGER.log(Level.INFO, "Connection pool has been destroyed");
        } catch (InterruptedException | SQLException e) {
           // LOGGER.log(Level.ERROR, e);
            throw new ConnectionPoolException(e);
        } finally {
            deregisterDrivers();
        }
    }

    private void deregisterDrivers() throws ConnectionPoolException {
        try {
            while (DriverManager.getDrivers().hasMoreElements()) {
                Driver driver = DriverManager.getDrivers().nextElement();
                DriverManager.deregisterDriver(driver);
            }
            //LOGGER.log(Level.INFO, "Drivers have been deregistered");
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

	/*
	 * public boolean returnConnectionIntoPool(Connection connection) { if
	 * (connection.equals(givenConnections.getFirst())) { givenConnections.remove();
	 * freeConnections.offer(connection); return true; } return false; }
	 */

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

	public void releaseConnection(ProxyConnection connection) {
		if (connection instanceof ProxyConnection
                && givenConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
            //LOGGER.log(Level.DEBUG, "Connection has been released");
        } else {
            //LOGGER.log(Level.ERROR, "Invalid connection to release");
        }
		
	}
}
