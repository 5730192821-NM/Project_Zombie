import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import render.GameScreen;
import render.RenderableHolder;
import ui.HeroStatus;
import ui.Pause;
import ui.SkillStatus;
import entity.Hero;
import entity.IceSkill;
import entity.Land;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Ez Game");
		GameScreen screen = new GameScreen(800, 600);
		Land land1 = new Land(0,0);
		Hero human = new Hero(20,315,land1);
		HeroStatus heroStatus = new HeroStatus();
		SkillStatus skillStatus = new SkillStatus();
		Pause button = new Pause();

		
		// Temp
		RenderableHolder.getInstance().add(human);
		RenderableHolder.getInstance().add(land1);
		RenderableHolder.getInstance().add(heroStatus);
		RenderableHolder.getInstance().add(skillStatus);
		RenderableHolder.getInstance().add(button);
		
		f.add(screen);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		screen.requestFocus();
	
		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			land1.update();
			human.update();
			screen.repaint();
		}
	}

}
