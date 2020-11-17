package by.svirski.testweb.dao.abstracts;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import by.svirski.testweb.bean.Penalty;
import by.svirski.testweb.bean.type.TypeOfParameters.PenaltyType;
import by.svirski.testweb.dao.BeanDao;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.dao.exception.TransactionException;

public abstract class AbstractPenaltyDaoImpl implements BeanDao<Penalty, PenaltyType> {

	public AbstractPenaltyDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(List<String> paramters, Connection cn, String request)
			throws DaoException, TransactionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Map<PenaltyType, String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Penalty> select(List<String> parameters, String request, Connection cn)
			throws DaoException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
