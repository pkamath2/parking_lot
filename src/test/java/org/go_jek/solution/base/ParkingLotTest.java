package org.go_jek.solution.base;

import java.util.Arrays;
import java.util.List;

import org.go_jek.solution.base.ParkingLot;
import org.go_jek.solution.bo.Car;
import org.go_jek.solution.bo.ParkingSlot;
import org.go_jek.solution.execption.ParkingLotException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *   Author: purnimakamath
 */
@SuppressWarnings("Duplicates")
public class ParkingLotTest {

	@Test
	public void shouldCreateParkingLotOfCapacity3() {
		int capacity = 3;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		assertEquals("Parking lot capacity should be " + capacity, capacity, parkingLot.getCapacity());
		assertEquals("Parking lot size should be 0.", 0, parkingLot.getSize());
	}

	@Test
	public void shouldReduceSizeAfterParkingCar() {
		int capacity = 3;
		ParkingLot parkingLot = ParkingLot.getInstance();
		Car someCar = new Car("SHC-123", "White");
		parkingLot.createLotWithCapacity(capacity);
		int slot = parkingLot.parkCar(someCar);

		assertNotEquals("Parking lot capacity should not be " + capacity, capacity, parkingLot.getSize());
		assertEquals("Parking lot size should be " + 1, 1, parkingLot.getSize());
	}

