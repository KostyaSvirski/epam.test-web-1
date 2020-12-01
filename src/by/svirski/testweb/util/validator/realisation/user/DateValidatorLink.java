package by.svirski.testweb.util.validator.realisation.user;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.util.validator.realisation.IntermidiateUserLink;

/**
 * class represents validator for date
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class DateValidatorLink extends IntermidiateUserLink {

	private final static String REG_EXP_DATE = "\\d{4}[.-]{1}\\d{1,2}[.-]{1}\\d{1,2}";

	public DateValidatorLink() {
	}

	/**
	 * overridden method {@link IntermidiateUserLink#validate(Map)} to check validity of date
	 */
	@Override
	public boolean validate(Map<UserType, String> params) {
		Pattern pattern = Pattern.compile(REG_EXP_DATE);
		Matcher matcher = pattern.matcher(params.get(UserType.DATE_OF_BIRTH));
		if (matcher.matches()) {
			return checkNextLink(params);
		}
		return false;
	}

}
