package ui;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class PauseButton implements Renderable {

	public boolean isPause = false;

	@Override
	public void draw(Graphics2D g) {
		if (!isPause)
			g.drawImage(Resource.pause, null, 730, 0);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 2;
	}

}
