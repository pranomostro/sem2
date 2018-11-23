package edu.tum.cs.i1.eist;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import edu.tum.cs.i1.eist.car.UserCar;


@SuppressWarnings("serial")
public class RotationsPerSecond extends Instrument  {

	private JLabel theLabel = new JLabel("", JLabel.LEFT);

	
	public RotationsPerSecond(UserCar car) {
		super(car);
		setLayout(new BorderLayout());
		add(theLabel, BorderLayout.CENTER);
		theLabel.setText(getSpeedText(car.getSpeed()));
	}
	
	protected String getSpeedText(float rotationsPerSecond){
		return "Umdrehungen pro Sekunde: "+rotationsPerSecond*1000;
	}

	@Override
	public void updateInstrument() {
		String newText = getSpeedText(car.getSpeed());
		if(!newText.equals(theLabel.getText())){
			theLabel.setText(newText);
		}
	}
}
