package org.go_jek.solution;

import org.go_jek.solution.bo.Car;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *   Author: purnimakamath
 */
public class ParkingLotAPITest {

	@Test
	public void shouldInitCarParkAndReturnFormattedString() {
		ParkingLotAPI api = new ParkingLotAPI();
		String message = api.initializeParkingLot(6);

		assertEquals("The create_parking_lot API should return the exact message", "Created a parking lot with 6 slots", message);
	}

	@Test
	public void shouldParkCarAndReturnFormattedString() {
		ParkingLotAPI api = new ParkingLotAPI();
		api.initializeParkingLot(6);

		Car car1 = new Car("SHC-123", "White");
		String message = api.parkCar(car1);

		assertThat("The parkCar API should return the exact message", message, CoreMatchers.containsString("Allocated slot number:"));
	}

	@Test
	public void shouldUnParkCarAndReturnFormattedString() {
		ParkingLotAPI api = new ParkingLotAPI();
		api.initializeParkingLot(6);

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-123", "Blue");
		api.parkCar(car1);
		api.parkCar(car2);

		String message = api.unParkCar(car1);
		assertThat("The unParkCar API should return the exact message", message, CoreMatchers.both(CoreMatchers.startsWith("Slot number"))
																										.and(CoreMatchers.endsWith("is free")));
	}
}
