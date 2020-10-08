package by.svirski.testweb.controller.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException;
}
