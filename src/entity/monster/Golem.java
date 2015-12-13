package entity.monster;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.Hero;
import entity.Land;
import render.Renderable;
import render.Resource;

public class Golem extends Monster implements Renderable {

	public Golem(int x, int y, Land land, Hero hero) {
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
			if (direction == 1)
				x += 2;
			else
				x -= 2;
		} else { // walk
			if (countWalk == 10) {
				countWalk = 0;
				frameCountWalk++;
				frameCountWalk %= 4;
			}
			countWalk++;
			if (x + 70 > hero.getX()) {
				direction = 1;
				x -= 1;
			} else if (x + 90 < hero.getX()) {
				direction = 2;
				x += 1;
			}
		}

		if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			if (!(hero.getX() < 400 || land.isEnd()))
				hero.setRight(true);
		}
		if (hero.getX() >= 400 && hero.isRight()) {
			setX(5);
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			if (!(hero.getX() > 200 || land.isStart())) {
				hero.setLeft(true);
			}
		}
		if (hero.getX() <= 200 && hero.isLeft()) {
			setX(-5);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (hp == 0) { // dead
			if (direction == 1) {
				g.drawImage(Resource.monster_golem_1_2.getSubimage(
						Resource.monster_golem_1_2.getWidth() / 7
								* frameCountDead, 0,
						Resource.monster_golem_1_2.getWidth() / 7,
						Resource.monster_golem_1_2.getHeight()), null, x, y);
			} else if (direction == 2) {
				g.drawImage(Resource.monster_golem_1_2_f.getSubimage(
						Resource.monster_golem_1_2_f.getWidth() / 7
								* (6 - frameCountDead), 0,
						Resource.monster_golem_1_2_f.getWidth() / 7,
						Resource.monster_golem_1_2_f.getHeight()), null, x, y);
			}
		} else if (isPanic) { // panic
			if (direction == 1) {
				g.drawImage(Resource.monster_golem_1_3.getSubimage(0, 0,
						Resource.monster_golem_1_3.getWidth(),
						Resource.monster_golem_1_3.getHeight()), null, x, y);
			} else if (direction == 2) {
				g.drawImage(Resource.monster_golem_1_3_f.getSubimage(0, 0,
						Resource.monster_golem_1_3_f.getWidth(),
						Resource.monster_golem_1_3_f.getHeight()), null, x, y);
			}
		} else { // walk
			if (direction == 1) {
				g.drawImage(Resource.monster_golem_1_1.getSubimage(
						Resource.monster_golem_1_1.getWidth() / 4
								* frameCountWalk, 0,
						Resource.monster_golem_1_1.getWidth() / 4,
						Resource.monster_golem_1_1.getHeight()), null, x, y);
			} else if (direction == 2) {
				g.drawImage(Resource.monster_golem_1_1_f.getSubimage(
						Resource.monster_golem_1_1_f.getWidth() / 4
								* (3 - frameCountWalk), 0,
						Resource.monster_golem_1_1_f.getWidth() / 4,
						Resource.monster_golem_1_1_f.getHeight()), null, x, y);
			}
		}
	}

	@Override
	public boolean isVisible() {
		return !isDead;
	}

	@Override
	public int getZ() {
		return 0;
	}

}
