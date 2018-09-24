package org.go_jek.solution;

import java.util.Scanner;

/**
 *   Author: purnimakamath
 */
public class ParkingLotCLI {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while(scanner.hasNext()){
			String command = scanner.nextLine();
			if(command.endsWith(".txt")) {
				System.out.println("Consuming input from file");
			}else{
				System.out.println("Consuming input from command line");
			}

		}

	}
}
