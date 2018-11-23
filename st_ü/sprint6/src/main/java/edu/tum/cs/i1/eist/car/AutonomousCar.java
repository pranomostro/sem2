package edu.tum.cs.i1.eist.car;

import java.awt.Image;

public class AutonomousCar extends Car {
	public static Image DEFAULT_AUTONOMOUS_CAR_IMAGE;

	public AutonomousCar(int max_x, int max_y){
		super(max_x, max_y);

		this.MIN_SPEED = 5;
		this.MAX_SPEED = 10;
		this.setRandomSpeed();
		setBody(DEFAULT_AUTONOMOUS_CAR_IMAGE);
	}
}