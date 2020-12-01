package by.svirski.testweb.util.validator;

import java.util.Map;

/**
 * abstract class represents Chain Responsibility pattern for validators
 * 
 * @param <T> - type of keys in parameters map
 *
 * @author Kostya Svirski
 * @version 1.0
 */
public abstract class PreparedValidatorsChain<T> {
	
	private PreparedValidatorsChain<T> nextLink;
	
	/**
	 * method for linking next validator to existing chain
	 * 
	 * @param nextLink - next validator
	 * @return last link of chain
	 */
	public PreparedValidatorsChain<T> linkWith(PreparedValidatorsChain<T> nextLink) {
		this.nextLink = nextLink;
		return nextLink;
	}
	
	/**
	 * abstract method for validation specific parameter
	 * 
	 * @param params - map with all parameters 
	 * @return true (validation passed successfully), false (validation passed not successfully)
	 */
	public abstract boolean validate(Map<T, String> params);
	
	protected boolean checkNextLink(Map<T, String> params) {
		if(nextLink == null) {
			return true;
		}
		return nextLink.validate(params);
	}

}
