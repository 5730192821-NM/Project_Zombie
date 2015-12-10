package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

public class GameScreen extends JComponent {

	public GameScreen(int x, int y) {
		this.setPreferredSize(new Dimension(x, y));
		this.requestFocus();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), true);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
			}
		});

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		((Graphics2D) g).setBackground(Color.BLACK);
		for (Renderable e : RenderableHolder.getInstance().getRenderableList()) {
			if (e.isVisible())
				e.draw((Graphics2D) g);
		}

	}

}
