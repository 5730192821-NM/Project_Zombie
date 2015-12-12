package entity;

import render.Renderable;

public abstract class Skill implements Renderable {

	protected boolean isPlaying = false;
	protected int x, y, frameCount, count, frameWidth, frameHeight;

	public abstract void update();
	
	public void setX(int x){
		this.x -= x; 
	}
	
	public void play() {
		isPlaying = true;
		frameCount = 0;
		count = 0;
	}

	public void stop() {
		isPlaying = false;
		frameCount = 0;
		count = 0;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

}
