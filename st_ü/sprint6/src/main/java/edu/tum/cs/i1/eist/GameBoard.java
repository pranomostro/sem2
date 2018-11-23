package edu.tum.cs.i1.eist;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import edu.tum.cs.i1.eist.car.Car;
import edu.tum.cs.i1.eist.car.UserCar;
import edu.tum.cs.i1.eist.car.AutonomousCar;

public class GameBoard {

	private List<Car> cars = new ArrayList<Car>();
	private Player player;
	private AudioPlayer audioPlayer;
	private Dimension size;

	private boolean isRunning;
	
	private CollisionStrategy collisionStrategy = new MyCollision();

	public static int NUMBER_OF_AUTONOMOUS_CARS = 5;

	public GameBoard(Dimension size) {
		UserCar userCar = new UserCar(250, 30);
		this.player = new Player(userCar);
		this.audioPlayer = new AudioPlayer();
		this.size = size;
		this.addCars();
	}

	public void addCars() {
		for (int i = 0; i < NUMBER_OF_AUTONOMOUS_CARS; i++) {
			cars.add(new AutonomousCar(size.width, size.height));
		}
		cars.add(new AutonomousCar(size.width, size.height));
	}

	public void resetCars() {
		this.player.getCar().reset(size.width, size.height);
		this.cars.clear();
		addCars();
	}

	public boolean isRunning(){
		return this.isRunning;
	}

	public List<Car> getCars() {
		return cars;
	}

	public UserCar getPlayerCar() {
		return this.player.getCar();
	}

	public AudioPlayer getAudioPlayer() {
		return this.audioPlayer;
	}

	public void update() {
		moveCars();
	}

	public void startGame() {
		playMusic();
		this.isRunning = true;
	}

	public void stopGame() {
		stopMusic();
		this.isRunning = false;
	}

	public void playMusic(){
		this.audioPlayer.playBackgroundMusic();
	}

	public void stopMusic(){
		this.audioPlayer.stopBackgroundMusic();
	}

	public void moveCars() {

		List<Car> cars = getCars();

		int maxX = size.width;
		int maxY = size.height;

		for (Car car : cars) {
			car.updatePosition(maxX, maxY);
		}

		player.getCar().updatePosition(maxX, maxY);

		for (Car car : cars) {
			if (car.isCrunched()){
				continue;
			}

			if(collisionStrategy.detectCollision(getPlayerCar(), car)) {
				collisionStrategy.evaluate(getPlayerCar(), car);
				audioPlayer.playBangAudio();
			}
		}
	}

	public CollisionStrategy getCollisionStrategy() {
		return collisionStrategy;
	}

	public void setCollisionStrategy(CollisionStrategy collisionStrategy) {
		this.collisionStrategy = collisionStrategy;
	}
}
