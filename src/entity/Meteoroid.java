package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Renderable;

public class Meteoroid extends Moving implements Renderable {

	private boolean cw = true;

	public Meteoroid(double a, int r, boolean cw) {
		super(a, r);
		this.cw = cw;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x, y, 30, 30);
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

	public void update() {
		if (cw)
			angle -= 0.01;
		else
			angle += 0.01;
		this.x = 350 + (int) ((Math.sin(angle) * radius));
		this.y = 500 + (int) ((Math.cos(angle) * radius));
	}

}
