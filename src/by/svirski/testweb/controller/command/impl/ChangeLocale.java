package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;

public class ChangeLocale implements ActionCommand {

	public ChangeLocale() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		HttpSession session = request.getSession();
		session.removeAttribute(RequestParameters.LANGUAGE);
		String newLocale = request.getParameter(RequestParameters.LANGUAGE);
		session.setAttribute(RequestParameters.LANGUAGE, newLocale);
		request.getServletContext().getRequestDispatcher(PagePath.INDEX_PAGE).forward(request, response);
	}

}
