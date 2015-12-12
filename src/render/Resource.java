package render;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Resource {
	
	public static final Font standardFont = new Font("Tahoma", Font.BOLD, 30);
	
	//Hero
	public static BufferedImage hero = getImage("res/Hero_2.png");
	public static BufferedImage hero_f = getImage("res/Hero_2_2.png");
	
	//UI
	public static BufferedImage statusBG = getImage("res/herostatus.png");
	public static BufferedImage skillBox = getImage("res/skill.png");
	public static BufferedImage pause = getImage("res/pause.png");
	
	//BG
	public static BufferedImage land1 = getImage("res/land1.png");
	
	//Skill
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
