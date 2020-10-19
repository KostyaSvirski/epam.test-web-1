package by.svirski.testweb.bean.type;

public final class TypeOfParameters {
	
	public static enum UserType {
		LOGIN, PASSWORD, IS_BLOCKED, ROLE_IN_PROJECT, NAME, SURNAME, PASSPORT_ID, PASSPORT_NUMBER, DATE_OF_BIRTH, EMAIL, PHONE_NUMBER, GENDER, ID
	}
	
	public static enum CarType {
		BRAND, MODEL, CAR_CLASS, POWER, ENGINE, ACCELERATION, DRIVE_UNIT, FUEL, COST, IMG, IS_BOOCKED;
	}
	
	private TypeOfParameters() {}
}
