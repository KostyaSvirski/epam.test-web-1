package by.svirski.testweb.bean.builder.impl;

import java.util.Map;

import by.svirski.testweb.bean.Comment;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.type.TypeOfParameters.CommentType;

public class CommentBuilder implements Builder<Comment, CommentType>{

	public CommentBuilder() {
	}

	@Override
	public Comment build(Map<CommentType, String> parameters) {
		Comment comment = new Comment();
		comment.setCommentInfo(parameters.get(CommentType.COMMENT));
		comment.setId(Integer.parseInt(parameters.get(CommentType.ID_COMMENT)));
		comment.setIdCar(Integer.parseInt(parameters.get(CommentType.ID_CAR)));
		comment.setIdUser(Integer.parseInt(parameters.get(CommentType.ID_USER)));
		comment.setSignature(parameters.get(CommentType.SIGNATURE));
		return comment;
	}
	

}
