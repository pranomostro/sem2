package edu.tum.cs.i1.eist;

import edu.tum.cs.i1.eist.car.Car;

public interface CollisionStrategy {

	public boolean detectCollision(Car car1, Car car2);
	public Car evaluate(Car car1, Car car2);
	
}
