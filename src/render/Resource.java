package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {
	public static BufferedImage planet;

	static {
		try {
			ClassLoader loader = Resource.class.getClassLoader();
			planet = ImageIO.read(loader.getResource("res/earth.png"));
		} catch (IOException e) {
			planet = null;
		}
	}
	
}
