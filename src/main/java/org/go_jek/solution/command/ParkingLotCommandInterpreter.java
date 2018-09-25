package org.go_jek.solution.command;

import java.util.Arrays;
import java.util.List;

import org.go_jek.solution.api.ParkingLotAPI;
import org.go_jek.solution.bo.Car;

/**
 *   Author: purnimakamath
 */
public class ParkingLotCommandInterpreter {

	private static String ERROR_INCORRECT_SYNTAX = "Error reading command: Incorrect syntax";
	private static String ERROR_INCOMPLETE_SYNTAX = "Error reading command: Incomplete syntax";
	private static List<String> ALL_COMMANDS = Arrays.asList("create_parking_lot","park","leave","status","slot_numbers_for_cars_with_colour","slot_number_for_registration_number","registration_numbers_for_cars_with_colour","exit");

	private ParkingLotAPI api = new ParkingLotAPI();


	public String executeCommand(String command) {
		String message = null;

		if (command == null || command.trim().equals("")) {
			message = ERROR_INCOMPLETE_SYNTAX;
		}
		else {
			try {
				String[] components = command.split(" ");

				//1. create_parking_lot
				if (command.startsWith("create_parking_lot")) {
					if (components.length == 2) {
						message = api.initializeParkingLot(Integer.parseInt(components[1]));
					}
					else {
						message = ERROR_INCOMPLETE_SYNTAX;
					}
				}

				//2. park
				if (command.startsWith("park")) {
					if (components.length == 3) {
						message = api.parkCar(new Car(components[1], components[2]));
					}
					else {
						message = ERROR_INCOMPLETE_SYNTAX;
					}
				}

				//3. leave
				if (command.startsWith("leave")) {
					if (components.length == 2) {
						message = api.leaveSlot(Integer.parseInt(components[1]));
					}
					else {
						message = ERROR_INCOMPLETE_SYNTAX;
					}
				}

				//4. status
				if (command.startsWith("status")) {
					if (components.length == 1) {
						message = api.getCarParkStatus();
					}
					else {
						message = ERROR_INCOMPLETE_SYNTAX;
					}
				}

				//5. slot_numbers_for_cars_with_colour
				if (command.startsWith("slot_numbers_for_cars_with_colour")) {
					if (components.length == 2) {
						message = api.findParkedSlotsByCarColor(components[1]);
					}
					else {
						message = ERROR_INCOMPLETE_SYNTAX;
					}
				}

				//6. slot_number_for_registration_number
				if (command.startsWith("slot_number_for_registration_number")) {
					if (components.length == 2) {
						message = api.findParkedSlotsByCarRegistration(components[1]);
					}
					else {
						message = ERROR_INCOMPLETE_SYNTAX;
					}
				}

				//7. slot_number_for_registration_number
				if (command.startsWith("registration_numbers_for_cars_with_colour")) {
					if (components.length == 2) {
						message = api.findCarRegistrationsByColor(components[1]);
					}
					else {
						message = ERROR_INCOMPLETE_SYNTAX;
					}
				}

				//8. exit
				if (command.startsWith("exit")) {
					System.exit(0);
				}

				//9. Final condition - if command belongs to no pre-existing set
				if (!ALL_COMMANDS.contains(components[0])){
					message = ERROR_INCORRECT_SYNTAX;
				}

			}
			catch (Exception e) {
				message = ERROR_INCORRECT_SYNTAX;
			}
		}

		return message;
	}

}
