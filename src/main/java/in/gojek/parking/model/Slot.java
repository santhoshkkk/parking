package in.gojek.parking.model;

public class Slot implements Comparable<Slot> {
	private int number;

	public Slot(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int compareTo(Slot o) {
		return null == o ? 1 : this.number - o.number;
	}

}
