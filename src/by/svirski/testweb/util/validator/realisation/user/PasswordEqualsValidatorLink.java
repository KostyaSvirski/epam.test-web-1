package by.svirski.testweb.util.validator.realisation.user;

import java.util.Map;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;

/**
 * class represents checker on equals password and repeated password
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class PasswordEqualsValidatorLink extends PasswordValidatorLink{

	public PasswordEqualsValidatorLink() {
	}

	/**
	 * overridden method {@link PasswordValidatorLink#validate(Map)} to check on equals password and repeated password
	 */
	@Override
	public boolean validate(Map<UserType, String> params) {
		if (!params.get(UserType.PASSWORD).equals(params.get(UserType.REPEAT_PASS))) {
			return false;
		}
		return checkNextLink(params);
	}
	
}
