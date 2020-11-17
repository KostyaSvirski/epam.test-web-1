package by.svirski.testweb.util.validator;

import java.util.Map;

import by.svirski.testweb.bean.type.TypeOfParameters;

public abstract class PreparedValidatorsChain {
	
	private CustomValidator nextValidator;

	public PreparedValidatorsChain() {
	}
	
	public CustomValidator linkWith(CustomValidator nextValidator) {
		this.nextValidator = nextValidator;
		return nextValidator;
	}
	
	protected boolean checkNext(Map<TypeOfParameters, String> parametersToValidate) {
		if(nextValidator == null) {
			return true;
		}
		return nextValidator.validate("");
	}

}
