package by.svirski.testweb.util.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.util.validator.CustomValidator;

public class NumberValidatorImpl implements CustomValidator {
	
	private static final String NUMBER_REGEXP = "[\\d|(.)]{0,}";

	public NumberValidatorImpl() {
	}

	@Override
	public boolean validate(String parameterToValidate) {
//		String[] split = parameterToValidate.split(" ");
		Pattern pattern = Pattern.compile(NUMBER_REGEXP);
		Matcher matcher = pattern.matcher(parameterToValidate);
		return matcher.matches();
	}

}
