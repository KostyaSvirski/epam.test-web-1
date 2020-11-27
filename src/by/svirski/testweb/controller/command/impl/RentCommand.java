package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomCarService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

public class RentCommand implements ActionCommand {

	private static Logger logger = LogManager.getLogger(RentCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		HttpSession session = request.getSession();
		User user = null;
		try {
			user = (User) session.getAttribute(RequestParameters.USER);
		} catch (IllegalStateException e) {
			logger.log(Level.ERROR, "сессия не работает");
			request.setAttribute(RequestParameters.ERROR, "сессия не работает");
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
			return;
		}
		if (user != null) {
			Map<TypeOfParameters.OrderType, String> parametersMap = new EnumMap<TypeOfParameters.OrderType, String>(
					OrderType.class);
			parametersMap.put(OrderType.USER_ID, Integer.toString(user.getId()));
			parametersMap.put(OrderType.CAR_ID, request.getParameter(RequestParameters.CAR_ID));
			parametersMap.put(OrderType.USER_SIGNATURE, request.getParameter(RequestParameters.USER_SIGNATURE));
			parametersMap.put(OrderType.DATE_OF_START, request.getParameter(RequestParameters.DATE_OF_START));
			parametersMap.put(OrderType.DATE_OF_FINISH, request.getParameter(RequestParameters.DATE_OF_FINISH));
			String cost = request.getParameter(RequestParameters.ORDER_COST);
			parametersMap.put(OrderType.COST, cost.substring(0, cost.indexOf(" $")));
			ServiceFactory factory = ServiceFactory.getInstance();
			CustomCarService service = factory.getCarService();
			try {
				boolean result = service.rentAuto(parametersMap);
				if(result) {
					response.sendRedirect(request.getContextPath() + PagePath.USER_PAGE);
				} else {
					logger.log(Level.ERROR, "ошибка в сервисе");
					request.setAttribute(RequestParameters.ERROR, RequestParameters.DEFAULT_ERROR);
					request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
				}
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "ошибка в сервисе");
				request.setAttribute(RequestParameters.ERROR, RequestParameters.DEFAULT_ERROR);
				request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
			}
		} else {
			logger.log(Level.ERROR, "атрибуты не найдены");
			request.setAttribute(RequestParameters.ERROR, "атрибуты не найдены");
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
			return;
		}
	}

}
