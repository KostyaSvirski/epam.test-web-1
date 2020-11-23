package by.svirski.testweb.bean;

public class Comment {

	private int id;
	private int idUser;
	private int idCar;
	private String commentInfo;
	private String signature;
	
	public Comment() {
	}

	public Comment(int id, int idUser, int idCar, String comment, String signature) {
		this.id = id;
		this.idUser = idUser;
		this.idCar = idCar;
		this.commentInfo = comment;
		this.signature = signature;
	}

	public int getId() {
		return id;
	}

	public int getIdUser() {
		return idUser;
	}

	public int getIdCar() {
		return idCar;
	}

	public String getCommentInfo() {
		return commentInfo;
	}

	public String getSignature() {
		return signature;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public void setCommentInfo(String comment) {
		this.commentInfo = comment;
	}

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
