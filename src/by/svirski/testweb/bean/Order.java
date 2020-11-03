package by.svirski.testweb.bean;

import java.util.Calendar;

public class Order {
	
	private int id;
	private int idCar;
	private int idUser;
	private String carBrand;
	private String carModel;
	private String signature;
	private Calendar dateOfStart;
	private Calendar dateOfFinish;
	private long totalPrice;
	private String condition;
	
	public Order() {
	}

	public Order(int id, int idCar, int idUser, String carBrand, String carModel, String signature,
			Calendar dateOfStart, Calendar dateOfFinish, long totalPrice, String condition) {
		this.id = id;
		this.idCar = idCar;
		this.idUser = idUser;
		this.carBrand = carBrand;
		this.carModel = carModel;
		this.signature = signature;
		this.dateOfStart = dateOfStart;
		this.dateOfFinish = dateOfFinish;
		this.totalPrice = totalPrice;
		this.condition = condition;
	}
	
	public Order(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getIdCar() {
		return idCar;
	}

	public int getIdUser() {
		return idUser;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public String getSignature() {
		return signature;
	}

	public String getDateOfStart() {
		String dayOfMonth = Integer.toString(dateOfStart.get(Calendar.DAY_OF_MONTH));
		String month = Integer.toString(dateOfStart.get(Calendar.MONTH)+1);
		String year = Integer.toString(dateOfStart.get(Calendar.YEAR));
		StringBuilder sb = new StringBuilder();
		sb.append(dayOfMonth);
		sb.append(".");
		sb.append(month);
		sb.append(".");
		sb.append(year);
		return sb.toString();
	}

	public String getDateOfFinish() {
		String dayOfMonth = Integer.toString(dateOfFinish.get(Calendar.DAY_OF_MONTH));
		String month = Integer.toString(dateOfFinish.get(Calendar.MONTH)+1);
		String year = Integer.toString(dateOfFinish.get(Calendar.YEAR));
		StringBuilder sb = new StringBuilder();
		sb.append(dayOfMonth);
		sb.append(".");
		sb.append(month);
		sb.append(".");
		sb.append(year);
		return sb.toString();
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public String getCondition() {
		return condition;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setDateOfStart(Calendar dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public void setDateOfFinish(Calendar dateOfFinish) {
		this.dateOfFinish = dateOfFinish;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carBrand == null) ? 0 : carBrand.hashCode());
		result = prime * result + ((carModel == null) ? 0 : carModel.hashCode());
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((dateOfFinish == null) ? 0 : dateOfFinish.hashCode());
		result = prime * result + ((dateOfStart == null) ? 0 : dateOfStart.hashCode());
		result = prime * result + id;
		result = prime * result + idCar;
		result = prime * result + idUser;
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + (int) (totalPrice ^ (totalPrice >>> 32));
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
		Order other = (Order) obj;
		if (carBrand == null) {
			if (other.carBrand != null) {
				return false;
			}
		} else if (!carBrand.equals(other.carBrand)) {
			return false;
		}
		if (carModel == null) {
			if (other.carModel != null) {
				return false;
			}
		} else if (!carModel.equals(other.carModel)) {
			return false;
		}
		if (condition == null) {
			if (other.condition != null) {
				return false;
			}
		} else if (!condition.equals(other.condition)) {
			return false;
		}
		if (dateOfFinish == null) {
			if (other.dateOfFinish != null) {
				return false;
			}
		} else if (!dateOfFinish.equals(other.dateOfFinish)) {
			return false;
		}
		if (dateOfStart == null) {
			if (other.dateOfStart != null) {
				return false;
			}
		} else if (!dateOfStart.equals(other.dateOfStart)) {
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
		if (totalPrice != other.totalPrice) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", idCar=");
		builder.append(idCar);
		builder.append(", idUser=");
		builder.append(idUser);
		builder.append(", carBrand=");
		builder.append(carBrand);
		builder.append(", carModel=");
		builder.append(carModel);
		builder.append(", signature=");
		builder.append(signature);
		builder.append(", dateOfStart=");
		builder.append(dateOfStart);
		builder.append(", dateOfFinish=");
		builder.append(dateOfFinish);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append(", condition=");
		builder.append(condition);
		builder.append("]");
		return builder.toString();
	}

	
	
}
