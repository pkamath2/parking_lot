package org.go_jek.solution.bo;

/**
 *   Author: purnimakamath
 */
public class ParkingSlot {

	private Integer slot;
	private Car parkedCar;

	public ParkingSlot(Integer slot, Car parkedCar) {
		this.slot = slot;
		this.parkedCar = parkedCar;
	}

	public Integer getSlot() {
		return slot;
	}

	public Car getParkedCar() {
		return parkedCar;
	}

}
