package edu.tum.cs.i1.eist.car;

import java.awt.Image;

public class OldCar extends Car {
	public static Image DEFAULT_OLD_CAR_IMAGE;

	public OldCar(int max_x, int max_y){
		super(max_x, max_y);

		this.MIN_SPEED = 5;
		this.MAX_SPEED = 10;
		this.setRandomSpeed();
		setBody(DEFAULT_OLD_CAR_IMAGE);
	}
}
