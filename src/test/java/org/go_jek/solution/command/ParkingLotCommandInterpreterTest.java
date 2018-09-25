package org.go_jek.solution.command;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *   Author: purnimakamath
 */
public class ParkingLotCommandInterpreterTest {

	@Test
	public void shouldFailGracefullyOnNullOrBlankCommand() {
		String command = null;
		String message = new ParkingLotCommandInterpreter().executeCommand(command);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:create_parking_lot API should return the exact message", "Error reading command: Incomplete syntax", message);

		command = "";
		message = new ParkingLotCommandInterpreter().executeCommand(command);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:create_parking_lot API should return the exact message", "Error reading command: Incomplete syntax", message);
	}

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


	@Test
	public void shouldFreeRequestedSlotInParkingLot() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command1 = "park ABC-123-456 Black";
		String command2 = "park MH-123-000 Blue";
		parkingLotCommandInterpreter.executeCommand(command1);
		parkingLotCommandInterpreter.executeCommand(command2);

		String command3 = "leave 2";
		message = parkingLotCommandInterpreter.executeCommand(command3);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Slot number 2 is free", message);

	}

	@Test
	public void shouldNotLeaveParkingLotIncorrectSyntax() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		parkingLotCommandInterpreter.executeCommand(command);

		String command1 = "park ABC-123-456 Black";
		String command2 = "park MH-123-000 Blue";
		parkingLotCommandInterpreter.executeCommand(command1);
		parkingLotCommandInterpreter.executeCommand(command2);

		String command3 = "leave $";
		String message = parkingLotCommandInterpreter.executeCommand(command3);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:create_parking_lot API should return an error message", "Error reading command: Incorrect syntax", message);
	}

	@Test
	public void shouldNotLeaveParkingLotIncompleteSyntax() {
		String command = "leave";
		String message = new ParkingLotCommandInterpreter().executeCommand(command);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:create_parking_lot API should return an error message", "Error reading command: Incomplete syntax", message);
	}


	@Test
	public void shouldShowStatusWithAllCarsParked() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command1 = "park ABC-123-456 Black";
		String command2 = "park MH-123-000 Blue";
		parkingLotCommandInterpreter.executeCommand(command1);
		parkingLotCommandInterpreter.executeCommand(command2);

		String command3 = "status";
		message = parkingLotCommandInterpreter.executeCommand(command3);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Slot No.    Registration No    Colour\n1           ABC-123-456      Black\n2           MH-123-000      Blue", message);

	}

	@Test
	public void shouldShowStatusWithNoCarsParked() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command3 = "status";
		message = parkingLotCommandInterpreter.executeCommand(command3);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Slot No.    Registration No    Colour", message);

	}


	@Test
	public void shouldShowSlotNumbersForAllWhiteCarsParked() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command1 = "park ABC-123-456 Black";
		String command2 = "park MH-123-000 White";
		String command3 = "park HYD-123-007 White";
		parkingLotCommandInterpreter.executeCommand(command1);
		parkingLotCommandInterpreter.executeCommand(command2);
		parkingLotCommandInterpreter.executeCommand(command3);

		String command4 = "slot_numbers_for_cars_with_colour White";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "2, 3", message);

	}

	@Test
	public void shouldShowNotFoundForNoWhiteCarsParked() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command1 = "park ABC-123-456 Black";
		String command2 = "park MH-123-000 Gold";
		String command3 = "park HYD-123-007 Pink";
		parkingLotCommandInterpreter.executeCommand(command1);
		parkingLotCommandInterpreter.executeCommand(command2);
		parkingLotCommandInterpreter.executeCommand(command3);

		String command4 = "slot_numbers_for_cars_with_colour White";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Not found", message);

	}


	@Test
	public void shouldShowNotFoundForNoCarsParked() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command4 = "slot_numbers_for_cars_with_colour White";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Not found", message);

	}

	@Test
	public void shouldShowIncompleteSyntaxForSlotNumbersWithColor() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command4 = "slot_numbers_for_cars_with_colour";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Error reading command: Incomplete syntax", message);

	}


	@Test
	public void shouldShowSlotNumbersForCarsParkedByRegistration() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command1 = "park ABC-123-456 Black";
		String command2 = "park MH-123-000 White";
		String command3 = "park HYD-123-007 White";
		parkingLotCommandInterpreter.executeCommand(command1);
		parkingLotCommandInterpreter.executeCommand(command2);
		parkingLotCommandInterpreter.executeCommand(command3);

		String command4 = "slot_number_for_registration_number ABC-123-456";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "1", message);

	}


	@Test
	public void shouldShowNotFoundForNoCarsParkedByRegistration() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command4 = "slot_number_for_registration_number ABC-123-456";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Not found", message);

	}

	@Test
	public void shouldShowIncompleteSyntaxForNoCarsParkedByRegistration() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command4 = "slot_number_for_registration_number";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Error reading command: Incomplete syntax", message);
	}

	@Test
	public void shouldShowRegNumbersForAllWhiteCarsParked() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command1 = "park ABC-123-456 Black";
		String command2 = "park MH-123-000 White";
		String command3 = "park HYD-123-007 White";
		parkingLotCommandInterpreter.executeCommand(command1);
		parkingLotCommandInterpreter.executeCommand(command2);
		parkingLotCommandInterpreter.executeCommand(command3);

		String command4 = "registration_numbers_for_cars_with_colour White";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "MH-123-000, HYD-123-007", message);

	}

	@Test
	public void shouldShowRegNumberNotFoundForNoWhiteCarsParked() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command1 = "park ABC-123-456 Black";
		String command2 = "park MH-123-000 Gold";
		String command3 = "park HYD-123-007 Pink";
		parkingLotCommandInterpreter.executeCommand(command1);
		parkingLotCommandInterpreter.executeCommand(command2);
		parkingLotCommandInterpreter.executeCommand(command3);

		String command4 = "registration_numbers_for_cars_with_colour White";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Not found", message);

	}


	@Test
	public void shouldShowREgNumberNotFoundForNoCarsParked() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command4 = "registration_numbers_for_cars_with_colour White";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Not found", message);

	}

	@Test
	public void shouldShowREgNumberIncompleteSyntax() {
		ParkingLotCommandInterpreter parkingLotCommandInterpreter = new ParkingLotCommandInterpreter();

		String command = "create_parking_lot 6";
		String message = parkingLotCommandInterpreter.executeCommand(command);

		String command4 = "registration_numbers_for_cars_with_colour";
		message = parkingLotCommandInterpreter.executeCommand(command4);

		assertEquals("The ParkingLotCommandInterpreter.executeCommand:park API should return the exact message", "Error reading command: Incomplete syntax", message);

	}

}
