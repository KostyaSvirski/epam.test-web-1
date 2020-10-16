package by.svirski.testweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.svirski.testweb.dao.SiteDao;
import by.svirski.testweb.dao.connector.ConnectionPool;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;

public class SiteDAOImpl implements SiteDao {

	private static final String REQUEST_COUNT_USERS = "select (id) from users";

	public SiteDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int countUsers() throws DaoException {
		Connection cn = null;
		ConnectionPool connectionPool = null;
		try {
			try {
				connectionPool = ConnectionPool.getInstance();
				cn = connectionPool.getConnection();
			} catch (ConnectionPoolException e1) {
				throw new DaoException(e1.getMessage(), e1);
			}		
			PreparedStatement ps = null;
			ResultSet rs = null;
			int count = 0;
			try {
				ps = cn.prepareStatement(REQUEST_COUNT_USERS);
				rs = ps.executeQuery();
				while (rs.next()) {
					count += 1;
				}
				return count;
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			}
		} finally {
			if (cn != null) {
				if (!connectionPool.returnConnectionIntoPool(cn)) {
					throw new DaoException("не закрыт ресурс connection");
				}
			}
		}
	}

}
