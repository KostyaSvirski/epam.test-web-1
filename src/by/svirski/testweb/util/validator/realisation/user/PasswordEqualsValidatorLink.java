package by.svirski.testweb.util.validator.realisation.user;

import java.util.Map;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;

public class PasswordEqualsValidatorLink extends PasswordValidatorLink{

	public PasswordEqualsValidatorLink() {
	}

	@Override
	public boolean validate(Map<UserType, String> params) {
		if (!params.get(UserType.PASSWORD).equals(params.get(UserType.REPEAT_PASS))) {
			return false;
		}
		return checkNextLink(params);
	}
	
}
