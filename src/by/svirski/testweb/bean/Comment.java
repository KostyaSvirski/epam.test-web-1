package by.svirski.testweb.bean;

/**
 * class represents Comment Bean with fields :
 * 	<b>id</b>, <b>idUser</b>, <b>idCar</b>, <b>commentInfo</b>, <b>signature</b>
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class Comment {

	/**
	 * represents id of comment in db
	 */
	private int id;
	/**
	 * represents id of user who leave comment in db
	 */
	private int idUser;
	/**
	 * represents id of car to which the comment was given in db
	 */
	private int idCar;
	/**
	 * represents text of comment
	 */
	private String commentInfo;
	/**
	 * represents signature of user
	 */
	private String signature;
	
	/**
	 * default constructor
	 * @see Comment#Comment(int, int, int, String, String)
	 */
	public Comment() {
	}

	/**
	 * constructor with all fields 
	 * @param id - id of comment
	 * @param idUser - id of user who leave comment
	 * @param idCar - id of car to which comment was given
	 * @param comment - text of comment
	 * @param signature - signature of user
	 * @see Comment#Comment()
	 */
	public Comment(int id, int idUser, int idCar, String comment, String signature) {
		this.id = id;
		this.idUser = idUser;
		this.idCar = idCar;
		this.commentInfo = comment;
		this.signature = signature;
	}
	
	/**
	 * method for getting id of comment
	 * @return id of comment
	 */
	public int getId() {
		return id;
	}

	/**
	 * method for getting id of user
	 * @return id of user
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * method for getting id of car 
	 * @return id of car
	 */
	public int getIdCar() {
		return idCar;
	}

	/**
	 * method for getting text of comment
	 * @return text of comment
	 */
	public String getCommentInfo() {
		return commentInfo;
	}

	/**
	 * method for getting signature of user
	 * @return signature of user
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * method for setting id of comment
	 * @param id - id of comment
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * method for setting id of user
	 * @param idUser - id of user
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * method for setting id of car
	 * @param idCar - id of car
	 */
	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	/**
	 * method for setting text of comment 
	 * @param comment - text of comment
	 */
	public void setCommentInfo(String comment) {
		this.commentInfo = comment;
	}

	/**
	 * method for setting signature of user
	 * @param signature - signature of user
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentInfo == null) ? 0 : commentInfo.hashCode());
		result = prime * result + id;
		result = prime * result + idCar;
		result = prime * result + idUser;
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Comment other = (Comment) obj;
		if (commentInfo == null) {
			if (other.commentInfo != null) {
				return false;
			}
		} else if (!commentInfo.equals(other.commentInfo)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (idCar != other.idCar) {
			return false;
		}
		if (idUser != other.idUser) {
			return false;
		}
		if (signature == null) {
			if (other.signature != null) {
				return false;
			}
		} else if (!signature.equals(other.signature)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [id=");
		builder.append(id);
		builder.append(", idUser=");
		builder.append(idUser);
		builder.append(", idCar=");
		builder.append(idCar);
		builder.append(", comment=");
		builder.append(commentInfo);
		builder.append(", signature=");
		builder.append(signature);
		builder.append("]");
		return builder.toString();
	}
	
	

}
