package by.svirski.testweb.util.creator;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.creator.impl.CreatorRequestForCarShowImpl;

/**
 * class represents Factory Pattern for creators of requests {@link RequestCreator}
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public final class FactoryForCreators {

	private static final FactoryForCreators INSTANCE = new FactoryForCreators();
	
	private final RequestCreator<CarType> creatorForCarShow = new CreatorRequestForCarShowImpl();
	
	private FactoryForCreators() {
	}

	/**
	 * static method for getting factory instance
	 * @return instance of factory
	 */
	public static FactoryForCreators getInstance() {
		return INSTANCE;
	}

	/**
	 * method of getting {@link CreatorRequestForCarShowImpl}
	 * @return {@link CreatorRequestForCarShowImpl}
	 */
	public RequestCreator<CarType> getCreatorForCarShow() {
		return creatorForCarShow;
	}

	
}
