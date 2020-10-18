package by.svirski.testweb.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import by.svirski.testweb.dao.exception.DaoException;

public interface SystemDao {
	
	int countUsers() throws DaoException;
	
	default void close(Statement statement) {
		/* final Logger logger = LogManager.getLogger(Dao.class); */
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
				/* logger.log(Level.ERROR, "Statement hasn't been closed"); */
            }
        }
    }

   default void close(Connection connection) {
		/* final Logger logger = LogManager.getLogger(Dao.class); */
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
				/* logger.log(Level.ERROR, "Statement hasn't been closed"); */
            }
        }
    }

}
