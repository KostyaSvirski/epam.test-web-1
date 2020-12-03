package by.svirski.testweb.controller.command.impl;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.controller.PagePath;

/**
 * class represents command to log out from system 
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class LogOutCommand implements ActionCommand {

	public LogOutCommand() {
	}

	/**
	 * overriden method {@link ActionCommand#execute(HttpServletRequest, HttpServletResponse)} to log out
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + PagePath.INDEX_PAGE);
	}

}
