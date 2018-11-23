package edu.tum.cs.i1.eist;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import edu.tum.cs.i1.eist.car.UserCar;

public class Speedometer extends Instrument {
	
	private static final long serialVersionUID = 1L;
	private JLabel theLabel = new JLabel("", JLabel.LEFT);
	
	public Speedometer(UserCar car){
		super(car);
		setLayout(new BorderLayout());
		add(theLabel, BorderLayout.CENTER);
		theLabel.setText(getSpeedText(car.getSpeed()));
	}
	
	protected String getSpeedText(float rotationsPerSecond){
		return "Geschwindigkeit: "+rotationsPerSecond*10*8/5+" km/h";
	}

	@Override
	public void updateInstrument() {
		String newText = getSpeedText(car.getSpeed());
		if(!newText.equals(theLabel.getText())){
			theLabel.setText(newText);
		}
	}
}
