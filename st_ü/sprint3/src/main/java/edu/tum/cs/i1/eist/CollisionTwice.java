package edu.tum.cs.i1.eist;

import edu.tum.cs.i1.eist.car.Car;

public class CollisionTwice extends Collision {
	protected Car car1;
	protected Car car2;
	public static int collisionCount;
	public boolean isCollision;

	public CollisionTwice(Car car1, Car car2) {
		super(car1, car2);
		collisionCount=0;
		detectCollision();
	}

	public boolean detectCollision() {
		if(collisionCount==0&&super.detectCollision()) {
			collisionCount++;
			System.out.println("0");
		} else if(collisionCount==1&&!super.detectCollision()) {
			collisionCount++;
			System.out.println("1");
		} else if(collisionCount==2&&super.detectCollision()) {
			collisionCount++;
			System.out.println("2");
		}
		return collisionCount>=3;
	}
}
