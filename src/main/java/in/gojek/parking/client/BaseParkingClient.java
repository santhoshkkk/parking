package in.gojek.parking.client;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;

import in.gojek.parking.FastParking;
import in.gojek.parking.Parking;
import in.gojek.parking.consts.Commands;
import in.gojek.parking.consts.Messages;
import in.gojek.parking.model.Slot;
import in.gojek.parking.model.Vehicle;

public abstract class BaseParkingClient implements ParkingClient {
	private static final String TAB = "\t";
	private static final String NEWLINE = "\n";
	private static final String COMMA = ", ";
	private static final String SPACE = " ";

	private Parking parking;

	protected void processCommand(String[] commandAndData) {
		String command = commandAndData[0];
		switch (command) {
		case Commands.CREATE:
			createParking(commandAndData);
			break;
		case Commands.PARK:
			doPark(commandAndData);
			break;
		case Commands.LEAVE:
			doLeave(commandAndData);
			break;
		case Commands.STATUS:
			printStatus();
			break;
		case Commands.QUERY_VEHICLE_BY_COLOR:
			printVehiclesByColor(commandAndData);
			break;
		case Commands.QUERY_SLOT_BY_COLOR:
			printSlotsByColor(commandAndData);
			break;
		case Commands.QUERY_SLOT_BY_NUMBER:
			Slot slot = parking.getSlot(commandAndData[1]);
			if (null != slot) {
				System.out.println(slot.getNumber());
			}
			break;
		default:
			System.out.println(Messages.INVALID_OPTION);
			break;
		}
	}

	private void printSlotsByColor(String[] commandAndData) {
		Collection<Slot> slotsByColor = parking.getSlotsByColor(commandAndData[1]);
		if (null != slotsByColor && slotsByColor.size() > 0) {
			StringBuilder sb = new StringBuilder();
			boolean first = true;
			for (Slot slot : slotsByColor) {
				if (first) {
					sb.append(slot.getNumber());
					first = false;
				} else {
					sb.append(COMMA).append(slot.getNumber());
				}
			}
			System.out.println(sb.toString());
		}
	}

	private void printVehiclesByColor(String[] commandAndData) {
		Collection<Vehicle> vehiclesByColor = parking.getVehiclesByColor(commandAndData[1]);
		if (null != vehiclesByColor && vehiclesByColor.size() > 0) {
			StringBuilder sb = new StringBuilder();
			boolean first = true;
			for (Vehicle veh : vehiclesByColor) {
				if (first) {
					sb.append(veh.getRegNum());
					first = false;
				} else {
					sb.append(COMMA).append(veh.getRegNum());
				}
			}
			System.out.println(sb.toString());
		}
	}

	private void printStatus() {
		Map<Slot, Vehicle> filled = parking.status();
		if (null != filled && filled.size() > 0) {
			System.out.println(Messages.STATUS_HEADER);
			StringBuilder sb = new StringBuilder();
			for (Slot slot : filled.keySet()) {
				Vehicle vehicle = filled.get(slot);
				sb.append(slot.getNumber()).append(TAB).append(vehicle.getRegNum()).append(TAB)
						.append(vehicle.getColor()).append(NEWLINE);
			}
			System.out.println(sb.toString());
		}
	}

	private void doLeave(String[] commandAndData) {
		int slotNum = Integer.parseInt(commandAndData[1]);
		parking.leave(slotNum);
		System.out.println(MessageFormat.format(Messages.LEFT, slotNum));
	}

	private void doPark(String[] commandAndData) {
		Vehicle vehicle = new Vehicle(commandAndData[1], commandAndData[2]);
		Slot allottedSlot = parking.park(vehicle);
		if (null == allottedSlot) {
			System.out.println(Messages.PARKING_FULL);
		} else {
			System.out.println(MessageFormat.format(Messages.PARKED, allottedSlot.getNumber()));
		}
	}

	private void createParking(String[] commandAndData) {
		if (null == parking) {
			int size = Integer.parseInt(commandAndData[1]);
			parking = new FastParking(size);
		} else {
			System.out.println("Parking already exists. Ignoring");
		}
	}

	protected String[] parseCommand(String commandLine) {
		return commandLine.split(SPACE);
	}

}
