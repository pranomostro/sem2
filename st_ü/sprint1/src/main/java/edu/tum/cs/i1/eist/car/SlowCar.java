package edu.tum.cs.i1.eist.car;

import java.awt.Image;

public class SlowCar extends Car {
	
	public static Image DEFAULT_SLOW_CAR_IMAGE;
	
	public SlowCar(int max_x, int max_y){
		super(max_x, max_y);
		this.MIN_SPEED = 2;
		this.MAX_SPEED = 5;
		this.setRandomSpeed();
		setBody(DEFAULT_SLOW_CAR_IMAGE);
	}
	
}
