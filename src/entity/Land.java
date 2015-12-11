package entity;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class Land extends Moving implements Renderable {
	private int xc;
	private boolean isEnd;

	public Land(int x, int y) {
		super(x, y);
		xc = 0;
		isEnd = false;
	}

	public void setX(int x) {
		this.xc += x;
	}

	public boolean isEnd() {
		return isEnd;
	}

	@Override
	public void update() {
		if (x + xc <= Resource.land1.getWidth() - 800) {
			x += xc;
			xc = 0;
		}
		if (x + xc >= Resource.land1.getWidth() - 800)
			isEnd = true;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(Resource.land1.getSubimage(x, 0, 800,Resource.land1.getHeight()), null, 0, 435);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return -1;
	}

}
