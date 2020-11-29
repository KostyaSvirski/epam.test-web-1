package by.svirski.testweb.util.validator.realisation.user;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.util.validator.realisation.IntermidiateUserLink;

public class PhoneValidatorLink extends IntermidiateUserLink {

	private final static String REG_EXP_PHONE = "[+]{1}\\d{12}";

	public PhoneValidatorLink() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(Map<UserType, String> params) {
		Pattern pattern = Pattern.compile(REG_EXP_PHONE);
		Matcher matcher = pattern.matcher(params.get(UserType.PHONE_NUMBER));
		if (matcher.matches()) {
			return checkNextLink(params);
		}
		return false;
	}

}
