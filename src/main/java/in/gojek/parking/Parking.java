package in.gojek.parking;

import java.util.Collection;

import in.gojek.parking.model.Slot;
import in.gojek.parking.model.Vehicle;

public interface Parking {

	boolean isFull();

	boolean isEmpty();

	Slot park(Vehicle vehicle);

	void leave(int slotNum);

	Collection<Vehicle> getCarsByColor(String color);

	Collection<Slot> getSlotsByColor(String color);

	Vehicle getBySlot(Slot slot);

}