package by.svirski.testweb.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;

/**
 * class represents incorrect command
 * 
 * @author Kostya Svirski 
 * @version 1.0
 */
public class IncorrectCommand implements ActionCommand {
	
	public IncorrectCommand() {
	}

	/**
	 * overriden method {@link ActionCommand#execute(HttpServletRequest, HttpServletResponse)} to show incorrect command
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(RequestParameters.ERROR, "не распознана команда");
		request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
	}

}
