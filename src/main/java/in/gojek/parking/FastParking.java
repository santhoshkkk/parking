package in.gojek.parking;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

import in.gojek.parking.model.Slot;
import in.gojek.parking.model.Vehicle;

/**
 * A fast implementation of Parking which does the operations in most time
 * efficient way. It is achieved by maintaining multiple data structures which
 * duplciates object references
 */
public class FastParking implements Parking {
	private int size;
	private int occupied;
	private PriorityQueue<Slot> freeSlots;
	private Map<Vehicle, Slot> vehicleSlot;
	private Map<String, Set<Vehicle>> colorVehicles;
	private Map<Slot, Vehicle> slotVehicle;

	public FastParking(int size) {
		this.size = size;
		occupied = 0;
		freeSlots = new PriorityQueue<>();
		for (int i = 1; i <= size; i++) {
			freeSlots.add(new Slot(i));
		}
		vehicleSlot = new HashMap<>();
		slotVehicle = new HashMap<>();
		colorVehicles = new HashMap<>();
	}

	public boolean isFull() {
		return occupied == size;
	}

	public boolean isEmpty() {
		return occupied == 0;
	}

	// O(1)
	public Slot park(Vehicle vehicle) {
		if (isFull()) {
			return null;
		}
		Slot minSlot = freeSlots.remove();
		vehicleSlot.put(vehicle, minSlot);
		slotVehicle.put(minSlot, vehicle);
		String color = vehicle.getColor();
		Set<Vehicle> sameColorVehs = colorVehicles.get(color);
		if (null == sameColorVehs) {
			sameColorVehs = new HashSet<>();
			colorVehicles.put(color, sameColorVehs);
		}
		sameColorVehs.add(vehicle);
		occupied++;
		return minSlot;
	}

	// O(log n)
	public void leave(int slotNum) {
		Slot slot = new Slot(slotNum);
		freeSlots.add(slot);
		Vehicle vehicle = slotVehicle.remove(slot);
		vehicleSlot.remove(vehicle);
		Set<Vehicle> sameColorVehs = colorVehicles.get(vehicle.getColor());
		sameColorVehs.remove(vehicle);
		occupied--;
	}

	// O(1)
	public Collection<Vehicle> getVehiclesByColor(String color) {
		return colorVehicles.get(color);
	}

	// O(vehicles with color)
	public Collection<Slot> getSlotsByColor(String color) {
		Set<Slot> slots = new HashSet<>();
		Collection<Vehicle> vehicles = getVehiclesByColor(color);
		if (null != vehicles) {
			for (Vehicle vehicle : vehicles) {
				slots.add(vehicleSlot.get(vehicle));
			}
		}
		return slots;
	}

	// O(1)
	public Slot getSlot(String regNum) {
		Vehicle veh = new Vehicle(regNum);
		return vehicleSlot.get(veh);
	}

	// This is mutable. If needed it can be made immutable
	@Override
	public Map<Slot, Vehicle> status() {
		Map<Slot, Vehicle> status = new TreeMap<>(slotVehicle);
		return status;
	}
}
