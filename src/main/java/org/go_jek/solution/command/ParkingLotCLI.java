package org.go_jek.solution.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *   Author: purnimakamath
 */
public class ParkingLotCLI {

	public static void main(String[] args) {

		ParkingLotCommandInterpreter commandInterpreter = new ParkingLotCommandInterpreter();

		if (args.length > 0) {
			List<String> commands = readFile(args[0]);
			for (String command : commands) {
				String message = commandInterpreter.executeCommand(command);
				System.out.println(message);
			}
		}
		else {
			Scanner scanner = new Scanner(System.in);

			while (scanner.hasNextLine()) {
				String command = scanner.nextLine();
				String message = commandInterpreter.executeCommand(command);
				System.out.println(message);
			}
		}
	}

	protected static List<String> readFile(String filePath) {
		List<String> commands = new ArrayList<>();

		try (FileReader fileReader = new FileReader(new File(filePath));
			 BufferedReader bufferedReader = new BufferedReader(fileReader)) {

			String command = bufferedReader.readLine();
			while (command != null) {
				commands.add(command);
				command = bufferedReader.readLine();
			}

		}
		catch (FileNotFoundException fnfe) {
			System.err.println("File Not Found - " + filePath);
			System.exit(1);
		}
		catch (IOException ioe) {
			System.err.println("File could not be opened/read - " + filePath);
			System.exit(1);
		}
		return commands;
	}
}
