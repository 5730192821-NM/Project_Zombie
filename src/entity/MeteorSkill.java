package entity;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class MeteorSkill implements Renderable{
	
	private static boolean isPlaying,isRelease;
	private static int frameCountX,frameCountY,count,x,y,frameWidth,frameHeight,direction;
	
	public MeteorSkill(int x,int y,int direction){
		MeteorSkill.direction=direction;
		if(direction==1)
			MeteorSkill.x=x+50;
		else MeteorSkill.x=x-100;
		MeteorSkill.y=305;
		try {
			frameWidth = Resource.meteor1.getWidth() / 7;
			frameHeight = Resource.meteor1.getHeight()/2;
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}
	
	public static void setX(int x){
		MeteorSkill.x -= x; 
	}
	
	public static boolean isPlaying(){
		return isPlaying;
	}
	
	public static void update(){
		if(isPlaying){
			if(count==7){
				count=0;
				frameCountX++;
				if(frameCountY==1 && frameCountX==7){
						stop();
				}
				if(frameCountX==7){
					frameCountX=0;
					frameCountY++;
					if(direction==1)
						x+=250;
					else
						x-=200;
				}
			}
			count++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if (isPlaying && (Resource.meteor1 != null)) {
			if(direction==2)
				g.drawImage(Resource.meteor1.getSubimage(Resource.meteor1.getWidth() / 7 * frameCountX,Resource.meteor1.getHeight()/2*frameCountY, frameWidth, frameHeight), null, x, y);
			else
				g.drawImage(Resource.meteor1_2.getSubimage(Resource.meteor1_2.getWidth() / 7 * (6-frameCountX),Resource.meteor1.getHeight()/2*frameCountY, frameWidth, frameHeight), null, x, y);
		}
	}
	
	public void play(){
		isPlaying=true;
		frameCountX=0;
		frameCountY=0;
		count=0;
		isRelease=false;
	}
	
	public static void stop(){
		isPlaying=false;
		frameCountX=0;
		frameCountY=0;
		count=0;
		isRelease=false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isPlaying;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
