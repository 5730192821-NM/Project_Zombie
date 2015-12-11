import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import render.GameScreen;
import render.RenderableHolder;
import entity.Hero;
import entity.HeroStatus;
import entity.IceSkill;
import entity.Land;

import entity.SkillStatus;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Ez Game");
		GameScreen screen = new GameScreen(800, 600);
		Land land1 = new Land(0,0);
		Hero human = new Hero(20,315);
		HeroStatus heroStatus = new HeroStatus();
		SkillStatus skillStatus = new SkillStatus();

		
		// Temp
		RenderableHolder.getInstance().add(human);
		RenderableHolder.getInstance().add(land1);
		RenderableHolder.getInstance().add(heroStatus);
		RenderableHolder.getInstance().add(skillStatus);
		
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
			System.out.println(human.x +" "+human.y + " " + land1.x);
			land1.update();
			human.update();
			screen.repaint();
		}
	}

}
