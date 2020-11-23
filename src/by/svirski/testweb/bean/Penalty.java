package by.svirski.testweb.bean;

public class Penalty {

	private int id;
	private int idOrder;
	private String info;
	private long costOfPenalty;
	private boolean isClosed;
	
	public Penalty() {
		// TODO Auto-generated constructor stub
	}

	public Penalty(int id, int idOrder, String info, long costOfPenalty, boolean isClosed) {
		super();
		this.id = id;
		this.idOrder = idOrder;
		this.info = info;
		this.costOfPenalty = costOfPenalty;
		this.isClosed = isClosed;
	}

	public int getId() {
		return id;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public String getInfo() {
		return info;
	}

	public long getCostOfPenalty() {
		return costOfPenalty;
	}

	public boolean getIsClosed() {
		return isClosed;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setCostOfPenalty(long costOfPenalty) {
		this.costOfPenalty = costOfPenalty;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (costOfPenalty ^ (costOfPenalty >>> 32));
		result = prime * result + id;
		result = prime * result + idOrder;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + (isClosed ? 1231 : 1237);
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
		Penalty other = (Penalty) obj;
		if (costOfPenalty != other.costOfPenalty) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (idOrder != other.idOrder) {
			return false;
		}
		if (info == null) {
			if (other.info != null) {
				return false;
			}
		} else if (!info.equals(other.info)) {
			return false;
		}
		if (isClosed != other.isClosed) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Penalty [id=");
		builder.append(id);
		builder.append(", idOrder=");
		builder.append(idOrder);
		builder.append(", info=");
		builder.append(info);
		builder.append(", costOfPenalty=");
		builder.append(costOfPenalty);
		builder.append(", isClosed=");
		builder.append(isClosed);
		builder.append("]");
		return builder.toString();
	}
	
	

}
