package entity;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class IceSkill extends Skill implements Renderable {

	private int frameCount, count, frameWidth, frameHeight;
	
	public IceSkill(int x, int y, int direction) {
		super(x,y,direction);
		try {
			frameWidth = Resource.ice1.getWidth() / 7;
			frameHeight = Resource.ice1.getHeight();
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}

	public void setX(int x) {
		this.x -= x;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void update() {
		if (isPlaying) {
			if (count == 10) {
				count = 0;
				frameCount++;
				if (frameCount == 7)
					stop();
			}
			count++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (isPlaying && (Resource.ice1 != null)) {
			g.drawImage(
					Resource.ice1.getSubimage(Resource.ice1.getWidth() / 7
							* frameCount, 0, frameWidth, frameHeight), null, x,
					y);
		}
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

	@Override
	public boolean isVisible() {
		return isPlaying;
	}

	@Override
	public int getZ() {
		return 0;
	}

}
