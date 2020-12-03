package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomUserService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

/**
 * class represents command to release order from side of user
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class ReleaseOrderUserCommand implements ActionCommand {

	private static Logger logger = LogManager.getLogger(ReleaseOrderUserCommand.class);

	public ReleaseOrderUserCommand() {
	}

	/**
	 * overriden method {@link ActionCommand#execute(HttpServletRequest, HttpServletResponse)} to release order from side of user
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<OrderType, String> parametersMap = new EnumMap<TypeOfParameters.OrderType, String>(OrderType.class);
		parametersMap.put(OrderType.ORDER_ID, request.getParameter(RequestParameters.ORDER_ID));
		parametersMap.put(OrderType.INFO, request.getParameter(RequestParameters.INFO));
		ServiceFactory factory = ServiceFactory.getInstance();
		boolean result = false;
		try {
			CustomUserService service = factory.getUserService();
			result = service.releaseRent(parametersMap);
			if (result) {
				response.sendRedirect(request.getContextPath() + PagePath.USER_PAGE);
			} else {
				logger.log(Level.ERROR, "что-то пошло не так");
				request.setAttribute(RequestParameters.ERROR, "что-то пошло не так");
				request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ошибка в сервисе");
			request.setAttribute(RequestParameters.ERROR, "ошибка в сервисе");
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}

	}

}
