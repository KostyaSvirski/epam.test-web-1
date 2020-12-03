package by.svirski.testweb.bean.builder.impl;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Comment;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.type.TypeOfParameters.CommentType;

/**
 * class represents building Comment Bean 
 * 
 * @see Comment
 * @see CommentType
 * @see Builder
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class CommentBuilder implements Builder<Comment, CommentType>{
	
	private static Logger logger = LogManager.getLogger(CommentBuilder.class);

	/**
	 * default constructor
	 */
	public CommentBuilder() {
	}

	/**
	 * overriden method {@link Builder#build(Map)} for build Comment Bean
	 */
	@Override
	public Comment build(Map<CommentType, String> parameters) {
		Comment comment = new Comment();
		comment.setCommentInfo(parameters.get(CommentType.COMMENT));
		comment.setId(Integer.parseInt(parameters.get(CommentType.ID_COMMENT)));
		comment.setIdCar(Integer.parseInt(parameters.get(CommentType.ID_CAR)));
		comment.setIdUser(Integer.parseInt(parameters.get(CommentType.ID_USER)));
		comment.setSignature(parameters.get(CommentType.SIGNATURE));
		logger.log(Level.DEBUG, "построен объект Comment");
		return comment;
	}
	

}
