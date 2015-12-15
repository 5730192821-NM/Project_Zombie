package ui;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class Banner implements Renderable {
	
	public boolean isVisible = false;

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(Resource.banner, null, 200, 120);
		g.setFont(Resource.wordFont);
		g.drawString("PAUSE", 320, 180);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 2;
	}

}
