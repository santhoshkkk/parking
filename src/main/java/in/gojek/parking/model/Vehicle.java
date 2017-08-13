package in.gojek.parking.model;

public class Vehicle {
	private String regNum;
	private String color;

	public Vehicle(String regNum, String color) {
		this.regNum = regNum;
		this.color = color;
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

}
