package entity;

import input.InputUtility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import render.Renderable;

public class Hero extends Moving implements Renderable {

	public Hero(double a, int r) {
		super(a, r);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			angle -= Math.toRadians(2);
			// this.x = 350 + (int) ((Math.sin(angle) * radius));
			// this.y = 500 + (int) ((Math.cos(angle) * radius));

		} else if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			angle += Math.toRadians(2);
			// this.x = 350 + (int) ((Math.sin(angle) * radius));
			// this.y = 500 + (int) ((Math.cos(angle) * radius));
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		// g.fillRect(x, y, 20, 40);

		AffineTransform backup = g.getTransform();
		AffineTransform trans = new AffineTransform();
		trans.rotate(angle, 350, 500);
		g.transform(trans);
		g.fillRect(x, y, 20, 40);
		g.setTransform(backup);
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
