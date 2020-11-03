package by.svirski.testweb.controller;

public final class RequestParameters {

	private RequestParameters() {
	}
	
	//LOCALE
	public static final String LANGUAGE = "lang";
	
	//CONTROLLER
	public static final String COMMAND = "command";
	public static final String CHAR_ENCODDING = "UTF-8";
	
	//AUTHORITHATION
	public static final String LOGIN = "login";
	public static final String PASSWORD = "pass";
	public static final String ERROR = "type_error";
	public static final String MESSAGE = "message"; 
	public static final String COLLOR = "collor"; 
	
	//REGISTRATION
	public static final String NAME = "name";
	public static final String SURNAME = "surname";
	public static final String REPEAT_PASSWORD = "pass_repeat";
	public static final String GENDER = "gender";
	public static final String PASSPORT_ID = "passport_id";
	public static final String PASSPORT_NUMBER = "passport_number";
	public static final String DATE_OF_BIRTH = "date_of_birth";
	public static final String PHONE = "phone";
	
	//WELCOME
	public static final String COUNT_USERS = "count";
	
	//CARS
	public static final String CARS = "cars";
	public static final String CAR = "car";
	public static final String CAR_ID = "id";
	
	//EDIT
	public static final String USER = "user";
	
	//REQUEST
	public static final String CURRENT_PAGE = "currentPage";
	
	//RENT
	public static final String DATE_OF_START = "startDate";
	public static final String DATE_OF_FINISH = "finishDate";
	public static final String USER_SIGNATURE = "signature";
	public static final String ORDER_COST = "cost";
	public static final String CONDITION = "condition";
	public static final String ORDERS = "orders";
	public static final String ORDER_ID = "order_id";
	
	
}
