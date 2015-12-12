package entity;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.skill.*;
import render.*;

public class Hero extends Moving implements Renderable {

	private int frameCount=0,count=0,direction=1,temp;
	private int gravity=1,velocityY;
	private boolean isJumped;
	private boolean isMid;
	private boolean isSkill;
	private Land land;
	private Skill [] skills = new Skill[5];
	//direction 1 : RIGHT direction 2 : LEFT
	
	public Hero(int x,int y,Land land) {
		super(x,y);
		this.land = land;
		temp=y;
		isJumped=false;
		velocityY=0;
		isMid=false;
		isSkill=false;
		
		skills[0] = new IceSkill(x,y,direction);
		skills[1] = new FireSkill(x,y,direction);
		skills[2] = new MeteorSkill(x,y,direction);
		skills[3] = new PoisonSkill(x,y,direction);
		skills[4] = new SpikeSkill(x,y,direction);
	}
	
	@Override
	public void update() {
		if(count==10){
			count=0;
			frameCount++;
			if(frameCount>3)
				frameCount%=4;
		}
		count++;
		if (InputUtility.getKeyPressed(KeyEvent.VK_UP)){
			if(!isJumped){
				isJumped=true;
				velocityY=-17;
			}
		}
		if(isJumped){
			velocityY+=gravity;
			y+=velocityY;
			if(y>temp){
				y=temp;
				isJumped=false;
				velocityY=0;
			}
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			direction=2;
			if(x+20>0)
				x-=5;
			isMid=false;
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			direction=1;
			if(x<400 || land.isEnd()){
				if(x<720)
					x+=5;
			}
			else{
				isMid=true;
				land.setX(5);
			}
		}
		if(isSkill){
			if(x>=400 && isMid){
				skills[0].setX(5);
				skills[1].setX(5);
				skills[2].setX(5);
				skills[3].setX(5);
				skills[4].setX(5);
			}
			skills[0].update();
			skills[1].update();
			skills[2].update();
			skills[3].update();
			skills[4].update();
			if(!skills[0].isPlaying() && !skills[1].isPlaying() && !skills[2].isPlaying() && !skills[3].isPlaying() && !skills[4].isPlaying()){
				isSkill=false;
			}
		}
		else if(InputUtility.getKeyPressed(KeyEvent.VK_SPACE)){
			skills[0] = new IceSkill(x,y,direction);
			RenderableHolder.getInstance().add(skills[0]);
			skills[0].play();
			isSkill=true;
		}
		else if(InputUtility.getKeyPressed(KeyEvent.VK_F)){
			skills[1] = new FireSkill(x,y,direction);
			RenderableHolder.getInstance().add(skills[1]);
			skills[1].play();
			isSkill=true;
		}
		else if(InputUtility.getKeyPressed(KeyEvent.VK_D)){
			skills[2] = new MeteorSkill(x, y,direction);
			RenderableHolder.getInstance().add(skills[2]);
			skills[2].play();
			isSkill=true;
		}
		else if(InputUtility.getKeyPressed(KeyEvent.VK_S)){
			skills[3] = new PoisonSkill(x, y,direction);
			RenderableHolder.getInstance().add(skills[3]);
			skills[3].play();
			isSkill=true;
		}
		else if(InputUtility.getKeyPressed(KeyEvent.VK_A)){
			skills[4] = new SpikeSkill(x, y,direction);
			RenderableHolder.getInstance().add(skills[4]);
			skills[4].play();
			isSkill=true;
		}
		isMid=false;
	}

	@Override
	public void draw(Graphics2D g) {
		if(direction==2){
			g.drawImage(Resource.hero.getSubimage(Resource.hero.getWidth()/4*frameCount, 0, Resource.hero.getWidth()/4, Resource.hero.getHeight() ), null, x, y);
		} else if(direction==1){
			g.drawImage(Resource.hero_f.getSubimage(Resource.hero_f.getWidth()/4*(3-frameCount), 0, Resource.hero_f.getWidth()/4, Resource.hero.getHeight() ), null, x, y);
		}
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 0;
	}
	
	public boolean getIsMid(){
		return isMid;
	}

}