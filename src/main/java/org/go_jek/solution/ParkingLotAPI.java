package org.go_jek.solution;

import org.go_jek.solution.bo.Car;

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
		int slot = parkingLot.parkCar(car);
		return String.format("Allocated slot number: %s", slot);
	}

	public String unParkCar(Car car){
		int slot = parkingLot.unParkCar(car);
		return String.format("Slot number %s is free", slot);
	}
}
