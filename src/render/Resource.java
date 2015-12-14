package render;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Resource {
	
	public static final int screenWidth = 800, screenHeight = 600;
	public static final Font wordFont = new Font("Tahoma", Font.BOLD, 45);
	public static final Font standardFont = new Font("Tahoma", Font.PLAIN, 15);
	public static final Font biggerFont = new Font("Tahoma", Font.PLAIN, 25);

	
	//Hero
	public static BufferedImage hero = getImage("res/Hero/Hero_2.png");
	public static BufferedImage hero_f = getImage("res/Hero/Hero_2_2.png");
	public static BufferedImage hero_a = getImage("res/Hero/Hero_2_3.png");
	public static BufferedImage hero_af = getImage("res/Hero/Hero_2_4.png");
	public static BufferedImage hero_d = getImage("res/Hero/Hero_2_5.png");
	public static BufferedImage hero_df = getImage("res/Hero/Hero_2_5_f.png");
	
	//Monster
	public static BufferedImage monster_golem_1_1 = getImage("res/monster/golem_1_1.png");
	public static BufferedImage monster_golem_1_2 = getImage("res/monster/golem_1_2.png");
	public static BufferedImage monster_golem_1_3 = getImage("res/monster/golem_1_3.png");
	public static BufferedImage monster_golem_1_1_f = getImage("res/monster/golem_1_1_f.png");
	public static BufferedImage monster_golem_1_2_f = getImage("res/monster/golem_1_2_f.png");
	public static BufferedImage monster_golem_1_3_f = getImage("res/monster/golem_1_3_f.png");
	public static BufferedImage monster_yeti_1_1 = getImage("res/monster/yeti_1_1.png");
	public static BufferedImage monster_yeti_1_2 = getImage("res/monster/yeti_1_2.png");
	public static BufferedImage monster_yeti_1_3 = getImage("res/monster/yeti_1_3.png");
	public static BufferedImage monster_yeti_1_4 = getImage("res/monster/yeti_1_4.png");
	public static BufferedImage monster_yeti_1_1_f = getImage("res/monster/yeti_1_1_f.png");
	public static BufferedImage monster_yeti_1_2_f = getImage("res/monster/yeti_1_2_f.png");
	public static BufferedImage monster_yeti_1_3_f = getImage("res/monster/yeti_1_3_f.png");
	public static BufferedImage monster_yeti_1_4_f = getImage("res/monster/yeti_1_4_f.png");
	
	//UI
	public static BufferedImage statusBG = getImage("res/ui/herostatus.png");
	public static BufferedImage skillBox = getImage("res/ui/skill.png");
	public static BufferedImage pause = getImage("res/ui/pause.png");
	
	//BG
	public static BufferedImage land1 = getImage("res/map/land1.png");
	public static BufferedImage background1 = getImage("res/map/background1.png");
	
	//Skill
	public static BufferedImage ice1 = getImage("res/skill/ice1.png");
	public static BufferedImage fire1 = getImage("res/skill/fire1.png");
	public static BufferedImage meteor1 = getImage("res/skill/meteor1.png");
	public static BufferedImage meteor1_2 = getImage("res/skill/meteor1_2.png");
	public static BufferedImage poison1 = getImage("res/skill/poison1.png");
	public static BufferedImage spike1 = getImage("res/skill/spike1.png");

	
	//SkillStatus
	public static BufferedImage fireStatus = getImage("res/ui/fire.png"); 
	public static BufferedImage iceStatus = getImage("res/ui/ice.png"); 
	public static BufferedImage meteorStatus = getImage("res/ui/meteor.png"); 
	public static BufferedImage poisonStatus = getImage("res/ui/poison.png"); 
	public static BufferedImage spikeStatus = getImage("res/ui/spike.png"); 

	//Debuff
	public static BufferedImage burn = getImage("res/skill/burn.png");
	public static BufferedImage poison = getImage("res/skill/poisoned.png");
	public static BufferedImage freeze = getImage("res/skill/freeze.png");


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
