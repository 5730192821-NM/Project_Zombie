package entity.monster;

import java.util.ArrayList;

import render.Renderable;
import render.RenderableHolder;

public abstract class Monster implements Renderable {

	protected int x, y, hp, type,attack,level;
	protected boolean isDead = false,isPanic = false;

	public Monster(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void killed() {
		isDead = true;
	}
	
	public void setX(int x){
		this.x-=x;
	}
	
	public void hit(int x){
		hp-=x;
		isPanic = true;
		if(hp<=0)
			hp=0;
	}

	public void summon() {
		//Copy it self before (but How??)
		RenderableHolder.getInstance().add(this);
	}
	
	public abstract void update();

}
