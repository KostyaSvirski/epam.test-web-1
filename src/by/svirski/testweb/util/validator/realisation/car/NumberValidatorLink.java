package by.svirski.testweb.util.validator.realisation.car;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.validator.realisation.IntermidiateCarLink;

/**
 * class represents validator for numeric data
 * @author костя
 *
 */
public class NumberValidatorLink extends IntermidiateCarLink {

	protected static final String NUMBER_REGEXP = "[\\d|(.)]{0,}";

	public NumberValidatorLink() {
	}

	/**
	 * overridden method {@link IntermidiateCarLink#validate(Map)} to check validity of cost
	 */
	@Override
	public boolean validate(Map<CarType, String> params) {
		Pattern pattern = Pattern.compile(NUMBER_REGEXP);
		Matcher matcher = pattern.matcher(params.get(CarType.COST));
		if (matcher.matches()) {
			return checkNextLink(params);
		}
		return false;
	}

}
