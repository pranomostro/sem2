package edu.tum.cs.i1.eist;

import javax.swing.JPanel;

import edu.tum.cs.i1.eist.car.UserCar;

@SuppressWarnings("serial")
public abstract class Instrument extends JPanel {
	
	protected UserCar car;

	public Instrument(UserCar car) {
		this.car = car;
	}
	
	public abstract void updateInstrument();

}
