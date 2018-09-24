package org.go_jek.solution;

import java.util.HashMap;
import java.util.Map;

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
		if(parkingLot.size() < capacity) {
			int slot = parkingLot.size() + 1;
			parkingLot.put(slot, car.getRegistration());
			return slot;
		}else{
			throw new ParkingLotException("Sorry, parking lot is full");
		}
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
