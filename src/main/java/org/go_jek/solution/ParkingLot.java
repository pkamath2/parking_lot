package org.go_jek.solution;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.go_jek.solution.bo.Car;
import org.go_jek.solution.execption.ParkingLotException;

/**
 *   Author: purnimakamath
 */
public class ParkingLot {

	private static ParkingLot instance = new ParkingLot();
	private Map<Integer, String> parkingLot = null;
	private int capacity = 0;
	private int size = 0;

	//Singleton instance
	private ParkingLot() {
	}

	public void createLotWithCapacity(int initialCapacity){
		this.capacity 	= initialCapacity;
		this.parkingLot = new HashMap<>();
	}

	public int parkCar(Car car) throws ParkingLotException {
		int slot = -1;
		if(parkingLot.size() < capacity) {
			slot = parkingLot.size() + 1;
			parkingLot.put(slot, car.getRegistration());
		}else{
			throw new ParkingLotException("Sorry, parking lot is full");
		}
		return slot;
	}

	public int unParkCar(Car car) throws ParkingLotException{
		Set<Integer> slots = parkingLot.keySet();
		int reservedSlot = -1;
		for (int slot:slots){
			if(car.getRegistration().equals(parkingLot.get(slot))){
				reservedSlot = slot;
				parkingLot.remove(slot, car.getRegistration());
				break;
			}
		}
		if(reservedSlot == -1){
			throw new ParkingLotException("Car not parked in the Parking Lot");
		}
		return reservedSlot;
	}

	public static ParkingLot getInstance() {
		return instance;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getSize() {
		this.size = parkingLot.size();
		return size;
	}
}
