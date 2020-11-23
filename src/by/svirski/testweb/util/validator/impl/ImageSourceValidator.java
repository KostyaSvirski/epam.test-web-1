package by.svirski.testweb.util.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.util.validator.CustomValidator;

public class ImageSourceValidator implements CustomValidator {

	private static final String URL_REGEXP = "http://\\w+(.){0,}(.)[a-z]+/.{0,}(.)(png)|"
			+ "http://\\w+(.){0,}(.)[a-z]+/.{0,}(.)(jpg)|"
			+ "http://\\w+(.){0,}(.)[a-z]+/(.){0,}(.)(jpeg)|"
			+ "https://\\w+(.){0,}(.)[a-z]+/(.){0,}(.)(png)|"
			+ "https://\\w+(.){0,}(.)[a-z]+/(.){0,}(.)(jpg)|"
			+ "https://\\w+(.){0,}(.)[a-z]+/(.){0,}(.)(jpeg)";

	public ImageSourceValidator() {
	}

	@Override
	public boolean validate(String parameterToValidate) {
		Pattern pattern = Pattern.compile(URL_REGEXP);
		Matcher matcher = pattern.matcher(parameterToValidate);
		return matcher.matches();
	}

}
