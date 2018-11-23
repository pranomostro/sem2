package de.tum.in.www1.eist.client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.tum.in.www1.eist.proxy.ImageSubject;

public class PictureJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	public PictureJFrame(ImageSubject imageSubject)
	{
		frame = new JFrame();
		frame.setTitle(imageSubject.getImageTitle());
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(imageSubject.getBufferedImage())));
		frame.pack();
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
