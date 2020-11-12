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
import by.svirski.testweb.util.validator.CustomValidator;
import by.svirski.testweb.util.validator.impl.DateValidator;

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
		CustomValidator validator = new DateValidator();
		if (validator.validate(parameters.get(OrderType.DATE_OF_START))) {
			CustomParser<Calendar> parser = new DateParser();
			Calendar dateOfStart = null;
			Calendar dateOfFinish = null;
			try {
				dateOfStart = parser.parse(parameters.get(OrderType.DATE_OF_START));
				dateOfFinish = parser.parse(parameters.get(OrderType.DATE_OF_FINISH));
			} catch (CustomParseException e) {
				dateOfStart = new GregorianCalendar();
				logger.log(Level.ERROR, "не возможно распарсить дату");
			}
			order.setDateOfStart(dateOfStart);
			order.setDateOfFinish(dateOfFinish);
		} else {
			logger.log(Level.ERROR, "не корректная дата");
		}
		order.setTotalPrice(Long.parseLong(parameters.get(OrderType.COST)));
		order.setCondition(parameters.get(OrderType.CONDITION));
		order.setSignature(parameters.get(OrderType.USER_SIGNATURE));
		order.setCarBrand(parameters.get(OrderType.BRAND));
		order.setCarModel(parameters.get(OrderType.MODEL));
		return order;
	}
	
	

}
