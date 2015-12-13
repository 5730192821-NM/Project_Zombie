package entity.monster;

import render.Renderable;
import render.RenderableHolder;

public abstract class Monster implements Renderable {

	protected int x, y, hp, type, STR, AGI, INT;
	protected boolean isDead = false,isPanic = false;

	public void killed() {
		isDead = true;
	}

	public void summon() {
		//Copy it self before (but How??)
		RenderableHolder.getInstance().add(this);
	}
	
	public abstract void update();

}
