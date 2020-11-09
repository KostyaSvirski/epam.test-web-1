package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomCarService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;

public class CarShowCommand implements ActionCommand {

	public CarShowCommand() {

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<TypeOfParameters.CarType, String> parametersMap = new EnumMap<TypeOfParameters.CarType, String>(
				TypeOfParameters.CarType.class);
		parametersMap = putParametersIntoMap(parametersMap, request);
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomCarService service = factory.getCarService();
		try {
			List<Car> listOfBeans = service.showCars(parametersMap);
			request.setAttribute(RequestParameters.CARS, listOfBeans);
			request.getServletContext().getRequestDispatcher(PagePath.CARS_PAGE).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, e);
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}

	}

	private Map<CarType, String> putParametersIntoMap(Map<CarType, String> parametersMap, HttpServletRequest request) {
		Map<String, String[]> parametersFromPage = request.getParameterMap();
		CarType[] carTypeValues = CarType.values();
		for (Entry<String, String[]> entry : parametersFromPage.entrySet()) {
			if (entry.getValue() != null && (!entry.getValue()[0].isBlank() || !entry.getValue()[0].isEmpty())) {
				for (CarType type : carTypeValues) {
					if (type.name().equalsIgnoreCase(entry.getKey())) {
						parametersMap.put(type, entry.getValue()[0]);
					}
				}
			}
		}
		return parametersMap;
	}

}
