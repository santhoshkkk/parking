package in.gojek.parking.model;

public class Vehicle {
	private String regNum;
	private String color;

	public Vehicle(String regNum, String color) {
		this.regNum = regNum;
		this.color = color;
	}

	public Vehicle(String regNum) {
		this.regNum = regNum;
		this.color = "";
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// Registration number is the unique way to identify a vehicle
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regNum == null) ? 0 : regNum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (regNum == null) {
			if (other.regNum != null)
				return false;
		} else if (!regNum.equals(other.regNum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(regNum).append(", ").append(color);
		return builder.toString();
	}

}
