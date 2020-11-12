package by.svirski.testweb.util.creator;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.creator.impl.CreatorRequestForCarShowImpl;

public final class FactoryForCreators {

	private static final FactoryForCreators INSTANCE = new FactoryForCreators();
	
	private final RequestCreator<CarType> creatorForCarShow = new CreatorRequestForCarShowImpl();
	
	private FactoryForCreators() {
	}

	public static FactoryForCreators getInstance() {
		return INSTANCE;
	}

	public RequestCreator<CarType> getCreatorForCarShow() {
		return creatorForCarShow;
	}

	
}
