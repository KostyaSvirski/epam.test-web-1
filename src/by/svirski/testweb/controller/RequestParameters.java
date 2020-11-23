package by.svirski.testweb.controller;

public final class RequestParameters {

	private RequestParameters() {
	}
	
	//LOCALE
	public static final String LANGUAGE = "lang";
	public static final String DEFAULT_LANG = "en_EN";
	
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
	public static final String CAR_PHOTO = "photo";
	public static final String CAR_BRAND = "brand";
	public static final String CAR_MODEL = "model";
	public static final String CAR_CLASS = "class";
	public static final String CAR_POWER = "power";
	public static final String CAR_ENGINE = "engine";
	public static final String CAR_ACCELERATION = "acceleration";
	public static final String CAR_DRIVE_UNIT= "drive_unit";
	public static final String CAR_FUEL= "fuel";
	public static final String CAR_COST= "cost";
	public static final String CAR_DETAIL= "detail";
	public static final String IS_BOOKED = "is_booked";
	public static final String COMMENTS = "comments";

	
	//EDIT
	public static final String USER = "user";
	public static final String USER_ID = "id_user";
	public static final String ACTION = "action";
	public static final String BLOCK = "block";
	public static final String UNBLOCK = "unblock";
	
	//PAGE
	public static final String CURRENT_PAGE = "currentPage";
	
	//RENT
	public static final String DATE_OF_START = "startDate";
	public static final String DATE_OF_FINISH = "finishDate";
	public static final String USER_SIGNATURE = "signature";
	public static final String ORDER_COST = "cost";
	public static final String CONDITION = "condition";
	public static final String ORDERS = "orders";
	public static final String ORDER_ID = "order_id";
	public static final String PROBLEMS = "problems";
	public static final String HAVE_PROBLEMS = "YES";
	public static final String NO_PROBLEMS = "NO";
	public static final String INFO = "info";
	
	//ADMIN
	public static final String USERS = "users";
	
	//RESPONSE
	public static final String DEFAULT_ERROR = "Что-то пошло не так соре";

	//PENALTY
	public static final String PENALTY = "penalty";
	public static final String PENALTY_ID = "id";

	
}
