package by.svirski.testweb.controller.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.util.encryptor.CustomEncryptor;
import by.svirski.testweb.util.encryptor.impl.PasswordEncryptor;

public interface ActionCommand {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException;
	
	default int encryptPassword(String password) {
		CustomEncryptor encryptor = new PasswordEncryptor();
		int encryptedPassword = encryptor.encrypt(password);
		return encryptedPassword;
	}
}
