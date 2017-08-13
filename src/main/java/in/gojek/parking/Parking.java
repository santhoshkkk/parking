package in.gojek.parking;

import java.util.Collection;
import java.util.Map;

import in.gojek.parking.model.Slot;
import in.gojek.parking.model.Vehicle;

public interface Parking {

	boolean isFull();

	boolean isEmpty();

	Slot park(Vehicle vehicle);

	void leave(int slotNum);

	Collection<Vehicle> getVehiclesByColor(String color);

	Collection<Slot> getSlotsByColor(String color);

	Slot getSlot(String regNum);

	Map<Slot, Vehicle> status();

}