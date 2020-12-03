package by.svirski.testweb.dao.abstracts.realisation;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Penalty;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.bean.type.TypeOfParameters.PenaltyType;
import by.svirski.testweb.dao.abstracts.AbstractPenaltyDAOImpl;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.exception.TransactionException;
import by.svirski.testweb.dao.pool.ConnectionPool;

public class PenaltyDAO extends AbstractPenaltyDAOImpl {

	private static Logger logger = LogManager.getLogger(PenaltyDAO.class);

	private static final String CREATE_PENALTY = "insert into penalty (id_order, info, total, is_closed) values (?, ?, ?, ?)";
	private static final String SHOW_PENALTY = "select id_penalty, id_order, info, total, is_closed from penalty where id_order = ?";
	private static final String UPDATE_CLOSED = "update penalty set is_closed = true where id_penalty = ?";

	public PenaltyDAO() {
	}

	@Override
	public boolean createPenalty(Map<OrderType, String> parameters) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			List<String> parametersList = createListForPenalty(parameters);
			boolean flag = false;
			try {
				flag = insert(parametersList, cn, CREATE_PENALTY);
				return flag;
			} catch (TransactionException e) {
				throw new DaoException(e);
			}

		} finally {
			close(cn);
		}
	}

	private List<String> createListForPenalty(Map<OrderType, String> parameters) {
		List<String> parametersList = new ArrayList<String>();
		parametersList.add(parameters.get(OrderType.ORDER_ID));
		parametersList.add(parameters.get(OrderType.INFO));
		parametersList.add(parameters.get(OrderType.PENALTY));
		parametersList.add(Boolean.toString(false));
		return parametersList;
	}

	@Override
	public Penalty showPenalty(Map<PenaltyType, String> parameters) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			List<String> listForSelect = new ArrayList<String>();
			listForSelect.add(parameters.get(PenaltyType.ORDER_ID));
			List<Penalty> listOfBeans = null;
			try {
				listOfBeans = select(listForSelect, SHOW_PENALTY, cn);
			} catch (TransactionException e) {
				throw new DaoException(e);
			}
			if (listOfBeans != null && listOfBeans.size() == 1) {
				return listOfBeans.get(0);
			}
		} finally {
			close(cn);
		}
		return null;
	}

	@Override
	public boolean closePenalty(Map<PenaltyType, String> parameters) throws DaoException {
		Connection cn = null;
		try {
			try {
				cn = ConnectionPool.getInstance().getConnection();
			} catch (ConnectionPoolException e) {
				logger.log(Level.ERROR, "соеденение не было получено");
				throw new DaoException("соеденение не было получено", e);
			}
			boolean flag = false;
			flag = update(parameters, UPDATE_CLOSED, cn);
			return flag;
		} catch (TransactionException e) {
			throw new DaoException(e);
		} finally {
			close(cn);
		}
	}

}
