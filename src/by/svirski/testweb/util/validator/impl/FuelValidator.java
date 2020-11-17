package by.svirski.testweb.util.validator.impl;

import by.svirski.testweb.bean.Fuel;
import by.svirski.testweb.util.validator.CustomValidator;

public class FuelValidator implements CustomValidator {

	public FuelValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(String parameterToValidate) {
		Fuel[] values = Fuel.values();
		for (Fuel fuel : values) {
			if (fuel.name().equalsIgnoreCase(parameterToValidate)
					|| fuel.toString().equalsIgnoreCase(parameterToValidate)) {
				return true;
			}
		}
		return false;
	}

}
