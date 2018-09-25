# GO-JEK Parking Lot Assignment

###Technical Requirements
This parking lot assignment works with the following software packages. Of these only Java and Maven need installations. The versions are what I am using on my local.
* Java 1.8
* JUnit 4.12
* Maven 3.3.9

###Setup
`bin/setup` downloads JUnit dependencies, builds the project and runs the tests. 
`bin/parking_lot functional_spec/fixtures/file_input.txt` executes the program in batch mode.
`bin/parking_lot` executes the program in interactive mode.

###Design
Some of the important classes and their explanation
* `org.go_jek.solution.base.ParkingLot` This is the core class of the implementation. Singleton instance and wraps around a java.util.Hashmap which maintains state of the parking lot. 
* `org.go_jek.solution.api.ParkingLotAPI` Wraps around the core ParkingLot to provide consumable APIs to the external world. Ideally, we could expose microservices via this class.
* `org.go_jek.solution.command.ParkingLotCLI` and `org.go_jek.solution.command.ParkingLotCommandInterpreter` Consumes commands from both file as well as interactive mode and delegated to the ParkingLotAPI to execute.

###Test Driven Development
Unit tests can be found under `src/test/java` directory. 