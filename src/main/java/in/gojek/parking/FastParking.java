package in.gojek.parking;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import in.gojek.parking.model.Slot;
import in.gojek.parking.model.Vehicle;

public class FastParking implements Parking {
	private int size;
	private int occupied;
	private Map<Slot, Vehicle> slots;

	public FastParking(int size) {
		this.size = size;
		occupied = 0;
		slots = new HashMap<Slot, Vehicle>(size);
	}

	public boolean isFull() {
		return occupied == size;
	}

	public boolean isEmpty() {
		return occupied == 0;
	}

	public Slot park(Vehicle vehicle) {
		return null;
	}

	public void leave(int slotNum) {

	}

	public Collection<Vehicle> getVehiclesByColor(String color) {
		return null;
	}

	public Collection<Slot> getSlotsByColor(String color) {
		return null;
	}

	public Slot getSlot(String regNum) {
		return null;
	}

	@Override
	public Map<Slot, Vehicle> status() {
		// TODO Auto-generated method stub
		return null;
	}
}
