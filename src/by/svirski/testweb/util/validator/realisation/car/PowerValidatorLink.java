package by.svirski.testweb.util.validator.realisation.car;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;

public class PowerValidatorLink extends NumberValidatorLink {
	
	public PowerValidatorLink() {
		// TODO Auto-generated constructor stub
	}

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