	@Test
	public void shouldParkingMoreThanCapacityThrowException() {
		int capacity = 3;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);
		String message = null;
		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		Car car3 = new Car("SHA-567", "Red");
		Car car4 = new Car("SHZ-789", "Yellow");

		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		try {
			parkingLot.parkCar(car4);
		}
		catch (ParkingLotException e) {
			message = e.getMessage();
		}
		assertEquals("Parking more then capacity should throw an exception", message, "Sorry, parking lot is full");
	}

	@Test
	public void shouldReduceSizeAfterParkedCarLeaves() {
		int capacity = 3;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		assertEquals("There should be 2 parked cars in the lot.", 2, parkingLot.getSize());

		parkingLot.unParkCar(car2);
		assertEquals("There should be only 1 parked car in the lot.", 1, parkingLot.getSize());
	}

	@Test
	public void shouldReduceSizeAfterParkedCarLeavesUsingLeaveAPI() {
		int capacity = 3;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		assertEquals("There should be 2 parked cars in the lot.", 2, parkingLot.getSize());

		parkingLot.leaveSlot(2);
		assertEquals("There should be only 1 parked car in the lot.", 1, parkingLot.getSize());
	}

	@Test
	public void shouldThrowExceptionOnUnParkingAnUnknownCar() {
		int capacity = 3;
		String message = null;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);

		Car carX = new Car("SHE-345", "UNKNOWN");
		try {
			parkingLot.unParkCar(carX);
		}
		catch (ParkingLotException e) {
			message = e.getMessage();
		}
		assertEquals("Should not unpark an previously unknown car.", "Car not parked in the Parking Lot", message);

	}

	@Test
	public void shouldThrowExceptionOnLeavingAnUnparkedSlotLeavesAPI() {
		int capacity = 3;
		String message = null;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);

		Car carX = new Car("SHE-345", "UNKNOWN");
		try {
			parkingLot.leaveSlot(3);
		}
		catch (ParkingLotException e) {
			message = e.getMessage();
		}
		assertEquals("Should not unpark an previously unknown car.", "Car not parked in the Parking Lot", message);

	}

	@Test
	public void shouldParkCarAtFirstAvailableSlot() {
		int capacity = 6;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		Car car3 = new Car("SHX-789", "Gold");
		Car car4 = new Car("SHD-098", "Purple!");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		parkingLot.parkCar(car4);
		parkingLot.printCarParkStatus();
		assertEquals("There should be 4 parked cars in the lot.", 4, parkingLot.getSize());

		parkingLot.unParkCar(car2);
		parkingLot.unParkCar(car4);
		parkingLot.printCarParkStatus();

		Car car5 = new Car("SHW-100", "Red");
		int slot = parkingLot.parkCar(car5);
		assertEquals("This car should be parked in slot 2", 2, slot);
		parkingLot.printCarParkStatus();
	}

	@Test
	public void shouldReturnOneCarForOneParkedWhiteCar() {
		int capacity = 6;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		Car car3 = new Car("SHX-789", "Gold");
		Car car4 = new Car("SHD-098", "Purple!");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		parkingLot.parkCar(car4);
		parkingLot.printCarParkStatus();

		List<Car> cars = parkingLot.findParkedCarsByColor("White");
		assertEquals("There should be One white car parked in the lot. ", 1, cars.size());
		assertEquals("There should be One white car with this registration parked in the lot. ", car1.getRegistration(), cars.get(0).getRegistration());
	}

	@Test
	public void shouldReturnThreeCarsForThreeParkedWhiteCars() {
		int capacity = 6;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "White");
		Car car3 = new Car("SHX-789", "White");
		Car car4 = new Car("SHD-098", "Purple!");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		parkingLot.parkCar(car4);
		parkingLot.printCarParkStatus();

		List<Car> cars = parkingLot.findParkedCarsByColor("White");
		assertEquals("There should be 3 white cars parked in the lot. ", 3, cars.size());
	}

	@Test
	public void shouldReturnNoCarsForNoneParkedWhiteCar() {
		int capacity = 6;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHB-345", "Blue");
		Car car2 = new Car("SHX-789", "Gold");
		Car car3 = new Car("SHD-098", "Purple!");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		parkingLot.printCarParkStatus();

		List<Car> cars = parkingLot.findParkedCarsByColor("White");
		assertEquals("There should be No white car parked in the lot. ", 0, cars.size());
	}

	@Test
	public void shouldReturnThreeCarsForThreeParkedWhiteCarsAfterUnParking() {
		int capacity = 6;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "White");
		Car car3 = new Car("SHX-789", "White");
		Car car4 = new Car("SHD-098", "White");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		parkingLot.parkCar(car4);

		parkingLot.unParkCar(car2);
		parkingLot.printCarParkStatus();

		List<Car> cars = parkingLot.findParkedCarsByColor("White");
		assertEquals("There should be 3 white cars parked in the lot. ", 3, cars.size());
	}


	@Test
	public void shouldReturnThreeParkedSlotsForThreeParkedWhiteCars() {
		int capacity = 6;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "White");
		Car car3 = new Car("SHX-789", "White");
		Car car4 = new Car("SHD-098", "Purple!");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		parkingLot.parkCar(car4);
		parkingLot.printCarParkStatus();

		List<Integer> slots = parkingLot.findParkedSlotsByCarColor("White");
		assertEquals("There should be 3 white cars parked in the lot. ", 3, slots.size());
		assertEquals("There should be 3 slots with white cars in the lot. ", Arrays.asList(1, 2, 3), slots);
	}

	@Test
	public void shouldReturnOneSlotsForOneRegisteredCars() {
		int capacity = 6;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		Car car3 = new Car("SHX-789", "White");
		Car car4 = new Car("SHD-098", "Purple!");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		parkingLot.parkCar(car4);

		parkingLot.unParkCar(car2);
		parkingLot.printCarParkStatus();

		Integer slot = parkingLot.findParkingSlotByCarRegistrationNumber("SHX-789");
		assertEquals("This car should be parked at slot 3", 3, slot.intValue());

	}

	@Test
	public void shouldReturnStatusOfAllCarsWithSlots() {
		int capacity = 6;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		Car car3 = new Car("SHX-789", "White");
		Car car4 = new Car("SHD-098", "Purple!");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		parkingLot.parkCar(car4);

		parkingLot.unParkCar(car2);
		parkingLot.printCarParkStatus();

		List<ParkingSlot> parkedSlots = parkingLot.findAllParkingSlotsWithCars();
		assertEquals("There should be 3 slots with parked cars.", 3, parkedSlots.size());

	}
}
