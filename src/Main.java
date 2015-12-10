import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import render.GameScreen;
import render.RenderableHolder;
import entity.Hero;
import entity.Meteoroid;
import entity.Planet;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Ez Game");
		GameScreen screen = new GameScreen(700, 400);
		Planet earth = new Planet(50, 200, 300);
		Hero human = new Hero(0,320);
		Meteoroid bomb = new Meteoroid(179.139,400,true);
		Meteoroid bomb2 = new Meteoroid(179.139,500,false);

		
		// Temp
		RenderableHolder.getInstance().add(earth);
		RenderableHolder.getInstance().add(human);
		RenderableHolder.getInstance().add(bomb);
		RenderableHolder.getInstance().add(bomb2);
		
		f.add(screen);
		f.pack();
		//f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		screen.requestFocus();
	
		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(human.x +" "+human.y+" "+human.angle);
			bomb.update();
			bomb2.update();
			human.update();
			screen.repaint();
		}
	}

}
