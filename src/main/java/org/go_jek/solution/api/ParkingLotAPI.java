package org.go_jek.solution.api;

import java.util.List;

import org.go_jek.solution.base.ParkingLot;
import org.go_jek.solution.bo.Car;
import org.go_jek.solution.bo.ParkingSlot;
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

	public String leaveSlot(int slotNumber){
		String message;
		try {
			parkingLot.leaveSlot(slotNumber);
			message = String.format("Slot number %s is free", slotNumber);
		}
		catch (ParkingLotException e) {
			message = e.getMessage();
		}
		return message;
	}

	public String findCarRegistrationsByColor(String color){
		String message = "";

		List<Car> coloredCars = parkingLot.findParkedCarsByColor(color);
		if(coloredCars.size() <= 0){
			message = "Not found";
		}
		for (Car car :coloredCars) {
			message = message + (message.length()>0?", ":"") + car.getRegistration();
		}
		return message;
	}

	public String findParkedSlotsByCarColor(String color){
		String message = "";

		List<Integer> slots = parkingLot.findParkedSlotsByCarColor(color);
		if(slots.size() <= 0){
			message = "Not found";
		}
		for (Integer slot :slots) {
			message = message + (message.length()>0?", ":"") + slot;
		}
		return message;
	}

	public String findParkedSlotsByCarRegistration(String registrationNumber){
		String message;

		Integer parkedSlot = parkingLot.findParkingSlotByCarRegistrationNumber(registrationNumber);
		if(parkedSlot == -1){
			message = "Not found";
		}else{
			message = parkedSlot.toString();
		}
		return message;
	}

	public String getCarParkStatus(){
		String message = "Slot No.\tRegistration No\tColour";
		String slotFormat = "%s\t%s\t%s";

		List<ParkingSlot> parkingSlots= parkingLot.findAllParkingSlotsWithCars();
		for (ParkingSlot parkingSlot :parkingSlots){
			message = message + "\n";
			message = message + String.format(slotFormat, parkingSlot.getSlot(), parkingSlot.getParkedCar().getRegistration(), parkingSlot.getParkedCar().getColor());
		}
		return message;
	}
}
