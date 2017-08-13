package in.gojek.parking.client;

import java.util.Scanner;

public class InteractiveParkingClient extends BaseParkingClient {
	private static final String QUIT = "quit";

	public void manageParking() {
		System.out.println("Enter your command below.(type quit to terminate program)");
		String commandLine;
		Scanner scanner = new Scanner(System.in);
		do {
			commandLine = scanner.nextLine();
			if (commandLine.startsWith(QUIT)) {
				break;
			}
			String[] commandAndData = parseCommand(commandLine);
			processCommand(commandAndData);
		} while (true);
		scanner.close();
	}

	@Override
	public void manageParking(String commadSource) {
		throw new UnsupportedOperationException();
	}

}
