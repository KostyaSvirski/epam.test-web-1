package by.svirski.testweb.bean.type;

/**
 * class represents keys for map with parameters 
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public final class TypeOfParameters {

	/**
	 * enumeration represents keys for map with parameters for users actions
	 */
	public static enum UserType {
		LOGIN, PASSWORD, REPEAT_PASS, IS_BLOCKED, ROLE_IN_PROJECT, NAME, SURNAME, PASSPORT_ID, PASSPORT_NUMBER,
		DATE_OF_BIRTH, EMAIL, PHONE_NUMBER, GENDER, ID, ACTION, BLOCKED, NOT_BLOCKED;
	}

	/**
	 * enumeration represents keys for map with parameters for cars actions
	 */
	public static enum CarType {
		BRAND, MODEL, CLASS, POWER, ENGINE, ACCELERATION, DRIVE_UNIT, FUEL, COST, IMG, IS_BOOKED, ID, DETAIL;
	}
	
	/**
	 * enumeration represents keys for map with parameters for orders actions
	 */
	public static enum OrderType {
		USER_SIGNATURE, ORDER_ID, USER_ID, CAR_ID, DATE_OF_START, DATE_OF_FINISH, COST, CONDITION, BRAND, MODEL, INFO,
		PROBLEMS, PENALTY, IS_CLOSED;
	}

	/**
	 * enumeration represents keys for map with parameters for penalties actions
	 */
	public static enum PenaltyType {
		ID_PENALTY, ORDER_ID, PENALTY_COST, INFO, IS_CLOSED;
	}
	
	/**
	 * enumeration represents keys for map with parameters for comments actions
	 */
	public static enum CommentType {
		ID_COMMENT, ID_CAR, ID_USER, COMMENT, SIGNATURE; 
	}

	private TypeOfParameters() {
	}
}
