package by.svirski.testweb.util.validator.realisation.car;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;

public class AccelerationValidatorLink extends NumberValidatorLink {

	public AccelerationValidatorLink() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(Map<CarType, String> params) {
		Pattern pattern = Pattern.compile(NUMBER_REGEXP);
		Matcher matcher = pattern.matcher(params.get(CarType.ACCELERATION));
		if (matcher.matches()) {
			return checkNextLink(params);
		} 
		return false;
	}

}
