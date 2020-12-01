package by.svirski.testweb.util.validator.realisation.car;

import java.util.Map;

import by.svirski.testweb.bean.DriveUnit;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.validator.realisation.IntermidiateCarLink;

/**
 * class represents validator for drive unit
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class DriveUnitValidatorLink extends IntermidiateCarLink {

	public DriveUnitValidatorLink() {
	}

	/**
	 * overridden method {@link IntermidiateCarLink#validate(Map)} to check validity of drive unit
	 * @see DriveUnit
	 */
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
