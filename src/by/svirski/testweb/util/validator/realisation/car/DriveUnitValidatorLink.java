package by.svirski.testweb.util.validator.realisation.car;

import java.util.Map;

import by.svirski.testweb.bean.DriveUnit;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.validator.realisation.IntermidiateCarLink;

public class DriveUnitValidatorLink extends IntermidiateCarLink {

	public DriveUnitValidatorLink() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(Map<CarType, String> params) {
		DriveUnit[] values = DriveUnit.values();
		for (DriveUnit unit : values) {
			if (unit.name().equalsIgnoreCase(params.get(CarType.DRIVE_UNIT))
					|| unit.toString().equalsIgnoreCase(params.get(CarType.DRIVE_UNIT))) {
				return checkNextLink(params);
			}
		}
		return false;
	}

}
