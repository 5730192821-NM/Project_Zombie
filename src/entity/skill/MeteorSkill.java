package entity.skill;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class MeteorSkill extends Skill implements Renderable {

	private int frameCountX, frameCountY, direction;

	public MeteorSkill(int x, int y, int direction) {
		this.direction = direction;
		if (direction == 1)
			this.x = x + 50;
		else
			this.x = x - 100;
		this.y = 305;
		try {
			frameWidth = Resource.meteor1.getWidth() / 7;
			frameHeight = Resource.meteor1.getHeight() / 2;
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}

	@Override
	public void update() {
		if (isPlaying) {
			if (count == 7) {
				count = 0;
				frameCountX++;
				if (frameCountY == 1 && frameCountX == 7) {
					stop();
				}
				if (frameCountX == 7) {
					frameCountX = 0;
					frameCountY++;
					if (direction == 1)
						x += 250;
					else
						x -= 200;
				}
			}
			count++;
		}
	}
	
	@Override
	public void play() {
		isPlaying = true;
		frameCountX = 0;
		frameCountY = 0;
		count = 0;
	}

	@Override
	public void stop() {
		isPlaying = false;
		frameCountX = 0;
		frameCountY = 0;
		count = 0;
	}

	@Override
	public void draw(Graphics2D g) {
		if (isPlaying && (Resource.meteor1 != null)) {
			if (direction == 2)
				g.drawImage(Resource.meteor1.getSubimage(
						Resource.meteor1.getWidth() / 7 * frameCountX,
						Resource.meteor1.getHeight() / 2 * frameCountY,
						frameWidth, frameHeight), null, x, y);
			else
				g.drawImage(Resource.meteor1_2.getSubimage(
						Resource.meteor1_2.getWidth() / 7 * (6 - frameCountX),
						Resource.meteor1.getHeight() / 2 * frameCountY,
						frameWidth, frameHeight), null, x, y);
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
