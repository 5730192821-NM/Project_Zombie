package entity.skill;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class IceSkill extends Skill implements Renderable {
	
	public IceSkill(int x, int y, int direction) {
		if (direction == 1)
			this.x = x + 300;
		else
			this.x = x - 270;
		this.y = 320;
		try {
			frameWidth = Resource.ice1.getWidth() / 7;
			frameHeight = Resource.ice1.getHeight();
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

	@Override
	public boolean isVisible() {
		return isPlaying;
	}

	@Override
	public int getZ() {
		return 0;
	}

}
