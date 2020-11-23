package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.Car;
import by.svirski.testweb.bean.Comment;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.builder.impl.CarBuilder;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.bean.type.TypeOfParameters.CommentType;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomCarService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

public class ShowDetailCarCommand implements ActionCommand {

	public ShowDetailCarCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<CarType, String> carTypeMap = new EnumMap<CarType, String>(CarType.class);
		carTypeMap.put(CarType.ID, request.getParameter(RequestParameters.CAR_ID));
		carTypeMap.put(CarType.ACCELERATION, request.getParameter(RequestParameters.CAR_ACCELERATION));
		carTypeMap.put(CarType.BRAND, request.getParameter(RequestParameters.CAR_BRAND));
		carTypeMap.put(CarType.CLASS, request.getParameter(RequestParameters.CAR_CLASS));
		carTypeMap.put(CarType.COST, request.getParameter(RequestParameters.CAR_COST));
		carTypeMap.put(CarType.DETAIL, request.getParameter(RequestParameters.CAR_DETAIL));
		carTypeMap.put(CarType.DRIVE_UNIT, request.getParameter(RequestParameters.CAR_DRIVE_UNIT));
		carTypeMap.put(CarType.ENGINE, request.getParameter(RequestParameters.CAR_ENGINE));
		carTypeMap.put(CarType.FUEL, request.getParameter(RequestParameters.CAR_FUEL));
		carTypeMap.put(CarType.IMG, request.getParameter(RequestParameters.CAR_PHOTO));
		carTypeMap.put(CarType.MODEL, request.getParameter(RequestParameters.CAR_MODEL));
		carTypeMap.put(CarType.POWER, request.getParameter(RequestParameters.CAR_POWER));
		carTypeMap.put(CarType.IS_BOOKED, request.getParameter(RequestParameters.IS_BOOKED));
		Builder<Car, CarType> builder = new CarBuilder();
		Car car = builder.build(carTypeMap);
		
		Map<CommentType, String> parametersMap = new EnumMap<CommentType, String>(CommentType.class);
		parametersMap.put(CommentType.ID_CAR, request.getParameter(RequestParameters.CAR_ID));
		ServiceFactory factory = ServiceFactory.getInstance(); 
		CustomCarService service = factory.getCarService();
		try {
			List<Comment> listOfComments = service.showComments(parametersMap);
			request.setAttribute(RequestParameters.COMMENTS, listOfComments);
			request.setAttribute(RequestParameters.CAR, car);
			request.getServletContext().getRequestDispatcher(PagePath.DETAIL_PAGE).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, RequestParameters.DEFAULT_ERROR);
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}
		
	}

}
