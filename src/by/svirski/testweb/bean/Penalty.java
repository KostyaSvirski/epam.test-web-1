package by.svirski.testweb.bean;

/**
 * class represents Penalty bean with fields:
 * 	<b>id</b>, <b>idOrder</b>, <b>info</b>, <b>costOfPenalty</b>, <b>isClosed</b>
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class Penalty {

	/**
	 * field represents id of penalty in db
	 */
	private int id;
	/**
	 * field represents id of order to which this penalty was given
	 */
	private int idOrder;
	/**
	 * field represents cause why the penalty was given
	 */
	private String info;
	/**
	 * field represents cost of penalty which user must to pay
	 */
	private long costOfPenalty;
	/**
	 * field represents fact of closing penalty
	 */
	private boolean isClosed;
	
	/**
	 * default constructor
	 * @see Penalty#Penalty(int, int, String, long, boolean)
	 */
	public Penalty() {
	}

	/**
	 * constructor with all fields
	 * @param id - id of penalty
	 * @param idOrder - id of order to which the penalty was given
	 * @param info - cause of penalty
	 * @param costOfPenalty - cost which user must pay
	 * @param isClosed - status of penalty
	 * @see Penalty#Penalty()
	 */
	public Penalty(int id, int idOrder, String info, long costOfPenalty, boolean isClosed) {
		super();
		this.id = id;
		this.idOrder = idOrder;
		this.info = info;
		this.costOfPenalty = costOfPenalty;
		this.isClosed = isClosed;
	}

	/**
	 * method for getting id of penalty
	 * @return id of penalty
	 */
	public int getId() {
		return id;
	}

	/**
	 * method for getting id of order to which penalty was given
	 * @return id of penalty
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * method for getting cause of penalty
	 * @return cause of penalty
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * method for getting cost of penalty
	 * @return cost of penalty
	 */
	public long getCostOfPenalty() {
		return costOfPenalty;
	}

	/**
	 * method for getting status of penalty
	 * @return status of penalty
	 */
	public boolean getIsClosed() {
		return isClosed;
	}

	/**
	 * method for setting id of penalty 
	 * @param id - id of penalty
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * method for setting id of order to which the penalty was given
	 * @param idOrder - id of order
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	/**
	 * method for setting cause of penalty
	 * @param info - cause
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * method for setting cost of penalty
	 * @param costOfPenalty - cost of penalty
	 */
	public void setCostOfPenalty(long costOfPenalty) {
		this.costOfPenalty = costOfPenalty;
	}

	/**
	 * method for setting status of penalty
	 * @param isClosed - status
	 */
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
