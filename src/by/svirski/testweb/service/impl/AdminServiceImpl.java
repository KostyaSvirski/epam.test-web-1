package by.svirski.testweb.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Order;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.dao.DaoFactory;
import by.svirski.testweb.dao.abstracts.AbstractUserDAOImpl;
import by.svirski.testweb.dao.exception.DaoException;
import by.svirski.testweb.service.CustomAdminService;
import by.svirski.testweb.service.exception.ServiceException;

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
	public List<Order> showAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCar(Map<CarType, String> parametersMap) {
		// TODO Auto-generated method stub
		return false;
	}

}
