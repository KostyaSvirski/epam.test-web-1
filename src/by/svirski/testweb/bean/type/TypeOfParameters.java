package by.svirski.testweb.bean.type;

public final class TypeOfParameters {
	
	public static enum UserType {
		LOGIN, PASSWORD, REPEAT_PASS, IS_BLOCKED, ROLE_IN_PROJECT, NAME, SURNAME, 
		PASSPORT_ID, PASSPORT_NUMBER, DATE_OF_BIRTH, EMAIL, PHONE_NUMBER, GENDER, ID, ACTION;
	}
	
	public static enum CarType {
		BRAND, MODEL, CLASS, POWER, ENGINE, ACCELERATION, DRIVE_UNIT, FUEL, COST, IMG, IS_BOOKED, ID;
	}
	
	public static enum OrderType {
		USER_SIGNATURE, ORDER_ID, USER_ID, CAR_ID, DATE_OF_START, DATE_OF_FINISH, COST, CONDITION, BRAND, MODEL;
	}
	
	private TypeOfParameters() {}
}
