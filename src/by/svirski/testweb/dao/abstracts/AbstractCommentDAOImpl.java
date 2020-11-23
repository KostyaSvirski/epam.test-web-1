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

import by.svirski.testweb.bean.Comment;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.builder.impl.CommentBuilder;
import by.svirski.testweb.bean.type.TypeOfParameters.CommentType;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.dao.BeanDao;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.exception.TransactionException;

public abstract class AbstractCommentDAOImpl implements BeanDao<Comment, CommentType> {

	private static Logger logger = LogManager.getLogger(AbstractCommentDAOImpl.class);

	public AbstractCommentDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(List<String> parameters, Connection cn, String request)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(parameters.get(0)));
			ps.setInt(2, Integer.parseInt(parameters.get(1)));
			ps.setString(3, parameters.get(2));
			ps.setString(4, parameters.get(3));
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ошибка при создании комментария");
			throw new TransactionException("ошибка при создании комментария");
		} finally {
			close(ps);
		}
	}

	@Override
	public boolean update(Map<CommentType, String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setString(1, parameters.get(CommentType.COMMENT));
			ps.setInt(2, Integer.parseInt(parameters.get(CommentType.ID_COMMENT)));
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ошибка обновления комментария");
			throw new TransactionException("ошибка обновления комментария");
		} finally {
			close(ps);
		}
	}

	@Override
	public List<Comment> select(List<String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(parameters.get(0)));
			List<Comment> listOfBeans = new ArrayList<Comment>();
			Builder<Comment, CommentType> builder = new CommentBuilder();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Map<CommentType, String> parametersToBuildBean = new HashMap<CommentType, String>();
				parametersToBuildBean.put(CommentType.ID_COMMENT, Integer.toString(rs.getInt(1)));
				parametersToBuildBean.put(CommentType.ID_USER, Integer.toString(rs.getInt(2)));
				parametersToBuildBean.put(CommentType.ID_CAR, Integer.toString(rs.getInt(3)));
				parametersToBuildBean.put(CommentType.SIGNATURE, rs.getString(4));
				parametersToBuildBean.put(CommentType.COMMENT, rs.getString(5));
				Comment comment = builder.build(parametersToBuildBean);
				listOfBeans.add(comment);
			}
			return listOfBeans;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ошибка при поиске комментариев");
			throw new TransactionException("ошибка при создании комментария");
		} finally {
			close(ps);
		}
	}

	@Override
	public boolean delete(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return false;
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

	protected Map<OrderType, String> findIDForComment(Map<OrderType, String> params, Connection cn, String request)
			throws TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(params.get(OrderType.ORDER_ID)));
			Map<OrderType, String> resultMap = new HashMap<OrderType, String>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				resultMap.put(OrderType.USER_ID, Integer.toString(rs.getInt(1)));
				resultMap.put(OrderType.CAR_ID, Integer.toString(rs.getInt(2)));
				resultMap.put(OrderType.USER_SIGNATURE, rs.getString(3));
				break;
			}			
			return resultMap;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ошибка при поиске id");
			throw new TransactionException("ошибка при поиске id");
		} finally {
			close(ps);
		}

	}

	public abstract boolean createComment(Map<OrderType, String> parametersMap) throws DaoException;

	public abstract List<Comment> findCommentToCar(Map<CommentType, String> parametersMap) throws DaoException;

	public abstract boolean updateComment(Map<CommentType, String> parametersMap) throws DaoException;

}
