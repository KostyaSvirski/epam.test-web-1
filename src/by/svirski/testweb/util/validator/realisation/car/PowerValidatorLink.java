package by.svirski.testweb.util.validator.realisation.car;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;

/**
 * class represents validator for power
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class PowerValidatorLink extends NumberValidatorLink {
	
	public PowerValidatorLink() {
	}

	/**
	 * overridden method {@link NumberValidatorLink#validate(Map)} to check validity of power
	 */
	@Override
	public boolean validate(Map<CarType, String> params) {
		Pattern pattern = Pattern.compile(NUMBER_REGEXP);
		Matcher matcher = pattern.matcher(params.get(CarType.POWER));
		if (matcher.matches()) {
			return checkNextLink(params);
		}
		return false;
	}

}
