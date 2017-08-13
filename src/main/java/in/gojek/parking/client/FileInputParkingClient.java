package in.gojek.parking.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileInputParkingClient extends BaseParkingClient {

	public void manageParking(String commandSource) {
		File inputFile = new File(commandSource);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			do{
				String commandLine = reader.readLine();
				if(null==commandLine){
					break;
				}
				String[] commandAndData = parseCommand(commandLine);
				processCommand(commandAndData);
			}while(true);
			
			reader.close();
		} catch (IOException e) {
			System.out.println("Error reading commands" + e.getMessage());
			return;
		}
	}

	@Override
	public void manageParking() {
		throw new UnsupportedOperationException();
	}

}
