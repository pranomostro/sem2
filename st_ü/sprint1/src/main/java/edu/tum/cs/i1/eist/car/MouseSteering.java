package edu.tum.cs.i1.eist.car;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.tum.cs.i1.eist.GameBoardUI;

public class MouseSteering extends MouseAdapter {
	private Car userCar;
	private GameBoardUI gameBoard;
	
	public MouseSteering(GameBoardUI playingField, Car userCar) {
		this.userCar=userCar;
		this.gameBoard=playingField;
		this.gameBoard.addMouseListener(this);
	}
	
	public void mousePressed(MouseEvent e) {
		//userCar.setDirection(degree);
	}
}
