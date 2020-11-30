package by.svirski.testweb.service.impl;

import java.util.Map;

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
import by.svirski.testweb.util.encryptor.CustomEncryptor;
import by.svirski.testweb.util.encryptor.impl.PasswordEncryptor;
import by.svirski.testweb.util.validator.PreparedValidatorsChain;
import by.svirski.testweb.util.validator.realisation.IntermidiateUserLink;
import by.svirski.testweb.util.validator.realisation.user.DateValidatorLink;
import by.svirski.testweb.util.validator.realisation.user.EmailValidatorLink;
import by.svirski.testweb.util.validator.realisation.user.PasswordRepeateValidatorLink;
import by.svirski.testweb.util.validator.realisation.user.PasswordValidatorLink;
import by.svirski.testweb.util.validator.realisation.user.PhoneValidatorLink;

public class UserServiceImpl implements CustomUserService {

	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

	public UserServiceImpl() {
	}

	@Override
	public boolean registrate(Map<TypeOfParameters.UserType, String> parameters)
			throws ServiceException, InvalidParameterException {

		PreparedValidatorsChain<UserType> chain = new IntermidiateUserLink();
		chain.linkWith(new DateValidatorLink()).linkWith(new PhoneValidatorLink()).linkWith(new EmailValidatorLink())
				.linkWith(new PasswordValidatorLink()).linkWith(new PasswordRepeateValidatorLink())
				.linkWith(new PasswordRepeateValidatorLink());
		boolean result = chain.validate(parameters);

		if (result) {
			CustomEncryptor encryptor = new PasswordEncryptor();
			String encryptedPassword = Integer.toString(encryptor.encrypt(parameters.remove(UserType.PASSWORD)));
			parameters.put(UserType.PASSWORD, encryptedPassword);
			parameters.remove(UserType.REPEAT_PASS);
			parameters.put(UserType.REPEAT_PASS, encryptedPassword);
			DaoFactory factory = DaoFactory.getInstance();
			AbstractUserDAOImpl dao = factory.getUserDao();
			boolean flag = false;
			try {
				flag = dao.registrateUser(parameters);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return flag;
		}
		logger.log(Level.INFO, "не валидные параметры");
		throw new InvalidParameterException("не валидные параметры");
	}

	@Override
	public User authorize(Map<UserType, String> parameters) throws ServiceException {
		CustomEncryptor encryptor = new PasswordEncryptor();
		String encryptedPassword = Integer.toString(encryptor.encrypt(parameters.remove(UserType.PASSWORD)));
		parameters.put(UserType.PASSWORD, encryptedPassword);
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

		PreparedValidatorsChain<UserType> chain = new IntermidiateUserLink();
		chain.linkWith(new DateValidatorLink()).linkWith(new PhoneValidatorLink());
		boolean result = chain.validate(parameters);

		if (result) {
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
		logger.log(Level.INFO, "не валидные параметры");
		throw new InvalidParameterException("не валидные данные");
	}

	@Override
	public boolean releaseRent(Map<OrderType, String> parameters) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractOrderDAOImpl dao = factory.getOrderDao();
		boolean result = false;
		try {
			result = dao.releaseRent(parameters);
			if (parameters.get(OrderType.INFO) != null) {
				AbstractCommentDAOImpl daoComment = factory.getCommentDao();
				result = daoComment.createComment(parameters);
			}
			if (result) {
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
