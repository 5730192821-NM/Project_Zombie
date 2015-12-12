package entity.skill;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class SpikeSkill extends Skill implements Renderable {

	public SpikeSkill(int x, int y, int direction) {
		if (direction == 1)
			this.x = x - 220;
		else
			this.x = x - 240;
		this.y = 328;
		try {
			frameWidth = Resource.spike1.getWidth() / 9;
			frameHeight = Resource.spike1.getHeight();
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}

	@Override
	public void update() {
		if (isPlaying) {
			if (count == 10) {
				count = 0;
				frameCount++;
				if (frameCount == 9)
					stop();
			}
			count++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (isPlaying && (Resource.spike1 != null)) {
			g.drawImage(
					Resource.spike1.getSubimage(Resource.spike1.getWidth() / 9
							* frameCount, 0, frameWidth, frameHeight), null, x,
					y);
		}
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
