package entity.monster;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.Hero;
import entity.Land;
import render.Resource;

public class Golem extends Monster {
	
	private int countWalk=0,frameCountWalk=0,countDead=0,frameCountDead=0,countPanic=0;
	private Land land;
	private Hero hero;
	
	public Golem(int x,int y,Land land,Hero hero){
		super(x,y);
		this.land=land;
		this.hero=hero;
		level = (int)(Math.random()*5)+1; // monster has level.
		hp=200*level;// level involve to hp.
		attack = 20*level;// level involve to attack.
		type=1; //immune to debuff.
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(hp==0){ //dead
			if (countDead == 15) {
				countDead = 0;
				frameCountDead++;
				if(frameCountDead==7)
					killed();
			}
			countDead++;
		}
		else if(isPanic){ //panic
			if (countPanic == 15) {
				countPanic = 0;
				isPanic=false;
			}
			countPanic++;
			x+=2;
		}
		else{ //walk
			if (countWalk == 10) {
				countWalk = 0;
				frameCountWalk++;
				frameCountWalk %= 4;
			}
			countWalk++;
			x-=1;
		}
		
		if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			if (!(hero.getX() < 400 || land.isEnd()))
				hero.setMid(true);
		}
		if(hero.getX()>=400 && hero.isMid()){
			setX(5);
			hit(20);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if(hp==0){ //dead
			g.drawImage(Resource.monster_golem_1_2.getSubimage(Resource.monster_golem_1_2.getWidth() / 7
					* frameCountDead, 0, Resource.monster_golem_1_2.getWidth() / 7,
					Resource.monster_golem_1_2.getHeight()), null, x, y);
		}
		else if(isPanic){ //panic
			g.drawImage(Resource.monster_golem_1_3.getSubimage(0, 0, Resource.monster_golem_1_3.getWidth(),
					Resource.monster_golem_1_3.getHeight()), null, x, y);
		}
		else{ //walk
			g.drawImage(Resource.monster_golem_1_1.getSubimage(Resource.monster_golem_1_1.getWidth() / 4
					* frameCountWalk, 0, Resource.monster_golem_1_1.getWidth() / 4,
					Resource.monster_golem_1_1.getHeight()), null, x, y);
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return !isDead;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 800;
	}

}
