package edu.tum.cs.i1.eist.car;

import java.awt.Image;

import edu.tum.cs.i1.eist.Instrument;
import edu.tum.cs.i1.eist.InstrumentPanel;

public class UserCar extends Car {
	
	public static Image DEFAULT_USER_CAR_IMAGE;
	
	private InstrumentPanel instrumentPanel;

	public UserCar(int max_x, int max_y) {
		super(max_x, max_y);
		instrumentPanel = new InstrumentPanel();
		instrumentPanel.setupInstrumentPanel(this);
		setBody(DEFAULT_USER_CAR_IMAGE);
	}

	public void subscribeInstrument(Instrument instrument) {
		if (instrument != null && !this.instrumentPanel.instruments.contains(instrument)) {
            this.instrumentPanel.instruments.add(instrument);
        }
	}
	
	public void notifyInstruments() {
		for (Instrument instrument: this.instrumentPanel.instruments) {
			instrument.updateInstrument();
		}
		
	}

	public InstrumentPanel getInstrumentPanel() {
		return this.instrumentPanel;
	}

	@Override
	public void updatePosition(int max_x, int max_y) {
		super.updatePosition(max_x, max_y);
		notifyInstruments();
	}
	
	

}
