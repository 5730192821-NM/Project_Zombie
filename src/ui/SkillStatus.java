package ui;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class SkillStatus implements Renderable {

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(Resource.skillBox, null, 250,0);
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
