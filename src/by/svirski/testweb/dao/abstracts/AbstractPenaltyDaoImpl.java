package by.svirski.testweb.dao.abstracts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Penalty;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.builder.impl.PenaltyBuilder;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.bean.type.TypeOfParameters.PenaltyType;
import by.svirski.testweb.dao.BeanDao;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.exception.TransactionException;

public abstract class AbstractPenaltyDAOImpl implements BeanDao<Penalty, PenaltyType> {

	private static Logger logger = LogManager.getLogger(AbstractPenaltyDAOImpl.class);

	public AbstractPenaltyDAOImpl() {
	}

	@Override
	public boolean insert(List<String> parameters, Connection cn, String request)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(parameters.get(0)));
			ps.setString(2, parameters.get(1));
			ps.setLong(3, Long.parseLong(parameters.get(2)));
			ps.setBoolean(4, Boolean.parseBoolean(parameters.get(3)));
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new TransactionException("ошибка ps");
		} finally {
			close(ps);
		}
	}

	@Override
	public boolean update(Map<PenaltyType, String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(parameters.get(PenaltyType.ID_PENALTY)));
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new TransactionException("ошибка ps");
		} finally {
			close(ps);
		}
	}

	@Override
	public List<Penalty> select(List<String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(request);
			ps.setInt(1, Integer.parseInt(parameters.get(0)));
			ResultSet rs = ps.executeQuery();
			List<Penalty> listOfBeans = new ArrayList<Penalty>();
			while (rs.next()) {
				Map<PenaltyType, String> parametersForBuildPenalty = new EnumMap<PenaltyType, String>(
						PenaltyType.class);
				parametersForBuildPenalty.put(PenaltyType.ID_PENALTY, Integer.toString(rs.getInt(1)));
				parametersForBuildPenalty.put(PenaltyType.ORDER_ID, Integer.toString(rs.getInt(2)));
				parametersForBuildPenalty.put(PenaltyType.INFO, rs.getString(3));
				parametersForBuildPenalty.put(PenaltyType.PENALTY_COST, Long.toString(rs.getLong(4)));
				parametersForBuildPenalty.put(PenaltyType.IS_CLOSED, Boolean.toString(rs.getBoolean(5)));
				Builder<Penalty, PenaltyType> builder = new PenaltyBuilder();
				Penalty penalty = builder.build(parametersForBuildPenalty);
				listOfBeans.add(penalty);
			}
			return listOfBeans;
		} catch (SQLException e) {
			throw new TransactionException("ошибка ps");
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

	public abstract boolean createPenalty(Map<OrderType, String> parameters) throws DaoException;

	public abstract Penalty showPenalty(Map<PenaltyType, String> parameters) throws DaoException;

	public abstract boolean closePenalty(Map<PenaltyType, String> parameters) throws DaoException;

}
