package entity;

import input.InputUtility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.annotation.PostConstruct;

import render.Renderable;
import render.RenderableHolder;
import render.Resource;

public class Hero extends Moving implements Renderable {

	private int frameCount=0,count=0,direction=1,temp;
	private int gravity=1,velocityY;
	private boolean isJumped;
	private boolean isMid;
	private boolean isSkill;
	private Land land;
	private Skill [] skill = new Skill[6];
	
	//direction 1 : RIGHT direction 2 : LEFT
	
	public Hero(int x,int y,Land l) {
		super(x,y);
		temp=y;
		isJumped=false;
		velocityY=0;
		isMid=false;
		isSkill=false;
		this.land = l;
		// TODO Auto-generated constructor stub
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
				skill[0].setX(5);
			}
			skill[0].update();
			if(!skill[0].isPlaying()){
				isSkill=false;
			}
		}
		else if(InputUtility.getKeyPressed(KeyEvent.VK_SPACE)){
			// need more skill to adapt
			IceSkill ice = new IceSkill(x, y,direction);
			skill[0] = ice;
			RenderableHolder.getInstance().add(ice);
			ice.play();
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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean getIsMid(){
		return isMid;
	}

}
