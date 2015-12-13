package ui;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class PauseButton implements Renderable {

	public boolean isPause = false;
	
	@Override
	public void draw(Graphics2D g) {
		if (!isPause) 
			g.drawImage(Resource.pause,null,730,0);
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
