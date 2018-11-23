package edu.tum.cs.i1.eist;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import edu.tum.cs.i1.eist.car.UserCar;



public class SpeedController extends Instrument implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private JButton up;
	private JButton down;
	
	public static Image UP;
	public static Image DOWN;
	
	private JLabel label = new JLabel("Geschw. Kontrolle", JLabel.LEFT);
	
	public SpeedController(UserCar car){
		super(car);
		
		setLayout(new BorderLayout());
		if(UP != null && DOWN != null){
			up = new JButton(new ImageIcon(UP));
			down = new JButton(new ImageIcon(DOWN));
		}else{
			up = new JButton("up");
			down = new JButton("down");
		}
		
		up.addActionListener(this);
		down.addActionListener(this);
		
		add(label, BorderLayout.WEST);
		JToolBar buttonBar = new JToolBar();
		buttonBar.setFloatable(false);
		buttonBar.add(up);
		buttonBar.add(down);
		add(buttonBar, BorderLayout.CENTER);
		enableButtons();
	}
	
	private void enableButtons() {
		if(this.car.getSpeed() < car.MAX_SPEED){
			up.setEnabled(true);
		}else{
			up.setEnabled(false);
		}
		
		if(this.car.getSpeed() > car.MIN_SPEED){
			down.setEnabled(true);
		}else{
			down.setEnabled(false);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(up)){
			car.incrementSpeed();
			car.notifyInstruments();
			
		}else{
			car.decrementSpeed();
			car.notifyInstruments();
		}
	}

	@Override
	public void updateInstrument() {
		enableButtons();
	}
}

