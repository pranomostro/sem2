package edu.tum.cs.i1.eist;

import java.awt.Point;

import edu.tum.cs.i1.eist.car.UserCar;

public class Player {

	private UserCar playerCar;

	public Player(UserCar car) {
		this.playerCar = car;
	}

	public void setCar(UserCar car) {
		this.playerCar = car;
	}

	public UserCar getCar() {
		return this.playerCar;
	}

	public void steerCar(Point clickPosition) {

		Point carPosition = this.playerCar.getPosition();
		int delta_x = clickPosition.x - carPosition.x;
		delta_x = Math.abs(delta_x);
		int delta_y = clickPosition.y - carPosition.y;
		double diff = ((double)delta_y)/((double)delta_x);
		double theta = Math.atan(diff);
		int direction = (int)Math.toDegrees(theta);

		if(clickPosition.x>carPosition.x){
			direction = 90-direction;
		}else{
			direction = 270+direction;
		}

		this.playerCar.setDirection(direction);
	}

}
