package org.go_jek.solution.command;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *   Author: purnimakamath
 */
public class ParkingLotCLITest {

	@Test
	public void shouldReadFileWithoutExceptions() {
		ParkingLotCLI cli = new ParkingLotCLI();
		List<String> commands = cli.readFile(ParkingLotCLITest.class.getClassLoader().getResource("file_inputs.txt").getPath());

		assertNotEquals("Should retrieve a list of commands", commands.size(), 0);
	}
}
