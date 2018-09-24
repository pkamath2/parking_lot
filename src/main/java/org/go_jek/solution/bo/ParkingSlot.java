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

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public Car getParkedCar() {
		return parkedCar;
	}

	public void setParkedCar(Car parkedCar) {
		this.parkedCar = parkedCar;
	}
}
