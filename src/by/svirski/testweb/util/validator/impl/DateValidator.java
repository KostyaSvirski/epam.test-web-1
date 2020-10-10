package by.svirski.testweb.util.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.util.validator.CustomValidator;

public class DateValidator implements CustomValidator {

	private final static String REG_EXP_DATE = "\\d{1,2}[.-]{1}\\d{1,2}[.-]{1}\\d{4}";
	
	public DateValidator() {
		
	}

	@Override
	public boolean validate(String parameterToValidate) {
		Pattern pattern = Pattern.compile(REG_EXP_DATE);
		Matcher matcher = pattern.matcher(parameterToValidate);
		return matcher.matches();
	}

}
