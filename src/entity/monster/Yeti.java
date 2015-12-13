package entity.monster;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.Hero;
import entity.Land;
import render.Renderable;
import render.Resource;

public class Yeti extends Monster implements Renderable {

	public Yeti(int x, int y, Land land, Hero hero) {
		this.x = x;
		this.y = y;
		this.land = land;
		this.hero = hero;
		level = (int) (Math.random() * 5) + 1; // monster has level.
		hp = 200 * level;// level involve to hp.
		attack = 20 * level;// level involve to attack.
		type = 1; // immune to debuff.

	}

	@Override
	public void update() {
		if (hp == 0) { // dead
			if (countDead == 15) {
				countDead = 0;
				frameCountDead++;
				if (frameCountDead == 7)
					killed();
			}
			countDead++;
		} else if (isPanic) { // panic
			if (countPanic == 15) {
				countPanic = 0;
				isPanic = false;
			}
			countPanic++;
			x += 2;
		} else { // walk
			if (countWalk == 10) {
				countWalk = 0;
				frameCountWalk++;
				frameCountWalk %= 4;
			}
			countWalk++;
			x -= 1;
		}

		if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			if (!(hero.getX() < 400 || land.isEnd()))
				hero.setMid(true);
		}
		if (hero.getX() >= 400 && hero.isMid()) {
			setX(5);
			hit(20);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (hp == 0) { // dead
			g.drawImage(Resource.monster_yeti_1_2.getSubimage(
					Resource.monster_yeti_1_2.getWidth() / 7 * frameCountDead,
					0, Resource.monster_yeti_1_2.getWidth() / 7,
					Resource.monster_yeti_1_2.getHeight()), null, x, y);
		} else if (isPanic) { // panic
			g.drawImage(Resource.monster_yeti_1_3.getSubimage(0, 0,
					Resource.monster_yeti_1_3.getWidth(),
					Resource.monster_yeti_1_3.getHeight()), null, x, y);
		} else { // walk
			g.drawImage(Resource.monster_yeti_1_1.getSubimage(
					Resource.monster_yeti_1_1.getWidth() / 4 * frameCountWalk,
					0, Resource.monster_yeti_1_1.getWidth() / 4,
					Resource.monster_yeti_1_1.getHeight()), null, x, y);
		}
	}

	@Override
	public boolean isVisible() {
		return !isDead;
	}

	@Override
	public int getZ() {
		return -1;
	}
	
}
