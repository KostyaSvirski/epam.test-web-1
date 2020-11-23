package by.svirski.testweb.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Order;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.dao.abstracts.AbstractCarDAOImpl;
import by.svirski.testweb.dao.abstracts.AbstractOrderDAOImpl;
import by.svirski.testweb.dao.abstracts.AbstractPenaltyDAOImpl;
import by.svirski.testweb.dao.abstracts.AbstractUserDAOImpl;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.service.CustomAdminService;
import by.svirski.testweb.service.exception.ServiceException;
import by.svirski.testweb.util.validator.CustomValidator;
import by.svirski.testweb.util.validator.impl.DriveUnitValidator;
import by.svirski.testweb.util.validator.impl.FuelValidator;
import by.svirski.testweb.util.validator.impl.ImageSourceValidator;
import by.svirski.testweb.util.validator.impl.NumberValidatorImpl;

public class AdminServiceImpl implements CustomAdminService {

	private static Logger logger = LogManager.getLogger(AdminServiceImpl.class);

	public AdminServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> showAllUsers() throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractUserDAOImpl dao = factory.getUserDao();
		try {
			List<User> resultList = dao.showAllUsers();
			return resultList;
		} catch (DaoException e) {
			logger.log(Level.ERROR, "ошибка в дао");
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean blockOrUnblockUser(Map<UserType, String> parametersMap) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractUserDAOImpl dao = factory.getUserDao();
		boolean flag = false;
		try {
			if (parametersMap.get(UserType.ACTION).equals(RequestParameters.BLOCK)) {
				flag = dao.blockUser(parametersMap);
			} else if (parametersMap.get(UserType.ACTION).equals(RequestParameters.UNBLOCK)) {
				flag = dao.unblockUser(parametersMap);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	@Override
	public boolean makeAdmin(Map<UserType, String> parametersMap) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractUserDAOImpl dao = factory.getUserDao();
		boolean flag = false;
		try {
			flag = dao.makeAdmin(parametersMap);
		} catch (DaoException e) {
			throw new ServiceException("ошибка в дао");
		}
		return flag;
	}

	@Override
	public User showThisUser(Map<UserType, String> parametersMap) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractUserDAOImpl dao = factory.getUserDao();
		User user = null;
		try {
			user = dao.findThisUser(parametersMap);
		} catch (DaoException e) {
			throw new ServiceException("ошибка в дао");
		}
		return user;
	}

	@Override
	public List<Order> showAllOrders() throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractOrderDAOImpl dao = factory.getOrderDao();
		List<Order> listOfOrders = null;
		try {
			listOfOrders = dao.showAllOrders();
		} catch (DaoException e) {
			throw new ServiceException("ошибка в дао");
		}
		return listOfOrders;
	}

	@Override
	public boolean confirmOrderOfUser(Map<OrderType, String> parametersMap) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractOrderDAOImpl dao = factory.getOrderDao();
		boolean flag = false;
		try {
			flag = dao.confirmOrder(parametersMap);
		} catch (DaoException e) {
			throw new ServiceException("ошибка в дао");
		}
		return flag;
	}

	@Override
	public boolean addCar(Map<CarType, String> parametersMap) throws ServiceException {
		CustomValidator validatorNumber = new NumberValidatorImpl();
		CustomValidator validatorUrl = new ImageSourceValidator();
		CustomValidator validatorFuel = new FuelValidator();
		CustomValidator validatorDriveUnit = new DriveUnitValidator();
		if (validatorNumber.validate(parametersMap.get(CarType.COST))
				&& validatorNumber.validate(parametersMap.get(CarType.ACCELERATION))
				&& validatorNumber.validate(parametersMap.get(CarType.POWER))
				&& validatorUrl.validate(parametersMap.get(CarType.IMG))
				&& validatorFuel.validate(parametersMap.get(CarType.FUEL))
				&& validatorDriveUnit.validate(parametersMap.get(CarType.DRIVE_UNIT))) {
			DaoFactory factory = DaoFactory.getInstance();
			AbstractCarDAOImpl dao = factory.getCarDao();
			boolean flag = false;
			try {
				flag = dao.addCar(parametersMap);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return flag;

		}

		return false;
	}

	@Override
	public boolean denyOrderOfUser(Map<OrderType, String> parametersMap) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractOrderDAOImpl dao = factory.getOrderDao();
		boolean flag = false;
		try {
			flag = dao.denyOrder(parametersMap);
		} catch (DaoException e) {
			throw new ServiceException("ошибка в дао");
		}
		return flag;
	}

	@Override
	public boolean releaseRent(Map<OrderType, String> parametersMap) throws ServiceException {
		DaoFactory factory = DaoFactory.getInstance();
		AbstractOrderDAOImpl dao = factory.getOrderDao();
		boolean result = false;
		try {
			if (parametersMap.get(OrderType.PROBLEMS).equals(RequestParameters.HAVE_PROBLEMS)) {
				result = dao.releaseRentWithPenalty(parametersMap);
				AbstractPenaltyDAOImpl penaltyDao = factory.getPenaltyDao();
				result = penaltyDao.createPenalty(parametersMap);
			} else {
				result = dao.releaseRentFinally(parametersMap);
			}
		} catch (DaoException e) {
			throw new ServiceException("ошибка в дао");
		}
		return result;
	}

}
