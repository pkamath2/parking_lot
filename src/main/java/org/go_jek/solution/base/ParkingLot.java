package org.go_jek.solution.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.go_jek.solution.bo.Car;
import org.go_jek.solution.bo.ParkingSlot;
import org.go_jek.solution.execption.ParkingLotException;

/**
 *   Author: purnimakamath
 */
public class ParkingLot {

	private static ParkingLot instance = new ParkingLot();
	private Map<Integer, Car> parkingLot = null;
	private int capacity = 0;
	private int size = 0;

	//Singleton instance
	private ParkingLot() {
	}

	public void createLotWithCapacity(int initialCapacity){
		this.capacity 	= initialCapacity;
		this.parkingLot = new HashMap<>();
		for(int i=1;i<initialCapacity+1;i++){
			parkingLot.put(i, null);
		}
	}

	public int parkCar(Car car) throws ParkingLotException {

		int reservedSlot = -1;
		if(getSize() >= capacity) {
			throw new ParkingLotException("Sorry, parking lot is full");
		}

		Set<Integer> slots = parkingLot.keySet();
		for (Integer slot: slots){
			if(parkingLot.get(slot) == null){
				reservedSlot = slot;
				parkingLot.put(slot, car);
				break;
			}

		}
		return reservedSlot;
	}

	public int unParkCar(Car car) throws ParkingLotException{
		Set<Integer> slots = parkingLot.keySet();
		int reservedSlot = -1;
		for (int slot:slots){
			if(parkingLot.get(slot) != null
					&& car.getRegistration().equals(parkingLot.get(slot).getRegistration())){
				reservedSlot = slot;
				parkingLot.put(slot, null);
				break;
			}
		}
		if(reservedSlot == -1){
			throw new ParkingLotException("Car not parked in the Parking Lot");
		}
		return reservedSlot;
	}

	public List<Car> findParkedCarsByColor(String color){
		List<Car> coloredCars = new ArrayList<>();
		Set<Integer> slots = parkingLot.keySet();
		for (Integer slot :slots) {
			Car parkedCar = parkingLot.get(slot);
			if(color != null
					&& parkedCar != null
					&& parkedCar.getColor() != null
					&& color.toUpperCase().equals(parkedCar.getColor().toUpperCase())){
				coloredCars.add(parkedCar);
			}
		}
		return coloredCars;
	}

	public List<Integer> findParkedSlotsByCarColor(String color){
		List<Integer> parkedSlots = new ArrayList<>();
		Set<Integer> slots = parkingLot.keySet();
		for (Integer slot :slots) {
			Car parkedCar = parkingLot.get(slot);
			if(color != null
					&& parkedCar != null
					&& parkedCar.getColor() != null
					&& color.toUpperCase().equals(parkedCar.getColor().toUpperCase())){
				parkedSlots.add(slot);
			}
		}
		return parkedSlots;
	}

	public Integer findParkingSlotByCarRegistrationNumber(String registrationNumber){
		Integer parkedSlot = -1;
		Set<Integer> slots = parkingLot.keySet();
		for (Integer slot :slots) {
			Car parkedCar = parkingLot.get(slot);
			if(registrationNumber != null
					&& parkedCar != null
					&& parkedCar.getRegistration() != null
					&& registrationNumber.toUpperCase().equals(parkedCar.getRegistration().toUpperCase())){
				parkedSlot = slot;
			}
		}
		return parkedSlot;
	}

	public List<ParkingSlot> findAllParkingSlotsWithCars(){
		List<ParkingSlot> parkingSlots = new ArrayList<>();
		Set<Integer> slots = parkingLot.keySet();
		for (Integer slot :slots) {
			Car parkedCar = parkingLot.get(slot);
			if(parkedCar != null){
				ParkingSlot parkingSlot = new ParkingSlot(slot, parkedCar);
				parkingSlots.add(parkingSlot);
			}
		}
		return parkingSlots;
	}

	public void printCarParkStatus(){
		Set<Integer> slots = parkingLot.keySet();
		System.out.println("---------Parking Lot--------------");
		for (Integer slot: slots) {
			System.out.println("Slot/Car: " + slot + " " + parkingLot.get(slot));
		}
		System.out.println("----------------------------------");
	}

	public static ParkingLot getInstance() {
		return instance;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getSize() {
		this.size = ((int) parkingLot.values().stream().filter((s -> s != null)).count());
		return size;
	}
}
