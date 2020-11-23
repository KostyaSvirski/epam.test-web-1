package by.svirski.testweb.bean.builder.impl;

import java.util.Map;

import by.svirski.testweb.bean.Penalty;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.type.TypeOfParameters.PenaltyType;

public class PenaltyBuilder implements Builder<Penalty, PenaltyType>{

	public PenaltyBuilder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Penalty build(Map<PenaltyType, String> parameters) {
		Penalty penalty = new Penalty();
		penalty.setId(Integer.parseInt(parameters.get(PenaltyType.ID_PENALTY)));
		penalty.setIdOrder(Integer.parseInt(parameters.get(PenaltyType.ORDER_ID)));
		penalty.setInfo(parameters.get(PenaltyType.INFO));
		penalty.setCostOfPenalty(Long.parseLong(parameters.get(PenaltyType.PENALTY_COST)));
		penalty.setClosed(Boolean.parseBoolean(parameters.get(PenaltyType.IS_CLOSED)));
		return penalty;
	}

}
