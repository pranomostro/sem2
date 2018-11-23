package edu.tum.cs.i1.eist;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import edu.tum.cs.i1.eist.car.Car;

public class MyCollision implements CollisionStrategy {

	@Override
	public boolean detectCollision(Car car1, Car car2) {
		Dimension d1 = car1.getSize( );
		Point     p1 = car1.getPosition( );
		Rectangle r1 = new Rectangle(p1, d1);
		r1.translate(p1.x / 8, p1.y / 8);
		r1.grow(-1 * d1.width / 4, -1 * d1.height / 4);

		Dimension d2 = car2.getSize();
		Point     p2 = car2.getPosition();
		Rectangle r2 = new Rectangle(p2, d2);
		r2.translate(p2.x / 8, p2.y / 8);
		r2.grow(-1 * d2.width / 4, -1 * d2.height / 4);

		return r1.intersects(r2);
	}

	@Override
	public Car evaluate(Car car1, Car car2) {
		Point p1 = car1.getPosition();
		Point p2 = car2.getPosition();

		if (p1.x > p2.x){
			car1.setCrunched();
			return car2;
		} else {
			car2.setCrunched();
			return car1;
		}
	}
}
