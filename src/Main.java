import javax.swing.JFrame;

import render.*;
import entity.*;

public class Main {

	public static void main(String[] args) {
		
		JFrame f = new JFrame("Easiest Game Of My Life");
		GameScreen screen = new GameScreen();
		GameLogic logic = new GameLogic();
		
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
			logic.update();
			screen.repaint();
		}
	}

}
