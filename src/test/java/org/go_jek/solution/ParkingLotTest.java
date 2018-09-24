package org.go_jek.solution;

import org.go_jek.solution.bo.Car;
import org.go_jek.solution.execption.ParkingLotException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *   Author: purnimakamath
 */
public class ParkingLotTest {

	@Test
	public void shouldCreateParkingLotOfCapacity3() {
		int capacity = 3;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);

		assertEquals("Parking lot capacity should be " + capacity, parkingLot.getCapacity(), capacity);
		assertEquals("Parking lot size should be 0.", parkingLot.getSize(), 0);
	}

	@Test
	public void shouldReduceSizeAfterParkingCar() {
		int capacity = 3;
		ParkingLot parkingLot = ParkingLot.getInstance();
		Car someCar = new Car("SHC-123", "White");
		parkingLot.createLotWithCapacity(capacity);
		int slot = parkingLot.parkCar(someCar);

		assertNotEquals("Parking lot capacity should not be "+capacity, parkingLot.getSize(), capacity);
		assertEquals("Parking lot size should be "+1, parkingLot.getSize(), 1);
	}

	@Test
	public void shouldParkingMoreThanCapacityThrowException() {
		int capacity = 3;
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.createLotWithCapacity(capacity);
		String message = null;
		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-345", "Blue");
		Car car3 = new Car("SHA-567","Red");
		Car car4 = new Car("SHZ-789","Yellow");

		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);
		parkingLot.parkCar(car3);
		try {
			parkingLot.parkCar(car4);
		}
		catch (ParkingLotException e) {
			message = e.getMessage();
		}
		assertEquals("Parking more then capacity should throw an exception", message,"Sorry, parking lot is full");
	}
}
