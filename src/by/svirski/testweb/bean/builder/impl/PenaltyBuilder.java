package by.svirski.testweb.bean.builder.impl;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Penalty;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.type.TypeOfParameters.PenaltyType;

/**
 * class represents builder for Penalty Bean
 * 
 * @see Penalty
 * @see PenaltyType
 * @see Builder
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class PenaltyBuilder implements Builder<Penalty, PenaltyType>{
	
	private static Logger logger = LogManager.getLogger(PenaltyBuilder.class);
	
	/**
	 * default constructor
	 */
	public PenaltyBuilder() {
	}

	/**
	 * overriden method {@link Builder#build(Map)} for build Penalty Bean
	 */
	@Override
	public Penalty build(Map<PenaltyType, String> parameters) {
		Penalty penalty = new Penalty();
		penalty.setId(Integer.parseInt(parameters.get(PenaltyType.ID_PENALTY)));
		penalty.setIdOrder(Integer.parseInt(parameters.get(PenaltyType.ORDER_ID)));
		penalty.setInfo(parameters.get(PenaltyType.INFO));
		penalty.setCostOfPenalty(Long.parseLong(parameters.get(PenaltyType.PENALTY_COST)));
		penalty.setClosed(Boolean.parseBoolean(parameters.get(PenaltyType.IS_CLOSED)));
		logger.log(Level.DEBUG, "построен объект Penalty");
		return penalty;
	}

}
