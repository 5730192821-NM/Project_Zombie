package ui;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class SkillStatus implements Renderable {

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(Resource.skillBox, null, 250,0);
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1;
	}

}
