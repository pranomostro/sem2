package edu.tum.cs.i1.eist;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JToolBar;

import edu.tum.cs.i1.eist.car.UserCar;

@SuppressWarnings("serial")
public class InstrumentPanel extends JToolBar {
	
	public List<Instrument> instruments = new ArrayList<Instrument>();

	public InstrumentPanel() {
		super(JToolBar.VERTICAL);
		setFloatable(false);
	}
	
	public void setupInstrumentPanel(UserCar userCar) {
		createInstruments(userCar);
		subscribeInstruments(userCar);
		addInstruments();
	}

	private void createInstruments(UserCar car) {
		if (instruments.size() == 0) {
			instruments.add(new RotationsPerSecond(car));
			instruments.add(new SpeedController(car));
			instruments.add(new GPS(car));
			instruments.add(new Speedometer(car));
		}
	}

	private void addInstruments() {
		for (Instrument instrument: instruments) {
			add(instrument);
		}
	}

	private void subscribeInstruments(UserCar car) {
		for (Instrument instrument: instruments) {
			car.subscribeInstrument(instrument);
		}
		
	}


}
