package by.svirski.testweb.util.validator.realisation.user;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.util.validator.realisation.IntermidiateUserLink;

/**
 * class represents validator for email
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class EmailValidatorLink extends IntermidiateUserLink{

	private final static String REG_EXP_EMAIL = "[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+";
	
	public EmailValidatorLink() {
	}

	/**
	 * overridden method {@link IntermidiateUserLink#validate(Map)} to check validity of email
	 */
	@Override
	public boolean validate(Map<UserType, String> params) {
		Pattern pattern = Pattern.compile(REG_EXP_EMAIL);
		Matcher matcher = pattern.matcher(params.get(UserType.LOGIN));
		if (matcher.matches()) {
			return checkNextLink(params);
		}
		return false;
	}

}
