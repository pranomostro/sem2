package edu.tum.cs.i1.eist.car;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

public abstract class Car {
	
	public int MAX_SPEED = 10;
	public int MIN_SPEED = 2;
	protected int speed = MIN_SPEED;
	
	private Image icon;
	protected Point position = new Point(5,5);
	private Dimension size = new Dimension(100,30);
	private int direction = 90;
	public boolean isCrunched = false;
	
	public Car(int max_x, int max_y){
		this.position.x = (int) (Math.random( ) * (max_x-getSize().width));
		this.position.y = (int) (Math.random( ) * (max_y-getSize().height));
		if(this.position.y < getSize().height){
			this.position.y = getSize().height;
		}
		
		setDirection((int)(Math.random( ) * 360));
		setRandomSpeed();
		this.isCrunched = false;
	}
	
	public void reset(int max_x, int max_y) {
		this.position.x = 0;
		this.position.y = max_y;
		setDirection(90);
		this.speed = 5;
		this.isCrunched = false;
	}
	
	protected void setRandomSpeed() {
		int initialSpeed = (int) (Math.random( ) * MAX_SPEED);
		if(initialSpeed < MIN_SPEED){
			initialSpeed = MIN_SPEED;
		}
		this.speed = initialSpeed;
	}

	public void setDirection(int direction)  throws IllegalArgumentException {
		if(direction < 0 || direction > 360){
			throw new IllegalArgumentException("Direction must be between 0 and 360");
		}
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void incrementSpeed(){
		if(this.speed < MAX_SPEED){
			this.speed++;
		}
	}
	
	public void decrementSpeed(){
		if(this.speed > MIN_SPEED){
			this.speed--;
		}
	}
	
	public Image getBody(){
		return this.icon;
	}
	
	public void setBody(Image body) throws IllegalArgumentException {
		if(body == null){
			throw new IllegalArgumentException("The chassis image of a car connot be null.");
		}
		this.icon = body;
	}
	
	public Point getPosition(){
		return this.position;
	}
	
	public Dimension getSize(){
		return this.size;
	}
	
	public final void setCrunched() {
		this.isCrunched = true;
    }
	
    public final boolean isCrunched() {
		return isCrunched;
    }
	
	public void updatePosition(int max_x, int max_y) {
		if (isCrunched) return;
		float delta_x = speed * (float) Math.sin(Math.toRadians(direction));
		float delta_y = speed * (float) Math.cos(Math.toRadians(direction));
		
		this.position.x = this.position.x + (int)delta_x;
		this.position.y = this.position.y + (int)delta_y;
		
		if (position.x < 0) {
			position.x = 0;
			direction = 360 - direction;
		}
		
		if (position.x + size.width > max_x) {
			position.x = max_x - size.width;
			direction = 360 - direction;
		}
		if (position.y-size.height < 0) {
			position.y = size.height;
			direction = 180 - direction;
			if(direction < 0){
				direction = 360 + direction;
			}
		}
		if (position.y > max_y) {
			position.y = max_y;
			direction = 180 - direction;
			if(direction < 0){
				direction = 360 + direction;
			}
		}
    }
}

