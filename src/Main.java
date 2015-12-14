import javax.swing.JFrame;

import render.*;
import entity.*;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Easiest Game Of My Life");

		GameScreen screen = new GameScreen();
		GameLogic logic = new GameLogic();
		GameTitle title = new GameTitle();

		f.add(title);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		title.requestFocus();

		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (title.isSwap()) {
				f.setContentPane(screen);
				f.revalidate();
				screen.requestFocus();
				title.setSwap(false);
			}

			if (!(title.isTitle())) {
				logic.update();
				screen.repaint();
			} else {
				title.update();
				title.repaint();
			}
		}
	}

}
