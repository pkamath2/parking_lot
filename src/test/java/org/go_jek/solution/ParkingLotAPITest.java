package org.go_jek.solution;

import java.util.Arrays;

import org.go_jek.solution.bo.Car;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *   Author: purnimakamath
 */
@SuppressWarnings("Duplicates")
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
	public void shouldParkCarAndReturnFormattedExceptionString() {
		ParkingLotAPI api = new ParkingLotAPI();
		api.initializeParkingLot(1);
		String message = null;

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHC-124", "Red");

		message = api.parkCar(car1);
		message = api.parkCar(car2);
		assertEquals("The parkCar API (Exception) should return the exact message", "Sorry, parking lot is full", message);
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

	@Test
	public void shouldUnParkCarAndReturnFormattedExceptionString() {
		ParkingLotAPI api = new ParkingLotAPI();
		api.initializeParkingLot(6);
		String message = null;

		Car car1 = new Car("SHC-123", "White");
		Car car2 = new Car("SHB-123", "Blue");
		api.parkCar(car1);

		message = api.unParkCar(car2);
		assertEquals("The unParkCar API (Exception) should return the exact message", "Car not parked in the Parking Lot", message);
	}

	@Test
	public void shouldReturnRegistrationNumbersOfThreeWhiteParkedCars() {
		ParkingLotAPI api = new ParkingLotAPI();
		api.initializeParkingLot(6);
		String message = null;

		Car car1 = new Car("SHC-123", "Blue");
		Car car2 = new Car("SHB-123", "White");
		Car car3 = new Car("SHD-123", "White");
		Car car4 = new Car("SHE-123", "White");

		api.parkCar(car1);
		api.parkCar(car2);
		api.parkCar(car3);
		api.parkCar(car4);

		message = api.findCarRegistrationsByColor("White");
		assertEquals("Must find all cars which are white", "SHB-123, SHD-123, SHE-123", message);
	}

	@Test
	public void shouldReturnNoRegistrationNumbersForNoWhiteParkedCars() {
		ParkingLotAPI api = new ParkingLotAPI();
		api.initializeParkingLot(6);
		String message = null;

		Car car1 = new Car("SHC-123", "Blue");
		Car car2 = new Car("SHB-123", "Red");
		Car car3 = new Car("SHD-123", "Yellow");
		Car car4 = new Car("SHE-123", "Blue");

		api.parkCar(car1);
		api.parkCar(car2);
		api.parkCar(car3);
		api.parkCar(car4);

		message = api.findCarRegistrationsByColor("White");
		assertEquals("Must return empty string as there are no white parked cars", "", message);
	}
}
