package entity;

import input.InputUtility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import render.Renderable;

public class Hero extends Living implements Renderable {

	public Hero(double a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			angle += 0.02;
			this.x = 350 + (int) ((Math.sin(angle) * 300));
			this.y = 500 + (int) ((Math.cos(angle) * 300));

		} else if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			angle -= 0.02;
			this.x = 350 + (int) ((Math.sin(angle) * 300));
			this.y = 500 + (int) ((Math.cos(angle) * 300));
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 20, 40);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
