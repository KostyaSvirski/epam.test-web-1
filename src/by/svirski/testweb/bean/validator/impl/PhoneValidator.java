package by.svirski.testweb.bean.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.validator.CustomValidator;

public class PhoneValidator implements CustomValidator {
	
	private final static String REG_EXP_PHONE = "[+]{1}\\d{12}";

	public PhoneValidator() {
		
	}

	@Override
	public boolean validate(String parameterToValidate) {
		Pattern pattern = Pattern.compile(REG_EXP_PHONE);
		Matcher matcher = pattern.matcher(parameterToValidate);
		return matcher.matches();
	}

}
