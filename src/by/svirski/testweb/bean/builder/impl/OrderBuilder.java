package by.svirski.testweb.bean.builder.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Order;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.util.parser.CustomParser;
import by.svirski.testweb.util.parser.exception.CustomParseException;
import by.svirski.testweb.util.parser.impl.DateParser;


public class OrderBuilder implements Builder<Order, OrderType> {

	private static Logger logger = LogManager.getLogger(OrderBuilder.class);

	public OrderBuilder() {
	}

	@Override
	public Order build(Map<OrderType, String> parameters) {
		Order order = new Order();
		order.setId(Integer.parseInt(parameters.get(OrderType.ORDER_ID)));
		order.setIdUser(Integer.parseInt(parameters.get(OrderType.USER_ID)));
		order.setIdCar(Integer.parseInt(parameters.get(OrderType.CAR_ID)));
		CustomParser<Calendar> parser = new DateParser();
		Calendar dateOfStart = null;
		Calendar dateOfFinish = null;
		try {
			dateOfStart = parser.parse(parameters.get(OrderType.DATE_OF_START));
			order.setDateOfStart(dateOfStart);
		} catch (CustomParseException e) {
			dateOfStart = new GregorianCalendar();
			logger.log(Level.ERROR, "не возможно распарсить дату");
		}
		try {
			dateOfFinish = parser.parse(parameters.get(OrderType.DATE_OF_FINISH));
			order.setDateOfFinish(dateOfFinish);
		} catch (CustomParseException e) {
			dateOfFinish = new GregorianCalendar();
			logger.log(Level.ERROR, "не возможно распарсить дату");
		}
		order.setTotalPrice(Long.parseLong(parameters.get(OrderType.COST)));
		order.setCondition(parameters.get(OrderType.CONDITION));
		order.setSignature(parameters.get(OrderType.USER_SIGNATURE));
		order.setCarBrand(parameters.get(OrderType.BRAND));
		order.setCarModel(parameters.get(OrderType.MODEL));
		order.setInfo(parameters.get(OrderType.INFO));
		return order;
	}

}
