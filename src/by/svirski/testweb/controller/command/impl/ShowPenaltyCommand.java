package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.Penalty;
import by.svirski.testweb.bean.type.TypeOfParameters.PenaltyType;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomUserService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

/**
 * class represents command to show penalty 
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class ShowPenaltyCommand implements ActionCommand {

	public ShowPenaltyCommand() {
	}

	/**
	 * overriden method {@link ActionCommand#execute(HttpServletRequest, HttpServletResponse)} to show penalty 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<PenaltyType, String> parametersMap = new EnumMap<PenaltyType, String>(PenaltyType.class);
		parametersMap.put(PenaltyType.ORDER_ID, request.getParameter(RequestParameters.ORDER_ID));
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomUserService service = factory.getUserService();
		Penalty penalty = null;
		try {
			penalty = service.showPenalty(parametersMap);
			request.setAttribute(RequestParameters.PENALTY, penalty);
			request.getServletContext().getRequestDispatcher(PagePath.PENALTY_PAGE).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, RequestParameters.DEFAULT_ERROR);
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}
	}

}
