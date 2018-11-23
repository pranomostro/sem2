package edu.tum.cs.i1.eist;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.tum.cs.i1.eist.car.Car;

public class MouseSteering extends MouseAdapter {
	private Car userCar;
	private GameBoardUI gameBoard;

	public MouseSteering(GameBoardUI playingField, Car userCar) {
		this.userCar = userCar;
		this.gameBoard = playingField;
		this.gameBoard.addMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		Point carPosition = userCar.getPosition();
		Point clickPosition = gameBoard.convertPosition(new Point(e.getX(), e.getY()));
		int delta_x = clickPosition.x - carPosition.x;
		delta_x = Math.abs(delta_x);
		int delta_y = clickPosition.y - carPosition.y;
		double diff = ((double)delta_y) / ((double)delta_x);
		double theta = Math.atan(diff);
		int degree = (int)Math.toDegrees(theta);

		if(clickPosition.x > carPosition.x) {
			degree = 90 - degree;
		} else {
			degree = 270 + degree;
		}
		userCar.setDirection(degree);
	}
}

