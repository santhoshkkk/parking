package in.gojek.parking;

import in.gojek.parking.client.FileInputParkingClient;
import in.gojek.parking.client.InteractiveParkingClient;
import in.gojek.parking.client.ParkingClient;

public class DriverProgram {

	public static void main(String[] args) {
		ParkingClient client;
		if (args.length == 0) {
			client = new InteractiveParkingClient();
			client.manageParking();
		} else {
			client = new FileInputParkingClient();
			client.manageParking(args[0]);
		}
	}
}
