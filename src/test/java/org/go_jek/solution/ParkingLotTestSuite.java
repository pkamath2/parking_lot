package org.go_jek.solution;
/**
 *   Author: purnimakamath
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		org.go_jek.solution.base.ParkingLotTest.class,
		org.go_jek.solution.api.ParkingLotAPITest.class,
		org.go_jek.solution.command.ParkingLotCommandInterpreterTest.class,
		org.go_jek.solution.command.ParkingLotCLITest.class
})
public class ParkingLotTestSuite {
}
