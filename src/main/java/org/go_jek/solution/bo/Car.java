package org.go_jek.solution.bo;

/**
 *   Author: purnimakamath
 */
public class Car {

	private String registration;
	private String color;

	public Car(String registration, String color) {
		this.registration = registration;
		this.color = color;
	}

	public String getRegistration() {
		return registration;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return '\t' + registration + '\t' + color;
	}
}
