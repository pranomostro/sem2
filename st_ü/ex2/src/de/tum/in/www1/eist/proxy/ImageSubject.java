package de.tum.in.www1.eist.proxy;

import java.awt.image.BufferedImage;

public abstract class ImageSubject {

	protected BufferedImage bufferedImage;
	protected String imageTitle;
	
	protected static final String filePath = System.getProperty("user.dir") + "/logo.jpg";
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public String getImageTitle() {
		return imageTitle;
	}
}