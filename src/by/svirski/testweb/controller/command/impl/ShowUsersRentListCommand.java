package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.Order;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomAdminService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

/**
 * class represents command to show orders from all users from side of admin
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class ShowUsersRentListCommand implements ActionCommand {

	public ShowUsersRentListCommand() {
	}

	/**
	 * overriden method {@link ActionCommand#execute(HttpServletRequest, HttpServletResponse)} to show orders from all users from side of admin
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomAdminService service = factory.getAdminService();
		try {
			List<Order> listOfOrders = service.showAllOrders();
			request.setAttribute(RequestParameters.ORDERS, listOfOrders);
			request.getServletContext().getRequestDispatcher(PagePath.ORDERS_PAGE).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, RequestParameters.DEFAULT_ERROR);
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}
		
 
	}

}
