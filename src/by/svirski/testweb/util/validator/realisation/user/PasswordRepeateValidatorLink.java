package by.svirski.testweb.util.validator.realisation.user;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;

/**
 * class represents validator for repeated passwrd
 * 
 * @author Kostya Svirski 
 * @version 1.0
 */
public class PasswordRepeateValidatorLink extends PasswordValidatorLink{

	public PasswordRepeateValidatorLink() {
	}

	/**
	 * overridden method {@link PasswordValidatorLink#validate(Map)} to check validity of repeated password
	 */
	@Override
	public boolean validate(Map<UserType, String> params) {
		Pattern pattern = Pattern.compile(REG_EXP_PASSWORD);
		Matcher matcher = pattern.matcher(params.get(UserType.REPEAT_PASS));
		if (matcher.matches()) {
			return checkNextLink(params);
		}
		return false;
	}
	
	

}
