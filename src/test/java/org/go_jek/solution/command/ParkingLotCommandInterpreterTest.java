package org.go_jek.solution.command;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *   Author: purnimakamath
 */
public class ParkingLotCommandInterpreterTest {

	@Test
	public void shouldCreateParkingLotOfCapacitySix() {
		String command = "create_parking_lot 6";
		String message = new ParkingLotCommandInterpreter().executeCommand(command);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:create_parking_lot API should return the exact message", "Created a parking lot with 6 slots", message);
	}

	@Test
	public void shouldNotCreateParkingLotIncorrectSyntax() {
		String command = "create_parking_lot $";
		String message = new ParkingLotCommandInterpreter().executeCommand(command);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:create_parking_lot API should return an error message", "Error reading command: Incorrect syntax", message);
	}

	@Test
	public void shouldNotCreateParkingLotIncompleteSyntax() {
		String command = "create_parking_lot";
		String message = new ParkingLotCommandInterpreter().executeCommand(command);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:create_parking_lot API should return an error message", "Error reading command: Incomplete syntax", message);
	}

	@Test
	public void shouldParkCarAtSlotOne() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		command = "park ABC-123-456 Black";
		message = parkingLotCommandInterpreter.executeCommand(command);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Allocated slot number: 1", message);

		command = "park ZBC-123-458 Black";
		message = new ParkingLotCommandInterpreter().executeCommand(command);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Allocated slot number: 2", message);
	}

	@Test
	public void shouldNotParkIncorrectSyntax() {
		String command = "park";
		String message = new ParkingLotCommandInterpreter().executeCommand(command);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return an error message", "Error reading command: Incomplete syntax", message);
	}




}
