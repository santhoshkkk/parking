package in.gojek.parking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
		return new Slot(1);
	}

	public void leave(int slotNum) {

	}

	public Collection<Vehicle> getVehiclesByColor(String color) {
		List<Vehicle> veh = new ArrayList<>();
		veh.add(new Vehicle("XXXX", "white"));
		return veh;
	}

	public Collection<Slot> getSlotsByColor(String color) {
		List<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		return slots;
	}

	public Slot getSlot(String regNum) {
		return new Slot(1);
	}

	@Override
	public Map<Slot, Vehicle> status() {
		Map<Slot, Vehicle> occupied = new TreeMap<>();
		occupied.put(new Slot(1), new Vehicle("XXXX", "white"));
		return occupied;
	}
}
