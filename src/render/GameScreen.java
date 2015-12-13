package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import entity.monster.Cage;
import entity.monster.Monster;

public class GameScreen extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int screenWidth = 800, screenHeight = 600;

	public GameScreen() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.requestFocus();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), true);
				if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) {
					InputUtility.setSpell(KeyEvent.getKeyText(e.getKeyCode()));
				}
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
		for (Monster m : Cage.getInstance().getCage()) {
			if (m.isVisible())
				m.draw((Graphics2D) g);
		}
	}

}
