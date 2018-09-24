package org.go_jek.solution;

import org.go_jek.solution.bo.Car;
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
}
