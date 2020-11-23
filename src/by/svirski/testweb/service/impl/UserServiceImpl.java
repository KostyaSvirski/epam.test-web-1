package by.svirski.testweb.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Penalty;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.bean.type.TypeOfParameters.PenaltyType;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.dao.abstracts.AbstractCommentDAOImpl;
import by.svirski.testweb.dao.abstracts.AbstractOrderDAOImpl;
import by.svirski.testweb.dao.abstracts.AbstractPenaltyDAOImpl;
import by.svirski.testweb.dao.abstracts.AbstractUserDAOImpl;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.service.CustomUserService;
import by.svirski.testweb.service.exception.InvalidParameterException;
import by.svirski.testweb.service.exception.ServiceException;
import by.svirski.testweb.util.validator.CustomValidator;
import by.svirski.testweb.util.validator.impl.DateValidator;
import by.svirski.testweb.util.validator.impl.PhoneValidator;

public class UserServiceImpl implements CustomUserService {

	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

	public UserServiceImpl() {
	}

	@Override
	public boolean registrate(Map<TypeOfParameters.UserType, String> parameters)
			throws ServiceException, InvalidParameterException {
		if(!checkParametersOnEmpty(parameters)) {
			throw new InvalidParameterException("не все поля заполнены");
		}
		if (!parameters.get(UserType.PASSWORD).equals(parameters.get(UserType.REPEAT_PASS))) {
			throw new InvalidParameterException("пароли не сопадают");
		}
		CustomValidator validatorDate = new DateValidator();
		if (!validatorDate.validate(parameters.get(UserType.DATE_OF_BIRTH))) {
			logger.log(Level.INFO, "не валидная дата");
			throw new InvalidParameterException("не валидная дата");
		}
		CustomValidator validatorPhone = new PhoneValidator();
		if (!validatorPhone.validate(parameters.get(UserType.PHONE_NUMBER))) {
			logger.log(Level.INFO, "не валидный телефон");
			throw new InvalidParameterException("не валидный телефон");
		}
		DaoFactory factory = DaoFactory.getInstance();
		AbstractUserDAOImpl dao = factory.getUserDao();
		boolean check = false;
		try {
			check = dao.registrateUser(parameters);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return check;
	}
	
	private boolean checkParametersOnEmpty(Map<UserType, String> parametersMap) {
		for (Entry<UserType, String> entry : parametersMap.entrySet()) {
			String value = entry.getValue();
			if (value == null) {
				return false;
			}
			if (value.isEmpty() || value.isBlank()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public User authorize(Map<UserType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractUserDAOImpl dao = factory.getUserDao();
		User user = null;
		try {
			user = dao.authorizateUser(parameters);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	public User editUser(Map<UserType, String> parameters) throws ServiceException, InvalidParameterException {
		CustomValidator validatorDate = new DateValidator();
		if (!validatorDate.validate(parameters.get(UserType.DATE_OF_BIRTH))) {
			logger.log(Level.INFO, "не валидная дата");
			throw new InvalidParameterException("не валидная дата");
		}
		CustomValidator validatorPhone = new PhoneValidator();
		if (!validatorPhone.validate(parameters.get(UserType.PHONE_NUMBER))) {
			logger.log(Level.INFO, "не валидный телефон");
			throw new InvalidParameterException("не валидный телефон");
		}
		DaoFactory factory = DaoFactory.getInstance();
		AbstractUserDAOImpl dao = factory.getUserDao();
		User updatedUser = null;
		try {
			updatedUser = dao.editUser(parameters);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return updatedUser;
	}

	@Override
	public boolean releaseRent(Map<OrderType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractOrderDAOImpl dao = factory.getOrderDao();
		boolean result = false;
		try {
			result = dao.releaseRent(parameters);
			if(parameters.get(OrderType.INFO) != null) {
				AbstractCommentDAOImpl daoComment = factory.getCommentDao();
				result = daoComment.createComment(parameters);
			}
			if(result) {
				return result;
			} else {
				throw new ServiceException("ошибка в дао");
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Penalty showPenalty(Map<PenaltyType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractPenaltyDAOImpl dao = factory.getPenaltyDao();
		Penalty penalty = null;
		try {
			penalty = dao.showPenalty(parameters);
		} catch (DaoException e) {
			throw new ServiceException("ошибка в дао");
		}
		
		return penalty;
	}

	@Override
	public boolean closePenalty(Map<PenaltyType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractPenaltyDAOImpl dao = factory.getPenaltyDao();
		boolean flag = false;
		try {
			flag = dao.closePenalty(parameters);
		} catch (DaoException e) {
			throw new ServiceException("ошибка в дао");
		}
		return flag;
	}
	
	
	

}
