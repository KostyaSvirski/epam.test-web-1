package by.svirski.testweb.controller;

import java.util.ArrayList;
import java.util.List;

public final class UrlPatterns {

	private UrlPatterns() {
	}
	
	public static final String SERVLET = "/MainController";
	public static final String JSP = "*.jsp";
	public static final String ALL = "/*";
	public static final String PREFIX = "/test-web-project/";
	
	public static final String MY_PAGE = "/my_page.jsp";
	public static final String ORDERS = "/orders.jsp";
	public static final String SIGN_OUT = "/sign_out.jsp";
	public static final String ERROR_PAGE = "/error_page.jsp";
	public static final String RENT_AUTO = "/rent_auto.jsp";
	public static final String EDIT_USER = "/edit_user.jsp";
	public static final String ADD_CAR = "/add_car.jsp";
	public static final String CARS = "/cars.jsp";
	public static final String DETAIL_DENY = "/detail_deny.jsp";
	public static final String DETAIL_RELEASE = "/detail_release.jsp";
	public static final String DETAIL = "/detail.jsp";
	public static final String INDEX = "/index.jsp";	
	public static final String PENALTY = "/penalty.jsp";
	public static final String SIGN_IN = "/sign_in.jsp";
	public static final String SIGN_UP = "/sign_up.jsp";
	public static final String THIS_USER = "/this_user.jsp";
	public static final String USERS = "/users.jsp";
	public static final String WELCOME = "/welcome.jsp";
	public static final String NOT_FOUND = "/not_found.jsp";
	
	public static List<String> getAllUrls() {
		List<String> listOfUrls = new ArrayList<String>();
		listOfUrls.add(DETAIL);
		listOfUrls.add(MY_PAGE);
		listOfUrls.add(ORDERS);
		listOfUrls.add(SIGN_OUT);
		listOfUrls.add(SIGN_IN);
		listOfUrls.add(SIGN_UP);
		listOfUrls.add(ERROR_PAGE);
		listOfUrls.add(RENT_AUTO);
		listOfUrls.add(EDIT_USER);
		listOfUrls.add(ADD_CAR);
		listOfUrls.add(CARS);
		listOfUrls.add(DETAIL_DENY);
		listOfUrls.add(DETAIL);
		listOfUrls.add(DETAIL_RELEASE);
		listOfUrls.add(INDEX);
		listOfUrls.add(PENALTY);
		listOfUrls.add(THIS_USER);
		listOfUrls.add(USERS);
		listOfUrls.add(WELCOME);
		listOfUrls.add(NOT_FOUND);
		return listOfUrls;
	}
}
