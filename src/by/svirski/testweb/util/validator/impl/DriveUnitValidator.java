package by.svirski.testweb.util.validator.impl;

import by.svirski.testweb.bean.DriveUnit;
import by.svirski.testweb.util.validator.CustomValidator;

public class DriveUnitValidator implements CustomValidator {

	public DriveUnitValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(String parameterToValidate) {
		DriveUnit[] values = DriveUnit.values();
		for (DriveUnit unit : values) {
			if (unit.name().equalsIgnoreCase(parameterToValidate)
					|| unit.toString().equalsIgnoreCase(parameterToValidate)) {
				return true;
			}
		}
		return false;
	}

}
