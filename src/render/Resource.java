package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {
	public static BufferedImage planet = getImage("res/earth.png");
	public static BufferedImage hero = getImage("res/Hero_1.png");
	public static BufferedImage hero_f = getImage("res/Hero_1.2.png");
	public static BufferedImage statusBG = getImage("res/herostatus.png");
	public static BufferedImage skillBox = getImage("res/skill.png");
	public static BufferedImage land1 = getImage("res/land1.png");
	public static BufferedImage ice1 = getImage("res/ice1.png");
	public static BufferedImage fire1 = getImage("res/fire1.png");
	public static BufferedImage meteor1 = getImage("res/meteor1.png");
	public static BufferedImage meteor1_2 = getImage("res/meteor1.2.png");
	public static BufferedImage poison1 = getImage("res/poison1.png");
	public static BufferedImage spike1 = getImage("res/spike1.png");

	static BufferedImage getImage(String directory) {
		BufferedImage b;
		try {
			ClassLoader loader = Resource.class.getClassLoader();
			b = ImageIO.read(loader.getResource(directory));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}
	
}
