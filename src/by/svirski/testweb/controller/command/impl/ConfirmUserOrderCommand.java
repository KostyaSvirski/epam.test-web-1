package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomAdminService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

public class ConfirmUserOrderCommand implements ActionCommand {

	public ConfirmUserOrderCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<OrderType, String> parametersMap = new EnumMap<OrderType, String>(OrderType.class);
		String idOrder = request.getParameter(RequestParameters.ORDER_ID);
		parametersMap.put(OrderType.ORDER_ID, idOrder);
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomAdminService service = factory.getAdminService();
		boolean flag = false;
		try {
			flag = service.confirmOrderOfUser(parametersMap);
			if (flag) {
				response.sendRedirect(request.getContextPath() + PagePath.USER_PAGE);
			} else {
				request.setAttribute(RequestParameters.ERROR, RequestParameters.DEFAULT_ERROR);
				request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
			}
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, RequestParameters.DEFAULT_ERROR);
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}
		

	}

}