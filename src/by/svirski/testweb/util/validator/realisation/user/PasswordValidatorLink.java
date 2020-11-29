package by.svirski.testweb.util.validator.realisation.user;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.util.validator.realisation.IntermidiateUserLink;

public class PasswordValidatorLink extends IntermidiateUserLink{

	protected final static String REG_EXP_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}";
	
	public PasswordValidatorLink() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(Map<UserType, String> params) {
		Pattern pattern = Pattern.compile(REG_EXP_PASSWORD);
		Matcher matcher = pattern.matcher(params.get(UserType.PASSWORD));
		if (matcher.matches()) {
			return checkNextLink(params);
		}
		return false;
	}

}
