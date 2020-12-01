package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Order;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomCarService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

/**
 * class represents coomand to rent list of user from side of user
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class ShowRentListCommand implements ActionCommand {
	
	private static Logger logger = LogManager.getLogger(ShowRentListCommand.class);

	public ShowRentListCommand() {
	}

	/**
	 * overriden method {@link ActionCommand#execute(HttpServletRequest, HttpServletResponse)} to show rent list of user from side of user
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		User user = (User) request.getSession().getAttribute(RequestParameters.USER);
		Map<UserType, String> parametersMap = new EnumMap<TypeOfParameters.UserType, String>(UserType.class);
		parametersMap.put(UserType.ID, Integer.toString(user.getId()));
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomCarService service = factory.getCarService();
		try {
			List<Order> orders = service.showOrders(parametersMap);
			request.setAttribute(RequestParameters.ORDERS, orders);
			request.getServletContext().getRequestDispatcher(PagePath.ORDERS_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ошибка в сервисе");
			request.setAttribute(RequestParameters.ERROR, "что-то пошло не так");
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}
		

	}

}
