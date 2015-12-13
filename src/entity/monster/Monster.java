package entity.monster;

import entity.Hero;
import entity.Land;
import render.Renderable;

public abstract class Monster implements Renderable {

	protected int x, y, hp, type, attack, level, countWalk = 0,
			frameCountWalk = 0, countDead = 0, frameCountDead = 0,
			countPanic = 0;
	protected boolean isDead = false, isPanic = false;
	protected Land land;
	protected Hero hero;

	public void killed() {
		isDead = true;
	}

	public void setX(int x) {
		this.x -= x;
	}

	public void hit(int x) {
		hp -= x;
		isPanic = true;
		if (hp <= 0)
			hp = 0;
	}

	public abstract void update();

}
