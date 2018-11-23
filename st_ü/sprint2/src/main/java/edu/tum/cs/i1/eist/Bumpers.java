package edu.tum.cs.i1.eist;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;

import edu.tum.cs.i1.eist.car.*;

public class Bumpers {

	public static void main(String[] args) {

		try {
			SlowCar.DEFAULT_SLOW_CAR_IMAGE = loadImage("Kaefer.gif");
			FastCar.DEFAULT_FAST_CAR_IMAGE = loadImage("A-Klasse.gif");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		BumpersWindow bumperWindow = new BumpersWindow();
		bumperWindow.pack();
		bumperWindow.setVisible(true);
	}

	public static Image loadImage(String name) throws Exception {
		URL url = Bumpers.class.getClassLoader().getResource(name);
		MediaTracker m = new MediaTracker(new JPanel());
		Image img = Toolkit.getDefaultToolkit().getImage(url);
		m.addImage(img, 0);
		m.waitForAll();
		return img;
	}

}

