package edu.tum.cs.i1.eist;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import edu.tum.cs.i1.eist.car.Car;
import edu.tum.cs.i1.eist.car.FastCar;
import edu.tum.cs.i1.eist.car.SlowCar;

public class GameBoard {

	//application logic
	private List<Car> cars = new ArrayList<Car>();
	private Player player;
	private AudioPlayer audioPlayer;
	private Dimension size;
	private MouseSteering mouseSteering;

	private boolean isRunning;

	//constants
	public static int NUMBER_OF_SLOW_CARS = 3;

	public GameBoard(Dimension size) {
		Car playerCar = new FastCar(250, 30);
		this.player = new Player(playerCar);
		this.audioPlayer = new AudioPlayer();
		this.size = size;
		this.addCars();
	}


	public void addCars() {
		for (int i = 0; i < NUMBER_OF_SLOW_CARS; i++) {
			cars.add(new SlowCar(size.width, size.height));
		}
	}

	public void resetCars() {
		this.player.getCar().reset(size.width, size.height);
		this.cars.clear();
		addCars();
	}

	public boolean isRunning(){
		return this.isRunning;
	}

	public MouseSteering getMouseSteering() {
		return this.mouseSteering;
	}

	public List<Car> getCars() {
		return cars;
	}

	public Car getPlayerCar() {
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

			Collision collision = new Collision(player.getCar(), car);
			if(collision.isCollision){
				Car winner = collision.evaluate();
				System.out.println(winner);
				this.audioPlayer.playBangAudio();
			}
		}
	}
}
