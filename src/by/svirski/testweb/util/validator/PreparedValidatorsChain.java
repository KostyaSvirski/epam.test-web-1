package by.svirski.testweb.util.validator;

import java.util.Map;

public abstract class PreparedValidatorsChain<T> {
	
	private PreparedValidatorsChain<T> nextLink;
	
	public PreparedValidatorsChain<T> linkWith(PreparedValidatorsChain<T> nextLink) {
		this.nextLink = nextLink;
		return nextLink;
	}
	
	public abstract boolean validate(Map<T, String> params);
	
	protected boolean checkNextLink(Map<T, String> params) {
		if(nextLink == null) {
			return true;
		}
		return nextLink.validate(params);
	}

}
