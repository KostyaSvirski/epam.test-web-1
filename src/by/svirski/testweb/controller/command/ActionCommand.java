package by.svirski.testweb.controller.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interface represents base interface to all commands
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public interface ActionCommand {
	/**
	 * method for executing request
	 * 
	 * @param request - request which is came from page
	 * @param response - response which should be deliver to page back
	 * @throws UnsupportedEncodingException - if characters in request can't be ridden with this encoding 
	 * @throws IOException - if error occurs by writing page back
	 * @throws ServletException - if servlet can't process this request
	 */
	void execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException;
	
}
