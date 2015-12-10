import java.awt.Dimension;

import javax.swing.JFrame;

import render.GameScreen;
import render.RenderableHolder;
import entity.Hero;
import entity.Planet;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Ez Game");
		GameScreen screen = new GameScreen(700, 400);
		Planet earth = new Planet(50, 200, 300);
		Hero human = new Hero(350);
		
		// Temp
		RenderableHolder.getInstance().add(earth);
		RenderableHolder.getInstance().add(human);

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
			System.out.println(human.x +" "+human.y);
			human.update();
			screen.repaint();
		}
	}

}
