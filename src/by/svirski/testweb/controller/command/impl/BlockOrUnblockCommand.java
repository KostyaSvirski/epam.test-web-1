package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomAdminService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

public class BlockOrUnblockCommand implements ActionCommand {

	public BlockOrUnblockCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<UserType, String> parametersMap = new EnumMap<UserType, String>(UserType.class);
		String idUser = request.getParameter(RequestParameters.USER_ID);
		String action = request.getParameter(RequestParameters.ACTION);
		parametersMap.put(UserType.ID, idUser);
		parametersMap.put(UserType.ACTION, action);
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomAdminService service = factory.getAdminService();
		try {
			boolean result = service.blockOrUnblockUser(parametersMap);
			if(result) {
				response.sendRedirect(request.getContextPath() + PagePath.USER_PAGE);
			} else {
				request.setAttribute(RequestParameters.ERROR, RequestParameters.DEFAULT_ERROR);
				request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
			}
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, e.getMessage());
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}		

	}

}
