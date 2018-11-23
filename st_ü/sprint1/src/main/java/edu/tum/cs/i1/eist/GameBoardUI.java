package edu.tum.cs.i1.eist;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import edu.tum.cs.i1.eist.car.*;

public class GameBoardUI extends Canvas implements Runnable {

	//constants
	private static Color backgroundColor = Color.WHITE;
	private static int SLEEP_TIME = 1000 / 25; //this gives us 25fps
	private static final long serialVersionUID = 1L;
	private static Dimension DEFAULT_SIZE = new Dimension(500, 300); 

	//animation
	private Thread theThread;

	//user interface objects
	private GameBoard gameBoard;
	private Dimension size;
	private ToolBar toolBar;

	public GameBoardUI(ToolBar toolBar) {
		this.toolBar = toolBar;
		this.size = getPreferredSize();
		this.gameBoard = new GameBoard(this.size);

		setSize(this.size);
		gameSetup();
	}

	public void update(Graphics graphics) {
		paint(graphics);
	}

	public Dimension getPreferredSize() {
		return DEFAULT_SIZE;
	}

	public Point convertPosition(Point toConvert) {
		return new Point(toConvert.x, getSize().height - toConvert.y);
	}

	public void run() {
		while (gameBoard.isRunning()) {
			this.gameBoard.update();
			repaint();
			try {
				Thread.sleep(SLEEP_TIME); //milliseconds to sleep
			}
			catch (InterruptedException ex) { 
				ex.printStackTrace(); 
			}
		}
	}

	public void paint(Graphics graphics) {

		graphics.setColor(backgroundColor);
		graphics.fillRect(0, 0, getSize().width, getSize().height);

		for (Car car : this.gameBoard.getCars()) {
			paintCar(car, graphics);
		}
		paintCar(this.gameBoard.getPlayerCar(), graphics);
	}

	private void paintCar(Car car, Graphics g) {
		Point carPosition = car.getPosition();
		Point canvasPosition = convertPosition(carPosition);
		g.drawImage(car.getBody(), canvasPosition.x, canvasPosition.y, car.getSize().width, car.getSize().height, null);
	}

	public void gameSetup() {
		gameBoard.resetCars();
		repaint();
	}

	public void startGame() {
		if(!gameBoard.isRunning()){

			this.gameBoard.startGame();

			theThread = new Thread(this);
			theThread.start();
			repaint();
			this.toolBar.resetToolBarButtonStatus(true);	
		}
	}

	public void stopGame() {
		if(gameBoard.isRunning()){

			this.gameBoard.stopGame();
			this.toolBar.resetToolBarButtonStatus(false);
		}
	}
}
