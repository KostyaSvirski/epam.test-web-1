package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomAdminService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

public class AddCarCommand implements ActionCommand {

	public AddCarCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<CarType, String> parametersMap = new EnumMap<CarType, String>(CarType.class);
		parametersMap.put(CarType.IMG, request.getParameter(RequestParameters.CAR_PHOTO));
		parametersMap.put(CarType.BRAND, request.getParameter(RequestParameters.CAR_BRAND));
		parametersMap.put(CarType.ACCELERATION, request.getParameter(RequestParameters.CAR_ACCELERATION).split(" ")[0]);
		parametersMap.put(CarType.CLASS, request.getParameter(RequestParameters.CAR_CLASS));
		parametersMap.put(CarType.COST, request.getParameter(RequestParameters.CAR_COST).split(" ")[0]);
		parametersMap.put(CarType.DETAIL, request.getParameter(RequestParameters.CAR_DETAIL));
		parametersMap.put(CarType.DRIVE_UNIT, request.getParameter(RequestParameters.CAR_DRIVE_UNIT));
		parametersMap.put(CarType.ENGINE, request.getParameter(RequestParameters.CAR_ENGINE));
		parametersMap.put(CarType.FUEL, request.getParameter(RequestParameters.CAR_FUEL));
		parametersMap.put(CarType.MODEL, request.getParameter(RequestParameters.CAR_MODEL));
		parametersMap.put(CarType.POWER, request.getParameter(RequestParameters.CAR_POWER).split(" ")[0]);
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomAdminService service = factory.getAdminService();
		boolean flag = false;
		try {
			flag = service.addCar(parametersMap);
			if (flag) {
				response.sendRedirect(request.getContextPath() + PagePath.USER_PAGE);
			} else {
				request.setAttribute(RequestParameters.ERROR, "не валидные данные");
				request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
			}
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, RequestParameters.DEFAULT_ERROR);
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}

	}

}
