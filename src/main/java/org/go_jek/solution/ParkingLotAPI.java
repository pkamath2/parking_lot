package org.go_jek.solution;

import org.go_jek.solution.bo.Car;
import org.go_jek.solution.execption.ParkingLotException;

/**
 *   Author: purnimakamath
 */
public class ParkingLotAPI {

	ParkingLot parkingLot = ParkingLot.getInstance();

	public String initializeParkingLot(int initialCapacity){
		parkingLot.createLotWithCapacity(initialCapacity);
		return String.format("Created a parking lot with %s slots", initialCapacity);
	}

	public String parkCar(Car car){
		String message;
		try {
			int slot = parkingLot.parkCar(car);
			message = String.format("Allocated slot number: %s", slot);
		}
		catch (ParkingLotException e) {
			message = e.getMessage();
		}
		return message;
	}

	public String unParkCar(Car car){
		String message;
		try {
			int slot = parkingLot.unParkCar(car);
			message = String.format("Slot number %s is free", slot);
		}
		catch (ParkingLotException e) {
			message = e.getMessage();
		}
		return message;
	}
}
