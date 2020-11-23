package by.svirski.testweb.dao.abstracts.realisation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Comment;
import by.svirski.testweb.bean.type.TypeOfParameters.CommentType;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.dao.abstracts.AbstractCommentDAOImpl;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.exception.TransactionException;
import by.svirski.testweb.dao.pool.ConnectionPool;

public class CommentDAO extends AbstractCommentDAOImpl {

	private static Logger logger = LogManager.getLogger(CommentDAO.class);

	private static final String CREATE_COMMENT = "insert into comments (id_car, id_user, signature_user, comment) values (?, ?, ?, ?)";
	private static final String UPDATE_COMMENT = "update comments set comment = ? where id_comment = ?";
	private static final String FIND_COMMENTS_TO_CAR = "select * from comments where id_car = ?";
	private static final String FIND_IDS_FOR_COMMENT = "select id_user, id_car, signature from order_list where id_order = ?";

	public CommentDAO() {
	}

	@Override
	public boolean createComment(Map<OrderType, String> parametersMap) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				logger.log(Level.DEBUG, "соеденение получено");
				cn.setAutoCommit(false);
			} catch (ConnectionPoolException | SQLException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			boolean flag = false;
			try {
				Map<OrderType, String> listOfID = findIDForComment(parametersMap, cn, FIND_IDS_FOR_COMMENT);
				List<String> listOfParams = new ArrayList<String>();
				listOfParams.add(listOfID.get(OrderType.CAR_ID));
				listOfParams.add(listOfID.get(OrderType.USER_ID));
				listOfParams.add(listOfID.get(OrderType.USER_SIGNATURE));
				listOfParams.add(parametersMap.get(OrderType.INFO));
				flag = insert(listOfParams, cn, CREATE_COMMENT);
				cn.commit();
			} catch (TransactionException e) {
				cn.rollback();
				throw new DaoException(e);
			}
			return flag;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ошибка rollback");
			throw new DaoException("ошибка rollback");
		} finally {
			close(cn);
		}

	}

	@Override
	public List<Comment> findCommentToCar(Map<CommentType, String> parametersMap) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				logger.log(Level.DEBUG, "соеденение получено");
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			List<String> listOfParams = new ArrayList<String>();
			listOfParams.add(parametersMap.get(CommentType.ID_CAR));
			List<Comment> commentsToCar = null;
			try {
				commentsToCar = select(listOfParams, FIND_COMMENTS_TO_CAR, cn);
			} catch (TransactionException e) {
				throw new DaoException(e);
			}
			return commentsToCar;
		} finally {
			close(cn);
		}
	}

	@Override
	public boolean updateComment(Map<CommentType, String> parametersMap) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
				logger.log(Level.DEBUG, "соеденение получено");
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			boolean flag = false;
			try {
				flag = update(parametersMap, UPDATE_COMMENT, cn);
			} catch (TransactionException e) {
				throw new DaoException(e);
			}
			return flag;
		} finally {
			close(cn);
		}
	}

}
